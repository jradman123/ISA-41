import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';

@Component({
  selector: 'app-edit-rules',
  templateUrl: './edit-rules.component.html',
  styleUrls: ['./edit-rules.component.css']
})
export class EditRulesComponent implements OnInit {

  rule:any;
  idCottage:any;
  rules:RuleDto[]=[];

  constructor(private ruleService:RuleService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.ruleService.findRulebyId(this.idCottage).subscribe((data) => {
      this.rules = data;
     
    });
  }

  addRule() { 
  
  }
  deleteRule(id:any) {

    this.ruleService.deleteRule(id,this.idCottage)
    .subscribe(data => {
      window.location.reload();
      
  
  
     

    });
  


  }

}
