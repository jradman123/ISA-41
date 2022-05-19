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

  utility:any;

  idCottage:any;
  utilities:UtilityDto[]=[];

  constructor(private utilityService:UtilityService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.utilityService.findUtilityById(this.idCottage).subscribe((data) => {
      this.utilities = data;
     
    });
  }

  addUtility() { 
  
  }
  deleteUtility(id:any) {
   /* let index = 0;
    for (let utility of this.utilities) {
      if (utility.id == id) {
        this.utilities.splice(index, 1);
      }
      index++;
    }*/

    this.utilityService.deleteUtility(id,this.idCottage)
    .subscribe(data => {
      window.location.reload();
      
      console.log(id);
      console.log(this.idCottage)
  
     

    });
  


  }

}
