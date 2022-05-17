import { Component, Input, OnInit } from '@angular/core';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import {  Router } from '@angular/router';
@Component({
  selector: 'app-cottage-list',
  templateUrl: './cottage-list.component.html',
  styleUrls: ['./cottage-list.component.css']
})
export class CottageListComponent implements OnInit {

  
  cottages: CottageDto[] = [];
 
  constructor(private cottageService:CottageService,  private router: Router) { }
 

  ngOnInit(): void {
    this.cottageService.findByEmail().subscribe((data) => {
      this.cottages = data;
   
    });
  }

  view(id:string){
    this.router.navigate(['cottageOwner/cottage-profile/'+id]);
    console.log(id);
  }

}
