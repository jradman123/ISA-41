import { Component, OnInit } from '@angular/core';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ActivatedRoute } from '@angular/router';
import { AsyncKeyword } from 'typescript';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';


@Component({
  selector: 'app-ship-profile',
  templateUrl: './ship-profile.component.html',
  styleUrls: ['./ship-profile.component.css']
})
export class ShipProfileComponent implements OnInit {

  ship!: ShipDto;
  id:any;
  rules:RuleDto[]=[];
  constructor(private shipService:ShipService,private router:ActivatedRoute,private  ruleService:RuleService) {
  }
  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.shipService.findbyId(this.id).subscribe((data) => {
      this.ship = data;});

      this.ruleService.findShipRulebyId(this.id).subscribe((data) => {
        this.rules = data;
       
      });
    
  }

}
