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
  startAvailableDate: any = null;
  endAvailableDate: any = null;
  id: any;
  constructor(private cottageService: CottageService, @Inject(MAT_DIALOG_DATA) public data: DataForDialogEmail, private reservationService: ReservationService, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReservationCottageComponent>) {
    this.newReservation = {} as CottageReservation;
  }
  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.findbyId(this.id).subscribe({
      next: (data: CottageDto) => {
        this.cottage = data
      },
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

      console.log("siufsdfhdsfkjds" + this.newReservation)
      this.sub = this.reservationService.reservatedCottage(this.newReservation)
        .subscribe({
          next: () => {

            this.dialogRef.close();
            window.location.reload();

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
    this.newReservation.price = this.form.value.price;
    this.newReservation.clientEmail = this.data.clientEmail;
    this.newReservation.objectId = this.data.id;
    this.newReservation.typeOfRes = 'COTTAGE';

  }
}

