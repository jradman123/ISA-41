import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { RuleService } from 'src/app/services/RuleService/rule.service';

@Component({
  selector: 'app-b-rules',
  templateUrl: './b-rules.component.html',
  styleUrls: ['./b-rules.component.css']
})
export class BRulesComponent implements OnInit {

  rule!: RuleDto;
  idShip: any;
  rules: RuleDto[] = [];
  id: any;
  rulee!: RuleDto
  haveReservations!: CottageReservation[]

  constructor(private ruleService: RuleService, private reservationService: ReservationService, 
    private router: ActivatedRoute, private route: Router) {
    this.rulee = {} as RuleDto;
  }

  ngOnInit(): void {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.reservationService.getShipReservationById(this.idShip).subscribe((data) => {
      this.haveReservations = data;

    });


    this.findRules();
  }
  findRules() {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.ruleService.findShipRulebyId(this.idShip).subscribe((data) => {
      this.rules = data;

    });
  }


}
