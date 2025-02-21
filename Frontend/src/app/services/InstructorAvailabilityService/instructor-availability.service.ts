import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InstructorAvailabilityDto } from 'src/app/interfaces/instructor-availability-dto';
import { NewInstructorAvailability } from 'src/app/interfaces/new-instructor-availability';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InstructorAvailabilityService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  getAllForInstructor(): Observable<InstructorAvailabilityDto[]> {
    return this.http.get<InstructorAvailabilityDto[]>(
      `${this.apiServerUrl}/instructor-availabilities/all-for-instructor`);
  }

  addNewAvailability(availability: NewInstructorAvailability) {
    return this.http.post(`${this.apiServerUrl}/instructor-availabilities/add-new-period`, availability,{
      responseType: 'text',
    });
  }

}
