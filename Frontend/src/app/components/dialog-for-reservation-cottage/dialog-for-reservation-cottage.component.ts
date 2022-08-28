import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { DataForDialogCottage } from '../cottage-profile/cottage-profile/cottage-profile.component';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { UserService } from 'src/app/services/UserService/user.service';
import { Subscription } from 'rxjs';

import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import Swal from 'sweetalert2';
import { DataForDialogEmail } from '../ship-reservation-history/ship-reservation-history.component';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-dialog-for-reservation-cottage',
  templateUrl: './dialog-for-reservation-cottage.component.html',
  styleUrls: ['./dialog-for-reservation-cottage.component.css']
})
export class DialogForReservationCottageComponent implements OnInit {

  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  newReservation!: CottageReservation;
  cottage!: CottageDto;
  start: any
  end: any
  id: any;
  utilities!: UtilityDto[];
  fullPrice: number = 0;
  price!: any;


  constructor(private cottageService: CottageService, private utilityService: UtilityService, @Inject(MAT_DIALOG_DATA) public data: DataForDialogEmail, private reservationService: ReservationService, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReservationCottageComponent>) {
    this.newReservation = {} as CottageReservation;
  }
  ngOnInit(): void {

    this.cottageService.findbyId(this.data.id).subscribe({
      next: (data: CottageDto) => {
        this.cottage = data
        this.price = data.price
      },
    });
    this.utilityService.findUtilityById(this.data.id).subscribe((data) => {
      this.utilities = data;
    });



    this.form = new FormGroup({
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      numberOfPerson: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
    })
  }
  reserved(): void {


    this.reservatCottage();




    if (this.newReservation.resStart >= this.newReservation.resEnd) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date is greater or equal then end date!',
      })
    }
    else if (this.newReservation.price == "0") {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'You must click the calculate button!',
      })
    }
    else if (this.newReservation.resStart < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
    } else {




      this.sub = this.reservationService.reservatedCottage(this.newReservation)
        .subscribe({
          next: (response) => {
            if (response == "NO!") {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'This appointment is already reserved or cottage is not available during a period!',
              })
            } else {

              this.dialogRef.close();
              window.location.reload();

            }
          }
        });
    }
  }

  reservatCottage(): void {



    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)


    console.log("dgwefedfsd" + this.data.clientEmail, this.data.id, this.form.value.price)

    this.newReservation.resStart = new Date(newStart.setHours(14, 0, 0, 0)),
      this.newReservation.resEnd = new Date(newEnd.setHours(11, 0, 0, 0)),
      this.newReservation.numberOfPerson = this.form.value.numberOfPerson;
    this.newReservation.price = this.fullPrice.toString();
    this.newReservation.clientEmail = this.data.clientEmail;
    this.newReservation.objectId = this.data.id;
    this.newReservation.typeOfRes = 'COTTAGE';

  }

  total() {

    var date1 = new Date(this.form.value.resStart);
    var date2 = new Date(this.form.value.resEnd);

    var Time = date2.getTime() - date1.getTime();
    var Days = Time / (1000 * 3600 * 24);
    this.fullPrice = Days * this.price;

  }
}

