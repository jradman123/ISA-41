import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AdventureRuleDto } from 'src/app/interfaces/adventure-rule-dto';
import { ResponseRules } from 'src/app/interfaces/response-rules';
import { AdventureRuleService } from 'src/app/services/AdventureRuleService/adventure-rule.service';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-adventure-rules',
  templateUrl: './adventure-rules.component.html',
  styleUrls: ['./adventure-rules.component.css']
})
export class AdventureRulesComponent implements OnInit {

  id : any;
  newAdventureRule : AdventureRuleDto;
  rules : ResponseRules[];
  constructor(private adventureService : AdventureService,private router: ActivatedRoute,
    private adventureRuleService : AdventureRuleService,private _snackBar : MatSnackBar) { 
    this.newAdventureRule = {} as AdventureRuleDto;
    this.rules = [] as ResponseRules[];
  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.getRules();
  }

  getRules() {
    this.adventureService.getAdventuresRules(this.id).subscribe({
      next: (res) => {
        this.rules = res
    }
  });
  }

  addRule() {
    if(this.ruleDescriptionForm.invalid){
      this._snackBar.open(
        'Rule cannot be empty.',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
      return;
    }
    this.newAdventureRule = {
      adventureId : this.id,
      ruleDescription: this.ruleDescriptionForm.get('description')?.value
    }
    this.adventureRuleService.addAdventureRule(this.newAdventureRule).subscribe((data) => {
      this.ruleDescriptionForm.reset();
      this.rules = [];
      this.getRules();
    });
  }

  deleteRule(ruleId: string) {

    this.adventureRuleService.deleteAdventureRule(ruleId)
      .subscribe((data) => {
        this.rules = [];
        this.getRules();
      });
  }

  ruleDescriptionForm = new FormGroup({ 
    description: new FormControl('', [
    Validators.required
  ])});

}
