import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title,CategoryScale, Legend, Tooltip, BarElement, BarController, PieController, ArcElement, PolarAreaController, RadialLinearScale } from 'chart.js';
import { AdventureAverageRating } from 'src/app/interfaces/adventure-average-rating';
import { DateRange } from 'src/app/interfaces/date-range';
import { AdventureService } from 'src/app/services/AdventureService/adventure.service';
import { StatisticService } from 'src/app/services/StatisticService/statistic.service';

@Component({
  selector: 'app-adventure-statistic',
  templateUrl: './adventure-statistic.component.html',
  styleUrls: ['./adventure-statistic.component.css']
})
export class AdventureStatisticComponent implements OnInit {

  statisticPerDays : number[];
  id : any;
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  date: string[];
  numbers!: string[];
  todayDate: Date = new Date();
  show : boolean = false;
  dateRange : DateRange;
  ctx! : any;
  canvas! : any;
  incomeChart! : any;
  adventureAverageRating! : AdventureAverageRating;

  constructor(private statisticService : StatisticService,private activatedRoute: ActivatedRoute,private adventureService : AdventureService) {;
    Chart.register(BarController,BarElement,CategoryScale,Tooltip,Legend,LineController, LineElement, PointElement, LinearScale, Title,PieController,ArcElement,PolarAreaController,RadialLinearScale );
    this.statisticPerDays = [] as number[];
    this.date = [] as string[];
    this.numbers = [] as string[];
    this.dateRange = {} as DateRange;
    this.adventureAverageRating = {} as AdventureAverageRating;
  }
  

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.paramMap.get('id')!;

    this.adventureService.getAverageRatingForAdventure(this.id).subscribe((data) => {
        this.adventureAverageRating = data;
        console.log(this.id)
    });

    this.statisticService.getAllForAdventurePerDays(this.id).subscribe((data) => {
    let days = Object.keys(data)
    let numbers = Object.values(data)
    
    const myChart = new Chart("myChart", {
          type: 'bar',
          data: {
              labels: days,
              datasets: [{
                  label : 'Number of reservations',
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

  this.statisticService.getAllForAdventurePerMonth(this.id).subscribe((data) => {
    let months = Object.keys(data)
    let numbers = Object.values(data)
    
    const monthChart = new Chart("monthChart", {
          type: 'line',
          data: {
              labels: months,
              datasets: [{
                  label : 'Number of reservations',
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

  this.statisticService.getAllForAdventurePerYear(this.id).subscribe((data) => {
    let years = Object.keys(data)
    let numbers = Object.values(data)
    
    const yearChart = new Chart("yearChart", {
          type: 'line',
          data: {
              labels: years,
              datasets: [{
                  label : 'Number of reservations',
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
    createIncomeChart(date: string[], numbers: string[]) {
        this.canvas = document.getElementById('incomeChart');
        this.ctx = this.canvas.getContext('2d');
        //this.incomeChart.destroy();
        this.incomeChart = new Chart("incomeChart", {
            type: 'polarArea',
            data: {
                labels: date,
                datasets: [{
                    label : 'Number of reservations',
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
        start : s.toISOString(),
        end : e.toISOString()
    }
    this.statisticService.getIncome(this.id,this.dateRange).subscribe((data) => {
      this.date = Object.keys(data)
      this.numbers = Object.values(data)
      this.show = true;
      this.createIncomeChart(this.date,this.numbers);
    });
  }

}
