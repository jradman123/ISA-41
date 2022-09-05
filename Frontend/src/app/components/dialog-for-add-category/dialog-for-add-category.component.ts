import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { CategoryDto } from 'src/app/interfaces/category-dto';
import { CategoryService } from 'src/app/services/CategoryService/category.service';

@Component({
  selector: 'app-dialog-for-add-category',
  templateUrl: './dialog-for-add-category.component.html',
  styleUrls: ['./dialog-for-add-category.component.css']
})
export class DialogForAddCategoryComponent implements OnInit {

  category!: CategoryDto;
  detailForm!: FormGroup;
  sub!: Subscription;
  constructor(private categoryService: CategoryService, public dialog: MatDialog, 
   public dialogRef: MatDialogRef<DialogForAddCategoryComponent>) { 
    this.category = {} as CategoryDto;
   }

  ngOnInit(): void {
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
    this.categoryService.addNew(this.category).subscribe((data) => {this.onNoClick});
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
