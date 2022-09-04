import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { ComplaintDto } from 'src/app/interfaces/complaint-dto';
import { ComplaintService } from 'src/app/services/ComplaintService/complaint.service';
import { AnswerComplaintDialogComponent } from '../answer-complaint-dialog/answer-complaint-dialog.component';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

  complaints : ComplaintDto[];
  sub!: Subscription;
  constructor(private complaintsService : ComplaintService,public dialog: MatDialog) {
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
          this.complaints = [];
          this.complaints = rep;
        },
      });
    }

    answer(id : string) {

      const dialogRef = this.dialog.open(AnswerComplaintDialogComponent, {
        width: '250px',
        data: {complaintId: id},
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.refresh();
      });
    }


}
