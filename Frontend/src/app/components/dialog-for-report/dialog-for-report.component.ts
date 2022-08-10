import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ReportDto } from 'src/app/interfaces/report-dto';
import { ReportService } from 'src/app/services/ReportService/report.service';
import { DataForReport } from '../cottage-profile/cottage-profile/cottage-profile.component';

@Component({
  selector: 'app-dialog-for-report',
  templateUrl: './dialog-for-report.component.html',
  styleUrls: ['./dialog-for-report.component.css']
})
export class DialogForReportComponent implements OnInit {


  reportData!: ReportDto;
  detailForm!: FormGroup
  sub!: Subscription;
  editMode = false

  constructor(private reportService: ReportService, private route: Router, @Inject(MAT_DIALOG_DATA) public data: DataForReport, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReportComponent>) { }

  ngOnInit(): void {
    this.reportService.getReportByReservationId(this.data.idReservation).subscribe((data) => {
      this.reportData = data;
      this.detailsForm.controls['comment'].setValue(data.comment)
      this.detailsForm.controls['appeared'].setValue(data.appeared)
      this.detailsForm.controls['sanctioned'].setValue(data.sanctioned)
    });
  }

  detailsForm = new FormGroup({
    comment: new FormControl('', [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    sanctioned: new FormControl('', [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    appeared: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),

  })

  onNoClick(): void {
    this.dialogRef.close();
  }
}
