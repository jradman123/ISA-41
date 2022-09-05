import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-a-utilities',
  templateUrl: './a-utilities.component.html',
  styleUrls: ['./a-utilities.component.css']
})
export class AUtilitiesComponent implements OnInit {

  id : any;
  utilities!: MatTableDataSource<ResponseUtility>;
  columnsToDisplayUtilities: string[] = [
    'Name',
    'Price'
  ];
  constructor(private adventureService : AdventureService,private router: ActivatedRoute) {
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


}
