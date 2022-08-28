import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureQuickReservationDto } from 'src/app/interfaces/adventure-quick-reservation-dto';
import { AdventureQuickReservationResponse } from 'src/app/interfaces/adventure-quick-reservation-response';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureQuickReservationService } from 'src/app/services/AdventureQuickReservationService/adventure-quick-reservation.service';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import Swal from 'sweetalert2';
import { DialogForAddReportComponent } from '../dialog-for-add-report/dialog-for-add-report.component';
import { DialogForGuestDataComponent } from '../dialog-for-guest-data/dialog-for-guest-data.component';
import { DialogForReportComponent } from '../dialog-for-report/dialog-for-report.component';
import { DialogForReservationAdventureComponent } from '../dialog-for-reservation-adventure/dialog-for-reservation-adventure.component';
import { DialogForReservationCottageComponent } from '../dialog-for-reservation-cottage/dialog-for-reservation-cottage.component';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  id : any;
  utilities: ResponseUtility[];
  adventure : AdventureDto;
  adventuresUtilities = new FormControl('');
  newAdventureQuickReservation : AdventureQuickReservationDto;
  response! : any;
  quickReservations : AdventureQuickReservationResponse[];
  pastAdventureReservations!: MatTableDataSource<AdventureReservation>;
  reservations!: MatTableDataSource<AdventureReservation>;

  columnsToDisplayAdventureReservations: string[] = [
    'No.',
    'StartDate',
    'EndDate',
    'Price',
    'NumberOfPerson',
    'ClientEmail',
    'Buttons'
  ];

  haveReservations!: AdventureReservation[];

  constructor(private activatedRoute: ActivatedRoute,private adventureService : AdventureService,
    private adventureQuickReservationService : AdventureQuickReservationService,private reservationService: ReservationService,
    public dialog: MatDialog){
    this.utilities = [] as ResponseUtility[];
    this.adventure = {} as AdventureDto;
    this.newAdventureQuickReservation = {} as AdventureQuickReservationDto;
    this.quickReservations = [] as AdventureQuickReservationResponse[];
    this.pastAdventureReservations = new MatTableDataSource<AdventureReservation>();
    this.reservations = new MatTableDataSource<AdventureReservation>(); 
  }

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.paramMap.get('id')!;
    this.getAdventure();
    this.getUtilitiesForAdventure();
    this.getQuickReservations();
    this.reservationService.getCurrentReservationByAdventure(this.id).subscribe((data) => {
      this.haveReservations = data;
    });
    this.findReservations();
  }

  parseStringToDate(dateTime: string) {
    return new Date(Date.parse(dateTime))
  }

  findPastReservations() {

    this.reservationService.getPastReservationsByAdventure(this.id)
      .subscribe({
        next: (pastReservations: AdventureReservation[]) => {
          this.pastAdventureReservations.data = pastReservations;
        },
      });
  }

  findReservations() {

    this.reservationService.getCurrentReservationByAdventure(this.id)
      .subscribe({
        next: (reservations: AdventureReservation[]) => {
          this.reservations.data = reservations;

        },
      });

  }
  
  getAdventure() {
    this.adventureService.findById(this.id).subscribe({
      next: (data: AdventureDto) => {
        this.adventure = data
      },
    });
  }
  getUtilitiesForAdventure() {
    this.adventureService.getAdventureUtilities(this.id).subscribe({
      next: (res : ResponseUtility[]) => {
        this.utilities = res
    }
  });
  }

  getQuickReservations() {
    this.adventureQuickReservationService.getAllForAdventure(this.id).subscribe({
      next: (data: AdventureQuickReservationResponse[]) => {
        this.quickReservations = [];
        this.quickReservations = data;
      },
    });
  }

  detailsForm = new FormGroup({ 
    price: new FormControl(null, [ Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
    guestLimit: new FormControl(null, [Validators.pattern('^\\d{1,3}$')]),
    startDate: new FormControl(null),
    endDate: new FormControl(null),
    validUntil: new FormControl(null),
})

cancel() { 
  this.detailsForm.reset();
  this.adventuresUtilities.reset();
}
  
add() {
  const s = new Date( this.detailsForm.value.startDate.getTime() -  this.detailsForm.value.startDate.getTimezoneOffset() * 60000)
  const e = new Date( this.detailsForm.value.endDate.getTime() -  this.detailsForm.value.endDate.getTimezoneOffset() * 60000)
  this.newAdventureQuickReservation = {
    startTime: s.toISOString(),
    endTime : e.toISOString(),
    validUntil : this.detailsForm.get('validUntil')?.value,
    price : this.detailsForm.get('price')?.value,
    guestLimit : this.detailsForm.get('guestLimit')?.value,
    adventureId : this.id,
    utilities : this.adventuresUtilities.value
  }

  if (this.newAdventureQuickReservation.startTime == null
    || this.newAdventureQuickReservation.endTime == null ||
    this.newAdventureQuickReservation.guestLimit == null ||
    this.newAdventureQuickReservation.price == null || this.newAdventureQuickReservation.validUntil == null) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Please fill all fields!',
      });
      return;
    } else if (new Date(this.newAdventureQuickReservation.startTime) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
      return;
    }
    else if (new Date(this.newAdventureQuickReservation.startTime) > new Date(this.newAdventureQuickReservation.endTime)) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'End date must be greater then start date!',
      })
      return;
    } 
     else if (new Date(this.newAdventureQuickReservation.validUntil) > new Date(this.newAdventureQuickReservation.startTime)) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid until must be before start date!',
      })
      return;
    }
    else if (new Date(this.newAdventureQuickReservation.validUntil) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid until  must not be before today!',
      })
      return;

    } else {
      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully added new quick reservation for adventure!',
      })

    }

  this.adventureQuickReservationService.addAdventaddAdventureQuickReservation(this.newAdventureQuickReservation).subscribe((data) => {
    this.detailsForm.reset();
    this.adventuresUtilities.reset();
    this.quickReservations = [];
    this.getQuickReservations();
    this.response = data;
  })
}

deleteQuickReservation(id: string) {
  this.adventureQuickReservationService.deleteQuickReservation(id)
    .subscribe(data => {
      this.quickReservations = []
      this.getQuickReservations();
    });

}

viewPersonalData(clientEmail: string) {
  const dialogConfig = new MatDialogConfig();

  dialogConfig.disableClose = true;
  dialogConfig.autoFocus = true;

  const dialogRef = this.dialog.open(DialogForGuestDataComponent, {

    data: { clientEmail: clientEmail },
  });
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    this.id = result;

  });

}

dialogViewReport(id: any) {

  const dialogConfig = new MatDialogConfig();

  dialogConfig.disableClose = true;
  dialogConfig.autoFocus = true;

  const dialogRef = this.dialog.open(DialogForReportComponent, {

    data: { idReservation: id },
  })
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');


  });

}

dialogReport(id: any) {


  const dialogConfig = new MatDialogConfig();

  dialogConfig.disableClose = true;
  dialogConfig.autoFocus = true;

  const dialogRef = this.dialog.open(DialogForAddReportComponent, {

    data: { idReservation: id },
  })
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');

    window.location.reload()


  });
}

  addNew(element: any) {
    return new Date(Date.parse(element.resStart)) <= new Date()
  }

  addReservation(clientEmail: string) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForReservationAdventureComponent, {

      data: { clientEmail: clientEmail, id: this.id },
    })
    /*dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');


    });*/

  }


}
