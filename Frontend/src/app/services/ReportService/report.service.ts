import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HaveReportDto } from 'src/app/interfaces/have-report';
import { ReportDto } from 'src/app/interfaces/report-dto';
import { ReportResponse } from 'src/app/interfaces/report-response';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ReportService {


  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  addReport(newReport: ReportDto) {
    console.log("Prvi" + newReport.appeared)
    console.log("Drugi" + newReport.sanctioned)
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

  getAllUnseen() : Observable<ReportResponse[]> {
    return this.http.get<ReportResponse[]>(
      `${this.apiServerUrl}/reports/unseen`);

  }

  approveReport(id : string) : Observable<ReportResponse[]> {
    return this.http.get<ReportResponse[]>(`${this.apiServerUrl}/reports/approve/${id}`);
  }

  rejectReport(id : string) :  Observable<ReportResponse[]> {
    return this.http.get<ReportResponse[]>(`${this.apiServerUrl}/reports/reject/${id}`);
  }



}
