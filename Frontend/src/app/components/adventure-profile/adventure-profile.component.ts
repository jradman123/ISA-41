import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

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

  constructor(private activatedRoute: ActivatedRoute,private adventureService : AdventureService){
    this.utilities = [] as ResponseUtility[];
    this.adventure = {} as AdventureDto;
  }

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.paramMap.get('id')!;
    this.getAdventure();
    this.getUtilitiesForAdventure();
    
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

  detailsForm = new FormGroup({ 
    price: new FormControl(null, [ Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
    guestLimit: new FormControl(null, [Validators.pattern('^\\d{1,3}$')]),
    startDate: new FormControl(null, [Validators.required]),
    endDate: new FormControl(null, [Validators.required]),
})

cancel() { 
  this.detailsForm.reset();
}
  
add() {
  let nesto = this.detailsForm.value.startDate;
  console.log(nesto);
  let datum = new Date(nesto);
  console.log('Datum je ' + datum);
}
}
