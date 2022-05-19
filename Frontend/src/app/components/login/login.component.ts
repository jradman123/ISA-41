import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/AuthService/auth.service';
import { UserService } from 'src/app/services/UserService/user.service';

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
     const loginObserver = {
       next: (x:any) => {
          this._snackBar.open("     Welcome","Dismiss");
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
       },
        error: (err:any) => {
          this._snackBar.open("Email or password are incorrect.Try again,please.","Dismiss"); 
        
        }};
     
     this._service.login(this.form.getRawValue()).subscribe(loginObserver);
    }

} 
