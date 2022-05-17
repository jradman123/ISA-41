import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/AuthService/auth.service';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent implements OnInit {

  loginStatus$: Observable<boolean>;
  constructor(private authService : AuthService , private router : Router) {
    this.loginStatus$ = this.authService.isLoggedIn;
    console.log(this.loginStatus$);
   }

  ngOnInit(): void {
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
