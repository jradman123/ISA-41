import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { DetailsAboutReservation } from 'src/app/interfaces/details-about-reservation';
import { ReviewResponse } from 'src/app/interfaces/review-response';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { ReviewService } from 'src/app/services/ReviewService/review.service';
import { DialogReservationViewComponent } from '../dialog-reservation-view/dialog-reservation-view.component';

@Component({
  selector: 'app-admin-review',
  templateUrl: './admin-review.component.html',
  styleUrls: ['./admin-review.component.css']
})
export class AdminReviewComponent implements OnInit {

  reviews : ReviewResponse[];
  sub!: Subscription;
  details : DetailsAboutReservation;
  constructor(private reviewService : ReviewService,private changeDetectorRefs: ChangeDetectorRef, public dialog: MatDialog,private reservationService : ReservationService) {
    this.reviews=[] as ReviewResponse[];
    this.details = {} as DetailsAboutReservation;
   }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.sub = this.reviewService
      .getAllUnseen()
      .subscribe({
        next: (rep: ReviewResponse[]) => {
          this.reviews = rep;
          this.changeDetectorRefs.detectChanges();
        },
      });
    }

    detailsAbouReservation(id : string): void {
      this.reservationService
      .getDetails(id)
      .subscribe((data)=>{
        this.details = data;
        const dialogRef = this.dialog.open(DialogReservationViewComponent, {
          width: '500px',
          data: this.details,
        });
      });
}

approve(id : string){
  this.sub = this.reviewService
  .approveReview(id)
  .subscribe((data) => {
      this.reviews = [];
      this.reviews = data
    });
}

reject(id : string){
  this.sub = this.reviewService
  .rejectReview(id)
  .subscribe((data) => {
      this.reviews = [];
      this.reviews = data;
    });
}
}
