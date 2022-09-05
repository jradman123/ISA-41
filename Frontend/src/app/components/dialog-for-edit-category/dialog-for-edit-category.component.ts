import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { CategoryDto } from 'src/app/interfaces/category-dto';
import { CategoryResponse } from 'src/app/interfaces/category-response';
import { CategoryService } from 'src/app/services/CategoryService/category.service';

@Component({
  selector: 'app-dialog-for-edit-category',
  templateUrl: './dialog-for-edit-category.component.html',
  styleUrls: ['./dialog-for-edit-category.component.css']
})
export class DialogForEditCategoryComponent implements OnInit {

  category!: CategoryDto;
  detailForm!: FormGroup;
  sub!: Subscription;
  constructor(private categoryService: CategoryService, public dialog: MatDialog, 
   public dialogRef: MatDialogRef<DialogForEditCategoryComponent>,@Inject(MAT_DIALOG_DATA) public data: CategoryResponse) { 
    this.category = {} as CategoryDto;
   }

  ngOnInit(): void {

    this.detailsForm.controls['name'].setValue(this.data.name)
    this.detailsForm.controls['minimum'].setValue(this.data.min)
    this.detailsForm.controls['maximum'].setValue(this.data.max)
    this.detailsForm.controls['discount'].setValue(this.data.discount)
  }

  detailsForm = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    minimum: new FormControl(null, [
      Validators.required
    ]),
    maximum: new FormControl(null, [
      Validators.required
    ]),
    discount: new FormControl(null, [
      Validators.required
    ])
  })
  
  add(){
    if(this.detailsForm.invalid){
      return;
    }

    this.createCategory();
    this.categoryService.edit(this.data.id,this.category).subscribe((data) => {this.onNoClick});
    this.onNoClick();


  }
  createCategory() {
    this.category = {
      name: this.detailsForm.get('name')?.value,
      discount: this.detailsForm.get('discount')?.value,
      min: this.detailsForm.get('minimum')?.value,
      max: this.detailsForm.get('maximum')?.value
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
