import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AdventureQuickReservationDto } from 'src/app/interfaces/adventure-quick-reservation-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdventureQuickReservationService {

  private apiServerUrl = environment.apiBaseUrl;
  
  constructor(private http: HttpClient) { }

  addAdventaddAdventureQuickReservation(adventureQuickReservation: AdventureQuickReservationDto) {
    return this.http.post(`${this.apiServerUrl}/adventure-quick-reservation`, adventureQuickReservation);
  }
}
