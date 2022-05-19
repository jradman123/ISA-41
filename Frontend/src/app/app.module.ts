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
    DialogForChangingPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    NgbModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [HttpClientModule,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
