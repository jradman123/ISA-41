import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { AdventureUtilityDto } from 'src/app/interfaces/adventure-utility-dto';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { AdventureUtilityService } from 'src/app/services/AdventureUtilityService/adventure-utility.service';

@Component({
  selector: 'app-adventure-utilities',
  templateUrl: './adventure-utilities.component.html',
  styleUrls: ['./adventure-utilities.component.css']
})
export class AdventureUtilitiesComponent implements OnInit {

  id : any;
  newAdventureUtility : AdventureUtilityDto;
  utilities!: MatTableDataSource<ResponseUtility>;
  columnsToDisplayUtilities: string[] = [
    'Name',
    'Price',
    'Buttons'
  ];
  constructor(private adventureService : AdventureService,private router: ActivatedRoute,
    private adventureUtilityService : AdventureUtilityService) {
    this.newAdventureUtility = {} as AdventureUtilityDto;
    this.utilities = new MatTableDataSource<ResponseUtility>();
   }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.getUtilities();
  }

  getUtilities() {
    this.adventureService.getAdventureUtilities(this.id).subscribe({
      next: (res : ResponseUtility[]) => {
        this.utilities.data = res
    }
  });
  }

  utilityForm = new FormGroup({ 
    name: new FormControl('', [Validators.required]),
    price: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
  });

}
