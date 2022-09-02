import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { ReservationService } from 'src/app/services/ReservationService/reservation.service';
import Swal from 'sweetalert2';
import { DialogForDeletingAdventureComponent } from '../dialog-for-deleting-adventure/dialog-for-deleting-adventure.component';

@Component({
  selector: 'app-adventures-list',
  templateUrl: './adventures-list.component.html',
  styleUrls: ['./adventures-list.component.css']
})
export class AdventuresListComponent implements OnInit {

  adventuresDto : AdventureDto[];
  textForSearch = '';
  reservationsExist! : string;
  constructor(private adventureService : AdventureService,private router : Router, private reservationService : ReservationService,public dialog: MatDialog) {
    this.adventuresDto = [] as AdventureDto[];
   }

  ngOnInit(): void {
   this.getAdventures();
  }
  getAdventures() {
    this.adventureService.getAllForInstructor().subscribe((data) => {
      this.adventuresDto = [];
      this.adventuresDto = data;
    });
  }

  view(id: string) {
    this.router.navigate(['instructor/adventure-profile/' + id]);
    console.log(id);
  }

  emitMe(searchText: any){
    this.textForSearch = searchText.target.value
  }

  check(id: string) {
    this.reservationService.reservationsExistForAdventure(id)
    .subscribe({
      next: (result: any) => {
        this.reservationsExist = result;
        this.updateField(id);
      },
    });
  }
  updateField(id : string) {
    if(this.reservationsExist === 'TRUE'){
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Adventure cannot be deleted!',
      });
      return;
    }else{
      this.delete(id);
    }
  }

  delete(id : string){
    const dialogRef = this.dialog.open(DialogForDeletingAdventureComponent, {
      width: '300px',
      data: {adventureId : id},
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAdventures();   
    });
  
  }

  report(id : string){
    this.router.navigate(['instructor/report/' + id]);
    console.log(id);
  }
  }
