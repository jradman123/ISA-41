import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReportDto } from 'src/app/interfaces/report-dto';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ReportService {


  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  addReport(newReport: ReportDto) {
    console.log("fsfdsf" + newReport.appeared)
    console.log("fsfdsf" + newReport.sanctioned)
    return this.http.post(`${this.apiServerUrl}/reports/createReport`, newReport, {
      responseType: 'text',
    });
  }
}
