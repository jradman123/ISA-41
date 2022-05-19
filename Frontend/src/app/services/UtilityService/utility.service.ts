import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilityService {
 

  

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  findUtilityById(id: string):Observable<UtilityDto[]> {
    return this.http.get<UtilityDto[]>(
      `${this.apiServerUrl}/utilities/findUtilitiesByCottage/${ id}`);
    }

    findShipUtilityById(id: string):Observable<UtilityDto[]> {
      return this.http.get<UtilityDto[]>(
        `${this.apiServerUrl}/utilities/findUtilitiesByBoat/${ id}`);
      }

      deleteUtility(id: any, idCottage: any) {
        return this.http.delete<UtilityDto>(
          `${this.apiServerUrl}/utilities/deleteUtilityByCottage/${ id}/${idCottage}`);
     
      }
  }

