import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';
import { EditCottageUtilityDialogComponent } from '../edit-cottage-utility-dialog/edit-cottage-utility-dialog.component';

@Component({
  selector: 'app-edit-ship-utilities',
  templateUrl: './edit-ship-utilities.component.html',
  styleUrls: ['./edit-ship-utilities.component.css']
})
export class EditShipUtilitiesComponent implements OnInit {


  utility: any;

  utilityy!: UtilityDto;
  idShip: any;
  utilities: UtilityDto[] = [];

  constructor(public dialog: MatDialog, private utilityService: UtilityService, private router: ActivatedRoute) {

    this.utilityy = {} as UtilityDto;
  }

  ngOnInit(): void {
    this.findUtilities();
  }

  findUtilities() {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.utilityService.findShipUtilityById(this.idShip).subscribe((data) => {
      this.utilities = data;

    });
  }

  addUtility() {
    this.utilityy.shipId = this.idShip;
    console.log("fedfefdfdf0" + this.utilityy.name)

    this.utilityService.addShipUtility(this.utilityy).subscribe((data) => {

      this.utilities = []
      this.findUtilities();

    });


  }


  deleteUtility(id: any) {


    this.utilityService.deleteUtilitybyShip(id, this.idShip)
      .subscribe(data => {
        this.utilities = []
        this.findUtilities();


      });



  }


}
