import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestForDeletingAccountDto } from 'src/app/interfaces/request-for-deleting-account-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RequestForDeletingAccountServiceService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  getAll(): Observable<RequestForDeletingAccountDto[]> {
    return this.http.get<RequestForDeletingAccountDto[]>(
      `${this.apiServerUrl}/account`
    );
  }

  approveRequest(email : string,response : string): Observable<RequestForDeletingAccountDto[]> {
    return this.http.put<RequestForDeletingAccountDto[]>(
      `${this.apiServerUrl}/account/delete/${email}`,response
    );
  }

  rejectRequest(email : string,response : string): Observable<RequestForDeletingAccountDto[]> {
    return this.http.put<RequestForDeletingAccountDto[]>(
      `${this.apiServerUrl}/account/reject/${email}`,response
    );
  }
}
