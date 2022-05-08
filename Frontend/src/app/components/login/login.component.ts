import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/AuthService/auth.service';

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
    private _snackBar: MatSnackBar) { }

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
       },
        error: (err:any) => {
          this._snackBar.open("Email or password are incorrect.Try again,please.","Dismiss"); 
        
        }};
     
     this._service.login(this.form.getRawValue()).subscribe(loginObserver);
     //this._router.navigate(['/record']);
    }

}
