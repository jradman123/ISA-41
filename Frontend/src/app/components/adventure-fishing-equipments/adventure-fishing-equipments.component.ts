import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { FishingEquipmentDto } from 'src/app/interfaces/fishing-equipment-dto';
import { ResponseFishingEquipment } from 'src/app/interfaces/response-fishing-equipment';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { FishingEquipmentService } from 'src/app/services/FishingEquipmentService/fishing-equipment.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-adventure-fishing-equipments',
  templateUrl: './adventure-fishing-equipments.component.html',
  styleUrls: ['./adventure-fishing-equipments.component.css']
})
export class AdventureFishingEquipmentsComponent implements OnInit {

  id : any;
  newFishingEquipment : FishingEquipmentDto;
  equipments : ResponseFishingEquipment[];
  reservationsExist! : string;
  constructor(private adventureService : AdventureService,private router: ActivatedRoute,
    private fishingEquipmentService : FishingEquipmentService,private _snackBar : MatSnackBar,
    private reservationService : ReservationService) { 
    this.newFishingEquipment = {} as FishingEquipmentDto;
    this.equipments = [] as ResponseFishingEquipment[];
  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.getEquipments();
    this.checkReservations();
  }

  checkReservations() {
    this.reservationService.reservationsExistForAdventure(this.id)
    .subscribe({
      next: (result: any) => {
        this.reservationsExist = result;
      },
    });
  }

  getEquipments() {
    this.adventureService.getAdventureFishingEquipment(this.id).subscribe({
      next: (res) => {
        console.log(res);
        this.equipments = res
    }
  });
  }

  fishingEquipmentForm = new FormGroup({ 
    name: new FormControl('', [
    Validators.required
  ])});

  addFishingEquipment() {
    if(this.fishingEquipmentForm.invalid){
      this._snackBar.open(
        'Name of fishing equipment cannot be empty.',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
      return;
    }
    if (this.reservationsExist === 'TRUE') {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The fishing equipment cannot be added because adventure has a reservation!',
      })
      return;

    }
    this.newFishingEquipment = {
      adventureId : this.id,
      fishingEquipmentName: this.fishingEquipmentForm.get('name')?.value
    }
    this.fishingEquipmentService.addAdventureFishingEquipment(this.newFishingEquipment).subscribe((data) => {
      this.fishingEquipmentForm.reset();
      this.equipments = [];
      this.getEquipments();
    });
  }

  deleteFishingEquipment(equipmentId: string) {
    if (this.reservationsExist === 'TRUE') {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'The fishing equipment cannot be deleted because adventure has a reservation!',
      })
      return;

    }
    this.fishingEquipmentService.deleteFishingEquipment(equipmentId)
      .subscribe((data) => {
        this.equipments = [];
        this.getEquipments();
      });
  }

}
