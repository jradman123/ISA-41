import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl
  constructor(private http: HttpClient, private router: Router) { }

  getPersonalData(): Observable<PersonalData> {
    return this.http.get<PersonalData>(
      `${this.apiServerUrl}/users/personal-data`
    );
  }

  updatePersonalData(data: PersonalData): Observable<PersonalData> {
    return this.http.put<PersonalData>(
      `${this.apiServerUrl}/users/update-personal-data`, data
    );
  }

  findAll(): Observable<PersonalData[]> {
    return this.http.get<PersonalData[]>(
      `${this.apiServerUrl}/users/all`);
  }

  changePassword(data: any) {
    return this.http.put(`${this.apiServerUrl}/users/change-password`, data)
  }

  isFirstLogin() {
    return this.http.get(`${this.apiServerUrl}/users/is-first-login`, {
      responseType: 'text'
    });
  }


}
