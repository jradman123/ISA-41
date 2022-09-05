import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CategoryDto } from 'src/app/interfaces/category-dto';
import { CategoryResponse } from 'src/app/interfaces/category-response';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router) { }

  getAll() : Observable<CategoryResponse[]> {
    return this.http.get<CategoryResponse[]>(
      `${this.apiServerUrl}/categories`);

  }
  addNew(category : CategoryDto)  {
    return this.http.post(`${this.apiServerUrl}/categories`,category);

  }

  edit(id : string,category : CategoryDto)  {
    return this.http.post(`${this.apiServerUrl}/categories/update/${id}`,category);
  }
}
