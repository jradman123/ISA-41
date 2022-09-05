import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { NavigationDto } from 'src/app/interfaces/navigation-dto';
import { NavigationService } from 'src/app/services/NavigationService/navigation.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

@Component({
  selector: 'app-b-equipments',
  templateUrl: './b-equipments.component.html',
  styleUrls: ['./b-equipments.component.css']
})
export class BEquipmentsComponent implements OnInit {

  navigation!: NavigationDto;
  idShip: any;
  navigations: NavigationDto[] = [];
  id: any;
  navigationn!: NavigationDto
  haveReservations!: CottageReservation[]
  constructor(private reservationService: ReservationService, private router: ActivatedRoute, private route: Router, private navigationService: NavigationService) {
    this.navigationn = {} as NavigationDto
  }

  ngOnInit(): void {
    this.findNavigation();
    this.idShip = +this.router.snapshot.paramMap.get('id')!;

    this.reservationService.getShipReservationById(this.idShip).subscribe((data) => {
      this.haveReservations = data;

    });
  }


  findNavigation() {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.navigationService.findNavigationById(this.idShip).subscribe((data) => {
      this.navigations = data;

    });

  }


}
