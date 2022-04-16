import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationRequest } from 'src/app/interfaces/registration-request';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private _http: HttpClient) { }

  registerUser(newUser: RegistrationRequest) {
    return this._http.post(`${this.apiServerUrl}/auth/signup`, newUser, {
      responseType: 'text',
    });
  }
}
