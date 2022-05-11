import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit, ɵɵqueryRefresh } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { RegistrationRequestViewDto } from 'src/app/interfaces/registration-request-view-dto';
import { RegRequestsService } from 'src/app/services/RegistrationRequestsService/reg-requests.service';

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

  constructor(private requestService : RegRequestsService,private changeDetectorRefs: ChangeDetectorRef,private _snackBar: MatSnackBar) {
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

  

}
