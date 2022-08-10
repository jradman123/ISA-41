import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute} from '@angular/router';
import { AdventureDto } from 'src/app/interfaces/adventure-dto';
import { Image } from 'src/app/interfaces/image';
import { ImagesResponse } from 'src/app/interfaces/images-response';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { AdventureUtilityService } from 'src/app/services/AdventureUtilityService/adventure-utility.service';

@Component({
  selector: 'app-adventure-profile',
  templateUrl: './adventure-profile.component.html',
  styleUrls: ['./adventure-profile.component.css']
})
export class AdventureProfileComponent implements OnInit {

  constructor(){}

  ngOnInit(): void {}

 

}
