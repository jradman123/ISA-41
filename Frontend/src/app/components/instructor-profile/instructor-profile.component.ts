import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DeleteAccountRequest } from 'src/app/interfaces/delete-account-request';
import { InstructorPersonalData } from 'src/app/interfaces/instructor-personal-data';
import { AuthService } from 'src/app/services/AuthService/auth.service';
import { RequestForDeletingAccountServiceService } from 'src/app/services/RequestForDeletingAccountService/request-for-deleting-account-service.service';
import { UserService } from 'src/app/services/UserService/user.service';
import { DialogForChangingPasswordComponent } from '../dialog-for-changing-password/dialog-for-changing-password.component';
import { DialogForDeletingAccountComponent } from '../dialog-for-deleting-account/dialog-for-deleting-account.component';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.css']
})
export class InstructorProfileComponent implements OnInit {

  personalData! : InstructorPersonalData;
  sub!: Subscription;
  request! : DeleteAccountRequest;


  constructor(private userService : UserService,public dialog: MatDialog,private authService : AuthService,private router : Router,
    private requestForDeletingAccountService : RequestForDeletingAccountServiceService) {
    this.personalData = {} as InstructorPersonalData;
    this.request = {} as DeleteAccountRequest;
   }

   @Input() 
  userDetails: any
  initialDetails: any
  editMode = false
  id!: number

  ngOnInit(): void {
    this.getPersonalData();
    if(localStorage.getItem('firstLogin') === 'true'){
      this.changePassword();
    }
  }

  getPersonalData() {
    this.sub = this.userService.getInstructorPersonalData().subscribe({
      next: (data: InstructorPersonalData) => {
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
        this.detailsForm.controls['biography'].setValue(data.biography)
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
  phoneNumber: new FormControl(null, [Validators.required]),
  biography: new FormControl(null, [
    Validators.required,
    Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
  ]), 
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
    this.detailsForm.controls['biography'].setValue(this.initialDetails.biography)
  }

  edit() {
    if(this.detailsForm.invalid){
      return;
    }
    this.userDetails = {
      firstName: this.detailsForm.get('firstName')?.value,
      lastName: this.detailsForm.get('lastName')?.value,
      email: this.detailsForm.get('email')?.value,
      phoneNumber: this.detailsForm.get('phoneNumber')?.value,
      streetName: this.detailsForm.get('streetName')?.value,
      streetNumber: this.detailsForm.get('streetNumber')?.value,
      city: this.detailsForm.get('city')?.value,
      country: this.detailsForm.get('country')?.value,
      biography: this.detailsForm.get('biography')?.value
    }
    this.userService.updateInstructorPersonalData(this.userDetails).subscribe((data) => {
      this.userDetails = data
      this.initialDetails = JSON.parse(JSON.stringify(data));
      this.editMode = false
    })
  }

  changePassword() {
    const dialogConfig = new MatDialogConfig();
 
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
 
    const dialogRef = this.dialog.open(DialogForChangingPasswordComponent, dialogConfig);
 
    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.userService.changePassword(data).subscribe((result:any) => {
            //localStorage.setItem('firstLogin','false')
            this.authService.logout();
            this.router.navigate(['/login']);
            void(0) 
          })
        }
      }
    );
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
          this.requestForDeletingAccountService.addNewRequest(this.request).subscribe((result:any) => {
           void(0)
          })
        }
      }
    );
  }

  createDeleteAccountRequest(data : any) {
    this.request.reason = data.reason;
    this.request.email = localStorage.getItem('email');
  }

}
