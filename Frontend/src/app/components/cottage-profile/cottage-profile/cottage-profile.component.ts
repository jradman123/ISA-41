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
import { DialogForReportComponent } from '../../dialog-for-report/dialog-for-report.component';
import { DialogForAddReportComponent } from '../../dialog-for-add-report/dialog-for-add-report.component';
import { ReportService } from 'src/app/services/ReportService/report.service';
import { RoomService } from 'src/app/services/RoomService/room.service';
import Swal from 'sweetalert2';
import { CottageQuickReservationResponse } from 'src/app/interfaces/cottage-quick-reservation-response';
import { AgmCoreModule } from '@agm/core';

export interface DataForDialogGuest {
  clientEmail: string;
}


export interface DataForDialogCottage {
  id: string;
  clientEmail: string;
}

export interface DataForReport {
  idReservation: string;
}


@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {


  fullPrice: number = 0;

  cottage: CottageDto;
  id: any;

  rooms: RoomDto[] = [];

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
  appointments: CottageQuickReservationResponse[] = []
  editMode = false
  viewOff = false;
  initialDetails: any
  updateCottage: CottageDto
  email: any
  pastReservations!: MatTableDataSource<CottageReservation>;
  roomm!: RoomDto
  haveReservations!: CottageReservation[]





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


  constructor(private roomService: RoomService, private reportService: ReportService, private route: Router, private appointmentService: AppointmentService, private availabilityService: AvailabilityService, private reservationService: ReservationService, private userService: UserService, private cottageService: CottageService, private imageService: ImageService, private router: ActivatedRoute, private ruleService: RuleService, public dialog: MatDialog, private utilityService: UtilityService) {
    this.newReservation = {} as CottageReservation;
    this.reservations = new MatTableDataSource<CottageReservation>();
    this.newAvailability = {} as CottageAvailability;
    this.cottage = {} as CottageDto;
    this.updateCottage = {} as CottageDto
    this.pastReservations = new MatTableDataSource<CottageReservation>();
    this.roomm = {} as RoomDto;
    this.appointments = [] as CottageQuickReservationResponse[];


    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];

  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.reservationService.getCottageReservationById(this.id).subscribe((data) => {
      this.haveReservations = data;

    });


    this.findAppointments();
    this.findAvailability();
    this.findReservations();
    this.findUsers();
    this.findCottages();

    this.findRooms();
    this.getImages();
    this.findPastReservations();


    this.form = new FormGroup({
      clientEmail: new FormControl('', Validators.required),
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      numberOfPerson: new FormControl('', Validators.required)
    })



  }


  parseStringToDate(dateTime: string) {
    return new Date(Date.parse(dateTime))
  }

  findPastReservations() {

    this.reservationService.getPastCottageReservationById(this.id)
      .subscribe({
        next: (pastReservations: CottageReservation[]) => {
          this.pastReservations.data = pastReservations;

        },
      });


  }

  findCottages() {
    this.cottageService.findbyId(this.id).subscribe({
      next: (data: CottageDto) => {
        this.cottage = data
        this.initialDetails = JSON.parse(JSON.stringify(data));
        this.detailsForm.controls['name'].setValue(data.name)
        this.detailsForm.controls['description'].setValue(data.description)
        this.detailsForm.controls['price'].setValue(data.price)
        this.detailsForm.controls['streetName'].setValue(data.streetName)
        this.detailsForm.controls['streetNumber'].setValue(data.streetNumber)
        this.detailsForm.controls['city'].setValue(data.city)
        this.detailsForm.controls['country'].setValue(data.country)
        this.detailsForm.controls['numberOfPeople'].setValue(data.numberOfPeople)
      },
    });
  }

  detailsForm = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    description: new FormControl('', [
      Validators.required
    ]),
    streetName: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    streetNumber: new FormControl(null, [
      Validators.required,
      Validators.pattern('^\\d{1,3}$'),
    ]),
    city: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    country: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    price: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
    numberOfPeople: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}$')])
  })

  findAvailability() {

    this.availabilityService.findAvailabilityByCottage(this.id).subscribe((data) => {
      this.availabilities = data;

    });
  }

  findAppointments() {
    this.appointmentService.findAppByCottage(this.id).subscribe((data) => {
      this.appointments = data;
      console.log("TERMIIIN" + this.appointments)


    });
  }
  findRooms() {

    this.cottageService.findRoomsById(this.id).subscribe((data) => {
      this.rooms = data;
      console.log(this.rooms);
    });

  }



  findUsers() {
    this.userService.findAll().subscribe((data) => {
      this.users = data;
    });
  }

  findReservations() {

    this.reservationService.getCottageReservationById(this.id)
      .subscribe({
        next: (reservations: CottageReservation[]) => {
          this.reservations.data = reservations;

        },
      });

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

        this.images = [];
        this.getImages();

      });
    });
  }







  deletePicture(idP: any) {


    this.imageService.deletePicture(idP, this.id)
      .subscribe(data => {
        window.location.reload();





      });

  }

  addReservation(clientEmail: string) {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForReservationCottageComponent, {

      data: { clientEmail: clientEmail, id: this.id },
    })
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');


    });

  }






  onNoClick(): void {
    window.location.reload();

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



  deleteApp(id: string) {
    this.appointmentService.deleteApp(id)
      .subscribe(data => {

        this.appointments = []
        this.findAppointments();

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
      this.appointments = [];
      this.findAppointments();

    });

  }


  edit() {
    if (this.detailsForm.invalid) {
      return;
    }



    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The cottage cannot be changed because it has a reservation!',
      })

    }
    else {

      this.email = localStorage.getItem('email')
      this.updateCottage = {
        name: this.detailsForm.get('name')?.value,
        description: this.detailsForm.get('description')?.value,
        price: this.detailsForm.get('price')?.value,
        streetName: this.detailsForm.get('streetName')?.value,
        streetNumber: this.detailsForm.get('streetNumber')?.value,
        city: this.detailsForm.get('city')?.value,
        country: this.detailsForm.get('country')?.value,
        id: this.id,
        ownerEmail: this.email,
        numberOfPeople: this.detailsForm.get('numberOfPeople')?.value,
        longitude: this.cottage.longitude,
        latitude: this.cottage.latitude,
      }
      this.cottageService.editCottage(this.updateCottage).subscribe((data) => {
        this.updateCottage = data
        this.initialDetails = JSON.parse(JSON.stringify(data));
        this.editMode = false
      })
      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully changed a  cottage!',
      })
    }
  }

  cancel() {
    this.editMode = false
    this.detailsForm.controls['name'].setValue(this.initialDetails.name)
    this.detailsForm.controls['description'].setValue(this.initialDetails.description)
    this.detailsForm.controls['cancellationConditions'].setValue(this.initialDetails.cancellationConditions)
    this.detailsForm.controls['price'].setValue(this.initialDetails.price)
    this.detailsForm.controls['streetName'].setValue(this.initialDetails.streetName)
    this.detailsForm.controls['streetNumber'].setValue(this.initialDetails.streetNumber)
    this.detailsForm.controls['city'].setValue(this.initialDetails.city)
    this.detailsForm.controls['country'].setValue(this.initialDetails.country)
    this.detailsForm.controls['guestLimit'].setValue(this.initialDetails.guestLimit)
  }

  dialogReport(id: any) {


    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForAddReportComponent, {

      data: { idReservation: id },
    })
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');

      window.location.reload()


    });

  }

  dialogViewReport(id: any) {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(DialogForReportComponent, {

      data: { idReservation: id },
    })
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');


    });

  }

  addRoom() {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The room cannot be added because cottage has a reservation!',
      })

    }
    else {

      console.log(this.roomm)
      this.roomm.cottageId = this.id;
      this.roomService.addRoom(this.roomm).subscribe((data) => {


        this.rooms = []
        this.findRooms();
      });
    }
  }

  deleteRoom(idR: any) {
    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The room cannot be deleted because cottage has a reservation!',
      })

    }
    else {


      this.roomService.deleteRoom(idR, this.id)
        .subscribe(data => {

          this.rooms = []
          this.findRooms();






        });
    }
  }
  addNew(element: any) {
    console.log("fdffdfd" + element.resStart)
    console.log("fsfsf" + new Date())

    return new Date(Date.parse(element.resStart)) <= new Date()
  }
}