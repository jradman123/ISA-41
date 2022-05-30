import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { CottageAvailability } from 'src/app/interfaces/cottage-availability';
@Injectable({
  providedIn: 'root'
})
export class AvailabilityService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }


  findAvailabilityByCottage(id: string) {
    return this.http.get<CottageAvailability[]>(
      `${this.apiServerUrl}/availability/getByCottage/${id}`);

  }


}

