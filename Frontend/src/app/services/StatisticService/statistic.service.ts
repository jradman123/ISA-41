import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { DateRange } from 'src/app/interfaces/date-range';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  getAllForAdventurePerDays(id : string) {
    return this.http.get(`${this.apiServerUrl}/statistics/adventure/${id}/per-days`);
  }

  getAllForAdventurePerMonth(id : string) {
    return this.http.get(`${this.apiServerUrl}/statistics/adventure/${id}/per-month`);
  }

  getAllForAdventurePerYear(id : string) {
    return this.http.get(`${this.apiServerUrl}/statistics/adventure/${id}/per-year`);
  }

  getIncome(id: any,dateRange : DateRange) {
    return this.http.post(`${this.apiServerUrl}/statistics/adventure/${id}/income`,dateRange);
  }
}
