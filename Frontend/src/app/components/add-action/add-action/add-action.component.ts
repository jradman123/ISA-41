import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { CottageService } from 'src/app/services/CottageService/cottage.service';


@Component({
  selector: 'app-add-action',
  templateUrl: './add-action.component.html',
  styleUrls: ['./add-action.component.css']
})
export class AddActionComponent implements OnInit {

  appointments: AppointmentDto[] = [];
  id: any;
  idCottage: any;
  cottage!: CottageDto
  constructor(private appointmentService: AppointmentService, private router: ActivatedRoute, private route: Router, private cottageService: CottageService) { }

  ngOnInit(): void {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.appointmentService.findAppByCottage(this.idCottage).subscribe((data) => {
      this.appointments = data;

    });
    this.cottageService.findbyId(this.idCottage).subscribe((data) => {
      this.cottage = data;
    });
  }

  clickHome() {
    this.route.navigate(['cottageOwner/cottage-profile/' + this.idCottage]);


  }

}
