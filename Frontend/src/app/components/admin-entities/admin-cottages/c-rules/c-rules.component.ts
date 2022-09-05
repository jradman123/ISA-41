import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { RuleService } from 'src/app/services/RuleService/rule.service';

@Component({
  selector: 'app-c-rules',
  templateUrl: './c-rules.component.html',
  styleUrls: ['./c-rules.component.css']
})
export class CRulesComponent implements OnInit {

  rule!: RuleDto;
  idCottage: any;
  rules: RuleDto[] = [];
  id: any;
  rulee!: RuleDto
  haveReservations!: CottageReservation[]
  
  constructor(private ruleService: RuleService, private reservationService: ReservationService, private router: ActivatedRoute, private route: Router) {
    this.rulee = {} as RuleDto;
  }

  ngOnInit(): void {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;


    this.reservationService.getCottageReservationById(this.idCottage).subscribe((data) => {
      this.haveReservations = data;

    });

    this.findRules();
  }
  findRules() {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.ruleService.findRulebyId(this.idCottage).subscribe((data) => {
      this.rules = data;

    });
  }





}
