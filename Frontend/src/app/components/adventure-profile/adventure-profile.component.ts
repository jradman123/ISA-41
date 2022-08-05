import { Component, OnInit } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  id : any;
  adventure : AdventureDto;

  constructor(private adventureService : AdventureService,private router: ActivatedRoute) { 
    this.adventure = {} as AdventureDto;
  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.adventureService.findById(this.id).subscribe((data) => {
      this.adventure = data;
    });
  }

}
