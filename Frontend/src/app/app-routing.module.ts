import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './AuthGuard/AuthGuard';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './components/admin-registration/admin-registration.component';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationRequestsComponent } from './components/registration-requests/registration-requests.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { CottageOwnerDashboardComponent } from './components/cottage-owner-dashboard/cottage-owner-dashboard/cottage-owner-dashboard.component';
import { CottageListComponent } from './components/cottage-list/cottage-list/cottage-list.component';
import { CottageProfileComponent } from './components/cottage-profile/cottage-profile/cottage-profile.component';
import { AddCottageComponent } from './components/add-cottage/add-cottage/add-cottage.component';
import { ShipOwnerDashboardComponent } from './components/ship-owner-dashboard/ship-owner-dashboard/ship-owner-dashboard.component';
import { ShipListComponent } from './components/ship-list/ship-list/ship-list.component';
import { ShipProfileComponent } from './components/ship-profile/ship-profile/ship-profile.component';
import { EditCottageComponent } from './components/edit-cottage/edit-cottage/edit-cottage.component';
import { EditRulesComponent } from './components/edit-rules/edit-rules/edit-rules.component';
import { EditUtilitiesComponent } from './components/edit-utilities/edit-utilities/edit-utilities.component';
import { RequestsForDeletingAccountComponent } from './components/requests-for-deleting-account/requests-for-deleting-account.component';
import { InstructorDashboardComponent } from './components/instructor-dashboard/instructor-dashboard.component';
import { InstructorProfileComponent } from './components/instructor-profile/instructor-profile.component';
import { CottageOwnerProfileComponent } from './components/cottage-owner-profile/cottage-owner-profile/cottage-owner-profile.component';
import { ShipOwnerProfileComponent } from './components/ship-owner-profile/ship-owner-profile/ship-owner-profile.component';
import { AddShipComponent } from './components/add-ship/add-ship/add-ship.component';
import { EditShipComponent } from './components/edit-ship/edit-ship/edit-ship.component';
import { EditRoomsComponent } from './components/edit-rooms/edit-rooms/edit-rooms.component';
import { AddActionComponent } from './components/add-action/add-action/add-action.component';
import { ReservationHistoryComponent } from './components/reservation-history/reservation-history/reservation-history.component';
import { AddReservationComponent } from './components/add-reservation/add-reservation/add-reservation.component';
import { CottageAvailabilityComponent } from './components/cottage-availability/cottage-availability/cottage-availability.component';
import { AllCottagesListComponent } from './components/all-cottages-list/all-cottages-list/all-cottages-list.component';
import { AdventuresListComponent } from './components/adventures-list/adventures-list.component';
import { NewAdventureComponent } from './components/new-adventure/new-adventure.component';
import { AdventureProfileComponent } from './components/adventure-profile/adventure-profile.component';
import { InstructorAvailabilityComponent } from './components/instructor-availability/instructor-availability.component';
import { AdventureStatisticComponent } from './components/adventure-statistic/adventure-statistic.component';
import { AdminComplaintsComponent } from './components/admin-complaints/admin-complaints.component';
import { AdminReportComponent } from './components/admin-report/admin-report.component';
import { AdminReviewComponent } from './components/admin-review/admin-review.component';
import { AdventuresViewComponent } from './components/admin-entities/admin-adventures/adventures-view/adventures-view.component';
import { AdventureViewComponent } from './components/admin-entities/admin-adventures/adventure-view/adventure-view.component';
import { CottagesViewComponent } from './components/admin-entities/admin-cottages/cottages-view/cottages-view.component';
import { CottageViewComponent } from './components/admin-entities/admin-cottages/cottage-view/cottage-view.component';

const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'allCottages', component: AllCottagesListComponent },
  {
    path: 'admin',
    component: AdminDashboardComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: RegistrationRequestsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'profile',
        component: AdminProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'add-new-admin',
        component: AdminRegistrationComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'requests-for-deleting',
        component: RequestsForDeletingAccountComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'reports',
        component: AdminReportComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'reviews',
        component: AdminReviewComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'complaints',
        component: AdminComplaintsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'adventures',
        component: AdventuresViewComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'adventure-view/:id',
        component: AdventureViewComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'cottages',
        component: CottagesViewComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'cottage-view/:id',
        component: CottageViewComponent,
        canActivate: [AuthGuard]
      },
    ],
  },
  {
    path: 'cottageOwner',
    component: CottageOwnerDashboardComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: CottageListComponent,
        canActivate: [AuthGuard]


      },
      {
        path: 'profile',
        component: CottageOwnerProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'cottage-profile/:id',
        component: CottageProfileComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'add-cottage',
        component: AddCottageComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'edit-cottage/:id',
        component: EditCottageComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'edit-rules/:id',
        component: EditRulesComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'edit-utilities/:id',
        component: EditUtilitiesComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'edit-rooms/:id',
        component: EditRoomsComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'add-action/:id',
        component: AddActionComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'reservation-history/:id',
        component: ReservationHistoryComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'cottage-availability/:id',
        component: CottageAvailabilityComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'add-reservation/:id',
        component: AddReservationComponent,
        canActivate: [AuthGuard]

      },





    ],

  },
  {
    path: 'shipOwner',
    component: ShipOwnerDashboardComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: ShipListComponent,
        canActivate: [AuthGuard]


      },
      {
        path: 'ship-profile/:id',
        component: ShipProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'profile',
        component: ShipOwnerProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'add-ship',
        component: AddShipComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'edit-ship/:id',
        component: EditShipComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'edit-rules/:id',
        component: EditRulesComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'edit-utilities/:id',
        component: EditUtilitiesComponent,
        canActivate: [AuthGuard]

      },


    ]
  },
  {
    path: 'instructor',
    component: InstructorDashboardComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: AdventuresListComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'profile',
        component: InstructorProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'new-adventure',
        component: NewAdventureComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'adventure-profile/:id',
        component: AdventureProfileComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'availability',
        component: InstructorAvailabilityComponent,
        canActivate: [AuthGuard]

      },
      {
        path: 'report/:id',
        component: AdventureStatisticComponent,
        canActivate : [AuthGuard]
      }
    ],

  },
  


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
