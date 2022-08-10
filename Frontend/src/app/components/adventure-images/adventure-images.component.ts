import { Component, OnInit } from '@angular/core';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { Image } from 'src/app/interfaces/image';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-adventure-images',
  templateUrl: './adventure-images.component.html',
  styleUrls: ['./adventure-images.component.css']
})
export class AdventureImagesComponent implements OnInit {

  id : any;
  uploaded: boolean = false;
  fileToUpload!: File;
  image : Image;
  imagesResponse : ImagesResponse;
  images : Image[];

  constructor(private adventureService : AdventureService,private router: ActivatedRoute,private _sanitizer: DomSanitizer) { 
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
        this.images = [];
        this.getImages();
      });
    });
  }

}
