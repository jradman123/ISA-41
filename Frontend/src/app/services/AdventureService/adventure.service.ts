import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { NewAdventureDto } from 'src/app/interfaces/new-adventure-dto';
import { environment } from 'src/environments/environment';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { ResponseRules } from 'src/app/interfaces/response-rules';
import { ResponseFishingEquipment } from 'src/app/interfaces/response-fishing-equipment';
import { ResponseUtility } from 'src/app/interfaces/response-utility';

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

  addImage(id : string,image : Image){
    console.log("usao u add image");
    return this.http.post<AdventureDto>(`${this.apiServerUrl}/adventures/add-image/${id}`, image);

  }

  getAdventuresImages(id : string){
    return this.http.get<ImagesResponse>(
      `${this.apiServerUrl}/adventures/${id}/images`);
  }

  updateAdventure(id : string,adventureDto : AdventureDto){
    return this.http.put<AdventureDto>(
      `${this.apiServerUrl}/adventures/${id}/update`,adventureDto);
  }

  getAdventuresRules(id : string){
    return this.http.get<ResponseRules[]>(
      `${this.apiServerUrl}/adventures/${id}/rules`);
  }

  getAdventureFishingEquipment(id : string){
    return this.http.get<ResponseFishingEquipment[]>(
      `${this.apiServerUrl}/adventures/${id}/fishing-equipments`);
  }

  getAdventureUtilities(id : string){
    return this.http.get<ResponseUtility[]>(
      `${this.apiServerUrl}/adventures/${id}/utilities`);
  }

}
