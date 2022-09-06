import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DateRange } from 'src/app/interfaces/date-range';
import { Chart, LineController, LineElement, PointElement, LinearScale, Title,CategoryScale, Legend, Tooltip, BarElement, BarController, PieController, ArcElement, PolarAreaController, RadialLinearScale } from 'chart.js';
import { StatisticService } from 'src/app/services/StatisticService/statistic.service';

@Component({
  selector: 'app-admin-statistic',
  templateUrl: './admin-statistic.component.html',
  styleUrls: ['./admin-statistic.component.css']
})
export class AdminStatisticComponent implements OnInit {

  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl(),
  });
  date: string[];
  numbers : string[];
  dateRange : DateRange;
  ctx! : any;
  canvas! : any;
  incomeChart! : any;
  constructor(private statisticService : StatisticService) {
    Chart.register(BarController,BarElement,CategoryScale,Tooltip,Legend,LineController, LineElement, PointElement, LinearScale, Title,PieController,ArcElement,PolarAreaController,RadialLinearScale );
    this.date = [] as string[];
    this.numbers = [] as string[];
    this.dateRange = {} as DateRange;
   }

  ngOnInit(): void {
  }

  createIncomeChart(date: string[], numbers: string[]) {
    this.canvas = document.getElementById('incomeChart');
    this.ctx = this.canvas.getContext('2d');
    //this.incomeChart.destroy();
    this.incomeChart = new Chart("incomeChart", {
        type: 'bar',
        data: {
            labels: date,
            datasets: [{
                label : 'Income in euro',
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
this.statisticService.getWebsiteIncome(this.dateRange).subscribe((data) => {
  this.date = Object.keys(data)
  this.numbers = Object.values(data)
  this.createIncomeChart(this.date,this.numbers);
});
}

}
