import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { RegistrationRequest } from 'src/app/interfaces/registration-request';
import { AuthService } from 'src/app/services/AuthService/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  errorMessage!: string;
  createForm!: FormGroup;
  formData!: FormData;
  uploaded: boolean = false;
  passMatch: boolean = false;
  newUser!: RegistrationRequest;

  constructor(private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router,
    private authService : AuthService) { 
      this.newUser = {} as RegistrationRequest;
    }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      firstName: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      lastName: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      street: new FormControl(null, [
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
      phone: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(8),
      ]),
      passConfirmed: new FormControl(null, [
        Validators.required,
        Validators.minLength(8),
      ]),
      description: new FormControl(null, [
        Validators.required
      ]),
      type: new FormControl(null, [Validators.required])
      /*longitude: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}.?\\d{1,7}$'),
      ]),
      latitude: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}.?\\d{1,7}$'),
      ])*/
    });

  }

  onPasswordInput(): void {
    this.passMatch =
      this.createForm.value.password === this.createForm.value.passConfirmed;
  }

  onSubmit(): void {
    if(this.createForm.invalid){
      console.log('nevalidno')
      return;
    }
      this.createUser();
      this.authService.registerUser(this.newUser).subscribe(
        (res) => {
          this.router.navigate(['/login']);
          this._snackBar.open(
            'Your registration request has been sumbitted. You need to wait for approval by admin.',
            '',
            {duration : 3000,panelClass: ['snack-bar']}
          );
        },
        (err) => {
          let parts = err.error.split(':');
          let mess = parts[parts.length - 1];
          let description = mess.substring(0, mess.length);
          this._snackBar.open(description,  '',
          {duration : 3000,panelClass: ['snack-bar']});
        }
      );
  }

  createUser(): void {
    /*let role = this.createForm.get('role')?.value;
    if(role == "CLIENT"){
      this.newUser.firstName = this.createForm.value.firstName;
      this.newUser.lastName = this.createForm.value.lastName;
      this.newUser.streetName = this.createForm.value.street;
      this.newUser.streetNumber = this.createForm.value.streetNumber;
      this.newUser.city = this.createForm.value.city;
      this.newUser.country = this.createForm.value.country;
      this.newUser.phoneNumber = this.createForm.value.phone;
      this.newUser.email = this.createForm.value.email;
      this.newUser.password = this.createForm.value.password;
      this.newUser.typeOfRegistration = this.createForm.value.type;
    } else {*/
      this.newUser.firstName = this.createForm.value.firstName;
      this.newUser.lastName = this.createForm.value.lastName;
      this.newUser.streetName = this.createForm.value.street;
      this.newUser.streetNumber = this.createForm.value.streetNumber;
      this.newUser.city = this.createForm.value.city;
      this.newUser.country = this.createForm.value.country;
      this.newUser.phoneNumber = this.createForm.value.phone;
      this.newUser.email = this.createForm.value.email;
      this.newUser.password = this.createForm.value.password;
      this.newUser.typeOfRegistration = this.createForm.value.type;
      this.newUser.descriptionOfRegistration = this.createForm.value.description;
      this.newUser.latitude = 0.0;
      this.newUser.longitude = 0.0;
    //}
  }

}

