import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { RoomDto } from 'src/app/interfaces/room-dto';


@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }



  deleteRoom(id: any, idCottage: any) {
    return this.http.delete<RoomDto>(
      `${this.apiServerUrl}/cottages/deleteRoomByCottage/${id}/${idCottage}`);

  }
  addRoom(room: RoomDto) {
    return this.http.post(`${this.apiServerUrl}/cottages/createRoomByCottage`, room, {
      responseType: 'text',
    });

  }
}