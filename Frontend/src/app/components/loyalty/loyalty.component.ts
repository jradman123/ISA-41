import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CategoryResponse } from 'src/app/interfaces/category-response';
import { PointsDto } from 'src/app/interfaces/points-dto';
import { CategoryService } from 'src/app/services/CategoryService/category.service';
import { DialogForAddCategoryComponent } from '../dialog-for-add-category/dialog-for-add-category.component';
import { DialogForEditCategoryComponent } from '../dialog-for-edit-category/dialog-for-edit-category.component';
import { DialogForEditPointsComponent } from '../dialog-for-edit-points/dialog-for-edit-points.component';

@Component({
  selector: 'app-loyalty',
  templateUrl: './loyalty.component.html',
  styleUrls: ['./loyalty.component.css']
})
export class LoyaltyComponent implements OnInit {

  categories : CategoryResponse[];
  points : PointsDto;
  constructor(private categoryService : CategoryService,public dialog: MatDialog,) {
    this.categories = [] as CategoryResponse[];
    this.points = {} as PointsDto;
   }

  ngOnInit(): void {
    this.refresh();
    this.findPoints();
  }
  findPoints() {
    this.categoryService.get().subscribe((data) => {this.points = data;});
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

  editCategory(category : CategoryResponse) {

    const dialogRef = this.dialog.open(DialogForEditCategoryComponent,{
      data : category
    });

    dialogRef.afterClosed().subscribe(result => {
      this.categories=[];
      this.refresh();
    });
  }

  editPoints(point : PointsDto) {

    const dialogRef = this.dialog.open(DialogForEditPointsComponent,{
      data : point
    });

    dialogRef.afterClosed().subscribe(result => {
      this.findPoints();
    });
  }

}
