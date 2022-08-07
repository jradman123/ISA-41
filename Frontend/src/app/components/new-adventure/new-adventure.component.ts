import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { NewAdventureDto } from 'src/app/interfaces/new-adventure-dto';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-new-adventure',
  templateUrl: './new-adventure.component.html',
  styleUrls: ['./new-adventure.component.css']
})
export class NewAdventureComponent implements OnInit {

  createForm!: FormGroup;
  adventure : NewAdventureDto;

  constructor(private formBuilder: FormBuilder,private adventureService : AdventureService,private _snackBar : MatSnackBar,private router : Router) { 
    this.adventure = {} as NewAdventureDto
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      name: new FormControl('', [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      guestLimit: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}$'),
      ]),
      price: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}.?\\d{1,3}$'),
      ]),
      cancellationConditions: new FormControl(null, [
        Validators.required,
        Validators.pattern('^\\d{1,3}.?\\d{1,3}$'),
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
      description: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ])
    });
  }

  save() : void {
    if(this.createForm.invalid){
      this._snackBar.open(
        'Fields should be filled',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
      return;
    }
    this.adventure = {
      name: this.createForm.get('name')?.value,
      guestLimit: this.createForm.get('guestLimit')?.value,
      streetName: this.createForm.get('street')?.value,
      streetNumber: this.createForm.get('streetNumber')?.value,
      city: this.createForm.get('city')?.value,
      country: this.createForm.get('country')?.value,
      description: this.createForm.get('description')?.value,
      cancellationConditions: this.createForm.get('cancellationConditions')?.value,
      price: this.createForm.get('price')?.value
    }
    this.adventureService.createNewAdventure(this.adventure).subscribe((res) => {
      this._snackBar.open(
        'Adventure is added',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
      this.router.navigate(['/instructor/adventures']);
    },
    (err) => {
      let parts = err.error.split(':');
      let mess = parts[parts.length - 1];
      let description = mess.substring(0, mess.length);
      this._snackBar.open(description, '',
      {duration : 3000,panelClass: ['snack-bar']});
    }
  );
  }

}
