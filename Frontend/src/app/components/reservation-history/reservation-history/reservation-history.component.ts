import { Component, OnInit } from '@angular/core';
import { MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { DialogForGuestDataComponent } from '../../dialog-for-guest-data/dialog-for-guest-data.component';
import { MatDialog } from '@angular/material/dialog';


export interface DataForDialogGuest {
  clientEmail: string;
}

@Component({
  selector: 'app-reservation-history',
  templateUrl: './reservation-history.component.html',
  styleUrls: ['./reservation-history.component.css']
})
export class ReservationHistoryComponent implements OnInit {


  clientEmail!: string;

  cottage!: CottageDto;
  sub!: Subscription;
  id: any;
  reservations!: MatTableDataSource<CottageReservation>;

  columnsToDisplayCottageReservations: string[] = [
    'No.',
    'StartDate',
    'EndDate',
    'Price',
    'NumberOfPerson',
    'ClientEmail',
    'Buttons'
  ];

  constructor(private reservationService: ReservationService, private route: Router, private router: ActivatedRoute, private cottageService: CottageService, public dialog: MatDialog) {
    this.reservations = new MatTableDataSource<CottageReservation>();

  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.findbyId(this.id).subscribe((data) => {
      this.cottage = data;

      this.reservationService.getCottageReservationById(this.id)
        .subscribe({
          next: (reservations: CottageReservation[]) => {
            this.reservations.data = reservations;

          },
        });
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
}

















