import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { RoomDto } from 'src/app/interfaces/room-dto';
@Injectable({
  providedIn: 'root'
})
export class CottageService {




  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }




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
}

