import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { DetailsAboutReservation } from 'src/app/interfaces/details-about-reservation';
import { ReportResponse } from 'src/app/interfaces/report-response';
import { ReportService } from 'src/app/services/ReportService/report.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { DialogReservationViewComponent } from '../dialog-reservation-view/dialog-reservation-view.component';

@Component({
  selector: 'app-admin-report',
  templateUrl: './admin-report.component.html',
  styleUrls: ['./admin-report.component.css']
})
export class AdminReportComponent implements OnInit {

  reports!: ReportResponse[];
  sub!: Subscription;
  columnsToDisplayReports: string[] = [
    'No.',
    'Date',
    'Comment',
    'Should be sanctioned?',
    'Did guest appeared?',
    'Buttons'
  ];
  details : DetailsAboutReservation;
  constructor(private reportService : ReportService,private changeDetectorRefs: ChangeDetectorRef,
    public dialog: MatDialog,private reservationService : ReservationService) { 
    this.reports = [] as ReportResponse[];
    this.details = {} as DetailsAboutReservation;
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.sub = this.reportService
      .getAllUnseen()
      .subscribe({
        next: (rep: ReportResponse[]) => {
          this.reports = rep;
          this.changeDetectorRefs.detectChanges();
        },
      });
    }

    approve(id : string){
      this.sub = this.reportService
      .approveReport(id)
      .subscribe((data) => {
          this.reports = [];
          this.reports=data;
        });
    }

    reject(id : string){
      this.sub = this.reportService
      .rejectReport(id)
      .subscribe((data) => {
        this.reports = [];
        this.reports=data;
      });
    }

    detailsAbouReservation(id : string): void {
      this.reservationService
      .getDetails(id)
      .subscribe((data)=>{
        this.details = data;
        const dialogRef = this.dialog.open(DialogReservationViewComponent, {
          width: '500px',
          data: this.details,
        });
      });
      
  
    }
}
