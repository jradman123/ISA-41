import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AdventureRuleDto } from 'src/app/interfaces/adventure-rule-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdventureRuleService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  addAdventureRule(rule: AdventureRuleDto) {
    return this.http.post(`${this.apiServerUrl}/adventure-rules`, rule);
  }

  deleteAdventureRule(id : string){
    return this.http.delete(`${this.apiServerUrl}/adventure-rules/${id}`);
  }
}
