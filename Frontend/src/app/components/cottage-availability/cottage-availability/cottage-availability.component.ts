import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { CottageAvailability } from 'src/app/interfaces/cottage-availability';
import { AvailabilityService } from 'src/app/services/availabilityService/availability.service';

@Component({
  selector: 'app-cottage-availability',
  templateUrl: './cottage-availability.component.html',
  styleUrls: ['./cottage-availability.component.css']
})
export class CottageAvailabilityComponent implements OnInit {

  id: any;
  pipe = new DatePipe('en-US');
  availabilities: CottageAvailability[] = [];
  constructor(private availabilityService: AvailabilityService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    console.log("dsdsfds" + this.id)

    this.availabilityService.findAvailabilityByCottage(this.id).subscribe((data) => {
      this.availabilities = data;

    });

  }

}
