import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title,CategoryScale, Legend, Tooltip, BarElement, BarController, PieController, ArcElement, PolarAreaController, RadialLinearScale } from 'chart.js';
import { StatisticService } from 'src/app/services/StatisticService/statistic.service';

@Component({
  selector: 'app-adventure-statistic',
  templateUrl: './adventure-statistic.component.html',
  styleUrls: ['./adventure-statistic.component.css']
})
export class AdventureStatisticComponent implements OnInit {

  statisticPerDays : number[];
  id : any;
  constructor(private statisticService : StatisticService,private activatedRoute: ActivatedRoute) {
    //Chart.register(LineController, LineElement, PointElement, LinearScale, Title, CategoryScale);
    Chart.register(BarController,BarElement,CategoryScale,Tooltip,Legend,LineController, LineElement, PointElement, LinearScale, Title,PieController,ArcElement,PolarAreaController,RadialLinearScale );
    this.statisticPerDays = [] as number[];
  }
  

  ngOnInit(): void {
    this.id = +this.activatedRoute.snapshot.paramMap.get('id')!;
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
    
    const myChart = new Chart("monthChart", {
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
    
    const myChart = new Chart("yearChart", {
          type: 'polarArea',
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

}
