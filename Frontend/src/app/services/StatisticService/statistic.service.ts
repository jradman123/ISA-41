import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  getAllForAdventure(id : string) {
    return this.http.get(`${this.apiServerUrl}/statistics/adventure/${id}/per-days`);
  }
}
