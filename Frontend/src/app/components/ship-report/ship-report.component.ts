import { Component, OnInit } from '@angular/core';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title, CategoryScale, Legend, Tooltip, BarElement, BarController, PieController, ArcElement, PolarAreaController, RadialLinearScale } from 'chart.js';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MarkDto } from 'src/app/interfaces/mark-dto';
import { ReportService } from 'src/app/services/report.service';
import { ShipService } from 'src/app/services/ShipService/ship.service';
import { ActivatedRoute } from '@angular/router';
import { DateRange } from 'src/app/interfaces/daterange';


@Component({
  selector: 'app-ship-report',
  templateUrl: './ship-report.component.html',
  styleUrls: ['./ship-report.component.css']
})
export class ShipReportComponent implements OnInit {

  show: boolean = false;
  id: any;
  date: string[];
  numbers!: string[];
  myChart: any;
  shipMark!: MarkDto
  todayDate: Date = new Date();
  dateRange: DateRange;
  statisticPerDays: number[];
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),

  });



  constructor(private statisticService: ReportService, private shipService: ShipService, private router: ActivatedRoute) {
    Chart.register(BarController, BarElement, CategoryScale, Tooltip, Legend, LineController, LineElement, PointElement, LinearScale, Title, PieController, ArcElement, PolarAreaController, RadialLinearScale);

    this.shipMark = {} as MarkDto;
    this.dateRange = {} as DateRange;
    this.date = [] as string[];
    this.numbers = [] as string[];

    this.statisticPerDays = [] as number[];


  }



  ngOnInit(): void {


    this.id = +this.router.snapshot.paramMap.get('id')!;
    this.shipService.getShipMark(this.id).subscribe((data) => {
      this.shipMark = data;

    });



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
    this.statisticService.getNumberPerYearShip(this.id).subscribe((data) => {
      let months = Object.keys(data)
      let numbers = Object.values(data)

      const myYearsChart = new Chart('myYearsChart', {
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





  }
  myPrice(date: string[], numbers: string[]) {


    this.myChart = new Chart("myChart", {
      type: 'polarArea',
      data: {
        labels: date,
        datasets: [{
          label: 'Income for specific period',
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
  }

  addDate() {
    const s = new Date(this.range.value.start.getTime() - this.range.value.start.getTimezoneOffset() * 60000)
    const e = new Date(this.range.value.end.getTime() - this.range.value.end.getTimezoneOffset() * 60000)

    this.dateRange = {
      start: s.toISOString(),
      end: e.toISOString()
    }
    this.statisticService.getPriceShip(this.id, this.dateRange).subscribe((data) => {
      this.date = Object.keys(data)
      this.numbers = Object.values(data)
      this.show = true;
      this.myPrice(this.date, this.numbers);
    });
  }


}
