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
import { MatTableDataSource } from '@angular/material/table';
import { DialogForGuestDataComponent } from '../../dialog-for-guest-data/dialog-for-guest-data.component';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { CottageAvailability } from 'src/app/interfaces/cottage-availability';
import { AvailabilityService } from 'src/app/services/availabilityService/availability.service';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { DialogForAppointmentComponent } from '../../dialog-for-appointment/dialog-for-appointment.component';

export interface DataForDialogGuest {
  clientEmail: string;
}


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
  utilityy!: UtilityDto;
  users!: PersonalData[];
  newReservation!: CottageReservation
  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  reservations!: MatTableDataSource<CottageReservation>;
  newAvailability!: CottageAvailability;
  availabilities: CottageAvailability[] = [];
  startAvailableDate: any = null;
  endAvailableDate: any = null;
  appointments: AppointmentDto[] = []




  uploaded: boolean = false;
  fileToUpload!: File;
  image: Image;
  imagesResponse: ImagesResponse;
  images: Image[];

  columnsToDisplayCottageReservations: string[] = [
    'No.',
    'StartDate',
    'EndDate',
    'Price',
    'NumberOfPerson',
    'ClientEmail',
    'Buttons'
  ];


  constructor(private route: Router, private appointmentService: AppointmentService, private availabilityService: AvailabilityService, private reservationService: ReservationService, private userService: UserService, private cottageService: CottageService, private imageService: ImageService, private router: ActivatedRoute, private ruleService: RuleService, public dialog: MatDialog, private utilityService: UtilityService) {
    this.rulee = {} as RuleDto;
    this.utilityy = {} as UtilityDto;
    this.newReservation = {} as CottageReservation;
    this.reservations = new MatTableDataSource<CottageReservation>();
    this.newAvailability = {} as CottageAvailability;

    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];

  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;

    this.appointmentService.findAppByCottage(this.id).subscribe((data) => {
      this.appointments = data;

    });

    this.availabilityService.findAvailabilityByCottage(this.id).subscribe((data) => {
      this.availabilities = data;

    });

    this.reservationService.getCottageReservationById(this.id)
      .subscribe({
        next: (reservations: CottageReservation[]) => {
          this.reservations.data = reservations;

        },
      });


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



    this.form = new FormGroup({
      clientEmail: new FormControl('', Validators.required),
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      numberOfPerson: new FormControl('', Validators.required)
    })

    this.getImages();

  }

  getImages() {
    this.cottageService.getCottageImages(this.id).subscribe({
      next: (res) => {
        console.log(res);
        this.imagesResponse = res
        this.imagesResponse.images.forEach((image) => {
          this.images.push(image)
        })
      }
    });
  }

  toBase64 = (file: Blob) =>
    new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
    });

  async uploadPicture() {
    if (this.uploaded) {
      await this.toBase64(this.fileToUpload).then(
        (res) => (this.image.url = res as string)
      );
    }
  }

  onFileSelected(event: any): void {
    this.fileToUpload = <File>event.target.files[0];
    this.uploaded = true;
    this.uploadPicture().then((resultt) => {
      this.cottageService.addImage(this.id, this.image).subscribe((data) => {
        this.cottage = data;
        this.images = [];
        this.getImages();
        window.location.reload();
      });
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

  viewPersonalData(clientEmail: string) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForGuestDataComponent, {

      data: { clientEmail: clientEmail },
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;

    });

  }

  addAvailability() {

    let startDate = new Date(this.startAvailableDate);
    let endDate = new Date(this.endAvailableDate);
    this.newAvailability.cottageId = this.cottage.id;
    this.newAvailability.startDate = startDate;
    this.newAvailability.endDate = endDate;
    console.log("fedfefdfdf0" + this.newAvailability.startDate)

    this.availabilityService.addAvailabilityCottage(this.newAvailability).subscribe((data) => {

      console.log(this.cottage)
      window.location.reload();
    });

  }

  deleteApp(id: string) {
    this.appointmentService.deleteApp(id)
      .subscribe(data => {

        window.location.reload();

      });

  }

  addApp() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForAppointmentComponent, {

      data: { id: this.id },
    })
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;

    });

  }
}