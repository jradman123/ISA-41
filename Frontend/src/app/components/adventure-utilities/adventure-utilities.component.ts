import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { AdventureUtilityDto } from 'src/app/interfaces/adventure-utility-dto';
import { ResponseUtility } from 'src/app/interfaces/response-utility';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { AdventureUtilityService } from 'src/app/services/AdventureUtilityService/adventure-utility.service';
import { EditUtilityDialogComponent } from '../edit-utility-dialog/edit-utility-dialog.component';

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
    private adventureUtilityService : AdventureUtilityService,public dialog: MatDialog,
    private _snackBar : MatSnackBar) {
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

  deleteUtility(id : string){
    this.adventureUtilityService.deleteAdventureUtility(id).subscribe((data) => {
        this.utilities.data = [];
        this.getUtilities();
    })
  }

  edit(utility : ResponseUtility) {
    console.log(utility);
    const dialogConfig = new MatDialogConfig();
 
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
 
    const dialogRef = this.dialog.open(EditUtilityDialogComponent,{
      data: {name: utility.name, price: utility.price,id : utility.id, adventureId : this.id},
    });
 
    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.getUtilities();
        }
      }
    );
  }

  form = new FormGroup({
    name: new FormControl(null, [
      Validators.required,
      Validators.pattern('^[A-ZŠĐŽČĆ][a-zšđćčžA-ZŠĐŽČĆ ]*$'),
    ]),
    price: new FormControl(null, [Validators.required, Validators.pattern('^\\d{1,3}.?\\d{1,3}$')]),
  })

  addUtility() {
    if(this.form.invalid){
      this._snackBar.open(
        'Field/s cannot be empty.',
        '',
        {duration : 3000,panelClass: ['snack-bar']}
      );
      return;
    }
    this.newAdventureUtility = {
      adventureId : this.id,
      name: this.form.get('name')?.value,
      price : this.form.get('price')?.value
    }
    this.adventureUtilityService.addAdventureUtility(this.newAdventureUtility).subscribe((data) => {
      this.form.reset();
      this.utilities.data = [];
      this.getUtilities();
    });
  }
  

}
