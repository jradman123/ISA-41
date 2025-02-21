import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { RegistrationRequestViewDto } from 'src/app/interfaces/registration-request-view-dto';
import { RegRequestsService } from 'src/app/services/RegistrationRequestsService/reg-requests.service';
import { DialogData } from '../registration-requests/registration-requests.component';

@Component({
  selector: 'app-dialog-for-reason',
  templateUrl: './dialog-for-reason.component.html',
  styleUrls: ['./dialog-for-reason.component.css']
})
export class DialogForReasonComponent implements OnInit {

  requests!: RegistrationRequestViewDto[];
  sub!: Subscription;

  constructor(
    public dialogRef: MatDialogRef<DialogForReasonComponent>,private _snackBar : MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,private requestService : RegRequestsService
  ) {}

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  reject() {
    if(this.data.reason===undefined){
      this._snackBar.open(
        'You have to write reason!',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
    }
    console.log(this.data.reason)
    this.sub = this.requestService
    .rejectRequest(this.data.email,this.data.reason)
    .subscribe({
      next: (requestsReg: RegistrationRequestViewDto[]) => {
        this.requests = requestsReg;
        this.onNoClick();
      }});

  }

}
