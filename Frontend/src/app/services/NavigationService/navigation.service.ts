import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NavigationDto } from 'src/app/interfaces/navigation-dto';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';





@Injectable({
  providedIn: 'root'
})
export class NavigationService {


  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  findNavigationById(id: string): Observable<NavigationDto[]> {
    return this.http.get<NavigationDto[]>(
      `${this.apiServerUrl}/navigation/findNavigationbyBoat/${id}`);
  }
}
