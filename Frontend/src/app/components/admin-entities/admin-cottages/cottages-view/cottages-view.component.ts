import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { ImageDto } from 'src/app/interfaces/image-dto';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { ImageService } from 'src/app/services/ImageService/image.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cottages-view',
  templateUrl: './cottages-view.component.html',
  styleUrls: ['./cottages-view.component.css']
})
export class CottagesViewComponent implements OnInit {

  searchText = ''

  cottages!: CottageDto[];
  images: ImageDto[] = [];
  id: any;
  image!: ImageDto;

  constructor(private cottageService: CottageService, private route: ActivatedRoute, private imageService: ImageService, private router: Router) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.findCottages();

  }

  findCottages() {
    this.cottageService.findAllUndeleted().subscribe((data) => {
      this.cottages = data;

    });
  }

  view(id: string) {
    this.router.navigate(['admin/cottage-view/' + id]);
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
