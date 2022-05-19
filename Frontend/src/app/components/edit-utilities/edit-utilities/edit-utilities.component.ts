import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UtilityDto } from 'src/app/interfaces/utility-dto';
import { UtilityService } from 'src/app/services/UtilityService/utility.service';


@Component({
  selector: 'app-edit-utilities',
  templateUrl: './edit-utilities.component.html',
  styleUrls: ['./edit-utilities.component.css']
})
export class EditUtilitiesComponent implements OnInit {

  utility:any;

  id:any;
  utilities:UtilityDto[]=[];

  constructor(private unilityService:UtilityService,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.unilityService.findUtilityById(this.id).subscribe((data) => {
      this.utilities = data;
     
    });
  }

  addUtility() { 
  
  }
  deleteUtility(id:any) {
    let index = 0;
    for (let utility of this.utilities) {
      if (utility.id == id) {
        this.utilities.splice(index, 1);
      }
      index++;
    }
  


  }

}
