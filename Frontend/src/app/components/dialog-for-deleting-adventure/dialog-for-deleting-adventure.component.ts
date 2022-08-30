import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-dialog-for-deleting-adventure',
  templateUrl: './dialog-for-deleting-adventure.component.html',
  styleUrls: ['./dialog-for-deleting-adventure.component.css']
})
export class DialogForDeletingAdventureComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DialogForDeletingAdventureComponent>,@Inject(MAT_DIALOG_DATA) public data: any,
              private adventureService : AdventureService){ }

  ngOnInit(): void {
  }

  close() {
    this.dialogRef.close();
  }

  delete(){
    this.adventureService.deleteAdventure(this.data.adventureId)
    .subscribe({
      next: (result: any) => {
        this.dialogRef.close();
      }
      
    });
  }
}
