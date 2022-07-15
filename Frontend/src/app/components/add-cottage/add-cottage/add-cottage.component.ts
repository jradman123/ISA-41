import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';

@Component({
  selector: 'app-add-cottage',
  templateUrl: './add-cottage.component.html',
  styleUrls: ['./add-cottage.component.css']
  
})
export class AddCottageComponent implements OnInit {


  errorMessage!: string;
  createForm!: FormGroup;
  formData!: FormData;
  newCottage!:CottageDto;


  constructor(private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
    private router: Router,private cottageService:CottageService) {
      this.newCottage={} as CottageDto;
     
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
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      numberOfPeople: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
     
     
   
      type: new FormControl(null, [Validators.required])
    })
  
  }

  onSubmit(): void {
    this.createCottage();
    console.log(this.createCottage);
    this.cottageService.saveCottage(this.newCottage).subscribe(
      (res) => {
        this.router.navigate(['/']);
        this._snackBar.open(
          'Your registration request has been sumbitted. Please check your email and confirm your email adress to activate your account.',
          '',
          {duration : 3000,panelClass: ['snack-bar']}
        );
      },
      (err) => {
        let parts = err.error.split(':');
        let mess = parts[parts.length - 1];
        let description = mess.substring(1, mess.length - 4);
        this._snackBar.open(description,'',
        {duration : 3000,panelClass: ['snack-bar']});
      }
    );
  
    
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
  
}
}
