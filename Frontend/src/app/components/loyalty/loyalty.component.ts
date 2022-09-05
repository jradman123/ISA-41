import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CategoryResponse } from 'src/app/interfaces/category-response';
import { CategoryService } from 'src/app/services/CategoryService/category.service';
import { DialogForAddCategoryComponent } from '../dialog-for-add-category/dialog-for-add-category.component';
import { DialogForEditCategoryComponent } from '../dialog-for-edit-category/dialog-for-edit-category.component';

@Component({
  selector: 'app-loyalty',
  templateUrl: './loyalty.component.html',
  styleUrls: ['./loyalty.component.css']
})
export class LoyaltyComponent implements OnInit {

  categories : CategoryResponse[];
  constructor(private categoryService : CategoryService,public dialog: MatDialog,) {
    this.categories = [] as CategoryResponse[];
   }

  ngOnInit(): void {
    this.refresh();
  }
  refresh(){
    this.categoryService.getAll().subscribe((data) => {this.categories = data;});
  }

  openDialog() {

    const dialogRef = this.dialog.open(DialogForAddCategoryComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.categories=[];
      this.refresh();
    });
  }

  edit(category : CategoryResponse) {

    const dialogRef = this.dialog.open(DialogForEditCategoryComponent,{
      data : category
    });

    dialogRef.afterClosed().subscribe(result => {
      this.categories=[];
      this.refresh();
    });
  }

}
