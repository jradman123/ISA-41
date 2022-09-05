import { Component, OnInit } from '@angular/core';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { Image } from 'src/app/interfaces/image';
import { ShipQuickReservationResponse } from 'src/app/interfaces/ship-quick-reservation-response';
import { ActivatedRoute, Router } from '@angular/router';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-boat-view',
  templateUrl: './boat-view.component.html',
  styleUrls: ['./boat-view.component.css']
})
export class BoatViewComponent implements OnInit {

  ship!: ShipDto;
  appointments: ShipQuickReservationResponse[] = []
  id: any;
  initialDetails: any
  email: any
  image: Image;
  imagesResponse: ImagesResponse;
  images: Image[];
  viewOff = false;
  haveReservations!: CottageReservation[]

  constructor(private shipService: ShipService, private reservationService: ReservationService, 
    private appointmentService: AppointmentService, 
    private router: ActivatedRoute, private route: Router) {
    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];
    this.ship = {} as ShipDto;
  }



  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.shipService.findbyId(this.id).subscribe({
      next: (data: ShipDto) => {
        this.ship = data
        this.initialDetails = JSON.parse(JSON.stringify(data));
        this.detailsForm.controls['name'].setValue(data.name)
        this.detailsForm.controls['description'].setValue(data.description)
        this.detailsForm.controls['price'].setValue(data.price)
        this.detailsForm.controls['street'].setValue(data.streetName)
        this.detailsForm.controls['streetNumber'].setValue(data.streetNumber)
        this.detailsForm.controls['cityName'].setValue(data.city)
        this.detailsForm.controls['countryName'].setValue(data.country)
        this.detailsForm.controls['capacity'].setValue(data.capacity)
        this.detailsForm.controls['type'].setValue(data.type)
        this.detailsForm.controls['maxSpeed'].setValue(data.maxSpeed)
        this.detailsForm.controls['strengthOfEngine'].setValue(data.strengthOfEngine)
        this.detailsForm.controls['numberOfEngine'].setValue(data.numberOfEngine)
        this.detailsForm.controls['length'].setValue(data.length)
        this.detailsForm.controls['cancelationConditions'].setValue(data.cancelationConditions)
        this.detailsForm.controls['fishingEquipment'].setValue(data.fishingEquipment)

      },
    });


    this.findAppointments();



    this.reservationService.getShipReservationById(this.id).subscribe((data) => {
      this.haveReservations = data;

    });


  }

  findAppointments() {
    this.appointmentService.findAppByShip(this.id).subscribe((data) => {
      this.appointments = data;

    });
  }

  getImages() {
    this.shipService.getShipImages(this.id).subscribe({
      next: (res) => {
        console.log(res);
        this.imagesResponse = res
        this.imagesResponse.images.forEach((image) => {
          this.images.push(image)
        })
      }
    });
  }


  detailsForm = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    description: new FormControl('', [
      Validators.required
    ]),
    street: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    streetNumber: new FormControl(null, [
      Validators.required,
      Validators.pattern('^\\d{1,3}$'),
    ]),
    cityName: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    countryName: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    strengthOfEngine: new FormControl(null, [
      Validators.required,

    ]),
    maxSpeed: new FormControl(null, [
      Validators.required,

    ]),
    numberOfEngine: new FormControl(null, [
      Validators.required,

    ]),
    length: new FormControl(null, [
      Validators.required,

    ]),
    type: new FormControl(null, [
      Validators.required,

    ]),
    fishingEquipment: new FormControl(null, [
      Validators.required,
    ]),
    cancelationConditions: new FormControl(null, [
      Validators.required,

    ]),
    price: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
    capacity: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}$')])
  })



  edit() {
    if (this.detailsForm.invalid) {
      return;
    }


    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The ship cannot be changed because it has a reservation!',
      })

    }
    else {


     
      

    }
  }
}
