import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import Swal from 'sweetalert2';
import { DataForDialogEmail } from '../ship-reservation-history/ship-reservation-history.component';

@Component({
  selector: 'app-dialog-for-reservation-adventure',
  templateUrl: './dialog-for-reservation-adventure.component.html',
  styleUrls: ['./dialog-for-reservation-adventure.component.css']
})
export class DialogForReservationAdventureComponent implements OnInit {

  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  newReservation!: AdventureReservation;
  adventure!: AdventureDto;
  start: any
  end: any
  id: any;
  adventuresUtilities = new FormControl('');
  utilities: ResponseUtility[];
  fullPrice: number = 0;
  price!: any;
  guestLimit! : number;
  constructor(private adventureService: AdventureService, @Inject(MAT_DIALOG_DATA) public data: DataForDialogEmail, 
  private reservationService: ReservationService, public dialog: MatDialog, 
  private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReservationAdventureComponent>) { 
    this.newReservation = {} as AdventureReservation;
    this.utilities = [] as ResponseUtility[];
  }

  ngOnInit(): void {
    this.adventureService.findById(this.data.id).subscribe({
      next: (data: AdventureDto) => {
        this.guestLimit = Number(data.guestLimit);
        this.adventure = data
        this.price = data.price
      },
    });
    this.adventureService.getAdventureUtilities(this.data.id).subscribe((data) => {
      this.utilities = data;
    });
    this.form = new FormGroup({
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      numberOfPerson: new FormControl('', Validators.required)
    })
  }

  reserveAdventure(): void {
    const s = new Date( this.form.value.resStart.getTime() -  this.form.value.resStart.getTimezoneOffset() * 60000)
    const e = new Date( this.form.value.resEnd.getTime() -  this.form.value.resEnd.getTimezoneOffset() * 60000)
    this.newReservation.resStart = s,
    this.newReservation.resEnd = e,
    this.newReservation.numberOfPerson = this.form.value.numberOfPerson;
    this.newReservation.price = this.fullPrice.toString();
    this.newReservation.clientEmail = this.data.clientEmail;
    this.newReservation.objectId = this.data.id;
    this.newReservation.typeOfRes = 'ADVENTURE';
    this.newReservation.utilities = this.adventuresUtilities.value;
    console.log(this.form.value)

  }

  reserve(): void {


    this.reserveAdventure();

    if (this.form.invalid) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'You must fill in all fields!',
      })
      return;
    }


    if (this.newReservation.resStart > this.newReservation.resEnd) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date is greater or equal then end date!',
      })
      return;
    }
    else if (this.newReservation.price == "0") {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'You must click the calculate button!',
      })
      return;
    }
    else if (this.newReservation.resStart < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
      return;
    }


      this.sub = this.reservationService.reserveAdventure(this.newReservation)
        .subscribe(
          (res: string) => {

            this.dialogRef.close();
            Swal.fire({
              icon: 'success',
              title: 'Success',
              text: res,
            })
            
          },
          (err) => {
            let parts = err.error.split(':');
            let mess = parts[parts.length - 1];
            let description = mess.substring(0, mess.length);
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: description
            })
          });
        }
         
    


  total() {
    this.fullPrice = this.form.value.numberOfPerson * this.price; 
    for(let i=0; i < this.adventuresUtilities.value.length; i++){
      this.fullPrice += Number(this.adventuresUtilities.value[i].price);
    }
  }

}
