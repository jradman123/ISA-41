import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { windowTime } from 'rxjs/operators';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';


@Component({
  selector: 'app-edit-utilities',
  templateUrl: './edit-utilities.component.html',
  styleUrls: ['./edit-utilities.component.css']
})
export class EditUtilitiesComponent implements OnInit {

  utility: any;

  utilityy!: UtilityDto;
  idCottage: any;
  utilities: UtilityDto[] = [];

  constructor(private utilityService: UtilityService, private router: ActivatedRoute) {

    this.utilityy = {} as UtilityDto;
  }

  ngOnInit(): void {
    this.findUtilities();
  }

  findUtilities() {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.utilityService.findUtilityById(this.idCottage).subscribe((data) => {
      this.utilities = data;

    });
  }

  addUtility() {
    this.utilityy.cottageId = this.idCottage;
    console.log("fedfefdfdf0" + this.utilityy.name)

    this.utilityService.addCottageUtility(this.utilityy).subscribe((data) => {

      this.utilities = []
      this.findUtilities();

    });


  }
  deleteUtility(id: any) {


    this.utilityService.deleteUtility(id, this.idCottage)
      .subscribe(data => {
        this.utilities = []
        this.findUtilities();


      });



  }

}
