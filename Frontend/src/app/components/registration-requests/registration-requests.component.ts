import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit, ɵɵqueryRefresh } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { RegistrationRequestViewDto } from 'src/app/interfaces/registration-request-view-dto';
import { RegRequestsService } from 'src/app/services/RegistrationRequestsService/reg-requests.service';
import { DialogForReasonComponent } from '../dialog-for-reason/dialog-for-reason.component';

export interface DialogData {
  reason: string;
  email: string;
}

@Component({
  selector: 'app-registration-requests',
  templateUrl: './registration-requests.component.html',
  styleUrls: ['./registration-requests.component.css']
})
export class RegistrationRequestsComponent implements OnInit {

  requests!: MatTableDataSource<RegistrationRequestViewDto>;
  sub!: Subscription;
  columnsToDisplayRegistrationRequests: string[] = [
    'No.',
    'FirstName',
    'LastName',
    'Email',
    'PhoneNumber',
    'StreetName',
    'StreetNumber',
    'City',
    'Country',
    'TypeOfRegistration',
    'DescriptionOfRegistration',
    'Buttons'
  ];

  reason!: string;
  email!: string;

  constructor(private requestService : RegRequestsService,private changeDetectorRefs: ChangeDetectorRef,public dialog: MatDialog) {
    this.requests = new MatTableDataSource<RegistrationRequestViewDto>();
  
   }

  
  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.sub = this.requestService
      .getAll()
      .subscribe({
        next: (requestsReg: RegistrationRequestViewDto[]) => {
          this.requests.data = requestsReg;
          this.changeDetectorRefs.detectChanges();
        },
      });
    }

    approveRequest(email: string) {
      this.sub = this.requestService
      .approveRequest(email)
      .subscribe({
        next: (requestsReg: RegistrationRequestViewDto[]) => {
          this.requests.data = requestsReg;
          this.changeDetectorRefs.detectChanges();
        }});

    }

    openDialog(email : string): void {
      const dialogRef = this.dialog.open(DialogForReasonComponent, {
        width: '250px',
        data: {reason: this.reason, email: email},
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.reason = result;
        this.refresh();
      });
    }
  }


