import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FishingEquipmentDto } from 'src/app/interfaces/fishing-equipment-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FishingEquipmentService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  addAdventureFishingEquipment(equipment: FishingEquipmentDto) {
    return this.http.post(`${this.apiServerUrl}/adventure-fishing-equipments`, equipment);
  }

  deleteFishingEquipment(id : string){
    return this.http.delete(`${this.apiServerUrl}/adventure-fishing-equipments/${id}`);
  }
}
