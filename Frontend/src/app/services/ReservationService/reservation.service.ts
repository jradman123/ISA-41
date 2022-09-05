import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { AdventureReservation } from 'src/app/interfaces/adventure-reservation';

import { AppointmentDto } from 'src/app/interfaces/appointment-dto';

import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { DetailsAboutReservation } from 'src/app/interfaces/details-about-reservation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }


  reservatedCottage(newReservation: CottageReservation) {
    console.log(newReservation.price)
    return this.http.post(`${this.apiServerUrl}/reservations/createCottageReservation`, newReservation, {
      responseType: 'text',
    });
  }
  reservatedShip(newReservation: CottageReservation) {
    console.log(newReservation.price)
    return this.http.post(`${this.apiServerUrl}/reservations/createShipReservation`, newReservation, {
      responseType: 'text',
    });
  }

  getPastCottageReservationById(id: string): Observable<CottageReservation[]> {

    return this.http.get<CottageReservation[]>(

      `${this.apiServerUrl}/reservations/findPastReservationsByCottage/${id}`);
  }

  geQuickCottageReservationById(id: string): Observable<AppointmentDto[]> {

    return this.http.get<AppointmentDto[]>(

      `${this.apiServerUrl}/appointments/getAppByCottage/${id}`);
  }


  getCottageReservationById(id: string): Observable<CottageReservation[]> {
    return this.http.get<CottageReservation[]>(
      `${this.apiServerUrl}/reservations/findReservationsByCottage/${id}`);
  }

  getPastShipReservationById(id: string): Observable<CottageReservation[]> {

    return this.http.get<CottageReservation[]>(

      `${this.apiServerUrl}/reservations/findPastReservationsByShip/${id}`);
  }

  getShipReservationById(id: string): Observable<CottageReservation[]> {
    return this.http.get<CottageReservation[]>(
      `${this.apiServerUrl}/reservations/findReservationsByShip/${id}`);
  }

  getPastReservationsByAdventure(id: string): Observable<AdventureReservation[]> {
    return this.http.get<AdventureReservation[]>(
      `${this.apiServerUrl}/reservations/find-past-reservations-by-adventure/${id}`);
  }

  getCurrentReservationByAdventure(id: string): Observable<AdventureReservation[]> {
    return this.http.get<AdventureReservation[]>(
      `${this.apiServerUrl}/reservations/find-current-reservations-by-adventure/${id}`);
  }

  reserveAdventure(newReservation: AdventureReservation) {
    console.log(newReservation.price)
    return this.http.post(`${this.apiServerUrl}/reservations/createReservation`, newReservation, {
      responseType: 'text',
    });
  }

  getFutureReservationByAdventure(id: string): Observable<AdventureReservation[]> {
    return this.http.get<AdventureReservation[]>(
      `${this.apiServerUrl}/reservations/find-future-reservations-by-adventure/${id}`);
  }

  reservationsExistForAdventure(id: string): Observable<string> {
    return this.http.get<string>(
      `${this.apiServerUrl}/reservations/reservations-exist-for-adventure/${id}`);
  }

  getReservationsForInstructor(): Observable<AdventureReservation[]> {
    return this.http.get<AdventureReservation[]>(
      `${this.apiServerUrl}/reservations/all-instructors`);
  }

  getDetails(id: string): Observable<DetailsAboutReservation> {
    return this.http.get<DetailsAboutReservation>(
      `${this.apiServerUrl}/reservations/${id}/details`);
  }




}
