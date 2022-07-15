import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-for-changing-password',
  templateUrl: './dialog-for-changing-password.component.html',
  styleUrls: ['./dialog-for-changing-password.component.css']
})
export class DialogForChangingPasswordComponent implements OnInit {

  error : any;
  firstLogin! : boolean;
  constructor(private dialogRef: MatDialogRef<DialogForChangingPasswordComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { 
    if(localStorage.getItem('firstLogin') === 'true'){
      this.firstLogin = true;
    }else{
      this.firstLogin = false;
    }
  }

  ngOnInit(): void {
  }

  form = new FormGroup({
    currentPassword: new FormControl('', Validators.required),
    newPassword: new FormControl('', Validators.required),
    repeatedPassword: new FormControl('', Validators.required)
  })

  submit() {
    if(this.form.invalid){
      return;
    }
    if (this.form.get('newPassword')?.value !== this.form.get('repeatedPassword')?.value) {
      this.error = true
      return
    }
    this.dialogRef.close(this.form.value);
  }
  close() {
    this.dialogRef.close();
  }


}
