import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

@Component({
  selector: 'app-reservation-history',
  templateUrl: './reservation-history.component.html',
  styleUrls: ['./reservation-history.component.css']
})
export class ReservationHistoryComponent implements OnInit {

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

  constructor(private reservationService: ReservationService, private route: Router, private router: ActivatedRoute, private cottageService: CottageService) {
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
}

















