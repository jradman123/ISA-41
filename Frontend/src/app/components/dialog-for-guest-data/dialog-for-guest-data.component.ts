import { Component, Inject, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DataForDialogGuest } from '../reservation-history/reservation-history/reservation-history.component';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { UserService } from 'src/app/services/UserService/user.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-dialog-for-guest-data',
  templateUrl: './dialog-for-guest-data.component.html',
  styleUrls: ['./dialog-for-guest-data.component.css']
})
export class DialogForGuestDataComponent implements OnInit {


  personalData!: PersonalData;
  detailForm!: FormGroup
  sub!: Subscription;
  constructor(private userService: UserService, private route: Router, @Inject(MAT_DIALOG_DATA) public data: DataForDialogGuest, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForGuestDataComponent>) {
    this.personalData = {} as PersonalData;
  }

  ngOnInit(): void {
    this.getPersonalData();

  }

  @Input()
  userDetails: any
  initialDetails: any
  editMode = false
  id!: number
  getPersonalData() {
    this.sub = this.userService.findbyEmail(this.data.clientEmail).subscribe({
      next: (data: PersonalData) => {
        this.userDetails = data
        this.initialDetails = JSON.parse(JSON.stringify(data));
        this.detailsForm.controls['firstName'].setValue(data.firstName)
        this.detailsForm.controls['lastName'].setValue(data.lastName)
        this.detailsForm.controls['email'].setValue(data.email)
        this.detailsForm.controls['phoneNumber'].setValue(data.phoneNumber)
        this.detailsForm.controls['streetName'].setValue(data.streetName)
        this.detailsForm.controls['streetNumber'].setValue(data.streetNumber)
        this.detailsForm.controls['city'].setValue(data.city)
        this.detailsForm.controls['country'].setValue(data.country)
      },
    });
  }



  detailsForm = new FormGroup({
    firstName: new FormControl('', [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    lastName: new FormControl('', [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    streetName: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    streetNumber: new FormControl(null, [
      Validators.required,
      Validators.pattern('^\\d{1,3}$'),
    ]),
    city: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    country: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    email: new FormControl(null, [Validators.required, Validators.email]),
    phoneNumber: new FormControl(null, [Validators.required])
  })

  onNoClick(): void {
    this.dialogRef.close();
  }

}
