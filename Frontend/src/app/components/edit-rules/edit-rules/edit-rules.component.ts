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
  id:any;
  rules:RuleDto[]=[];

  constructor(private ruleService:RuleService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.ruleService.findRulebyId(this.id).subscribe((data) => {
      this.rules = data;
     
    });
  }

  addRule() { 
  
  }
  deleteRule(id:any) {
    let index = 0;
    for (let rule of this.rules) {
      if (rule.id == id) {
        this.rules.splice(index, 1);
      }
      index++;
    }


  }
}
