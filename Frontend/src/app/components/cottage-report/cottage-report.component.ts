import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title, CategoryScale, Legend, Tooltip, BarElement, BarController, PieController, ArcElement, PolarAreaController, RadialLinearScale } from 'chart.js';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';


import { MarkDto } from 'src/app/interfaces/mark-dto';
import { CottageService } from 'src/app/services/CottageService/cottage.service';
import { ReportService } from 'src/app/services/report.service';
import { DateRange } from 'src/app/interfaces/daterange';



@Component({
  selector: 'app-cottage-report',
  templateUrl: './cottage-report.component.html',
  styleUrls: ['./cottage-report.component.css']
})
export class CottageReportComponent implements OnInit {
  id: any;
  show: boolean = false;
  cottageMark!: MarkDto
  myChart!: any;
  date: string[];
  numbers!: string[];
  todayDate: Date = new Date();
  dateRange: DateRange;

  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),

  });



  constructor(private statisticService: ReportService, private cottageService: CottageService, private router: ActivatedRoute) {
    Chart.register(BarController, BarElement, CategoryScale, Tooltip, Legend, LineController, LineElement, PointElement, LinearScale, Title, PieController, ArcElement, PolarAreaController, RadialLinearScale);

    this.cottageMark = {} as MarkDto;
    this.date = [] as string[];
    this.numbers = [] as string[];
    this.dateRange = {} as DateRange;


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
    this.statisticService.getNumberPerYear(this.id).subscribe((data) => {
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
    this.statisticService.getPrice(this.id, this.dateRange).subscribe((data) => {
      this.date = Object.keys(data)
      this.numbers = Object.values(data)
      this.show = true;
      this.myPrice(this.date, this.numbers);
    });
  }


}
