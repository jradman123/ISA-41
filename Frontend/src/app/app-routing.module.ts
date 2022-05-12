import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationRequestsComponent } from './components/registration-requests/registration-requests.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { CottageOwnerDashboardComponent } from './components/cottage-owner-dashboard/cottage-owner-dashboard/cottage-owner-dashboard.component';
const routes: Routes = [
  { path: '', component: LandingComponent },
  {path: 'registration', component: RegistrationComponent},
  {path: 'login', component: LoginComponent},
  {
    path: 'admin',
    component: AdminDashboardComponent,
    children: [
      {
        path: '',
        component: RegistrationRequestsComponent,
      },
    ],
  },
  {
    path: 'cottageOwner',
    component: CottageOwnerDashboardComponent,
    
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
