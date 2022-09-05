import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PointsService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router) { }

}
