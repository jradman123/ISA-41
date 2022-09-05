import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { DialogForGuestDataComponent } from 'src/app/components/dialog-for-guest-data/dialog-for-guest-data.component';
import { DialogForReportComponent } from 'src/app/components/dialog-for-report/dialog-for-report.component';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureQuickReservationDto } from 'src/app/interfaces/adventure-quick-reservation-dto';
import { AdventureQuickReservationResponse } from 'src/app/interfaces/adventure-quick-reservation-response';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureQuickReservationService } from 'src/app/services/AdventureQuickReservationService/adventure-quick-reservation.service';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

@Component({
  selector: 'app-adventure-view',
  templateUrl: './adventure-view.component.html',
  styleUrls: ['./adventure-view.component.css']
})
export class AdventureViewComponent implements OnInit {

  id : any;
  utilities: ResponseUtility[];
  adventure : AdventureDto;
  adventuresUtilities = new FormControl('');
  newAdventureQuickReservation : AdventureQuickReservationDto;
  response! : any;
  quickReservations : AdventureQuickReservationResponse[];
  pastAdventureReservations!: MatTableDataSource<AdventureReservation>;
  reservations!: MatTableDataSource<AdventureReservation>;
  futureAdventureReservations!: MatTableDataSource<AdventureReservation>;
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
    private adventureQuickReservationService : AdventureQuickReservationService,private reservationService: ReservationService,private dialog : MatDialog) 
    {
    this.utilities = [] as ResponseUtility[];
    this.adventure = {} as AdventureDto;
    this.newAdventureQuickReservation = {} as AdventureQuickReservationDto;
    this.quickReservations = [] as AdventureQuickReservationResponse[];
    this.pastAdventureReservations = new MatTableDataSource<AdventureReservation>();
    this.reservations = new MatTableDataSource<AdventureReservation>();
    this.futureAdventureReservations = new MatTableDataSource<AdventureReservation>(); 
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
    this.findPastReservations();
    this.findFutureReservations();
  }

  findFutureReservations() {
    this.reservationService.getFutureReservationByAdventure(this.id)
      .subscribe({
        next: (futureReservations: AdventureReservation[]) => {
          this.futureAdventureReservations.data = futureReservations;
        },
      });
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


}
