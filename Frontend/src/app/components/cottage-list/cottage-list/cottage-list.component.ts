import { Component, Input, OnInit } from '@angular/core';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ImageService } from 'src/app/services/ImageService/image.service';
import { ImageDto } from 'src/app/interfaces/image-dto';
@Component({
  selector: 'app-cottage-list',
  templateUrl: './cottage-list.component.html',
  styleUrls: ['./cottage-list.component.css']
})
export class CottageListComponent implements OnInit {


  cottages: CottageDto[] = [];
  images: ImageDto[] = [];
  id: any;
  image!: ImageDto;

  constructor(private cottageService: CottageService, private route: ActivatedRoute, private imageService: ImageService, private router: Router) { }


  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.cottageService.findByEmail().subscribe((data) => {
      this.cottages = data;

    });

    this.imageService.findImageByCottageId(this.id).subscribe((data) => {
      this.images = data;
      console.log(this.images);

    });

  }

  view(id: string) {
    this.router.navigate(['cottageOwner/cottage-profile/' + id]);
    console.log(id);
  }
  delete(id: string) {
    this.cottageService.deleteCottage(id)
      .subscribe(data => {

        window.location.reload();

      });

  }

}
