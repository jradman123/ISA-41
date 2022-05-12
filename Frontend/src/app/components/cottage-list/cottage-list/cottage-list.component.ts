import { Component, OnInit } from '@angular/core';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
@Component({
  selector: 'app-cottage-list',
  templateUrl: './cottage-list.component.html',
  styleUrls: ['./cottage-list.component.css']
})
export class CottageListComponent implements OnInit {

  cottages:any=[];
  constructor(private cottageService:CottageService) { }
 

  ngOnInit(): void {
    this.cottageService.findAll().subscribe((cottages:any)=>this.cottages=cottages);
  }

}
