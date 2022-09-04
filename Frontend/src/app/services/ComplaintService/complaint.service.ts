import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Answer } from 'src/app/interfaces/answer';
import { ComplaintDto } from 'src/app/interfaces/complaint-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient, private router: Router) { }


  getAllUnanswered() : Observable<ComplaintDto[]> {
    return this.http.get<ComplaintDto[]>(
      `${this.apiServerUrl}/complaints/unanswered`);

  }

  answerOnComplaint(answer: Answer,id : string) {
    return this.http.post(`${this.apiServerUrl}/complaints/${id}/answer`, answer,{
      responseType: 'text',
    });
  }
}
