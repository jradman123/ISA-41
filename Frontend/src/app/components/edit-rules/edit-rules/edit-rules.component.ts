import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';

@Component({
  selector: 'app-edit-rules',
  templateUrl: './edit-rules.component.html',
  styleUrls: ['./edit-rules.component.css']
})
export class EditRulesComponent implements OnInit {

  rule!: RuleDto;
  idCottage: any;
  rules: RuleDto[] = [];
  id: any;
  rulee!: RuleDto

  constructor(private ruleService: RuleService, private router: ActivatedRoute, private route: Router) {
    this.rulee = {} as RuleDto;
  }

  ngOnInit(): void {


    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.ruleService.findRulebyId(this.idCottage).subscribe((data) => {
      this.rules = data;

    });
  }

  addRule() {

    this.rulee.cottageId = this.idCottage;

    console.log(this.rulee)
    this.ruleService.addRule(this.rulee).subscribe((data) => {
      window.location.reload();


    });


  }
  deleteRule(id: any) {

    this.ruleService.deleteRule(id, this.idCottage)
      .subscribe(data => {
        window.location.reload();





      });



  }

}
