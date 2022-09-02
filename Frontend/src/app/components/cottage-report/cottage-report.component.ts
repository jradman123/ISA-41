import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title, CategoryScale, Legend, Tooltip, BarElement, BarController } from 'chart.js';

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
  statisticPerDays: number[];



  constructor(private cottageService: CottageService, private router: ActivatedRoute) {
    Chart.register(BarController, BarElement, CategoryScale, Tooltip, Legend, LineController, LineElement, PointElement, LinearScale, Title);

    this.cottageMark = {} as MarkDto;
    this.statisticPerDays = [] as number[];


  }



  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.getCottageMark(this.id).subscribe((data) => {
      this.cottageMark = data;
      console.log(this.id)


    });

    const myChart = new Chart('myChart', {
      type: 'bar',
      data: {
        labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
        datasets: [{
          label: 'Number of reservations',
          data: [1.0, 5.0, 9.0, 7.0, 13.0, 21.0, 30.0],
          backgroundColor: [
            'rgba(0, 200, 32,0.6)',
            'rgba(255, 0, 71, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ]
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          },
          legend: {
            display: false
          }
        },

      }
    });
  }

}
