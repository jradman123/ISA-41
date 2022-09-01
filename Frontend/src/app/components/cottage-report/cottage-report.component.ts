import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart } from 'chart.js';
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


  constructor(private cottageService: CottageService, private router: ActivatedRoute, private elementRef: ElementRef) {
    this.cottageMark = {} as MarkDto;


  }
  canvas: any
  ctx: any
  myChart: any;


  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.getCottageMark(this.id).subscribe((data) => {
      this.cottageMark = data;
      console.log(this.id)


    });
    let htmlRef = this.elementRef.nativeElement.querySelector(`#myChart`);


    // this.ctx = this.canvas.getContext('2d');

    var myChart = new Chart(htmlRef, {
      type: 'bar',
      data: {
        labels: ['Red', 'Blue', 'Yellow'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3],
          backgroundColor: [
            'rgba(255,99,132,0.2)',
            'rgba(54,162,235,0.2)',
            'rgba(255,206,86,0.2)',
            'rgba(75,192,192,0.2)',
            'rgba(153,182,255,0.2)',
            'rgba(255,150,64,0.2)'

          ],
          borderColor: [
            'rgba(255,99,132,1)',
            'rgba(54,162,235,1)',
            'rgba(255,206,86,1)',
            'rgba(75,192,192,1)',
            'rgba(153,102,255,1)'
          ],
          borderWidth: 1

        }]
      },
      options: {
        scales: {
          x: {
            ticks: {
              display: true,

            },
          },
          y: {
            ticks: {
              display: true,
            },
          },




        }

      }

    }




    )
  }

}
