import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { RoomDto } from 'src/app/interfaces/room-dto';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { MarkDto } from 'src/app/interfaces/mark-dto';
@Injectable({
  providedIn: 'root'
})
export class CottageService {




  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }


  findAll(): Observable<CottageDto[]> {
    return this.http.get<CottageDto[]>(
      `${this.apiServerUrl}/cottages/all`);
  }


  findByEmail(): Observable<CottageDto[]> {
    return this.http.get<CottageDto[]>(
      `${this.apiServerUrl}/cottages/findOwnerCottages/${localStorage.getItem('email')}`);
  }

  findbyId(id: string) {
    return this.http.get<CottageDto>(
      `${this.apiServerUrl}/cottages/findCottage/${id}`);
  }

  findRoomsById(id: string): Observable<RoomDto[]> {
    return this.http.get<RoomDto[]>(
      `${this.apiServerUrl}/cottages/findRoomsById/${id}`);
  }


  saveCottage(newCottage: CottageDto) {
    return this.http.post(`${this.apiServerUrl}/cottages/createCottage`, newCottage, {
      responseType: 'text',
    });
  }

  editCottage(cottage: CottageDto) {

    return this.http.put<CottageDto>(`${this.apiServerUrl}/cottages/editCottage`, cottage);
  }

  deleteCottage(id: any) {
    return this.http.delete<CottageDto>(
      `${this.apiServerUrl}/cottages/deleteCottage/${id}`);
  }

  addImage(id: string, image: Image) {
    console.log("usao u add image");
    return this.http.post<CottageDto>(`${this.apiServerUrl}/cottages/add-image/${id}`, image);

  }

  getCottageImages(id: string) {
    return this.http.get<ImagesResponse>(
      `${this.apiServerUrl}/cottages/${id}/images`);
  }


  getCottageMark(id: string) {
    return this.http.get<MarkDto>(
      `${this.apiServerUrl}/cottages/getCottageReport/${id}`);
  }


}

