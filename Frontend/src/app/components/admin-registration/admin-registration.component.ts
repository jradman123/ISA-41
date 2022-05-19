import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { RegistrationRequest } from 'src/app/interfaces/registration-request';
import { AuthService } from 'src/app/services/AuthService/auth.service';
import { UserService } from 'src/app/services/UserService/user.service';

@Component({
  selector: 'app-admin-registration',
  templateUrl: './admin-registration.component.html',
  styleUrls: ['./admin-registration.component.css']
})
export class AdminRegistrationComponent implements OnInit {

  errorMessage!: string;
  createForm!: FormGroup;
  @Input() 
  adminInformations: any
  formData!: FormData;
  uploaded: boolean = false;
  passMatch: boolean = false;
  newUser!: RegistrationRequest;
  constructor(private formBuilder: FormBuilder,private authService : AuthService,private userService : UserService,
    private _snackBar: MatSnackBar,
    private router: Router) { }

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
      middleName: new FormControl('', [
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
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(8),
      ]),
      passConfirmed: new FormControl(null, [
        Validators.required,
        Validators.minLength(8),
      ])
  })

}

  onSubmit() {
    this.adminInformations = {
      firstName: this.createForm.get('firstName')?.value,
      lastName: this.createForm.get('lastName')?.value,
      email: this.createForm.get('email')?.value,
      phoneNumber: this.createForm.get('phoneNumber')?.value,
      streetName: this.createForm.get('streetName')?.value,
      streetNumber: this.createForm.get('streetNumber')?.value,
      city: this.createForm.get('city')?.value,
      country: this.createForm.get('country')?.value,
      password: this.createForm.get('password')?.value,
      

    }
    this.authService.addAdmin(this.adminInformations).subscribe((res) => {
      this._snackBar.open(
        'Admin is added',
        'Dismiss'
      );
      this.router.navigate(['/admin']);
    },
    (err) => {
      let parts = err.error.split(':');
      let mess = parts[parts.length - 1];
      let description = mess.substring(1, mess.length - 4);
      this._snackBar.open(description, 'Dismiss');
    }
  );
  }

  onPasswordInput(): void {
    this.passMatch =
      this.createForm.value.password === this.createForm.value.passConfirmed;
  }
}
