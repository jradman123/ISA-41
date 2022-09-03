import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DetailsAboutReservation } from 'src/app/interfaces/details-about-reservation';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

@Component({
  selector: 'app-dialog-reservation-view',
  templateUrl: './dialog-reservation-view.component.html',
  styleUrls: ['./dialog-reservation-view.component.css']
})
export class DialogReservationViewComponent implements OnInit {

  //details : DetailsAboutReservation;
  constructor(public dialogRef: MatDialogRef<DialogReservationViewComponent>, @Inject(MAT_DIALOG_DATA) public data: DetailsAboutReservation,
  /*private reservationService : ReservationService*/) {
    //this.details = {} as DetailsAboutReservation;
   }

  ngOnInit(): void {

  }

  close(): void {
    this.dialogRef.close();
  }

}
