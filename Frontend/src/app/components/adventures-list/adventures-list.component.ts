import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-adventures-list',
  templateUrl: './adventures-list.component.html',
  styleUrls: ['./adventures-list.component.css']
})
export class AdventuresListComponent implements OnInit {

  adventuresDto : AdventureDto[];
  constructor(private adventureService : AdventureService,private router : Router) {
    this.adventuresDto = [] as AdventureDto[];
   }

  ngOnInit(): void {
    this.adventureService.getAllForInstructor().subscribe((data) => {
      this.adventuresDto = data;
    });
  }

  view(id: string) {
    this.router.navigate(['instructor/adventure-profile/' + id]);
    console.log(id);
  }
  }
