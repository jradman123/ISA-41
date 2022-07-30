import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.css']
})
export class AddReservationComponent implements OnInit {


  id: any;
  cottage!: CottageDto;
  startDate: Date = new Date();
  endDate: Date = new Date();
  constructor(private route: ActivatedRoute, private cottageService: CottageService) { }

  ngOnInit(): void {

    this.id = +this.route.snapshot.paramMap.get('id')!;

    this.cottageService.findbyId(this.id).subscribe((data) => {
      this.cottage = data;
    });

  }


}
