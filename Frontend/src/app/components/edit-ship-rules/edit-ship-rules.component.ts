import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';

@Component({
  selector: 'app-edit-ship-rules',
  templateUrl: './edit-ship-rules.component.html',
  styleUrls: ['./edit-ship-rules.component.css']
})
export class EditShipRulesComponent implements OnInit {

  rule!: RuleDto;
  idShip: any;
  rules: RuleDto[] = [];
  id: any;
  rulee!: RuleDto

  constructor(private ruleService: RuleService, private router: ActivatedRoute, private route: Router) {
    this.rulee = {} as RuleDto;
  }

  ngOnInit(): void {


    this.findRules();
  }
  findRules() {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.ruleService.findShipRulebyId(this.idShip).subscribe((data) => {
      this.rules = data;

    });
  }

  addRule() {

    this.rulee.shipId = this.idShip;

    console.log(this.rulee)
    this.ruleService.addRulebyShip(this.rulee).subscribe((data) => {
      this.rules = []
      this.findRules();


    });


  }
  deleteRule(id: any) {

    this.ruleService.deleteRulebyShip(id, this.idShip)
      .subscribe(data => {
        this.rules = []
        this.findRules();





      });



  }
}
