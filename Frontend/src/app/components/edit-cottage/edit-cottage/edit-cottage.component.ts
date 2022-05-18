import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';

@Component({
  selector: 'app-edit-cottage',
  templateUrl: './edit-cottage.component.html',
  styleUrls: ['./edit-cottage.component.css']
})
export class EditCottageComponent implements OnInit {

  id:any;
  cottage!: CottageDto;
  rules:RuleDto[]=[];
  utilities:UtilityDto[]=[];
  constructor(private ruleService:RuleService,private utilityService:UtilityService,private route:Router,private router:ActivatedRoute,private cottageService:CottageService) { }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.findbyId(this.id).subscribe((data) => {
      this.cottage = data;});
      this.ruleService.findRulebyId(this.id).subscribe((data) => {
        this.rules = data;
        console.log(this.rules)
       
      });

      this.utilityService.findUtilityById(this.id).subscribe((data) => {
        this.utilities = data;
        
      });

  }


  submit() {
    this.cottageService.editCottage(this.cottage).subscribe((data) =>{
     
      this.route.navigate(['cottageOwner/cottage-profile/'+this.id]);
    });
  }
  cancel(){
    this.route.navigate(['cottageOwner/cottage-profile/'+this.id]);
  }

}
