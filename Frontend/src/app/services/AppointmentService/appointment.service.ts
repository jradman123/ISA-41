import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { CottageQuickReservationDto } from 'src/app/interfaces/cottage-quick-reservation';
import { CottageQuickReservationResponse } from 'src/app/interfaces/cottage-quick-reservation-response';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }



  findAppByCottage(id: string): Observable<CottageQuickReservationResponse[]> {
    return this.http.get<CottageQuickReservationResponse[]>(
      `${this.apiServerUrl}/cottage-quick-reservation/getAppByCottage/${id}`);


  }

  findAppByShip(id: string): Observable<AppointmentDto[]> {
    return this.http.get<AppointmentDto[]>(
      `${this.apiServerUrl}/appointments/getAppByShip/${id}`);


  }

  deleteApp(id: any) {
    return this.http.delete<CottageQuickReservationDto>(
      `${this.apiServerUrl}/cottage-quick-reservation/createApp/deleteApp/${id}`);
  }

  createApp(newAppointment: CottageQuickReservationDto) {
    console.log("ooop" + newAppointment)
    return this.http.post(`${this.apiServerUrl}/cottage-quick-reservation/createApp`, newAppointment, {
      responseType: 'text',
    });

  }
}
