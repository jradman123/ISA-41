import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';

@Component({
  selector: 'app-edit-cottage-utility-dialog',
  templateUrl: './edit-cottage-utility-dialog.component.html',
  styleUrls: ['./edit-cottage-utility-dialog.component.css']
})
export class EditCottageUtilityDialogComponent implements OnInit {

  form: FormGroup;
  utilityForUpdate: UtilityDto;
  constructor(private dialogRef: MatDialogRef<EditCottageUtilityDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
    private utilityService: UtilityService) {
    this.utilityForUpdate = {} as UtilityDto;
    this.form = new FormGroup({
      name: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
      ]),
      price: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
    })
  }

  ngOnInit(): void {
    this.form.controls['name'].setValue(this.data.name)
    this.form.controls['price'].setValue(this.data.price)
  }



  close() {
    this.dialogRef.close();
  }

  submit() {
    if (this.form.invalid) {
      return;
    }

    this.utilityForUpdate = {
      name: this.form.get('name')?.value,
      price: this.form.get('price')?.value,
      cottageId: this.data.cottageId,
      id: this.data.id,
      shipId: ''
    }
    this.utilityService.upadteUtility(this.data.id, this.utilityForUpdate).subscribe((data) => {
      console.log("success!")
    });
    this.dialogRef.close(this.form.value);
  }



}
