import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material-module';
import { RegistrationComponent } from './components/registration/registration.component';
import { LandingComponent } from './components/landing/landing.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { RegistrationRequestsComponent } from './components/registration-requests/registration-requests.component';
import { JwtInterceptor } from './JwtInterceptor/jwt-interceptor';
import { DialogForReasonComponent } from './components/dialog-for-reason/dialog-for-reason.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { DialogForChangingPasswordComponent } from './components/dialog-for-changing-password/dialog-for-changing-password.component';
import { AdminRegistrationComponent } from './components/admin-registration/admin-registration.component';
import { CottageOwnerDashboardComponent } from './components/cottage-owner-dashboard/cottage-owner-dashboard/cottage-owner-dashboard.component';
import { CottageOwnerNavbarComponent } from './components/cottage-owner-navbar/cottage-owner-navbar/cottage-owner-navbar.component';
import { CottageListComponent } from './components/cottage-list/cottage-list/cottage-list.component';
import { CottageProfileComponent } from './components/cottage-profile/cottage-profile/cottage-profile.component';
import { AddCottageComponent } from './components/add-cottage/add-cottage/add-cottage.component';
import { ShipOwnerDashboardComponent } from './components/ship-owner-dashboard/ship-owner-dashboard/ship-owner-dashboard.component';
import { ShipOwnerNavbarComponent } from './components/ship-owner-navbar/ship-owner-navbar/ship-owner-navbar.component';
import { ShipListComponent } from './components/ship-list/ship-list/ship-list.component';
import { ShipProfileComponent } from './components/ship-profile/ship-profile/ship-profile.component';
import { EditCottageComponent } from './components/edit-cottage/edit-cottage/edit-cottage.component';
import { EditRulesComponent } from './components/edit-rules/edit-rules/edit-rules.component';
import { EditUtilitiesComponent } from './components/edit-utilities/edit-utilities/edit-utilities.component';
import { RequestsForDeletingAccountComponent } from './components/requests-for-deleting-account/requests-for-deleting-account.component';
import { DialogForResponseDeletingComponent } from './components/dialog-for-response-deleting/dialog-for-response-deleting.component';
import { InstructorDashboardComponent } from './components/instructor-dashboard/instructor-dashboard.component';
import { InstructorNavbarComponent } from './components/instructor-navbar/instructor-navbar.component';
import { InstructorProfileComponent } from './components/instructor-profile/instructor-profile.component';
import { DialogForDeletingAccountComponent } from './components/dialog-for-deleting-account/dialog-for-deleting-account.component';
import { CottageOwnerProfileComponent } from './components/cottage-owner-profile/cottage-owner-profile/cottage-owner-profile.component';
import { ShipOwnerProfileComponent } from './components/ship-owner-profile/ship-owner-profile/ship-owner-profile.component';
import { AddShipComponent } from './components/add-ship/add-ship/add-ship.component';
import { EditShipComponent } from './components/edit-ship/edit-ship/edit-ship.component';
import { EditRoomsComponent } from './components/edit-rooms/edit-rooms/edit-rooms.component';
import { AddActionComponent } from './components/add-action/add-action/add-action.component';
import { ReservationHistoryComponent } from './components/reservation-history/reservation-history/reservation-history.component';
import { AddReservationComponent } from './components/add-reservation/add-reservation/add-reservation.component';
import { CottageAvailabilityComponent } from './components/cottage-availability/cottage-availability/cottage-availability.component';
import { LandingNavbarComponent } from './components/landing-navbar/landing-navbar/landing-navbar.component';
import { AllCottagesListComponent } from './components/all-cottages-list/all-cottages-list/all-cottages-list.component';
import { CottagesSearchPipe } from './pipes/cottages-search.pipe';
import { ShipSearchPipe } from './pipes/ship-search.pipe';

import { AdventuresListComponent } from './components/adventures-list/adventures-list.component';
import { NewAdventureComponent } from './components/new-adventure/new-adventure.component';
import { AdventureProfileComponent } from './components/adventure-profile/adventure-profile.component';
import { MatCarouselComponent, MatCarouselModule } from '@ngbmodule/material-carousel';

import { DialogForReservationCottageComponent } from './components/dialog-for-reservation-cottage/dialog-for-reservation-cottage.component';
import { DialogForGuestDataComponent } from './components/dialog-for-guest-data/dialog-for-guest-data.component';




@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LandingComponent,
    LoginComponent,
    AdminDashboardComponent,
    AdminNavbarComponent,
    RegistrationRequestsComponent,
    DialogForReasonComponent,
    AdminProfileComponent,
    DialogForChangingPasswordComponent,
    AdminRegistrationComponent,
    CottageOwnerDashboardComponent,
    CottageOwnerNavbarComponent,
    CottageListComponent,
    CottageProfileComponent,
    AddCottageComponent,
    ShipOwnerDashboardComponent,
    ShipOwnerNavbarComponent,
    ShipListComponent,
    ShipProfileComponent,
    EditCottageComponent,
    EditRulesComponent,
    EditUtilitiesComponent,
    RequestsForDeletingAccountComponent,
    DialogForResponseDeletingComponent,
    InstructorDashboardComponent,
    InstructorNavbarComponent,
    InstructorProfileComponent,
    DialogForDeletingAccountComponent,
    CottageOwnerProfileComponent,
    ShipOwnerProfileComponent,
    AddShipComponent,
    EditShipComponent,
    EditRoomsComponent,
    AddActionComponent,
    ReservationHistoryComponent,
    AddReservationComponent,
    CottageAvailabilityComponent,
    LandingNavbarComponent,
    AllCottagesListComponent,
    CottagesSearchPipe,
    ShipSearchPipe,

    AdventuresListComponent,
    NewAdventureComponent,
    AdventureProfileComponent,

    DialogForReservationCottageComponent,
    DialogForGuestDataComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatCarouselModule
  ],
  providers: [HttpClientModule,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
