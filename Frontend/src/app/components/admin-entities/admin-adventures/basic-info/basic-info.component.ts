import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['./basic-info.component.css']
})
export class BasicInfoComponent implements OnInit {

  id : any;
  adventure : AdventureDto;
  initialDetails: any;
  reservationsExist! : string;
  constructor(private adventureService : AdventureService,private router: ActivatedRoute,
    private reservationService : ReservationService) {
    this.adventure = {} as AdventureDto;
   }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.adventureService.findById(this.id).subscribe({
      next: (data: AdventureDto) => {
        this.adventure = data
        this.initialDetails = JSON.parse(JSON.stringify(data)); 
        this.detailsForm.controls['name'].setValue(data.name)
        this.detailsForm.controls['description'].setValue(data.description)
        this.detailsForm.controls['price'].setValue(data.price)
        this.detailsForm.controls['cancellationConditions'].setValue(data.cancellationConditions)
        this.detailsForm.controls['streetName'].setValue(data.streetName)
        this.detailsForm.controls['streetNumber'].setValue(data.streetNumber)
        this.detailsForm.controls['city'].setValue(data.city)
        this.detailsForm.controls['country'].setValue(data.country)
        this.detailsForm.controls['guestLimit'].setValue(data.guestLimit)
        this.detailsForm.controls['biography'].setValue(data.instructorsBiography)
        this.detailsForm.controls['longitude'].setValue(data.longitude)
        this.detailsForm.controls['latitude'].setValue(data.latitude)
      },
    });

    this.reservationService.reservationsExistForAdventure(this.id)
    .subscribe({
      next: (result: any) => {
        this.reservationsExist = result;
      },
    });
  }
  
  detailsForm = new FormGroup({ 
    name: new FormControl('', [
    Validators.required,
    Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
  ]),
  description: new FormControl('', [
    Validators.required
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
  price: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
  cancellationConditions: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
  guestLimit: new FormControl(null, [Validators.required,Validators.pattern('^\\d{1,3}$')]),
  biography : new FormControl(null),
  longitude: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,7}$')]),
  latitude: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,7}$')]),
})
}
