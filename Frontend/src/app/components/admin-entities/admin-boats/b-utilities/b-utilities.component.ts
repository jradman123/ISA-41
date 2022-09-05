import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';

@Component({
  selector: 'app-b-utilities',
  templateUrl: './b-utilities.component.html',
  styleUrls: ['./b-utilities.component.css']
})
export class BUtilitiesComponent implements OnInit {

  utility: any;

  utilityy!: UtilityDto;
  idShip: any;
  utilities!: MatTableDataSource<UtilityDto>;
  columnsToDisplayUtilities: string[] = [
    'Name',
    'Price'
  ];

  haveReservations!: CottageReservation[]

  constructor(private reservationService: ReservationService, private utilityService: UtilityService, 
    private router: ActivatedRoute) {
      this.utilities = new MatTableDataSource<UtilityDto>();
    this.utilityy = {} as UtilityDto;
  }

  ngOnInit(): void {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;

    this.reservationService.getShipReservationById(this.idShip).subscribe((data) => {
      this.haveReservations = data;

    });
    this.findUtilities();
  }

  findUtilities() {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.utilityService.findShipUtilityById(this.idShip).subscribe((data) => {
      this.utilities.data = data;

    });
  }

}
