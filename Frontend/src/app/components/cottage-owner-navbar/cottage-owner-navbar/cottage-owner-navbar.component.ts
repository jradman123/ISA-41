import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/AuthService/auth.service';

@Component({
  selector: 'app-cottage-owner-navbar',
  templateUrl: './cottage-owner-navbar.component.html',
  styleUrls: ['./cottage-owner-navbar.component.css']
})
export class CottageOwnerNavbarComponent implements OnInit {

  loginStatus$: Observable<boolean>;
  constructor(public authService : AuthService , private router : Router) {
    this.loginStatus$ = this.authService.isLoggedIn;
   
   }
  ngOnInit(): void {
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
