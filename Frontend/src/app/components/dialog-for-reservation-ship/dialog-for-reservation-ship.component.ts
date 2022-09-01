import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { DataForDialogGuest } from '../reservation-history/reservation-history/reservation-history.component';
import Swal from 'sweetalert2';
import { DataForDialogEmail } from '../ship-reservation-history/ship-reservation-history.component';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';

@Component({
  selector: 'app-dialog-for-reservation-ship',
  templateUrl: './dialog-for-reservation-ship.component.html',
  styleUrls: ['./dialog-for-reservation-ship.component.css']
})
export class DialogForReservationShipComponent implements OnInit {

  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  newReservation!: CottageReservation;
  ship!: ShipDto;
  utilities!: UtilityDto[];
  fullPrice: number = 0;
  price!: any;
  Utilities = new FormControl('');


  id: any;
  constructor(private shipService: ShipService, private utilityService: UtilityService, @Inject(MAT_DIALOG_DATA) public data: DataForDialogEmail, private reservationService: ReservationService, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReservationShipComponent>) {
    this.newReservation = {} as CottageReservation;
  }
  ngOnInit(): void {

    this.shipService.findbyId(this.data.id).subscribe({
      next: (data: ShipDto) => {
        this.ship = data
        this.price = data.price
      },
    });
    this.utilityService.findShipUtilityById(this.data.id).subscribe((data) => {
      this.utilities = data;
    });



    this.form = new FormGroup({
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      capacity: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
    })
  }
  reserved(): void {



    this.reservateShip();


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
    } else if (this.newReservation.resStart < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
    } else {



      console.log("siufsdfhdsfkjds" + this.newReservation.clientEmail)
      this.sub = this.reservationService.reservatedShip(this.newReservation)
        .subscribe({
          next: (response) => {
            if (response == "NO!") {
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'This appointment is already reserved or ship is not available during a period!',
              })
            } else {
              Swal.fire({
                icon: 'success',
                title: 'Good job!',
                text: 'You have successfully booked a ship!',
              })

              this.dialogRef.close();
              window.location.reload();

            }
          }
        });
    }
  }

  reservateShip(): void {



    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)


    console.log("dgwefedfsd" + this.data.clientEmail, this.data.id, this.form.value.price)

    this.newReservation.resStart = new Date(newStart.setHours(14, 0, 0, 0)),
      this.newReservation.resEnd = new Date(newEnd.setHours(11, 0, 0, 0)),
      this.newReservation.numberOfPerson = this.form.value.numberOfPerson;
    this.newReservation.price = this.fullPrice.toString();
    this.newReservation.clientEmail = this.data.clientEmail;
    this.newReservation.objectId = this.data.id;
    this.newReservation.typeOfRes = 'BOAT';
    this.newReservation.utilities = this.Utilities.value;


  }

  total() {

    var date1 = new Date(this.form.value.resStart);
    var date2 = new Date(this.form.value.resEnd);

    var Time = date2.getTime() - date1.getTime();
    var Days = Time / (1000 * 3600 * 24);
    this.fullPrice = Days * this.price;
    for (let i = 0; i < this.Utilities.value.length; i++) {
      this.fullPrice += Number(this.Utilities.value[i].price)

    }


  }
}
