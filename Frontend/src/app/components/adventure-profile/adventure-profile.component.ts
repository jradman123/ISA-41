import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute} from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { EditCottageComponent } from '../edit-cottage/edit-cottage/edit-cottage.component';

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

  constructor(private adventureService : AdventureService,private router: ActivatedRoute,private _sanitizer: DomSanitizer,) { 
    this.adventure = {} as AdventureDto;
    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];
    this.adventureForUpdate = {} as AdventureDto;
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

}
