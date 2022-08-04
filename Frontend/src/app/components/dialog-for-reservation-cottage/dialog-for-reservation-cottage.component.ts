import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { DataForDialogCottage } from '../cottage-profile/cottage-profile/cottage-profile.component';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { UserService } from 'src/app/services/UserService/user.service';


@Component({
  selector: 'app-dialog-for-reservation-cottage',
  templateUrl: './dialog-for-reservation-cottage.component.html',
  styleUrls: ['./dialog-for-reservation-cottage.component.css']
})
export class DialogForReservationCottageComponent implements OnInit {

  fullPrice: number = 0;
  cottage!: CottageDto;
  users!: PersonalData[];
  id: any;

  constructor(private userService: UserService, private route: Router, @Inject(MAT_DIALOG_DATA) public data: DataForDialogCottage, private cottageService: CottageService, public dialog: MatDialog, private router: ActivatedRoute, public dialogRef: MatDialogRef<DialogForReservationCottageComponent>) {

  }

  ngOnInit() {
    this.userService.findAll().subscribe((data) => {
      this.users = data;
    });
    console.log(this.data.id)


    this.cottageService.findbyId(this.data.id).subscribe((data) => {
      this.cottage = data;
    });

  }


  form = new FormGroup({
    clientEmail: new FormControl('', Validators.required),
    resStart: new FormControl('', Validators.required),
    resEnd: new FormControl('', Validators.required),
    numberOfPerson: new FormControl('', Validators.required)
  })

  calcPrice() {
    const date1 = Date.UTC(this.form.value.resStart.getFullYear(), this.form.value.resStart.getMonth(), this.form.value.resStart.getDate());
    const date2 = Date.UTC(this.form.value.resEnd.getFullYear(), this.form.value.resEnd.getMonth(), this.form.value.resEnd.s.endingDate.getDate());
    const different = Math.floor((date1 - date2) / (24 * 60 * 60 * 1000));
    if (different <= 0) { alert("Please enter valid dates!"); return; }
    console.log(different)

  }

}


