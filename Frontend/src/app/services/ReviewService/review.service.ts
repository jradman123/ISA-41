import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReviewResponse } from 'src/app/interfaces/review-response';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getAllUnseen() : Observable<ReviewResponse[]> {
    return this.http.get<ReviewResponse[]>(`${this.apiServerUrl}/reviews/all-unseen`);
  }

  approveReview(id : string) : Observable<ReviewResponse[]> {
    return this.http.get<ReviewResponse[]>(`${this.apiServerUrl}/reviews/approve/${id}`);
  }

  rejectReview(id : string) : Observable<ReviewResponse[]> {
    return this.http.get<ReviewResponse[]>(`${this.apiServerUrl}/reviews/reject/${id}`);
  }

}
