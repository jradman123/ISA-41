import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ResponseRules } from 'src/app/interfaces/response-rules';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';

@Component({
  selector: 'app-a-rules',
  templateUrl: './a-rules.component.html',
  styleUrls: ['./a-rules.component.css']
})
export class ARulesComponent implements OnInit {

  id : any;
  rules : ResponseRules[];
  constructor(private adventureService : AdventureService,private router: ActivatedRoute) { 
    this.rules = [] as ResponseRules[];
  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.getRules();
  }

  getRules() {
    this.adventureService.getAdventuresRules(this.id).subscribe({
      next: (res) => {
        this.rules = res
    }
  });
  }

}
