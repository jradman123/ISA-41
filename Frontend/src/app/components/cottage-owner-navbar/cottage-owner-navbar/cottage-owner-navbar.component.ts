import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/AuthService/auth.service';

@Component({
  selector: 'app-cottage-owner-navbar',
  templateUrl: './cottage-owner-navbar.component.html',
  styleUrls: ['./cottage-owner-navbar.component.css']
})
export class CottageOwnerNavbarComponent implements OnInit {
  @Output() searchInput: EventEmitter<string> = new EventEmitter();


  loginStatus$: Observable<boolean>;
  constructor(public authService: AuthService, private router: Router) {
    this.loginStatus$ = this.authService.isLoggedIn;

  }
  ngOnInit(): void {
  }

  emitMe(searchText: any) {
    this.searchInput.emit(searchText.target.value);
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
