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

const routes: Routes = [
  { path: '', component: LandingComponent },
  {path: 'registration', component: RegistrationComponent},
  {path: 'login', component: LoginComponent},
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
        path: 'addNewAdmin',
        component: AdminRegistrationComponent,
        canActivate: [AuthGuard]
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
