import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { NavigationDto } from 'src/app/interfaces/navigation-dto';
import { NavigationService } from 'src/app/services/NavigationService/navigation.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-ship-navigation',
  templateUrl: './edit-ship-navigation.component.html',
  styleUrls: ['./edit-ship-navigation.component.css']
})
export class EditShipNavigationComponent implements OnInit {

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

  addNavigation() {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The navigation cannot be added because ship has a reservation!',
      })

    }
    else {

      this.navigationn.shipId = this.idShip;

      console.log(this.navigationn)
      this.navigationService.addNavigation(this.navigationn).subscribe((data) => {
        this.navigations = []
        this.findNavigation();


      });
    }

  }
  deleteNavigation(id: any) {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The navigation cannot be deleted because ship has a reservation!',
      })

    }
    else {

      this.navigationService.deleteNavigation(id, this.idShip)
        .subscribe(data => {
          this.navigations = []
          this.findNavigation();





        });
    }


  }
}

