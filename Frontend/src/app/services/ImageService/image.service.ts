import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ImageDto } from 'src/app/interfaces/image-dto';


@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  findImageByCottageId(id: string): Observable<ImageDto[]> {
    return this.http.get<ImageDto[]>(
      `${this.apiServerUrl}/picture/getPicturesByCottage/${id}`);
  }

}
