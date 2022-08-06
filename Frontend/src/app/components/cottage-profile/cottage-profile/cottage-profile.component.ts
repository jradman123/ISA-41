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


export interface DataForDialogCottage {
  id: string;
}

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {



  rulee!: RuleDto
  cottage!: CottageDto;
  id: any;
  rules: RuleDto[] = [];
  utilities: UtilityDto[] = [];
  rooms: RoomDto[] = [];
  images: ImageDto[] = [];

  constructor(private route: Router, private cottageService: CottageService, private imageService: ImageService, private router: ActivatedRoute, private ruleService: RuleService, public dialog: MatDialog, private utilityService: UtilityService) {
    this.rulee = {} as RuleDto;
  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
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




  }
  reservationHistory() {
    this.route.navigate(['cottageOwner/reservation-history/' + this.id]);


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

  viewAvailability() {
    this.route.navigate(['cottageOwner/cottage-availability/' + this.id]);


  }

  addAction() {
    this.route.navigate(['cottageOwner/add-action/' + this.id]);

  }

  editCottage() {
    this.route.navigate(['cottageOwner/edit-cottage/' + this.id]);
  }

  editRooms() {
    this.route.navigate(['cottageOwner/edit-rooms/' + this.id]);
  }
  editRules() {
    this.route.navigate(['cottageOwner/edit-rules/' + this.id]);
  }

  editUtilities() {
    this.route.navigate(['cottageOwner/edit-utilities/' + this.id]);

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

}