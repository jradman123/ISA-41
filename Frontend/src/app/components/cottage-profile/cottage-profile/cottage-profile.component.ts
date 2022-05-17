import { Component, OnInit } from '@angular/core';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { ActivatedRoute } from '@angular/router';
import { AsyncKeyword } from 'typescript';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {

 
  cottage!: CottageDto;
  id:any;
  rules:RuleDto[]=[];
  
  constructor(private cottageService:CottageService,private router:ActivatedRoute,private ruleService:RuleService) {
    
   }

  ngOnInit():void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.findbyId(this.id).subscribe((data) => {
      this.cottage = data;});
    
      this.ruleService.findRulebyId(this.id).subscribe((data) => {
        this.rules = data;
        console.log(this.rules);
      });



}
}
