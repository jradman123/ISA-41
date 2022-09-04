import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ComplaintDto } from 'src/app/interfaces/complaint-dto';
import { ComplaintService } from 'src/app/services/ComplaintService/complaint.service';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

  complaints : ComplaintDto[];
  sub!: Subscription;
  constructor(private complaintsService : ComplaintService) {
    this.complaints = [] as ComplaintDto[];
   }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.sub = this.complaintsService
      .getAllUnanswered()
      .subscribe({
        next: (rep: ComplaintDto[]) => {
          this.complaints = rep;
        },
      });
    }

    approve(id : string) {

    }

    reject(id : string){
      
    }

}
