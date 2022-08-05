import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { NewAdventureDto } from 'src/app/interfaces/new-adventure-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  createNewAdventure(newAdventure: NewAdventureDto) {
    return this.http.post(`${this.apiServerUrl}/adventures`, newAdventure);
  }

  getAllForInstructor(): Observable<AdventureDto[]> {
    return this.http.get<AdventureDto[]>(
      `${this.apiServerUrl}/adventures/all-for-instructor`);
  }

  findById(id: string) {
    return this.http.get<AdventureDto>(
      `${this.apiServerUrl}/adventures/find-adventure/${id}`);
  }

}
