import { Component, Input, OnInit } from '@angular/core';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ImageService } from 'src/app/services/ImageService/image.service';
import { ImageDto } from 'src/app/interfaces/image-dto';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-cottage-list',
  templateUrl: './cottage-list.component.html',
  styleUrls: ['./cottage-list.component.css']
})
export class CottageListComponent implements OnInit {

  searchText = ''

  cottages!: CottageDto[];
  images: ImageDto[] = [];
  id: any;
  image!: ImageDto;
  latitude = 45.2889;
  longitude = 19.7245;

  constructor(private cottageService: CottageService, private route: ActivatedRoute, private imageService: ImageService, private router: Router) { }


  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.findCottages();



  }

  findCottages() {
    this.cottageService.findByEmail().subscribe((data) => {
      this.cottages = data;

    });

  }

  handleMe(searchText: string) {
    this.searchText = searchText;
  }

  statistics(id: string) {
    this.router.navigate(['cottageOwner/cottage-statistics/' + id]);
    console.log(id);
  }

  view(id: string) {
    this.router.navigate(['cottageOwner/cottage-profile/' + id]);
    console.log(id);
  }
  delete(id: string) {
    this.cottageService.deleteCottage(id)
      .subscribe(response => {


        if (response == null) {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'The cottage cannot be deleted because it has a reservation!',
          })

        } else {
          this.cottages = []
          this.findCottages();
        }
      },

      );

  }

  emitMe(searchText: any) {
    this.searchText = searchText.target.value
  }

}
