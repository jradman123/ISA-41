import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';

@Component({
  selector: 'app-c-utilities',
  templateUrl: './c-utilities.component.html',
  styleUrls: ['./c-utilities.component.css']
})
export class CUtilitiesComponent implements OnInit {


  id : any;
  utilities!: MatTableDataSource<UtilityDto>;
  columnsToDisplayUtilities: string[] = [
    'Name',
    'Price'
  ];
  constructor(private reservationService: ReservationService, private utilityService: UtilityService, private router: ActivatedRoute) {
    this.utilities = new MatTableDataSource<UtilityDto>();
   }

   ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.getUtilities();
  }

  getUtilities() {
    this.utilityService.findUtilityById(this.id).subscribe((data) => {
      this.utilities.data = data;

    });
  }

  

  

}
