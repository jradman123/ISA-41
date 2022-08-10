import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AdventureUtilityDto } from 'src/app/interfaces/adventure-utility-dto';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureUtilityService } from 'src/app/services/AdventureUtilityService/adventure-utility.service';

@Component({
  selector: 'app-edit-utility-dialog',
  templateUrl: './edit-utility-dialog.component.html',
  styleUrls: ['./edit-utility-dialog.component.css']
})
export class EditUtilityDialogComponent implements OnInit {

  form : FormGroup;
  utilityForUpdate : AdventureUtilityDto;
  constructor(private dialogRef: MatDialogRef<EditUtilityDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
  private adventureUtilityService : AdventureUtilityService) { 
    this.utilityForUpdate = {} as AdventureUtilityDto;
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
    if(this.form.invalid){
      return;
    }

    this.utilityForUpdate = {
      name: this.form.get('name')?.value,
      price: this.form.get('price')?.value,
      adventureId : this.data.adventureId
    }
    this.adventureUtilityService.updateAdventureUtility(this.data.id,this.utilityForUpdate).subscribe((data) => {
      console.log("success!")
    });
    this.dialogRef.close(this.form.value);
  }

}
