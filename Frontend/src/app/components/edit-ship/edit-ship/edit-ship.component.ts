import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';


@Component({
  selector: 'app-edit-ship',
  templateUrl: './edit-ship.component.html',
  styleUrls: ['./edit-ship.component.css']
})
export class EditShipComponent implements OnInit {

  id: any;
  ship!: ShipDto;
  rules: RuleDto[] = [];
  utilities: UtilityDto[] = [];
  constructor(private ruleService: RuleService, private utilityService: UtilityService, private route: Router, private router: ActivatedRoute, private shipService: ShipService) { }


  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.shipService.findbyId(this.id).subscribe((data) => {
      this.ship = data;
    });
    this.ruleService.findRulebyId(this.id).subscribe((data) => {
      this.rules = data;
      console.log(this.rules)

    });

    this.utilityService.findUtilityById(this.id).subscribe((data) => {
      this.utilities = data;

    });
  }

  submit() {
    this.shipService.editShip(this.ship).subscribe((data) => {

      console.log(this.ship)
      this.route.navigate(['shipOwner/ship-profile/' + this.id]);
    });
  }
  cancel() {
    this.route.navigate(['shipOwner/ship-profile/' + this.id]);
  }



}
