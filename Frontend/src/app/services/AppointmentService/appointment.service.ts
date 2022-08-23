import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { CottageQuickReservationDto } from 'src/app/interfaces/cottage-quick-reservation';
import { CottageQuickReservationResponse } from 'src/app/interfaces/cottage-quick-reservation-response';
import { ShipQuickReservationResponse } from 'src/app/interfaces/ship-quick-reservation-response';
import { ShipQuickReservationDto } from 'src/app/interfaces/ship-quick-reservation';

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

  findAppByShip(id: string): Observable<ShipQuickReservationResponse[]> {
    return this.http.get<ShipQuickReservationResponse[]>(
      `${this.apiServerUrl}/ship-quick-reservation/getAppByShip/${id}`);


  }

  deleteApp(id: any) {
    return this.http.delete<CottageQuickReservationDto>(
      `${this.apiServerUrl}/cottage-quick-reservation/deleteApp/${id}`);
  }

  deleteAppbyShip(id: any) {
    return this.http.delete<ShipQuickReservationDto>(
      `${this.apiServerUrl}/ship-quick-reservation/deleteApp/${id}`);
  }

  createApp(newAppointment: CottageQuickReservationDto) {
    console.log("ooop" + newAppointment)
    return this.http.post(`${this.apiServerUrl}/cottage-quick-reservation/createApp`, newAppointment, {
      responseType: 'text',
    });

  }

  createAppbyShip(newAppointment: ShipQuickReservationDto) {
    console.log("ooop" + newAppointment)
    return this.http.post(`${this.apiServerUrl}/ship-quick-reservation/createApp`, newAppointment, {
      responseType: 'text',
    });

  }
}
