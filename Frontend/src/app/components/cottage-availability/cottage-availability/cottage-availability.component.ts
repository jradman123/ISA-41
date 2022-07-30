import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageAvailability } from 'src/app/interfaces/cottage-availability';
import { AvailabilityService } from 'src/app/services/availabilityService/availability.service';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';

@Component({
  selector: 'app-cottage-availability',
  templateUrl: './cottage-availability.component.html',
  styleUrls: ['./cottage-availability.component.css']
})
export class CottageAvailabilityComponent implements OnInit {

  newAvailability!: CottageAvailability;
  id: any;
  cottage!: CottageDto;
  startDate: any = null;
  endDate: any = null;
  pipe = new DatePipe('en-US');
  availabilities: CottageAvailability[] = [];
  constructor(private availabilityService: AvailabilityService, private route: ActivatedRoute, private cottageService: CottageService, private router: Router) {

    this.newAvailability = {} as CottageAvailability;
  }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findbyId(this.id).subscribe((data) => {
      this.cottage = data;
    });

    this.availabilityService.findAvailabilityByCottage(this.id).subscribe((data) => {
      this.availabilities = data;

    });

  }

  clickHome() {
    this.router.navigate(['cottageOwner/cottage-profile/' + this.id]);


  }
  addAvailability() {

    let startDate = new Date(this.startDate);
    let endDate = new Date(this.endDate);
    this.newAvailability.cottageId = this.cottage.id;
    this.newAvailability.startDate = startDate;
    this.newAvailability.endDate = endDate;
    console.log("fedfefdfdf0" + this.newAvailability.startDate)

    this.availabilityService.addAvailabilityCottage(this.newAvailability).subscribe((data) => {

      console.log(this.cottage)
      this.router.navigate(['cottageOwner/cottage-availability/' + this.id]);
    });

  }
}
