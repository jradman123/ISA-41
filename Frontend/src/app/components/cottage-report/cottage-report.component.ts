import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MarkDto } from 'src/app/interfaces/mark-dto';
import { CottageService } from 'src/app/services/CottageService/cottage.service';

@Component({
  selector: 'app-cottage-report',
  templateUrl: './cottage-report.component.html',
  styleUrls: ['./cottage-report.component.css']
})
export class CottageReportComponent implements OnInit {
  id: any;
  cottageMark!: MarkDto

  constructor(private cottageService: CottageService, private router: ActivatedRoute) {
    this.cottageMark = {} as MarkDto;

  }

  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.getCottageMark(this.id).subscribe((data) => {
      this.cottageMark = data;
      console.log(this.id)
      console.log("OCENA" + data)

    });
  }

}
