import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
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

  appointments: AppointmentDto[] = [];
  newAppointment!: AppointmentDto;
  id: any;
  idCottage: any;
  cottage!: CottageDto;
  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  utilities!: UtilityDto[];
  constructor(private utilityService: UtilityService, private appointmentService: AppointmentService, private router: ActivatedRoute, private route: Router, private cottageService: CottageService) {
    this.newAppointment = {} as AppointmentDto;
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
      capacity: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      validUntil: new FormControl('', Validators.required),


    })
  }

  add() {

    this.App();
    if (this.newAppointment.startDate == null
      || this.newAppointment.endDate == null ||
      this.newAppointment.capacity == null ||
      this.newAppointment.price == null || this.newAppointment.validUntil == null) { alert("Please fill all fields!"); return; }

    if (this.newAppointment.startDate >= this.newAppointment.endDate) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date is greater or equal then end date!',
      })
    }
    else if (this.newAppointment.startDate < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
    } else if (this.newAppointment.validUntil > this.newAppointment.startDate) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid until must be before start date!',
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

            this.onNoClick();
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
    this.newAppointment.startDate = new Date(newStart.setHours(14, 0, 0, 0));
    this.newAppointment.endDate = new Date(newEnd.setHours(11, 0, 0, 0));
    this.newAppointment.capacity = this.form.value.capacity;
    this.newAppointment.price = this.form.value.price;
    this.newAppointment.cottageId = this.idCottage;
    this.newAppointment.validUntil = new Date(Valid.setHours(14, 0, 0, 0));




  }



}

