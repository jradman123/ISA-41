import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { UserService } from 'src/app/services/UserService/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-ship-reservation',
  templateUrl: './ship-reservation.component.html',
  styleUrls: ['./ship-reservation.component.css']
})
export class ShipReservationComponent implements OnInit {
  fullPrice: number = 0;
  form!: FormGroup;
  formData!: FormData;
  ship!: ShipDto;
  startAvailableDate: any = null;
  endAvailableDate: any = null;
  users!: PersonalData[];
  id: any;
  sub!: Subscription;
  newReservation!: CottageReservation

  constructor(private shipService: ShipService, private router: ActivatedRoute, private reservationService: ReservationService, private userService: UserService) {
    this.newReservation = {} as CottageReservation;


  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.findUsers();
    this.findShip();

    this.form = new FormGroup({
      clientEmail: new FormControl('', Validators.required),
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      capacity: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
    })
  }


  findUsers() {
    this.userService.findAll().subscribe((data) => {
      this.users = data;
    });
  }


  findShip() {
    this.shipService.findbyId(this.id).subscribe({
      next: (data: ShipDto) => {
        this.ship = data
      },
    });
  }
  reserved(): void {


    this.reservateShip();
    if (this.newReservation.resStart >= this.newReservation.resEnd) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date is greater or equal then end date!',
      })
    } else if (this.newReservation.resStart < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
    } else {

      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully booked a ship!',
      })


      this.sub = this.reservationService.reservatedCottage(this.newReservation)
        .subscribe({
          next: () => {

            window.location.reload();

          }
        });
    }
  }

  reservateShip(): void {



    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)


    console.log(this.ship.price)


    this.newReservation.resStart = new Date(newStart.setHours(14, 0, 0, 0)),
      this.newReservation.resEnd = new Date(newEnd.setHours(11, 0, 0, 0)),
      this.newReservation.numberOfPerson = this.form.value.numberOfPerson;
    this.newReservation.price = this.form.value.price;
    this.newReservation.clientEmail = this.form.value.clientEmail;
    this.newReservation.objectId = this.id;
    this.newReservation.typeOfRes = 'BOAT';

  }


}
