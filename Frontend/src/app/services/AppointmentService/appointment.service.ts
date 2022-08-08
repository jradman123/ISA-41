import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }



  findAppByCottage(id: string): Observable<AppointmentDto[]> {
    return this.http.get<AppointmentDto[]>(
      `${this.apiServerUrl}/appointments/getAppByCottage/${id}`);


  }

  deleteApp(id: any) {
    return this.http.delete<AppointmentDto>(
      `${this.apiServerUrl}/appointments/deleteApp/${id}`);
  }
}
