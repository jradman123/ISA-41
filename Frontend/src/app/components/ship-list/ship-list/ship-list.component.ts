import { Component, Input, OnInit } from '@angular/core';
import { ShipDto } from 'src/app/interfaces/ship-list-view';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { ShipService } from 'src/app/services/ShipService/ship.service';

@Component({
  selector: 'app-ship-list',
  templateUrl: './ship-list.component.html',
  styleUrls: ['./ship-list.component.css']
})
export class ShipListComponent implements OnInit {

  ships: ShipDto[] = [];
  constructor(private shipService: ShipService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.shipService.findByEmail().subscribe((data) => {
      this.ships = data;

    });
  }

  view(id: string) {
    this.router.navigate(['shipOwner/ship-profile/' + id]);

  }
  delete(id: string) {
    this.shipService.deleteShip(id)
      .subscribe(data => {

        window.location.reload();

      });

  }

}
