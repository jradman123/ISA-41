import { Component, Input, OnInit } from '@angular/core';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { StringLiteralLike } from 'typescript';
@Component({
  selector: 'app-cottage-list',
  templateUrl: './cottage-list.component.html',
  styleUrls: ['./cottage-list.component.css']
})
export class CottageListComponent implements OnInit {

  @Input() cottageOwner: any;
  cottages: CottageDto[] = [];
  cottagesAll: CottageDto[] = [];
  email!: string | null;
  constructor(private cottageService:CottageService) { }
 

  ngOnInit(): void {
    this.email = localStorage.getItem('email'); 
    console.log(this.email);
    this.cottageService.findByEmail().subscribe((data) => {
      this.cottages = data;
      this.cottagesAll = data;
    });
  }

}
