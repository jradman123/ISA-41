import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { DataForDialogCottage } from '../cottage-profile/cottage-profile/cottage-profile.component';

@Component({
  selector: 'app-dialog-for-appointment',
  templateUrl: './dialog-for-appointment.component.html',
  styleUrls: ['./dialog-for-appointment.component.css']
})
export class DialogForAppointmentComponent implements OnInit {


  cottage!: CottageDto;
  newAppointment!: AppointmentDto
  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  constructor(private cottageService: CottageService, private appointmentService: AppointmentService, @Inject(MAT_DIALOG_DATA) public data: DataForDialogCottage, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForAppointmentComponent>) {
    this.newAppointment = {} as AppointmentDto;
  }

  ngOnInit(): void {
    this.cottageService.findbyId(this.data.id).subscribe((data) => {
      this.cottage = data;
    });

    this.form = new FormGroup({
      appStart: new FormControl('', Validators.required),
      appEnd: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      capacity: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      validUntil: new FormControl('', Validators.required)
    })


  }

  addApp(): void {




  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  App(): void {
    let newStart = new Date(this.form.value.appStart)
    let newEnd = new Date(this.form.value.appEnd)
    let Valid = new Date(this.form.value.validUntil)

    console.log(newStart)
    console.log(newEnd)
    this.newAppointment.startDate = new Date(newStart.setHours(14, 0, 0, 0));
    this.newAppointment.endDate = new Date(newEnd.setHours(11, 0, 0, 0));
    this.newAppointment.capacity = this.form.value.capacity;
    this.newAppointment.price = this.form.value.price;
    this.newAppointment.cottageId = this.data.id;
    this.newAppointment.validUntil = new Date(Valid.setHours(14, 0, 0, 0));


  }
}
