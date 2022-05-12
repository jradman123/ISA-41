import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
@Injectable({
  providedIn: 'root'
})
export class CottageService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router : Router) {}

    
    findByEmail(): Observable<CottageDto[]> {
      return this.http.get<CottageDto[]>(
        `${this.apiServerUrl}/cottages/findOwnerCottages/${ localStorage.getItem('email')}`);
      }

}
