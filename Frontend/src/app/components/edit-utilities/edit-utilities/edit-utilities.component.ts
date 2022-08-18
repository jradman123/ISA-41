import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { windowTime } from 'rxjs/operators';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { EditCottageUtilityDialogComponent } from '../../edit-cottage-utility-dialog/edit-cottage-utility-dialog.component';
import { EditUtilityDialogComponent } from '../../edit-utility-dialog/edit-utility-dialog.component';
import Swal from 'sweetalert2';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

@Component({
  selector: 'app-edit-utilities',
  templateUrl: './edit-utilities.component.html',
  styleUrls: ['./edit-utilities.component.css']
})
export class EditUtilitiesComponent implements OnInit {

  utility: any;

  utilityy!: UtilityDto;
  idCottage: any;
  utilities: UtilityDto[] = [];
  haveReservations!: CottageReservation[]

  constructor(public dialog: MatDialog, private reservationService: ReservationService, private utilityService: UtilityService, private router: ActivatedRoute) {

    this.utilityy = {} as UtilityDto;
  }

  ngOnInit(): void {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;

    this.reservationService.getCottageReservationById(this.idCottage).subscribe((data) => {
      this.haveReservations = data;

    });
    this.findUtilities();
  }

  findUtilities() {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.utilityService.findUtilityById(this.idCottage).subscribe((data) => {
      this.utilities = data;

    });
  }

  addUtility() {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The utility cannot be added because cottage has a reservation!',
      })

    }
    else {
      this.utilityy.cottageId = this.idCottage;
      console.log("fedfefdfdf0" + this.utilityy.name)

      this.utilityService.addCottageUtility(this.utilityy).subscribe((data) => {

        this.utilities = []
        this.findUtilities();

      });
    }

  }

  editUtility(utility: UtilityDto) {
    console.log(utility);
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(EditCottageUtilityDialogComponent, {
      data: { name: utility.name, price: utility.price, id: utility.id, cottageId: this.idCottage },
    });

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.findUtilities();
        }
      }
    );
  }
  deleteUtility(id: any) {

    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The utility cannot be deleted because cottage has a reservation!',
      })

    }
    else {

      this.utilityService.deleteUtility(id, this.idCottage)
        .subscribe(data => {
          this.utilities = []
          this.findUtilities();


        });
    }


  }

}
