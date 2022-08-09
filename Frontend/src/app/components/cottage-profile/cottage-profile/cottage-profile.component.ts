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
  cottage: CottageDto;
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
  editMode = false
  initialDetails: any
  updateCottage: CottageDto
  email: any




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
    this.cottage = {} as CottageDto;
    this.updateCottage = {} as CottageDto

    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];

  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;

    this.findAppointments();
    this.findAvailability();
    this.findReservations();
    this.findUsers();
    this.findCottages();
    this.findRules();
    this.findUtility();
    this.findRooms();
    this.getImages();


    this.form = new FormGroup({
      clientEmail: new FormControl('', Validators.required),
      resStart: new FormControl('', Validators.required),
      resEnd: new FormControl('', Validators.required),
      numberOfPerson: new FormControl('', Validators.required)
    })



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

    });
  }
  findRooms() {

    this.cottageService.findRoomsById(this.id).subscribe((data) => {
      this.rooms = data;
      console.log(this.utilities);
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

  findRules() {
    this.ruleService.findRulebyId(this.id).subscribe((data) => {
      this.rules = data;

    });
  }

  findUtility() {
    this.utilityService.findUtilityById(this.id).subscribe((data) => {
      this.utilities = data;
      console.log(this.utilities);
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
        this.cottage = data;
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

  addRule() {

    if (this.rulee.ruleDescription == null) { alert("Please add rule description!"); return; }

    this.rulee.cottageId = this.id;

    console.log(this.rulee)
    this.ruleService.addRule(this.rulee).subscribe((data) => {
      this.rules = []
      this.findRules();


    });


  }
  deleteRule(idR: any) {

    this.ruleService.deleteRule(idR, this.id)
      .subscribe(data => {
        this.rules = []
        this.findRules();




      });
  }

  addUtility() {
    if (this.utilityy.name == null) { alert("Please add name of utility!"); return; }

    this.utilityy.cottageId = this.id;
    console.log("fedfefdfdf0" + this.utilityy.name)

    this.utilityService.addCottageUtility(this.utilityy).subscribe((data) => {

      this.utilities = []
      this.findUtility();


    });


  }
  deleteUtility(idU: any) {


    this.utilityService.deleteUtility(idU, this.id)
      .subscribe(data => {
        this.utilities = []
        this.findUtility();



      });



  }

  reserved(): void {
    if (this.newReservation.resStart == null
      || this.newReservation.resEnd == null ||
      this.newReservation.numberOfPerson == null ||
      this.newReservation.price == null || this.newReservation.clientEmail == null) { alert("Please fill all fields!"); return; }


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
      this.availabilities = []
      this.findAvailability();

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
      numberOfPeople: this.detailsForm.get('numberOfPeople')?.value
    }
    this.cottageService.editCottage(this.updateCottage).subscribe((data) => {
      this.updateCottage = data
      this.initialDetails = JSON.parse(JSON.stringify(data));
      this.editMode = false
    })
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
}