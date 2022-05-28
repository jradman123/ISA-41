import { Component, OnInit } from '@angular/core';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ActivatedRoute, Router } from '@angular/router';
import { AsyncKeyword } from 'typescript';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { NavigationService } from 'src/app/services/NavigationService/navigation.service';
import { NavigationDto } from 'src/app/interfaces/navigation-dto';

@Component({
  selector: 'app-ship-profile',
  templateUrl: './ship-profile.component.html',
  styleUrls: ['./ship-profile.component.css']

})
export class ShipProfileComponent implements OnInit {

  ship!: ShipDto;
  id: any;
  rules: RuleDto[] = [];
  utilities: UtilityDto[] = [];
  navigations: NavigationDto[] = [];

  constructor(private shipService: ShipService, private navigationService: NavigationService, private router: ActivatedRoute, private ruleService: RuleService, private utilityService: UtilityService, private route: Router) {
  }
  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.shipService.findbyId(this.id).subscribe((data) => {
      this.ship = data;
    });

    this.ruleService.findShipRulebyId(this.id).subscribe((data) => {
      this.rules = data;

    });

    this.utilityService.findShipUtilityById(this.id).subscribe((data) => {
      this.utilities = data;

    });
    this.navigationService.findNavigationById(this.id).subscribe((data) => {
      this.navigations = data;

    });
  }

  editShip() {
    this.route.navigate(['shipOwner/edit-ship/' + this.id]);
  }

  deleteShip() {
    this.shipService.deleteShip(this.id)
      .subscribe(data => {


        window.location.reload();
      });
  }
  editRules() {
    this.route.navigate(['shipOwner/edit-rules/' + this.id]);
  }

  editUtilities() {
    this.route.navigate(['shipOwner/edit-utilities/' + this.id]);

  }

}
