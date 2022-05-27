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
import { CottageOwnerProfileComponent } from './components/cottage-owner-profile/cottage-owner-profile/cottage-owner-profile.component';
import { ShipOwnerProfileComponent } from './components/ship-owner-profile/ship-owner-profile/ship-owner-profile.component';
const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
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
    ]
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
