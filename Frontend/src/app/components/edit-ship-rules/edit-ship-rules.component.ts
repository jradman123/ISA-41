import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { RuleService } from 'src/app/services/RuleService/rule.service';
import Swal from 'sweetalert2';

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
  haveReservations!: CottageReservation[]

  constructor(private ruleService: RuleService, private reservationService: ReservationService, private router: ActivatedRoute, private route: Router) {
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

  addRule() {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The rule cannot be changed because ship has a reservation!',
      })

    }
    else {

      this.rulee.shipId = this.idShip;

      console.log(this.rulee)
      this.ruleService.addRulebyShip(this.rulee).subscribe((data) => {
        this.rules = []
        this.findRules();


      });
    }

  }
  deleteRule(id: any) {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The rule cannot be deleted because ship has a reservation!',
      })

    }
    else {

      this.ruleService.deleteRulebyShip(id, this.idShip)
        .subscribe(data => {
          this.rules = []
          this.findRules();





        });

    }

  }
}
