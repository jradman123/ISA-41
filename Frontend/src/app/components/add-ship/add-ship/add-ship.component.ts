import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-ship',
  templateUrl: './add-ship.component.html',
  styleUrls: ['./add-ship.component.css']
})
export class AddShipComponent implements OnInit {


  errorMessage!: string;
  createForm!: FormGroup;
  formData!: FormData;
  newShip!: ShipDto;
  email: any;

  constructor(private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router, private shipService: ShipService) {
    this.newShip = {} as ShipDto;
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
        Validators.pattern('^\\d{1,3}$'),
      ]),
      capacity: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      lenght: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      numberOfEngine: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      strengthOfEngine: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      maxSpeed: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      typeofShip: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      cancelationConditions: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      fishingEquipment: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),




      type: new FormControl(null, [Validators.required])
    })
  }

  onSubmit(): void {
    this.createShip();

    if (this.newShip.name == '' || this.newShip.city == '' ||
      this.newShip.country == '' || this.newShip.description == ''
      || this.newShip.cancelationConditions == '' || this.newShip.streetName == '' ||
      this.newShip.price == '' || this.newShip.capacity == '' || this.newShip.streetName == '' ||
      this.newShip.length == '' || this.newShip.strengthOfEngine == '' ||
      this.newShip.type == '' || this.newShip.fishingEquipment == '' || this.newShip.numberOfEngine == '' ||
      this.newShip.maxSpeed == '') {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'You must fill in all fields!',
      })

    } else {

      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully added a new ship!',
      })


      console.log(this.createShip);
      this.shipService.saveShip(this.newShip).subscribe(
        (res) => {
          this.router.navigate(['/shipOwner']);

        },
        (err) => {
          let parts = err.error.split(':');
          let mess = parts[parts.length - 1];
          let description = mess.substring(1, mess.length - 4);

        }
      );
    }

  }

  createShip(): void {
    this.newShip.name = this.createForm.value.name;
    this.newShip.description = this.createForm.value.description;
    this.newShip.streetName = this.createForm.value.street;
    this.newShip.streetNumber = this.createForm.value.streetNumber;
    this.newShip.city = this.createForm.value.city;
    this.newShip.country = this.createForm.value.country;
    this.newShip.price = this.createForm.value.price;
    this.newShip.capacity = this.createForm.value.capacity;
    this.newShip.length = this.createForm.value.lenght;
    this.newShip.numberOfEngine = this.createForm.value.numberOfEngine;
    this.newShip.strengthOfEngine = this.createForm.value.strengthOfEngine;
    this.newShip.maxSpeed = this.createForm.value.maxSpeed;
    this.newShip.type = this.createForm.value.typeofShip;
    this.newShip.cancelationConditions = this.createForm.value.cancelationConditions;
    this.newShip.fishingEquipment = this.createForm.value.fishingEquipment;

    this.newShip.ownerEmail = this.email;

  }

}
