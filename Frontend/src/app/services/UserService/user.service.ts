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
  constructor(private http: HttpClient, private router : Router) { }

  getPersonalData(): Observable<PersonalData> {
    return this.http.get<PersonalData>(
      `${this.apiServerUrl}/users/getPersonalData`
    );
  }

  updatePersonalData( data : PersonalData): Observable<PersonalData> {
    return this.http.put<PersonalData>(
      `${this.apiServerUrl}/users/updatePersonalData`, data
    );
  }
}
