import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { RequestForDeletingAccountDto } from 'src/app/interfaces/request-for-deleting-account-dto';
import { RequestForDeletingAccountServiceService } from 'src/app/services/RequestForDeletingAccountService/request-for-deleting-account-service.service';
import { DialogForResponseDeletingComponent } from '../dialog-for-response-deleting/dialog-for-response-deleting.component';

export interface DataForDialog {
  reason: string;
  email: string;
  approve : boolean;
}
@Component({
  selector: 'app-requests-for-deleting-account',
  templateUrl: './requests-for-deleting-account.component.html',
  styleUrls: ['./requests-for-deleting-account.component.css']
})
export class RequestsForDeletingAccountComponent implements OnInit {

  requests!: MatTableDataSource<RequestForDeletingAccountDto>;
  sub!: Subscription;
  columnsToDisplayRequestsForDeletingAccount: string[] = [
    'No.',
    'FirstName',
    'LastName',
    'Email',
    'PhoneNumber',
    'UserType',
    'Reason',
    'Buttons'
  ];

  reason!: string;
  email!: string;
  approve! : boolean;

  constructor(private requestService : RequestForDeletingAccountServiceService,private changeDetectorRefs: ChangeDetectorRef,public dialog: MatDialog) {
    this.requests = new MatTableDataSource<RequestForDeletingAccountDto>();
  
   }

  
  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.sub = this.requestService
      .getAll()
      .subscribe({
        next: (requestsReg: RequestForDeletingAccountDto[]) => {
          this.requests.data = requestsReg;
          this.changeDetectorRefs.detectChanges();
        },
      });
    }

    approveReq(email : string) {
      this.approve = true;
      this.openDialog(email);
    }

    rejectReq(email : string){
      this.approve = false;
      this.openDialog(email);
    }

    openDialog(email : string): void {
      const dialogRef = this.dialog.open(DialogForResponseDeletingComponent, {
        width: '250px',
        data: {reason: this.reason, email: email,approve : this.approve},
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.reason = result;
        this.refresh();
      });
    }

}
