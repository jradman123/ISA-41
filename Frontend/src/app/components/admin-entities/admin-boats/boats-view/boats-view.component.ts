import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-boats-view',
  templateUrl: './boats-view.component.html',
  styleUrls: ['./boats-view.component.css']
})
export class BoatsViewComponent implements OnInit {

  searchText = ''
  ships!: ShipDto[];
  constructor(private shipService: ShipService, private route: ActivatedRoute, 
    private router: Router) {
      this.ships =[] as ShipDto[];
     }

  ngOnInit(): void {
    this.findShips();
  }

  findShips() {
    this.shipService.findAllUndeleted().subscribe((data) => {
      this.ships = data;

    });

  }
  view(id: string) {
    this.router.navigate(['admin/boat-view/' + id]);

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
