import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { DialogForAddReportComponent } from '../dialog-for-add-report/dialog-for-add-report.component';
import { DialogForGuestDataComponent } from '../dialog-for-guest-data/dialog-for-guest-data.component';
import { DialogForReportComponent } from '../dialog-for-report/dialog-for-report.component';
import { DialogForReservationShipComponent } from '../dialog-for-reservation-ship/dialog-for-reservation-ship.component';
import { ShipReservationComponent } from '../ship-reservation/ship-reservation.component';

export interface DataForDialogEmail {
  clientEmail: string;
  id: string;
}
@Component({
  selector: 'app-ship-reservation-history',
  templateUrl: './ship-reservation-history.component.html',
  styleUrls: ['./ship-reservation-history.component.css']
})
export class ShipReservationHistoryComponent implements OnInit {

  pastReservations!: MatTableDataSource<CottageReservation>;
  newReservation!: CottageReservation;
  reservations!: MatTableDataSource<CottageReservation>;
  today!: Date

  id: any;

  columnsToDisplayCottageReservations: string[] = [
    'No.',
    'StartDate',
    'EndDate',
    'Price',
    'NumberOfPerson',
    'ClientEmail',
    'Buttons'
  ];
  constructor(public dialog: MatDialog, public reservationService: ReservationService, public router: ActivatedRoute) {
    this.reservations = new MatTableDataSource<CottageReservation>();
    this.pastReservations = new MatTableDataSource<CottageReservation>();
    this.newReservation = {} as CottageReservation;

  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.today = new Date();


    this.findPastReservations();
    this.findReservations();
  }


  findPastReservations() {

    this.reservationService.getPastShipReservationById(this.id)
      .subscribe({
        next: (pastReservations: CottageReservation[]) => {
          this.pastReservations.data = pastReservations;

        },
      });



  }

  findReservations() {

    this.reservationService.getShipReservationById(this.id)
      .subscribe({
        next: (reservations: CottageReservation[]) => {
          this.reservations.data = reservations;

        },
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

  parseStringToDate(dateTime: string) {
    return new Date(Date.parse(dateTime))
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

  viewPersonalData(clientEmail: string) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForGuestDataComponent, {

      data: {
        clientEmail: clientEmail
      },
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;

    });

  }


  addReservation(clientEmail: string) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForReservationShipComponent, {

      data: { clientEmail: clientEmail, id: this.id },
    })
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');


    });

  }
}
