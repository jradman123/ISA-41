import { Component, OnInit } from '@angular/core';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title, CategoryScale, Legend, Tooltip, BarElement, BarController } from 'chart.js';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MarkDto } from 'src/app/interfaces/mark-dto';
import { ReportService } from 'src/app/services/report.service';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-ship-report',
  templateUrl: './ship-report.component.html',
  styleUrls: ['./ship-report.component.css']
})
export class ShipReportComponent implements OnInit {

  id: any;
  shipMark!: MarkDto
  todayDate: Date = new Date();
  statisticPerDays: number[];
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),

  });



  constructor(private statisticService: ReportService, private shipService: ShipService, private router: ActivatedRoute) {
    Chart.register(BarController, BarElement, CategoryScale, Tooltip, Legend, LineController, LineElement, PointElement, LinearScale, Title, Legend);

    this.shipMark = {} as MarkDto;
    this.statisticPerDays = [] as number[];


  }



  ngOnInit(): void {


    this.id = +this.router.snapshot.paramMap.get('id')!;




    this.statisticService.getNumberPerDaysShip(this.id).subscribe((data) => {
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

    this.statisticService.getNumberPerMonthShip(this.id).subscribe((data) => {
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
      type: 'line',
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
    this.statisticService.getNumberPerMonthShip(this.id).subscribe((data) => {
      let months = Object.keys(data)
      let numbers = Object.values(data)
      const myPriceChart = new Chart('myChart', {
        type: 'bar',
        data: {
          labels: ['2020', '2021', '2022'],
          datasets: [{
            label: 'Number of reservations',
            data: [1.0, 13.0, 9.0],
            backgroundColor: [
              'rgba(0, 200, 32,0.6)',
              'rgba(255, 0, 71, 1)',
              'rgba(75, 192, 192, 1)',

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
  }

}
