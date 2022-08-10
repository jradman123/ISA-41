import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AdventureUtilityDto } from 'src/app/interfaces/adventure-utility-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdventureUtilityService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  addAdventureUtility(newUtility : AdventureUtilityDto){
    return this.http.post(`${this.apiServerUrl}/adventure-utilities`, newUtility);
  }

  deleteAdventureUtility(id : string){
    return this.http.delete(`${this.apiServerUrl}/adventure-utilities/${id}`);
  }
}
