import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AgmCoreModule } from '@agm/core';
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

import { AdventureBasicInfoComponent } from './components/adventure-basic-info/adventure-basic-info.component';
import { AdventureRulesComponent } from './components/adventure-rules/adventure-rules.component';
import { AdventureFishingEquipmentsComponent } from './components/adventure-fishing-equipments/adventure-fishing-equipments.component';
import { AdventureUtilitiesComponent } from './components/adventure-utilities/adventure-utilities.component';
import { AdventureImagesComponent } from './components/adventure-images/adventure-images.component';
import { EditUtilityDialogComponent } from './components/edit-utility-dialog/edit-utility-dialog.component';
import { InstructorAvailabilityComponent } from './components/instructor-availability/instructor-availability.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { DialogForAppointmentComponent } from './components/dialog-for-appointment/dialog-for-appointment.component';
import { DialogForReportComponent } from './components/dialog-for-report/dialog-for-report.component';
import { DialogForAddReportComponent } from './components/dialog-for-add-report/dialog-for-add-report.component';
import { EditCottageUtilityDialogComponent } from './components/edit-cottage-utility-dialog/edit-cottage-utility-dialog.component';
import { EditShipRulesComponent } from './components/edit-ship-rules/edit-ship-rules.component';
import { EditShipUtilitiesComponent } from './components/edit-ship-utilities/edit-ship-utilities.component';
import { ShipReservationHistoryComponent } from './components/ship-reservation-history/ship-reservation-history.component';
import { EditShipNavigationComponent } from './components/edit-ship-navigation/edit-ship-navigation.component';
import { ShipReservationComponent } from './components/ship-reservation/ship-reservation.component';
import { ShipAddActionComponent } from './components/ship-add-action/ship-add-action.component';
import { DialogForReservationShipComponent } from './components/dialog-for-reservation-ship/dialog-for-reservation-ship.component';
import { AdventureSearchPipe } from './pipes/adventure-search.pipe';

import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-date-and-time-picker';
import { ShipAvailabilityComponent } from './components/ship-availability/ship-availability.component';

import { CottageReportComponent } from './components/cottage-report/cottage-report.component';
import { DialogForDeletingAdventureComponent } from './components/dialog-for-deleting-adventure/dialog-for-deleting-adventure.component';
import { DatePipe } from '@angular/common';
import { DialogForReservationAdventureComponent } from './components/dialog-for-reservation-adventure/dialog-for-reservation-adventure.component';
import { AdventureStatisticComponent } from './components/adventure-statistic/adventure-statistic.component';
import { AdminReportComponent } from './components/admin-report/admin-report.component';
import { DialogReservationViewComponent } from './components/dialog-reservation-view/dialog-reservation-view.component';
import { AdminReviewComponent } from './components/admin-review/admin-review.component';
import { AdminComplaintsComponent } from './components/admin-complaints/admin-complaints.component';
import { AnswerComplaintDialogComponent } from './components/answer-complaint-dialog/answer-complaint-dialog.component';
import { AdventuresViewComponent } from './components/admin-entities/admin-adventures/adventures-view/adventures-view.component';
import { AdventureViewComponent } from './components/admin-entities/admin-adventures/adventure-view/adventure-view.component';
import { BasicInfoComponent } from './components/admin-entities/admin-adventures/basic-info/basic-info.component';
import { ImagesComponent } from './components/admin-entities/admin-adventures/images/images.component';
import { ARulesComponent } from './components/admin-entities/admin-adventures/a-rules/a-rules.component';
import { AFishingEquipmentsComponent } from './components/admin-entities/admin-adventures/a-fishing-equipments/a-fishing-equipments.component';
import { AUtilitiesComponent } from './components/admin-entities/admin-adventures/a-utilities/a-utilities.component';
import { CottagesViewComponent } from './components/admin-entities/admin-cottages/cottages-view/cottages-view.component';
import { CottageViewComponent } from './components/admin-entities/admin-cottages/cottage-view/cottage-view.component';
import { CUtilitiesComponent } from './components/admin-entities/admin-cottages/c-utilities/c-utilities.component';
import { CRulesComponent } from './components/admin-entities/admin-cottages/c-rules/c-rules.component';
import { CAvailabilityComponent } from './components/admin-entities/admin-cottages/c-availability/c-availability.component';
import { BoatsViewComponent } from './components/admin-entities/admin-boats/boats-view/boats-view.component';
import { BoatViewComponent } from './components/admin-entities/admin-boats/boat-view/boat-view.component';
import { BRulesComponent } from './components/admin-entities/admin-boats/b-rules/b-rules.component';
import { BEquipmentsComponent } from './components/admin-entities/admin-boats/b-equipments/b-equipments.component';
import { BAvailabilityComponent } from './components/admin-entities/admin-boats/b-availability/b-availability.component';
import { BReservationsComponent } from './components/admin-entities/admin-boats/b-reservations/b-reservations.component';
import { BUtilitiesComponent } from './components/admin-entities/admin-boats/b-utilities/b-utilities.component';


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
    DialogForGuestDataComponent,
    AdventureBasicInfoComponent,
    AdventureRulesComponent,
    AdventureFishingEquipmentsComponent,
    AdventureUtilitiesComponent,
    AdventureImagesComponent,
    EditUtilityDialogComponent,
    DialogForAppointmentComponent,
    DialogForReportComponent,
    DialogForAddReportComponent,
    EditCottageUtilityDialogComponent,
    EditShipRulesComponent,
    EditShipUtilitiesComponent,
    ShipReservationHistoryComponent,
    EditShipNavigationComponent,
    ShipReservationComponent,
    ShipAddActionComponent,
    DialogForReservationShipComponent,
    InstructorAvailabilityComponent,
    AdventureSearchPipe,
    DialogForReservationAdventureComponent,
    ShipAvailabilityComponent,
    CottageReportComponent,
    DialogForDeletingAdventureComponent,
    AdventureStatisticComponent,
    AdminReportComponent,
    DialogReservationViewComponent,
    AdminReviewComponent,
    AdminComplaintsComponent,
    AnswerComplaintDialogComponent,
    AdventuresViewComponent,
    AdventureViewComponent,
    BasicInfoComponent,
    ImagesComponent,
    ARulesComponent,
    AFishingEquipmentsComponent,
    AUtilitiesComponent,
    CottagesViewComponent,
    CottageViewComponent,
    CUtilitiesComponent,
    CRulesComponent,
    CAvailabilityComponent,
    BoatsViewComponent,
    BoatViewComponent,
    BRulesComponent,
    BEquipmentsComponent,
    BAvailabilityComponent,
    BReservationsComponent,
    BUtilitiesComponent


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
    MatCarouselModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),
    OwlDateTimeModule,
    OwlNativeDateTimeModule,

    AgmCoreModule.forRoot({ apiKey: 'AIzaSyDjkYy-y0-wlKYTQC0cMskJm9DuCWMmvVE' }),
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory })

  ],
  providers: [HttpClientModule,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
