import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { ImageService } from 'src/app/services/ImageService/image.service';

@Component({
  selector: 'app-all-cottages-list',
  templateUrl: './all-cottages-list.component.html',
  styleUrls: ['./all-cottages-list.component.css']
})
export class AllCottagesListComponent implements OnInit {

  cottages: CottageDto[] = [];
  constructor(private cottageService: CottageService, private route: ActivatedRoute, private imageService: ImageService, private router: Router) { }


  ngOnInit(): void {
    this.cottageService.findAll().subscribe((data) => {
      this.cottages = data;

    });
  }

}
