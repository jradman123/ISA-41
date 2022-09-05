import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageQuickReservationResponse } from 'src/app/interfaces/cottage-quick-reservation-response';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { PersonalData } from 'src/app/interfaces/personal-data';
import { RoomDto } from 'src/app/interfaces/room-dto';
import { Image } from 'src/app/interfaces/image';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { RuleService } from 'src/app/services/RuleService/rule.service';
import { RoomService } from 'src/app/services/RoomService/room.service';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { UserService } from 'src/app/services/UserService/user.service';
import { ReportService } from 'src/app/services/ReportService/report.service';
import { AvailabilityService } from 'src/app/services/availabilityService/availability.service';
import { ImageService } from 'src/app/services/ImageService/image.service';
import { DialogForGuestDataComponent } from 'src/app/components/dialog-for-guest-data/dialog-for-guest-data.component';
import { DialogForReportComponent } from 'src/app/components/dialog-for-report/dialog-for-report.component';

@Component({
  selector: 'app-cottage-view',
  templateUrl: './cottage-view.component.html',
  styleUrls: ['./cottage-view.component.css']
})
export class CottageViewComponent implements OnInit {

  fullPrice: number = 0;

  cottage: CottageDto;
  id: any;

  rooms: RoomDto[] = [];

  users!: PersonalData[];
  form!: FormGroup;
  formData!: FormData;
  sub!: Subscription;
  reservations!: MatTableDataSource<CottageReservation>;
  startAvailableDate: any = null;
  endAvailableDate: any = null;
  appointments: CottageQuickReservationResponse[] = []
  editMode = false
  viewOff = false;
  initialDetails: any
  email: any
  pastReservations!: MatTableDataSource<CottageReservation>;
  roomm!: RoomDto
  haveReservations!: CottageReservation[]
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
  constructor( 
     private appointmentService: AppointmentService, 
     private reservationService: ReservationService, private userService: UserService, 
     private cottageService: CottageService, private router: ActivatedRoute, 
     private ruleService: RuleService, public dialog: MatDialog) { 

    this.reservations = new MatTableDataSource<CottageReservation>();
    this.cottage = {} as CottageDto;
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
          this.detailsForm.controls['cancelled'].setValue(data.cancelled_conditions)
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
      numberOfPeople: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}$')]),
  
      cancelled: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}$')])
  
    })

    findAppointments() {
      this.appointmentService.findAppByCottage(this.id).subscribe((data) => {
        this.appointments = data;
  
  
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

}
