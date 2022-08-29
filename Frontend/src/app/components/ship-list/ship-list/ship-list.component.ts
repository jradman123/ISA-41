import { Component, Input, OnInit } from '@angular/core';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { ShipService } from 'src/app/services/ShipService/ship.service';

@Component({
  selector: 'app-ship-list',
  templateUrl: './ship-list.component.html',
  styleUrls: ['./ship-list.component.css']
})
export class ShipListComponent implements OnInit {

  searchText = ''
  ships!: ShipDto[];
  constructor(private shipService: ShipService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.findShips();
  }

  findShips() {
    this.shipService.findByEmail().subscribe((data) => {
      this.ships = data;

    });

  }
  view(id: string) {
    this.router.navigate(['shipOwner/ship-profile/' + id]);

  }
  delete(id: string) {
    this.shipService.deleteShip(id)
      .subscribe(response => {


        if (response == null) {
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'The ship cannot be deleted because it has a reservation!',
          })

        } else {
          this.ships = []
          this.findShips();
        }
      },

      );

  }

  handleMe(searchText: string) {
    this.searchText = searchText;
  }

  emitMe(searchText: any) {
    this.searchText = searchText.target.value
  }

}
