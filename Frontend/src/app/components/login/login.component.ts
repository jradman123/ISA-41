import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/AuthService/auth.service';
import { UserService } from 'src/app/services/UserService/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public form!: FormGroup;
  constructor( private formBuilder: FormBuilder,
    private _router: Router,
    private _service: AuthService,
    private _snackBar: MatSnackBar,
    private userService : UserService) { }

    ngOnInit(): void {
      this.form = this.formBuilder.group({
        email:'',
        password:''
      });
    }
    
    submit():void{
      if(this.form.invalid){
        return;
      }
     const loginObserver = {
       next: (x:any) => {
          if(localStorage.getItem('role') == "Admin"){
            if(localStorage.getItem('firstLogin')=== 'true'){
              this._router.navigate(['/admin/profile']);
            }else{
              this._router.navigate(['/admin']);
            }
          }
          else if(localStorage.getItem('role') == "CottageAdvertiser"){
            this._router.navigate(['/cottageOwner']);
           }
          else if(localStorage.getItem('role') == "ShipAdvertiser"){
            this._router.navigate(['/shipOwner']);
          }
          else{
            this._router.navigate(['instructor']);
          }
       this._snackBar.open(
        'Welcome!',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
       },
        error: (err:any) => {
            let parts = err.error.split(':');
            let mess = parts[parts.length - 1];
            let description = mess.substring(0, mess.length);
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: description
            })
        }};
     
     this._service.login(this.form.getRawValue()).subscribe(loginObserver);
    }

} 
