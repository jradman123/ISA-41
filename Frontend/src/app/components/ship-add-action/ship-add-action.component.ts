import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ShipQuickReservationDto } from 'src/app/interfaces/ship-quick-reservation';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-ship-add-action',
  templateUrl: './ship-add-action.component.html',
  styleUrls: ['./ship-add-action.component.css']
})
export class ShipAddActionComponent implements OnInit {

  appointments: ShipQuickReservationDto[] = [];
  newAppointment!: ShipQuickReservationDto;
  id: any;
  idShip: any;
  shipDto!: ShipDto;
  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;

  haveReservation!: CottageReservation[]
  Utilities = new FormControl('');
  utilities!: ResponseUtility[];
  constructor(private utilityService: UtilityService, private reservationService: ReservationService, private appointmentService: AppointmentService, private router: ActivatedRoute, private route: Router, private shipService: ShipService) {
    this.newAppointment = {} as ShipQuickReservationDto;
    this.utilities = [] as ResponseUtility[];
  }
  ngOnInit(): void {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.shipService.findbyId(this.idShip).subscribe((data) => {
      this.shipDto = data;
    });
    this.reservationService.getShipReservationById(this.idShip).subscribe((data) => {
      this.haveReservation = data;
    });

    this.utilityService.findShipUtilityById(this.idShip).subscribe((data) => {
      this.utilities = data;
    });

    this.form = new FormGroup({
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      capacity: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      validUntil: new FormControl('', Validators.required),


    })


  }

  add() {


    this.App();
    if (this.form.invalid) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The appointment cannot be added.You must fill in all fields!',
      })
    } else if (new Date(this.newAppointment.startTime) >= new Date(this.newAppointment.endTime)) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date is greater or equal then end date!',
      })
    } else if (new Date(this.newAppointment.startTime) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })

    } else if (new Date(this.newAppointment.validUntil) > new Date(this.newAppointment.startTime)) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid until  must be before start date!',
      })

    }
    else if (new Date(this.newAppointment.validUntil) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid date  must not be before today!',
      })

    }

    else {

      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully added a appointment!',
      })

      this.sub = this.appointmentService.createAppbyShip(this.newAppointment)
        .subscribe({
          next: () => {

            //   this.onNoClick();
          }
        });
    }


  }
  onNoClick(): void {
    window.location.reload();

  }

  App(): void {
    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)
    let Valid = new Date(this.form.value.validUntil)

    console.log(newStart)
    console.log(newEnd)
    this.newAppointment.startTime = new Date(newStart.setHours(14, 0, 0, 0)).toISOString();
    this.newAppointment.endTime = new Date(newEnd.setHours(11, 0, 0, 0)).toISOString();
    this.newAppointment.guestLimit = this.form.value.capacity;
    this.newAppointment.price = this.form.value.price;
    this.newAppointment.shipId = this.idShip;
    this.newAppointment.validUntil = new Date(Valid.setHours(14, 0, 0, 0)).toISOString();
    this.newAppointment.utilities = this.Utilities.value;

  }

}
