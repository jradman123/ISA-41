import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageQuickReservationDto } from 'src/app/interfaces/cottage-quick-reservation';
import { CottageQuickReservationResponse } from 'src/app/interfaces/cottage-quick-reservation-response';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-action',
  templateUrl: './add-action.component.html',
  styleUrls: ['./add-action.component.css']
})
export class AddActionComponent implements OnInit {

  appointments: CottageQuickReservationResponse[] = [];
  newAppointment!: CottageQuickReservationDto;
  id: any;
  idCottage: any;
  cottage!: CottageDto;
  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  Utilities = new FormControl('');
  utilities!: ResponseUtility[];
  constructor(private utilityService: UtilityService, private appointmentService: AppointmentService, private router: ActivatedRoute, private route: Router, private cottageService: CottageService) {
    this.newAppointment = {} as CottageQuickReservationDto;
    this.utilities = [] as ResponseUtility[];
  }

  ngOnInit(): void {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;

    this.cottageService.findbyId(this.idCottage).subscribe((data) => {
      this.cottage = data;
    });

    this.utilityService.findUtilityById(this.idCottage).subscribe((data) => {
      this.utilities = data;
    });



    this.form = new FormGroup({
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      capacity: new FormControl('', [Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
      price: new FormControl('', Validators.required),
      validUntil: new FormControl('', Validators.required),


    })
  }

  add() {

    this.App();
    if (this.newAppointment.startTime == null
      || this.newAppointment.endTime == null ||
      this.newAppointment.guestLimit == null ||
      this.newAppointment.price == null || this.newAppointment.validUntil == null) { alert("Please fill all fields!"); return; }

    if (this.newAppointment.startTime >= this.newAppointment.endTime) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date is greater or equal then end date!',
      })
    }
    else if (new Date(this.newAppointment.startTime) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
    } else if (new Date(this.newAppointment.validUntil) > new Date(this.newAppointment.startTime)) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid until must be before start date!',
      })
    }
    else if (new Date(this.newAppointment.validUntil) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid date  must not be before today!',
      })

    } else {
      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully added new appointment!',
      })
      this.sub = this.appointmentService.createApp(this.newAppointment)
        .subscribe({
          next: () => {

            window.location.reload();
          }
        });
    }
  }


  App(): void {
    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)
    let Valid = new Date(this.form.value.validUntil)


    this.newAppointment.startTime = (new Date(newStart.setHours(14, 0, 0, 0))).toISOString();
    this.newAppointment.endTime = (new Date(newEnd.setHours(11, 0, 0, 0))).toISOString();
    this.newAppointment.guestLimit = this.form.value.capacity;
    this.newAppointment.price = this.form.value.price;
    this.newAppointment.cottageId = this.idCottage;
    this.newAppointment.validUntil = (new Date(Valid.setHours(14, 0, 0, 0))).toISOString();
    this.newAppointment.utilities = this.Utilities.value;


  }



}

