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
  constructor(private adventureService: AdventureService, @Inject(MAT_DIALOG_DATA) public data: DataForDialogEmail, 
  private reservationService: ReservationService, public dialog: MatDialog, 
  private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReservationAdventureComponent>) { 
    this.newReservation = {} as AdventureReservation;
    this.utilities = [] as ResponseUtility[];
  }

  ngOnInit(): void {
    this.adventureService.findById(this.data.id).subscribe({
      next: (data: AdventureDto) => {
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



    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)
    this.newReservation.resStart = new Date(newStart.setHours(14, 0, 0, 0)),
    this.newReservation.resEnd = new Date(newEnd.setHours(11, 0, 0, 0)),
    this.newReservation.numberOfPerson = this.form.value.numberOfPerson;
    this.newReservation.price = this.fullPrice.toString();
    this.newReservation.clientEmail = this.data.clientEmail;
    this.newReservation.objectId = this.data.id;
    this.newReservation.typeOfRes = 'ADVENTURE';
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


    if (this.newReservation.resStart >= this.newReservation.resEnd) {
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
    } else {

      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully booked an adventure!',
      })


      this.sub = this.reservationService.reserveAdventure(this.newReservation)
        .subscribe({
          next: () => {

            this.dialogRef.close();
            window.location.reload();

          }
        });
    }
  }

  total() {
    this.fullPrice = this.form.value.numberOfPerson * this.price;
  }

}
