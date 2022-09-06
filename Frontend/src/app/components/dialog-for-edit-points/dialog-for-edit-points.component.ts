import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { PointsDto } from 'src/app/interfaces/points-dto';
import { CategoryService } from 'src/app/services/CategoryService/category.service';

@Component({
  selector: 'app-dialog-for-edit-points',
  templateUrl: './dialog-for-edit-points.component.html',
  styleUrls: ['./dialog-for-edit-points.component.css']
})
export class DialogForEditPointsComponent implements OnInit {

  points!: PointsDto;
  detailForm!: FormGroup;
  sub!: Subscription;
  constructor(private categoryService: CategoryService, public dialog: MatDialog, 
   public dialogRef: MatDialogRef<DialogForEditPointsComponent>,@Inject(MAT_DIALOG_DATA) public data: PointsDto) { 
    this.points = {} as PointsDto;
   }

  ngOnInit(): void {

    this.detailsForm.controls['client'].setValue(this.data.client)
    this.detailsForm.controls['owner'].setValue(this.data.owner)
    this.detailsForm.controls['discount'].setValue(this.data.keepsApp)
  }

  detailsForm = new FormGroup({
    client: new FormControl('', [
      Validators.required
    ]),
    owner: new FormControl(null, [
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

    this.createPoints();
    this.categoryService.editPoints(this.points).subscribe((data) => {this.onNoClick});
    this.onNoClick();


  }
  createPoints() {
    this.points = {
      client: this.detailsForm.get('client')?.value,
      owner: this.detailsForm.get('owner')?.value,
      keepsApp : this.detailsForm.get('discount')?.value,
      id : this.data.id
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }



}
