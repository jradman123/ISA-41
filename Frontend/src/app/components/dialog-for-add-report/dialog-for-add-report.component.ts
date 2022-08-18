import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { ReportDto } from 'src/app/interfaces/report-dto';
import { ReportService } from 'src/app/services/ReportService/report.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { DataForReport } from '../cottage-profile/cottage-profile/cottage-profile.component';

@Component({
  selector: 'app-dialog-for-add-report',
  templateUrl: './dialog-for-add-report.component.html',
  styleUrls: ['./dialog-for-add-report.component.css']
})
export class DialogForAddReportComponent implements OnInit {


  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  newReport!: ReportDto;
  constructor(private reportService: ReportService, @Inject(MAT_DIALOG_DATA) public data: DataForReport, private reservationService: ReservationService, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForAddReportComponent>) {
    this.newReport = {} as ReportDto;
  }

  ngOnInit(): void {


    this.form = new FormGroup({
      comment: new FormControl('', Validators.required),
      sanctioned: new FormControl('', Validators.required),
      appeared: new FormControl('', Validators.required),
    })


  }

  addReport() {

    this.createReport();
    this.sub = this.reportService.addReport(this.newReport)
      .subscribe({
        next: () => {
          this.dialogRef.close();

        }
      });

  }

  close() {
    this.dialogRef.close();

  }

  createReport(): void {





    this.newReport.comment = this.form.value.comment;
    this.newReport.reservationId = this.data.idReservation;
    this.newReport.appeared = this.form.value.appeared;
    this.newReport.sanctioned = this.form.value.sanctioned;

  }

}
