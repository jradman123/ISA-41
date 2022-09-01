import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  getAllForInstructor(): Observable<number[]> {
    return this.http.get<number[]>(
      `${this.apiServerUrl}/statistics/statistic-per-days`);
  }
}
