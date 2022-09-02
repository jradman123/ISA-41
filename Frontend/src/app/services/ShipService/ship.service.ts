import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { MarkDto } from 'src/app/interfaces/mark-dto';
@Injectable({
  providedIn: 'root'
})
export class ShipService {



  private apiServerUrl = environment.apiBaseUrl;


  constructor(private http: HttpClient, private router: Router) { }

  findByEmail(): Observable<ShipDto[]> {
    return this.http.get<ShipDto[]>(
      `${this.apiServerUrl}/ships/findOwnerShips/${localStorage.getItem('email')}`);
  }

  deleteShip(id: any) {
    return this.http.delete<ShipDto>(
      `${this.apiServerUrl}/ships/deleteShip/${id}`);
  }

  findbyId(id: string) {
    return this.http.get<ShipDto>(
      `${this.apiServerUrl}/ships/findShip/${id}`);
  }

  saveShip(newShip: ShipDto) {
    return this.http.post(`${this.apiServerUrl}/ships/createShips`, newShip, {
      responseType: 'text',
    });
  }

  editShip(ship: ShipDto) {

    return this.http.put<ShipDto>(`${this.apiServerUrl}/ships/editShip`, ship);
  }

  addImage(id: string, image: Image) {
    console.log("usao u add image");
    return this.http.post<ShipDto>(`${this.apiServerUrl}/ships/add-image/${id}`, image);

  }

  getShipImages(id: string) {
    return this.http.get<ImagesResponse>(
      `${this.apiServerUrl}/ships/${id}/images`);
  }

  getShipMark(id: string) {
    return this.http.get<MarkDto>(
      `${this.apiServerUrl}/ships/getShipReport/${id}`);
  }


}

