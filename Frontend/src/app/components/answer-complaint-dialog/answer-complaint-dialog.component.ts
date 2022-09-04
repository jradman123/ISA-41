import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Answer } from 'src/app/interfaces/answer';
import { ComplaintService } from 'src/app/services/ComplaintService/complaint.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-answer-complaint-dialog',
  templateUrl: './answer-complaint-dialog.component.html',
  styleUrls: ['./answer-complaint-dialog.component.css']
})
export class AnswerComplaintDialogComponent implements OnInit {

  answer : Answer;
  constructor(public dialogRef: MatDialogRef<AnswerComplaintDialogComponent>,@Inject(MAT_DIALOG_DATA) public data: any,
    private complaintService : ComplaintService) { 
    this.answer = {} as Answer;
  }

  ngOnInit(): void {
  }

  answerOnComplaint(){
    if(this.answer.answer === undefined){
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Answer must be written!'
      });
      return;
    }
    this.complaintService
      .answerOnComplaint(this.answer,this.data.complaintId)
      .subscribe((data) => {
        this.onNoClick();
      });
        

  }

  onNoClick(){
    this.dialogRef.close();
  }

}
