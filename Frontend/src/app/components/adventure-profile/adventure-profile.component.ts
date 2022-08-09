import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute} from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureRuleDto } from 'src/app/interfaces/adventure-rule-dto';
import { FishingEquipmentDto } from 'src/app/interfaces/fishing-equipment-dto';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { ResponseFishingEquipment } from 'src/app/interfaces/response-fishing-equipment';
import { ResponseRules } from 'src/app/interfaces/response-rules';
import { AdventureRuleService } from 'src/app/services/AdventureRuleService/adventure-rule.service';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { FishingEquipmentService } from 'src/app/services/FishingEquipmentService/fishing-equipment.service';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  id : any;
  adventure : AdventureDto;
  uploaded: boolean = false;
  fileToUpload!: File;
  image : Image;
  imagesResponse : ImagesResponse;
  images : Image[];
  adventureForUpdate : AdventureDto;
  initialDetails: any
  editMode = false
  newAdventureRule : AdventureRuleDto;
  rules : ResponseRules[];
  newFishingEquipment : FishingEquipmentDto;
  equipments : ResponseFishingEquipment[];

  constructor(private adventureService : AdventureService,private router: ActivatedRoute,private _sanitizer: DomSanitizer,
    private adventureRuleService : AdventureRuleService,private _snackBar : MatSnackBar,private fishingEquipmentService : FishingEquipmentService) { 
    this.adventure = {} as AdventureDto;
    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];
    this.adventureForUpdate = {} as AdventureDto;
    this.newAdventureRule = {} as AdventureRuleDto;
    this.rules = [] as ResponseRules[];
    this.newFishingEquipment = {} as FishingEquipmentDto;
    this.equipments = [] as ResponseFishingEquipment[];
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
      },
    });

    this.getImages();
    this.getRules();
    this.getEquipments();
  }

  getImages() {
    this.adventureService.getAdventuresImages(this.id).subscribe({
      next: (res) => {
        console.log(res);
        this.imagesResponse = res
        this.imagesResponse.images.forEach((image) => {
          this.images.push(image)
        })
    }
  });
  }

  getRules() {
    this.adventureService.getAdventuresRules(this.id).subscribe({
      next: (res) => {
        console.log(res);
        this.rules = res
    }
  });
  }

  getEquipments() {
    this.adventureService.getAdventureFishingEquipment(this.id).subscribe({
      next: (res) => {
        console.log(res);
        this.equipments = res
    }
  });
  }

  addRule() {
    if(this.ruleDescriptionForm.invalid){
      this._snackBar.open(
        'Rule cannot be empty.',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
      return;
    }
    this.newAdventureRule = {
      adventureId : this.id,
      ruleDescription: this.ruleDescriptionForm.get('description')?.value
    }
    this.adventureRuleService.addAdventureRule(this.newAdventureRule).subscribe((data) => {
      this.ruleDescriptionForm.controls['description'].setValue('');
      this.rules = [];
      this.getRules();
    });
  }

  deleteRule(ruleId: string) {

    this.adventureRuleService.deleteAdventureRule(ruleId)
      .subscribe((data) => {
        this.rules = [];
        this.getRules();
      });
  }

  addFishingEquipment() {
    if(this.fishingEquipmentForm.invalid){
      this._snackBar.open(
        'Name of fishing equipment cannot be empty.',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
      return;
    }
    this.newFishingEquipment = {
      adventureId : this.id,
      fishingEquipmentName: this.fishingEquipmentForm.get('name')?.value
    }
    this.fishingEquipmentService.addAdventureFishingEquipment(this.newFishingEquipment).subscribe((data) => {
      this.fishingEquipmentForm.controls['name'].setValue('');
      this.equipments = [];
      this.getEquipments();
    });
  }

  deleteFishingEquipment(equipmentId: string) {

    this.fishingEquipmentService.deleteFishingEquipment(equipmentId)
      .subscribe((data) => {
        this.equipments = [];
        this.getEquipments();
      });
  }

  toBase64 = (file: Blob) =>
    new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
    });

  async uploadPicture() {
    if (this.uploaded) {
      await this.toBase64(this.fileToUpload).then(
        (res) => (this.image.url = res as string)
      );
    }
  }

  onFileSelected(event: any): void {
    this.fileToUpload = <File>event.target.files[0];
    this.uploaded = true;
    this.uploadPicture().then((resultt) => {
      this.adventureService.addImage(this.id,this.image).subscribe((data) => {
        this.adventure = data;
        this.images = [];
        this.getImages();
      });
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
  guestLimit: new FormControl(null, [Validators.required,Validators.pattern('^\\d{1,3}$')])
})


edit() {
  if(this.detailsForm.invalid){
    return;
  }
  this.adventureForUpdate = {
    name: this.detailsForm.get('name')?.value,
    description: this.detailsForm.get('description')?.value,
    price: this.detailsForm.get('price')?.value,
    cancellationConditions: this.detailsForm.get('cancellationConditions')?.value,
    streetName: this.detailsForm.get('streetName')?.value,
    streetNumber: this.detailsForm.get('streetNumber')?.value,
    city: this.detailsForm.get('city')?.value,
    country: this.detailsForm.get('country')?.value,
    id : this.id,
    guestLimit : this.detailsForm.get('guestLimit')?.value
  }
  this.adventureService.updateAdventure(this.id,this.adventureForUpdate).subscribe((data) => {
    this.adventureForUpdate = data
    this.initialDetails = JSON.parse(JSON.stringify(data));
    this.editMode = false
  })
}

cancel() {
  this.editMode = false
  this.detailsForm.controls['name'].setValue(this.initialDetails.name)
  this.detailsForm.controls['description'].setValue(this.initialDetails.description)
  this.detailsForm.controls['cancellationConditions'].setValue(this.initialDetails.cancellationConditions)
  this.detailsForm.controls['price'].setValue(this.initialDetails.price)
  this.detailsForm.controls['streetName'].setValue(this.initialDetails.streetName)
  this.detailsForm.controls['streetNumber'].setValue(this.initialDetails.streetNumber)
  this.detailsForm.controls['city'].setValue(this.initialDetails.city)
  this.detailsForm.controls['country'].setValue(this.initialDetails.country)
  this.detailsForm.controls['guestLimit'].setValue(this.initialDetails.guestLimit)
}


ruleDescriptionForm = new FormGroup({ 
  description: new FormControl('', [
  Validators.required
])});

fishingEquipmentForm = new FormGroup({ 
  name: new FormControl('', [
  Validators.required
])});


}
