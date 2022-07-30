import { Injectable } from '@angular/core';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RuleService {





  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  findRulebyId(id: string): Observable<RuleDto[]> {
    return this.http.get<RuleDto[]>(
      `${this.apiServerUrl}/rules/findRulesByCottage/${id}`);
  }

  findShipRulebyId(id: string): Observable<RuleDto[]> {
    return this.http.get<RuleDto[]>(
      `${this.apiServerUrl}/rules/findRulesByBoat/${id}`);
  }

  deleteRule(id: any, idCottage: any) {
    return this.http.delete<RuleDto>(
      `${this.apiServerUrl}/rules/deleteRyleByCottage/${id}/${idCottage}`);

  }
  addRule(rule: RuleDto) {
    return this.http.post(`${this.apiServerUrl}/rules/createCottageRule`, rule, {
      responseType: 'text',
    });

  }

}
