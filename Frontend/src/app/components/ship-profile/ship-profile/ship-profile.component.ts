import { Component, OnInit } from '@angular/core';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ActivatedRoute, Router } from '@angular/router';
import { AsyncKeyword } from 'typescript';
import { RuleDto } from 'src/app/interfaces/rule-dto';
import { RuleService } from 'src/app/services/RuleService/rule.service';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { NavigationService } from 'src/app/services/NavigationService/navigation.service';
import { NavigationDto } from 'src/app/interfaces/navigation-dto';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { AppointmentDto } from 'src/app/interfaces/appointment-dto';
import { AppointmentService } from 'src/app/services/AppointmentService/appointment.service';
import { AgmCoreModule } from '@agm/core';

import Swal from 'sweetalert2';
import { CottageReservation } from 'src/app/interfaces/cottage-reservation';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import { ShipQuickReservationResponse } from 'src/app/interfaces/ship-quick-reservation-response';
@Component({
  selector: 'app-ship-profile',
  templateUrl: './ship-profile.component.html',
  styleUrls: ['./ship-profile.component.css']

})
export class ShipProfileComponent implements OnInit {

  ship!: ShipDto;
  appointments: ShipQuickReservationResponse[] = []
  id: any;
  rules: RuleDto[] = [];
  utilities: UtilityDto[] = [];
  navigations: NavigationDto[] = [];
  initialDetails: any
  email: any
  editMode = false;
  updateShip: ShipDto;
  uploaded: boolean = false;
  fileToUpload!: File;
  image: Image;
  imagesResponse: ImagesResponse;
  images: Image[];
  viewOff = false;
  haveReservations!: CottageReservation[]

  constructor(private shipService: ShipService, private reservationService: ReservationService, private appointmentService: AppointmentService, private navigationService: NavigationService, private router: ActivatedRoute, private ruleService: RuleService, private utilityService: UtilityService, private route: Router) {
    this.updateShip = {} as ShipDto;
    this.image = {} as Image;
    this.imagesResponse = {} as ImagesResponse;
    this.images = [] as Image[];
    this.ship = {} as ShipDto;



  }



  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.shipService.findbyId(this.id).subscribe({
      next: (data: ShipDto) => {
        this.ship = data
        this.initialDetails = JSON.parse(JSON.stringify(data));
        this.detailsForm.controls['name'].setValue(data.name)
        this.detailsForm.controls['description'].setValue(data.description)
        this.detailsForm.controls['price'].setValue(data.price)
        this.detailsForm.controls['street'].setValue(data.streetName)
        this.detailsForm.controls['streetNumber'].setValue(data.streetNumber)
        this.detailsForm.controls['cityName'].setValue(data.city)
        this.detailsForm.controls['countryName'].setValue(data.country)
        this.detailsForm.controls['capacity'].setValue(data.capacity)
        this.detailsForm.controls['type'].setValue(data.type)
        this.detailsForm.controls['maxSpeed'].setValue(data.maxSpeed)
        this.detailsForm.controls['strengthOfEngine'].setValue(data.strengthOfEngine)
        this.detailsForm.controls['numberOfEngine'].setValue(data.numberOfEngine)
        this.detailsForm.controls['length'].setValue(data.length)
        this.detailsForm.controls['cancelationConditions'].setValue(data.cancelationConditions)
        this.detailsForm.controls['fishingEquipment'].setValue(data.fishingEquipment)

      },
    });

    this.getImages();
    this.findAppointments();



    this.reservationService.getShipReservationById(this.id).subscribe((data) => {
      this.haveReservations = data;

    });


  }

  findAppointments() {
    this.appointmentService.findAppByShip(this.id).subscribe((data) => {
      this.appointments = data;

    });
  }

  getImages() {
    this.shipService.getShipImages(this.id).subscribe({
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
      this.shipService.addImage(this.id, this.image).subscribe((data) => {
        this.ship = data;
        this.images = [];
        this.getImages();
        window.location.reload();

      });
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
    street: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    streetNumber: new FormControl(null, [
      Validators.required,
      Validators.pattern('^\\d{1,3}$'),
    ]),
    cityName: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    countryName: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    strengthOfEngine: new FormControl(null, [
      Validators.required,

    ]),
    maxSpeed: new FormControl(null, [
      Validators.required,

    ]),
    numberOfEngine: new FormControl(null, [
      Validators.required,

    ]),
    length: new FormControl(null, [
      Validators.required,

    ]),
    type: new FormControl(null, [
      Validators.required,

    ]),
    fishingEquipment: new FormControl(null, [
      Validators.required,
    ]),
    cancelationConditions: new FormControl(null, [
      Validators.required,

    ]),
    price: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
    capacity: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}$')])
  })



  edit() {
    if (this.detailsForm.invalid) {
      return;
    }


    if (this.haveReservations.length != 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The ship cannot be changed because it has a reservation!',
      })

    }
    else {


      this.email = localStorage.getItem('email')
      this.updateShip = {
        name: this.detailsForm.get('name')?.value,
        description: this.detailsForm.get('description')?.value,
        price: this.detailsForm.get('price')?.value,
        streetName: this.detailsForm.get('street')?.value,
        streetNumber: this.detailsForm.get('streetNumber')?.value,
        city: this.detailsForm.get('cityName')?.value,
        country: this.detailsForm.get('countryName')?.value,
        id: this.id,
        ownerEmail: this.email,
        capacity: this.detailsForm.get('capacity')?.value,
        type: this.detailsForm.get('type')?.value,
        maxSpeed: this.detailsForm.get('maxSpeed')?.value,
        strengthOfEngine: this.detailsForm.get('strengthOfEngine')?.value,
        numberOfEngine: this.detailsForm.get('numberOfEngine')?.value,
        length: this.detailsForm.get('length')?.value,
        cancelationConditions: this.detailsForm.get('cancelationConditions')?.value,
        fishingEquipment: this.detailsForm.get('fishingEquipment')?.value,
        longitude: this.ship.longitude,
        latitude: this.ship.latitude,
      }
      this.shipService.editShip(this.updateShip).subscribe((data) => {
        this.updateShip = data
        this.initialDetails = JSON.parse(JSON.stringify(data));
        this.editMode = false
      })
      Swal.fire({
        icon: 'success',
        title: 'Good job!',
        text: 'You have successfully changed a  ship!',
      })

    }
  }

  cancel() {
    this.editMode = false
    this.detailsForm.controls['name'].setValue(this.initialDetails.name)
    this.detailsForm.controls['description'].setValue(this.initialDetails.description)
    this.detailsForm.controls['price'].setValue(this.initialDetails.price)
    this.detailsForm.controls['street'].setValue(this.initialDetails.streetName)
    this.detailsForm.controls['streetNumber'].setValue(this.initialDetails.streetNumber)
    this.detailsForm.controls['cityName'].setValue(this.initialDetails.city)
    this.detailsForm.controls['countryName'].setValue(this.initialDetails.country)
    this.detailsForm.controls['type'].setValue(this.initialDetails.type)
    this.detailsForm.controls['maxSpeed'].setValue(this.initialDetails.maxSpeed)
    this.detailsForm.controls['strengthOfEngine'].setValue(this.initialDetails.strengthOfEngine)
    this.detailsForm.controls['numberOfEngine'].setValue(this.initialDetails.numberOfEngine)
    this.detailsForm.controls['length'].setValue(this.initialDetails.length)
    this.detailsForm.controls['fishingEquipment'].setValue(this.initialDetails.fishingEquipment)
    this.detailsForm.controls['capacity'].setValue(this.initialDetails.capacity)

  }

  deleteApp(id: string) {
    this.appointmentService.deleteAppbyShip(id)
      .subscribe(data => {

        this.appointments = []
        this.findAppointments();

      });

  }

}
