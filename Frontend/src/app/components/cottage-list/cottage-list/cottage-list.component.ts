import { CottageDto } from 'src/app/interfaces/cottage-list-view';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { StringLiteralLike } from 'typescript';
import { Subscription } from 'rxjs';
import { ChangeDetectorRef, Component, OnInit, ɵɵqueryRefresh } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-cottage-list',
  templateUrl: './cottage-list.component.html',
  styleUrls: ['./cottage-list.component.css']
})
export class CottageListComponent implements OnInit {

  cottages!: MatTableDataSource<CottageDto>;
  sub!: Subscription;
  columnsToDisplayRegistrationRequests: string[] = [
    'No.',
    'Image',
    'Name',
    'Description',
    'Address',
    'Price',
    'NumberOfPeople',
    'Buttons'
    
  ];
  constructor(private cottageService:CottageService,private changeDetectorRefs: ChangeDetectorRef,private _snackBar: MatSnackBar) {

    this.cottages = new MatTableDataSource<CottageDto>();
  
   }
 

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.sub = this.cottageService
      .findByEmail()
      .subscribe({
        next: (cottages: CottageDto[]) => {
          this.cottages.data = cottages;
          this.changeDetectorRefs.detectChanges();
        },
      });
    }
}
