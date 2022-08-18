import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';

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

  addRule() {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The rule cannot be added because cottage has a reservation!',
      })

    }
    else {

      this.rulee.cottageId = this.idCottage;

      console.log(this.rulee)
      this.ruleService.addRule(this.rulee).subscribe((data) => {
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
        text: 'The rule cannot be deleted because cottage has a reservation!',
      })

    }
    else {
      this.ruleService.deleteRule(id, this.idCottage)
        .subscribe(data => {
          this.rules = []
          this.findRules();





        });
    }



  }

}
