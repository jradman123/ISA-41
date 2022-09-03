import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { ReportResponse } from 'src/app/interfaces/report-response';
import { ReportService } from 'src/app/services/ReportService/report.service';

@Component({
  selector: 'app-admin-report',
  templateUrl: './admin-report.component.html',
  styleUrls: ['./admin-report.component.css']
})
export class AdminReportComponent implements OnInit {

  reports!: MatTableDataSource<ReportResponse>;
  sub!: Subscription;
  columnsToDisplayReports: string[] = [
    'No.',
    'Date',
    'Comment',
    'Should be sanctioned?',
    'Did guest appeared?',
    'Buttons'
  ];
  constructor(private reportService : ReportService,private changeDetectorRefs: ChangeDetectorRef) { 
    this.reports = new MatTableDataSource<ReportResponse>();
  }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.sub = this.reportService
      .getAllUnseen()
      .subscribe({
        next: (rep: ReportResponse[]) => {
          this.reports.data = rep;
          this.changeDetectorRefs.detectChanges();
        },
      });
    }

    approve(id : string){
      this.sub = this.reportService
      .approveReport(id)
      .subscribe({
        next: () => {
          this.reports.data = [];
          this.refresh();
        }});
    }

    reject(id : string){
      this.sub = this.reportService
      .rejectReport(id)
      .subscribe({
        next: () => {
          this.reports.data = [];
          this.refresh();
        }});
    }
}
