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

  getNumberPerYear(id: string) {
    return this.http.get(`${this.apiServerUrl}/cottage-statistics/per-years/${id}`);
  }


  getNumberPerMonth(id: string) {
    return this.http.get(`${this.apiServerUrl}/cottage-statistics/per-months/${id}`);
  }

  getNumberPerYearShip(id: string) {
    return this.http.get(`${this.apiServerUrl}/ship-statistics/per-year/${id}`);
  }
  getNumberPerDaysShip(id: string) {
    return this.http.get(`${this.apiServerUrl}/ship-statistics/per-days/${id}`);
  }
  getNumberPerMonthShip(id: string) {
    return this.http.get(`${this.apiServerUrl}/ship-statistics/per-months/${id}`);
  }

}
