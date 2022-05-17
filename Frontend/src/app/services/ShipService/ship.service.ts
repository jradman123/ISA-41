import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
@Injectable({
  providedIn: 'root'
})
export class ShipService {
 
 

  private apiServerUrl = environment.apiBaseUrl;
 
  
  constructor(private http: HttpClient,private router:Router) { }

  findByEmail() : Observable<ShipDto[]> {
    return this.http.get<ShipDto[]>(
      `${this.apiServerUrl}/ships/findOwnerShips/${ localStorage.getItem('email')}`);
    }

  findbyId(id: string) {
    return this.http.get<ShipDto>(
      `${this.apiServerUrl}/ships/findShip/${ id}`);
    }
}

