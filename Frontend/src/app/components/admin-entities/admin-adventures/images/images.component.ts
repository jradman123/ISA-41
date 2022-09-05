import { Component, OnInit } from '@angular/core';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import {Image} from 'src/app/interfaces/image';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {

  id : any;
  image : Image;
  imagesResponse : ImagesResponse;
  images : Image[];
  constructor(private adventureService : AdventureService,private router: ActivatedRoute) { 
    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];
  }


  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
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

}
