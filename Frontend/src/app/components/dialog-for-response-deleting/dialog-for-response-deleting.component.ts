import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { RequestForDeletingAccountDto } from 'src/app/interfaces/request-for-deleting-account-dto';
import { RequestForDeletingAccountServiceService } from 'src/app/services/RequestForDeletingAccountService/request-for-deleting-account-service.service';
import { DataForDialog } from '../requests-for-deleting-account/requests-for-deleting-account.component';

@Component({
  selector: 'app-dialog-for-response-deleting',
  templateUrl: './dialog-for-response-deleting.component.html',
  styleUrls: ['./dialog-for-response-deleting.component.css']
})
export class DialogForResponseDeletingComponent implements OnInit {

  requests!: RequestForDeletingAccountDto[];
  sub!: Subscription;

  constructor( public dialogRef: MatDialogRef<DialogForResponseDeletingComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DataForDialog,private requestService : RequestForDeletingAccountServiceService) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  clicked() {
    console.log(this.data.approve);
    if(this.data.approve == false){
      this.rejectDeleting();
    }else{
      this.approveDeleting();
    }
  }

  rejectDeleting() {
    console.log(this.data.reason)
    this.sub = this.requestService
    .rejectRequest(this.data.email,this.data.reason)
    .subscribe({
      next: (requestsReg: RequestForDeletingAccountDto[]) => {
        this.requests = requestsReg;
        this.onNoClick();
      }});

  }

  approveDeleting() {
    console.log(this.data.reason)
    this.sub = this.requestService
    .approveRequest(this.data.email,this.data.reason)
    .subscribe({
      next: (requestsReg: RequestForDeletingAccountDto[]) => {
        this.requests = requestsReg;
        this.onNoClick();
      }});

  }

}
