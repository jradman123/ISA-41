import { Component, OnInit } from '@angular/core';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { ActivatedRoute } from '@angular/router';
import { AsyncKeyword } from 'typescript';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { Router } from '@angular/router';
import { RoomDto } from 'src/app/interfaces/room-dto';

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {



  cottage!: CottageDto;
  id: any;
  rules: RuleDto[] = [];
  utilities: UtilityDto[] = [];
  rooms: RoomDto[] = [];

  constructor(private route: Router, private cottageService: CottageService, private router: ActivatedRoute, private ruleService: RuleService, private utilityService: UtilityService) {

  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.findbyId(this.id).subscribe((data) => {
      this.cottage = data;
    });

    this.ruleService.findRulebyId(this.id).subscribe((data) => {
      this.rules = data;

    });

    this.utilityService.findUtilityById(this.id).subscribe((data) => {
      this.utilities = data;
      console.log(this.utilities);
    });

    this.cottageService.findRoomsById(this.id).subscribe((data) => {
      this.rooms = data;
      console.log(this.utilities);
    });



  }

  editCottage() {
    this.route.navigate(['cottageOwner/edit-cottage/' + this.id]);
  }

  deleteCottage() {
    this.cottageService.deleteCottage(this.id)
      .subscribe(data => {

        this.route.navigate(['cottageOwner']);

      });
  }
  editRules() {
    this.route.navigate(['cottageOwner/edit-rules/' + this.id]);
  }

  editUtilities() {
    this.route.navigate(['cottageOwner/edit-utilities/' + this.id]);

  }
}
