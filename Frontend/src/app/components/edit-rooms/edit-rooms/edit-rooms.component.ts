import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RoomDto } from 'src/app/interfaces/room-dto';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { RoomService } from 'src/app/services/RoomService/room.service';

@Component({
  selector: 'app-edit-rooms',
  templateUrl: './edit-rooms.component.html',
  styleUrls: ['./edit-rooms.component.css']
})
export class EditRoomsComponent implements OnInit {

  room!: RoomDto;
  idCottage: any;
  rooms: RoomDto[] = [];
  id: any;
  roomm!: RoomDto
  constructor(private roomService: RoomService, private cottageService: CottageService, private router: ActivatedRoute, private route: Router) {

  }

  ngOnInit(): void {
    this.idCottage = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.findRoomsById(this.idCottage).subscribe((data) => {
      this.rooms = data;

    });
  }


  addRoom() {


    console.log(this.room)
    this.roomService.addRoom(this.roomm).subscribe((data) => {



      this.route.navigate(['cottageOwner/edit-cottage/' + this.id]);
    });


  }
  deleteRoom(id: any) {
    console.log("fefefeef" + id);

    this.roomService.deleteRoom(id, this.idCottage)
      .subscribe(data => {
        window.location.reload();





      });



  }

}
