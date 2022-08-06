import { Component, OnInit } from '@angular/core';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { ActivatedRoute } from '@angular/router';
import { AsyncKeyword } from 'typescript';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { Router } from '@angular/router';
import { RoomDto } from 'src/app/interfaces/room-dto';
import { ImageService } from 'src/app/services/ImageService/image.service';
import { ImageDto } from 'src/app/interfaces/image-dto';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { DialogForReservationCottageComponent } from '../../dialog-for-reservation-cottage/dialog-for-reservation-cottage.component';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { UserService } from 'src/app/services/UserService/user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { Subscription } from 'rxjs';



export interface DataForDialogCottage {
  id: string;
}

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {


  fullPrice: number = 0;
  rulee!: RuleDto
  cottage!: CottageDto;
  id: any;
  rules: RuleDto[] = [];
  utilities: UtilityDto[] = [];
  rooms: RoomDto[] = [];
  images: ImageDto[] = [];
  utilityy!: UtilityDto;
  users!: PersonalData[];
  newReservation!: CottageReservation
  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;

  constructor(private route: Router, private reservationService: ReservationService, private userService: UserService, private cottageService: CottageService, private imageService: ImageService, private router: ActivatedRoute, private ruleService: RuleService, public dialog: MatDialog, private utilityService: UtilityService) {
    this.rulee = {} as RuleDto;
    this.utilityy = {} as UtilityDto;
    this.newReservation = {} as CottageReservation;
  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;

    this.userService.findAll().subscribe((data) => {
      this.users = data;
    });
    this.cottageService.findbyId(this.id).subscribe((data) => {
      this.cottage = data;
    });

    this.ruleService.findRulebyId(this.id).subscribe((data) => {
      this.rules = data;

    });

    this.utilityService.findUtilityById(this.id).subscribe((data) => {
      this.utilities = data;
      console.log(this.utilities);
    });

    this.cottageService.findRoomsById(this.id).subscribe((data) => {
      this.rooms = data;
      console.log(this.utilities);
    });
    this.imageService.findImageByCottageId(this.id).subscribe((data) => {
      this.images = data;
      console.log(this.images);
    });


    this.form = new FormGroup({
      clientEmail: new FormControl('', Validators.required),
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      numberOfPerson: new FormControl('', Validators.required)
    })

  }

  addReservation() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForReservationCottageComponent, {

      data: { id: this.id },
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;

    });
  }


  deletePicture(idP: any) {


    this.imageService.deletePicture(idP, this.id)
      .subscribe(data => {
        window.location.reload();





      });

  }

  addRule() {

    this.rulee.cottageId = this.id;

    console.log(this.rulee)
    this.ruleService.addRule(this.rulee).subscribe((data) => {
      window.location.reload();


    });


  }
  deleteRule(idR: any) {

    this.ruleService.deleteRule(idR, this.id)
      .subscribe(data => {
        window.location.reload();





      });
  }

  addUtility() {
    this.utilityy.cottageId = this.id;
    console.log("fedfefdfdf0" + this.utilityy.name)

    this.utilityService.addCottageUtility(this.utilityy).subscribe((data) => {

      window.location.reload();

    });


  }
  deleteUtility(idU: any) {


    this.utilityService.deleteUtility(idU, this.id)
      .subscribe(data => {
        window.location.reload();


        console.log(this.id)



      });



  }

  reserved(): void {
    this.reservateCottage();
    this.sub = this.reservationService.reservatedCottage(this.newReservation)
      .subscribe({
        next: () => {

          window.location.reload();

        }
      });
  }

  onNoClick(): void {
    window.location.reload();

  }

  reservateCottage(): void {


    let newStart = new Date(this.form.value.resStart)
    let newEnd = new Date(this.form.value.resEnd)


    console.log(newStart)
    console.log(newEnd)
    this.newReservation.resStart = new Date(newStart.setHours(14, 0, 0, 0)),
      this.newReservation.resEnd = new Date(newEnd.setHours(11, 0, 0, 0)),
      this.newReservation.numberOfPerson = this.form.value.numberOfPerson;
    this.newReservation.price = this.cottage.price;
    this.newReservation.clientEmail = this.form.value.clientEmail;
    this.newReservation.objectId = this.id;
    this.newReservation.typeOfRes = 'COTTAGE';

  }

}