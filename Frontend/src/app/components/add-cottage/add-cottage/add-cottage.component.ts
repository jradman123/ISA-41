import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-add-cottage',
  templateUrl: './add-cottage.component.html',
  styleUrls: ['./add-cottage.component.css']

})
export class AddCottageComponent implements OnInit {


  errorMessage!: string;
  createForm!: FormGroup;
  formData!: FormData;
  newCottage!: CottageDto;
  email: any;

  constructor(private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router, private cottageService: CottageService) {
    this.newCottage = {} as CottageDto;
    this.email = localStorage.getItem('email')


  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group({
      name: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      description: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      streetNumber: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      street: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),

      city: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      country: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      price: new FormControl(null, [
        Validators.required,
      ]),
      longitude: new FormControl(null, [
        Validators.required,
      ]),
      latitude: new FormControl(null, [
        Validators.required,
      ]),

      numberOfPeople: new FormControl(null, [
        Validators.required,
      ]),
      cancelled: new FormControl(null, [
        Validators.required,
      ]),



      type: new FormControl(null, [Validators.required])
    })

  }

  onSubmit(): void {
    Swal.fire({
      icon: 'success',
      title: 'Good job!',
      text: 'You have successfully added a new cottage!',
    })

    this.createCottage();
    if (this.newCottage.name == '' || this.newCottage.city == '' ||
      this.newCottage.country == '' || this.newCottage.description == ''
      || this.newCottage.numberOfPeople == '' || this.newCottage.streetName == '' ||
      this.newCottage.price == '') {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'You must fill in all fields!',
      })

    } else {
      console.log(this.createCottage);
      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully added a new cottage!',
      })
      this.cottageService.saveCottage(this.newCottage).subscribe(
        (res) => {
          this.router.navigate(['/cottageOwner']);

        },
        (err) => {
          let parts = err.error.split(':');
          let mess = parts[parts.length - 1];
          let description = mess.substring(1, mess.length - 4);

        }
      );

    }
  }

  createCottage(): void {
    this.newCottage.name = this.createForm.value.name;
    this.newCottage.description = this.createForm.value.description;
    this.newCottage.streetName = this.createForm.value.street;
    this.newCottage.streetNumber = this.createForm.value.streetNumber;
    this.newCottage.city = this.createForm.value.city;
    this.newCottage.country = this.createForm.value.country;
    this.newCottage.price = this.createForm.value.price;
    this.newCottage.numberOfPeople = this.createForm.value.numberOfPeople;
    this.newCottage.ownerEmail = this.email;
    this.newCottage.cancelled_conditions = this.createForm.value.cancelled;
    this.newCottage.latitude = this.createForm.value.latitude;
    this.newCottage.longitude = this.createForm.value.longitude;
  }
}
