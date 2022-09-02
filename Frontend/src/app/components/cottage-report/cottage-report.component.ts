import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title, CategoryScale, Legend, Tooltip, BarElement, BarController } from 'chart.js';

import { MarkDto } from 'src/app/interfaces/mark-dto';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { ReportService } from 'src/app/services/report.service';

@Component({
  selector: 'app-cottage-report',
  templateUrl: './cottage-report.component.html',
  styleUrls: ['./cottage-report.component.css']
})
export class CottageReportComponent implements OnInit {
  id: any;
  cottageMark!: MarkDto
  statisticPerDays: number[];



  constructor(private statisticService: ReportService, private cottageService: CottageService, private router: ActivatedRoute) {
    Chart.register(BarController, BarElement, CategoryScale, Tooltip, Legend, LineController, LineElement, PointElement, LinearScale, Title, Legend);

    this.cottageMark = {} as MarkDto;
    this.statisticPerDays = [] as number[];


  }



  ngOnInit(): void {
    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.cottageService.getCottageMark(this.id).subscribe((data) => {
      this.cottageMark = data;


      console.log(this.id)


    });
    this.statisticService.getNumberPerDays(this.id).subscribe((data) => {
      let days = Object.keys(data)
      let numbers = Object.values(data)



      const myChart = new Chart('myWeekChart', {
        type: 'bar',
        data: {
          labels: days,
          datasets: [{
            label: 'Number of reservations',
            data: numbers,
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
    });

    this.statisticService.getNumberPerMonth(this.id).subscribe((data) => {
      let months = Object.keys(data)
      let numbers = Object.values(data)
      const myMonthChart = new Chart('myMonthChart', {
        type: 'line',
        data: {
          labels: months,
          datasets: [{
            label: 'Number of reservations',
            data: numbers,
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
    });
    const myYearsChart = new Chart('myYearsChart', {
      type: 'scatter',
      data: {
        labels: ['2020', '2021', '2022'],
        datasets: [{
          label: 'Number of reservations',
          data: [1.0, 13.0, 9.0],
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
