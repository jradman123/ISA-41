import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { DeleteAccountRequest } from 'src/app/interfaces/delete-account-request';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { RequestForDeletingAccountServiceService } from 'src/app/services/RequestForDeletingAccountService/request-for-deleting-account-service.service';
import { UserService } from 'src/app/services/UserService/user.service';
import { DialogForChangingPasswordComponent } from '../../dialog-for-changing-password/dialog-for-changing-password.component';
import { DialogForDeletingAccountComponent } from '../../dialog-for-deleting-account/dialog-for-deleting-account.component';


@Component({
  selector: 'app-cottage-owner-profile',
  templateUrl: './cottage-owner-profile.component.html',
  styleUrls: ['./cottage-owner-profile.component.css']
})
export class CottageOwnerProfileComponent implements OnInit {

  personalData!: PersonalData;
  sub!: Subscription;
  constructor(private userService: UserService, private requestForDeletingAccountService: RequestForDeletingAccountServiceService, public dialog: MatDialog) {
    this.personalData = {} as PersonalData;
  }

  @Input()
  userDetails: any
  initialDetails: any
  editMode = false
  id!: number
  request!: DeleteAccountRequest;
  ngOnInit(): void {
    this.getPersonalData();
  }

  getPersonalData() {
    this.sub = this.userService.getPersonalData().subscribe({
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
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
    streetName: new FormControl('', Validators.required),
    streetNumber: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required)
  })
  cancel() {
    this.editMode = false
    this.detailsForm.controls['firstName'].setValue(this.initialDetails.firstName)
    this.detailsForm.controls['lastName'].setValue(this.initialDetails.lastName)
    this.detailsForm.controls['email'].setValue(this.initialDetails.email)
    this.detailsForm.controls['phoneNumber'].setValue(this.initialDetails.phoneNumber)
    this.detailsForm.controls['streetName'].setValue(this.initialDetails.streetName)
    this.detailsForm.controls['streetNumber'].setValue(this.initialDetails.streetNumber)
    this.detailsForm.controls['city'].setValue(this.initialDetails.city)
    this.detailsForm.controls['country'].setValue(this.initialDetails.country)
  }

  edit() {
    this.userDetails = {
      firstName: this.detailsForm.get('firstName')?.value,
      lastName: this.detailsForm.get('lastName')?.value,
      email: this.detailsForm.get('email')?.value,
      phoneNumber: this.detailsForm.get('phoneNumber')?.value,
      streetName: this.detailsForm.get('streetName')?.value,
      streetNumber: this.detailsForm.get('streetNumber')?.value,
      city: this.detailsForm.get('city')?.value,
      country: this.detailsForm.get('country')?.value
    }
    this.userService.updatePersonalData(this.userDetails).subscribe((data) => {
      this.userDetails = data
      this.initialDetails = JSON.parse(JSON.stringify(data));
      this.editMode = false
    })
  }

  deleteAccount() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForDeletingAccountComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.createDeleteAccountRequest(data);
          this.requestForDeletingAccountService.addNewRequest(this.request).subscribe((result: any) => {
            void (0)
          })
        }
      }
    );

  }

  createDeleteAccountRequest(data: any) {
    this.request.reason = data.reason;
    this.request.email = localStorage.getItem('email');
  }
  changePassword() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForChangingPasswordComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.userService.changePassword(data).subscribe((result: any) => {
            void (0)
          })
        }
      }
    );
  }

}




