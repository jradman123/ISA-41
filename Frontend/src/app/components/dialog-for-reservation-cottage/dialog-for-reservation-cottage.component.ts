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


@Component({
  selector: 'app-dialog-for-reservation-cottage',
  templateUrl: './dialog-for-reservation-cottage.component.html',
  styleUrls: ['./dialog-for-reservation-cottage.component.css']
})
export class DialogForReservationCottageComponent implements OnInit {


  fullPrice: number = 0;
  cottage!: CottageDto;
  users!: PersonalData[];
  id: any;
  sub!: Subscription;
  newReservation!: CottageReservation
  form!: FormGroup;
  formData!: FormData;




  constructor(private userService: UserService, private reservationService: ReservationService, private route: Router, @Inject(MAT_DIALOG_DATA) public data: DataForDialogCottage, private cottageService: CottageService, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReservationCottageComponent>) {
    this.newReservation = {} as CottageReservation;
  }

  ngOnInit() {
    this.userService.findAll().subscribe((data) => {
      this.users = data;
    });
    console.log(this.data.id)


    this.cottageService.findbyId(this.data.id).subscribe((data) => {
      this.cottage = data;
    });

    this.form = new FormGroup({
      clientEmail: new FormControl('', Validators.required),
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      numberOfPerson: new FormControl('', Validators.required)
    })

  }




  calcPrice() {




  }


  reserved(): void {
    this.reservateCottage();
    this.sub = this.reservationService.reservatedCottage(this.newReservation)
      .subscribe({
        next: () => {

          this.onNoClick();
        }
      });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  reservateCottage(): void {


    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)


    console.log(newStart)
    console.log(newEnd)
    this.newReservation.resStart = new Date(newStart.setHours(14, 0, 0, 0)),
      this.newReservation.resEnd = new Date(newEnd.setHours(11, 0, 0, 0)),
      this.newReservation.numberOfPerson = this.form.value.numberOfPerson;
    this.newReservation.price = this.cottage.price;
    this.newReservation.clientEmail = this.form.value.clientEmail;
    this.newReservation.objectId = this.data.id;
    this.newReservation.typeOfRes = 'COTTAGE';

  }
}


