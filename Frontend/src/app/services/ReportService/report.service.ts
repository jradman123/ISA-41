import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HaveReportDto } from 'src/app/interfaces/have-report';
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

  haveReport(id: string): Observable<any> {
    return this.http.get<HaveReportDto>(`${this.apiServerUrl}/reports/haveReport/` + id);
  }

  getReportByReservationId(id: string) {
    return this.http.get<ReportDto>(
      `${this.apiServerUrl}/reports/findReportByResId/${id}`);

  }


}
