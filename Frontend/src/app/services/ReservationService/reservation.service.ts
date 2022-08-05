import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }


  reservatedCottage(newReservation: CottageReservation) {
    return this.http.post(`${this.apiServerUrl}/reservations/createReservation`, newReservation, {
      responseType: 'text',
    });
  }
}
