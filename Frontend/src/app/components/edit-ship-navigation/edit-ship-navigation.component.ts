import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavigationDto } from 'src/app/interfaces/navigation-dto';
import { NavigationService } from 'src/app/services/NavigationService/navigation.service';

@Component({
  selector: 'app-edit-ship-navigation',
  templateUrl: './edit-ship-navigation.component.html',
  styleUrls: ['./edit-ship-navigation.component.css']
})
export class EditShipNavigationComponent implements OnInit {

  navigation!: NavigationDto;
  idShip: any;
  navigations: NavigationDto[] = [];
  id: any;
  navigationn!: NavigationDto
  constructor(private router: ActivatedRoute, private route: Router, private navigationService: NavigationService) {
    this.navigationn = {} as NavigationDto
  }

  ngOnInit(): void {
    this.findNavigation();
  }


  findNavigation() {
    this.idShip = +this.router.snapshot.paramMap.get('id')!;
    this.navigationService.findNavigationById(this.idShip).subscribe((data) => {
      this.navigations = data;

    });

  }

  addNavigation() {

    this.navigationn.shipId = this.idShip;

    console.log(this.navigationn)
    this.navigationService.addNavigation(this.navigationn).subscribe((data) => {
      this.navigations = []
      this.findNavigation();


    });


  }
  deleteNavigation(id: any) {

    this.navigationService.deleteNavigation(id, this.idShip)
      .subscribe(data => {
        this.navigations = []
        this.findNavigation();





      });



  }
}

