import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/AuthService/auth.service';



@Component({
  selector: 'app-ship-owner-navbar',
  templateUrl: './ship-owner-navbar.component.html',
  styleUrls: ['./ship-owner-navbar.component.css']
})
export class ShipOwnerNavbarComponent implements OnInit {
  @Output() searchInput: EventEmitter<string> = new EventEmitter();


  loginStatus$: Observable<boolean>;
  constructor(public authService: AuthService, private router: Router) {
    this.loginStatus$ = this.authService.isLoggedIn;

  }

  ngOnInit(): void {
  }
  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }


  emitMe(searchText: any) {
    this.searchInput.emit(searchText.target.value);
  }

}
