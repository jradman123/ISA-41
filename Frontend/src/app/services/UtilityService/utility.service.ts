import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { AdventureUtilityDto } from 'src/app/interfaces/adventure-utility-dto';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {




  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  findUtilityById(id: string): Observable<UtilityDto[]> {
    return this.http.get<UtilityDto[]>(
      `${this.apiServerUrl}/utilities/findUtilitiesByCottage/${id}`);
  }

  findShipUtilityById(id: string): Observable<UtilityDto[]> {
    return this.http.get<UtilityDto[]>(
      `${this.apiServerUrl}/utilities/findUtilitiesByBoat/${id}`);
  }

  deleteUtility(id: any, idCottage: any) {
    return this.http.delete<UtilityDto>(
      `${this.apiServerUrl}/utilities/deleteUtilityByCottage/${id}/${idCottage}`);

  }

  deleteUtilitybyShip(id: any, idShip: any) {
    return this.http.delete<UtilityDto>(
      `${this.apiServerUrl}/utilities/deleteUtilityByShip/${id}/${idShip}`);

  }

  addCottageUtility(newUtility: UtilityDto) {
    console.log(newUtility)
    return this.http.post(`${this.apiServerUrl}/utilities/addCottageUtility`, newUtility, {
      responseType: 'text',
    });

  }

  addShipUtility(newUtility: UtilityDto) {
    console.log(newUtility)
    return this.http.post(`${this.apiServerUrl}/utilities/addShipUtility`, newUtility, {
      responseType: 'text',
    });

  }
  upadteUtility(id: string, utility: UtilityDto) {
    return this.http.put(`${this.apiServerUrl}/utilities/updateCottageUtility/${id}`, utility);
  }
}

