import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureQuickReservationDto } from 'src/app/interfaces/adventure-quick-reservation-dto';
import { AdventureQuickReservationResponse } from 'src/app/interfaces/adventure-quick-reservation-response';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureQuickReservationService } from 'src/app/services/AdventureQuickReservationService/adventure-quick-reservation.service';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  id : any;
  utilities: ResponseUtility[];
  adventure : AdventureDto;
  adventuresUtilities = new FormControl('');
  newAdventureQuickReservation : AdventureQuickReservationDto;
  response! : any;
  quickReservations : AdventureQuickReservationResponse[];

  constructor(private activatedRoute: ActivatedRoute,private adventureService : AdventureService,
    private adventureQuickReservationService : AdventureQuickReservationService){
    this.utilities = [] as ResponseUtility[];
    this.adventure = {} as AdventureDto;
    this.newAdventureQuickReservation = {} as AdventureQuickReservationDto;
    this.quickReservations = [] as AdventureQuickReservationResponse[];
  }

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.paramMap.get('id')!;
    this.getAdventure();
    this.getUtilitiesForAdventure();
    this.getQuickReservations();
  }
  
  getAdventure() {
    this.adventureService.findById(this.id).subscribe({
      next: (data: AdventureDto) => {
        this.adventure = data
      },
    });
  }
  getUtilitiesForAdventure() {
    this.adventureService.getAdventureUtilities(this.id).subscribe({
      next: (res : ResponseUtility[]) => {
        this.utilities = res
    }
  });
  }

  getQuickReservations() {
    this.adventureQuickReservationService.getAllForAdventure(this.id).subscribe({
      next: (data: AdventureQuickReservationResponse[]) => {
        this.quickReservations = [];
        this.quickReservations = data;
      },
    });
  }

  detailsForm = new FormGroup({ 
    price: new FormControl(null, [ Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
    guestLimit: new FormControl(null, [Validators.pattern('^\\d{1,3}$')]),
    startDate: new FormControl(null),
    endDate: new FormControl(null),
    validUntil: new FormControl(null),
})

cancel() { 
  this.detailsForm.reset();
  this.adventuresUtilities.reset();
}
  
add() {
  this.newAdventureQuickReservation = {
    startTime: this.detailsForm.get('startDate')?.value,
    endTime : this.detailsForm.get('endDate')?.value,
    validUntil : this.detailsForm.get('validUntil')?.value,
    price : this.detailsForm.get('price')?.value,
    guestLimit : this.detailsForm.get('guestLimit')?.value,
    adventureId : this.id,
    utilities : this.adventuresUtilities.value
  }

  if (this.newAdventureQuickReservation.startTime == null
    || this.newAdventureQuickReservation.endTime == null ||
    this.newAdventureQuickReservation.guestLimit == null ||
    this.newAdventureQuickReservation.price == null || this.newAdventureQuickReservation.validUntil == null) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Please fill all fields!',
      });
      return;
    } else if (new Date(this.newAdventureQuickReservation.startTime) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Start date must be greater then today!',
      })
      return;
    }
    else if (new Date(this.newAdventureQuickReservation.startTime) > new Date(this.newAdventureQuickReservation.endTime)) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'End date must be greater then start date!',
      })
      return;
    } 
     else if (new Date(this.newAdventureQuickReservation.validUntil) > new Date(this.newAdventureQuickReservation.startTime)) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid until must be before start date!',
      })
      return;
    }
    else if (new Date(this.newAdventureQuickReservation.validUntil) < new Date()) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Valid until  must not be before today!',
      })
      return;

    } else {
      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully added new appointment!',
      })

    }

  this.adventureQuickReservationService.addAdventaddAdventureQuickReservation(this.newAdventureQuickReservation).subscribe((data) => {
    this.detailsForm.reset();
    this.adventuresUtilities.reset();
    this.quickReservations = [];
    this.getQuickReservations();
    this.response = data;
  })
}
}
