import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RegistrationRequestViewDto } from 'src/app/interfaces/registration-request-view-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegRequestsService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router : Router) { }

  getAll(): Observable<RegistrationRequestViewDto[]> {
    return this.http.get<RegistrationRequestViewDto[]>(
      `${this.apiServerUrl}/requests/getAll`
    );
  }

  approveRequest(email : string): Observable<RegistrationRequestViewDto[]> {
    return this.http.put<RegistrationRequestViewDto[]>(
      `${this.apiServerUrl}/requests/approve/${email}`,{}
    );
  }
}
