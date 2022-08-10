import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AdventureUtilityDto } from 'src/app/interfaces/adventure-utility-dto';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
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

  updateAdventureUtility(id : string,utility : AdventureUtilityDto){
    return this.http.put(`${this.apiServerUrl}/adventure-utilities/${id}`, utility);
  }
}
