import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthenticatedUserDto } from 'src/app/interfaces/authenticated-user-dto';
import { RegistrationRequest } from 'src/app/interfaces/registration-request';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = environment.apiBaseUrl;

  private currentUserSubject: BehaviorSubject<AuthenticatedUserDto>;
  public currentUser: Observable<AuthenticatedUserDto>;
  private user! : AuthenticatedUserDto;
  private loginStatus = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router : Router) {
    this.currentUserSubject = new BehaviorSubject<AuthenticatedUserDto>(
      JSON.parse(localStorage.getItem('currentUser')!)
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): AuthenticatedUserDto {
      return this.currentUserSubject.value;
  }

public getUserValue() : AuthenticatedUserDto {
    console.log("Token" + this.currentUserSubject.value.token.accessToken);
    return this.currentUserSubject.value;
}

registerUser(newUser: RegistrationRequest) {
  return this.http.post(`${this.apiServerUrl}/auth/signup`, newUser, {
    responseType: 'text',
  });
}

login(model: any): Observable<AuthenticatedUserDto> {
  return this.http.post(`${this.apiServerUrl}/auth/login`, model).pipe(
    map((response: any) => {
      if (response && response.token) {
        this.loginStatus.next(true);
        localStorage.setItem('token', response.token.accessToken);
        localStorage.setItem('currentUser', JSON.stringify(response));
        localStorage.setItem('role' ,response.role)
        this.currentUserSubject.next(response);
      }
      return this.user;
    })
  );
}
}