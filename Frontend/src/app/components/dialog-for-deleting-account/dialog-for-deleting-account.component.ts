import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-for-deleting-account',
  templateUrl: './dialog-for-deleting-account.component.html',
  styleUrls: ['./dialog-for-deleting-account.component.css']
})
export class DialogForDeletingAccountComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogForDeletingAccountComponent>,@Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  form = new FormGroup({
    reason: new FormControl('', Validators.required)
  })

  close() {
    this.dialogRef.close();
  }

  submit() {
    if(this.form.invalid){
      return;
    }
    this.dialogRef.close(this.form.value);
  }

}
