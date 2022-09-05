import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ResponseFishingEquipment } from 'src/app/interfaces/response-fishing-equipment';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-a-fishing-equipments',
  templateUrl: './a-fishing-equipments.component.html',
  styleUrls: ['./a-fishing-equipments.component.css']
})
export class AFishingEquipmentsComponent implements OnInit {

  id : any;
  equipments : ResponseFishingEquipment[];
  constructor(private adventureService : AdventureService,private router: ActivatedRoute) { 
    this.equipments = [] as ResponseFishingEquipment[];
  }
  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.getEquipments();
  }

  getEquipments() {
    this.adventureService.getAdventureFishingEquipment(this.id).subscribe({
      next: (res) => {
        console.log(res);
        this.equipments = res
    }
  });
}

}
