import { Component, OnInit } from '@angular/core';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title,CategoryScale, Legend, Tooltip, BarElement, BarController } from 'chart.js';
import { StatisticService } from 'src/app/services/StatisticService/statistic.service';

@Component({
  selector: 'app-adventure-statistic',
  templateUrl: './adventure-statistic.component.html',
  styleUrls: ['./adventure-statistic.component.css']
})
export class AdventureStatisticComponent implements OnInit {

  statisticPerDays : number[];
  constructor(private statisticService : StatisticService) {
    //Chart.register(LineController, LineElement, PointElement, LinearScale, Title, CategoryScale);
    Chart.register(BarController,BarElement,CategoryScale,Tooltip,Legend,LineController, LineElement, PointElement, LinearScale, Title);
    this.statisticPerDays = [] as number[];
    }
  

  ngOnInit(): void {
    this.statisticService.getAllForInstructor().subscribe((data) => {
      this.statisticPerDays = data;

    
  const myChart = new Chart("myChart", {
        type: 'bar',
        data: {
            labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'],
            datasets: [{
                label : 'Number of reservations',
                data: [this.statisticPerDays[0],this.statisticPerDays[1],this.statisticPerDays[2],this.statisticPerDays[3],this.statisticPerDays[4],this.statisticPerDays[5],this.statisticPerDays[6]],
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
