import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private apiServerUrl = environment.apiBaseUrl

  constructor(private http: HttpClient) { }

  getNumberPerDays(id: string) {
    return this.http.get(`${this.apiServerUrl}/cottage-statistics/per-days/${id}`);
  }


  getNumberPerMonth(id: string) {
    return this.http.get(`${this.apiServerUrl}/cottage-statistics/per-months/${id}`);
  }

}
