import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { InstructorPersonalData } from 'src/app/interfaces/instructor-personal-data';
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
      `${this.apiServerUrl}/users/personal-data`
    );
  }

  updatePersonalData( data : PersonalData): Observable<PersonalData> {
    return this.http.put<PersonalData>(
      `${this.apiServerUrl}/users/update-personal-data`, data
    );
  }

  changePassword(data: any) {
    return this.http.put(`${this.apiServerUrl}/users/change-password`, data)
  }

  isFirstLogin() {
    return this.http.get(`${this.apiServerUrl}/users/is-first-login`, {
      responseType : 'text'
    });
  }

  getInstructorPersonalData(): Observable<InstructorPersonalData> {
    return this.http.get<InstructorPersonalData>(
      `${this.apiServerUrl}/users/instructor-personal-data`
    );
  }

  updateInstructorPersonalData( data : PersonalData): Observable<InstructorPersonalData> {
    return this.http.put<InstructorPersonalData>(
      `${this.apiServerUrl}/users/update-instructor-personal-data`, data
    );
  }


}
