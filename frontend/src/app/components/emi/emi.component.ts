import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CalcService } from '../../services/calc.service';
import { EmiCriteria } from '../../model/emi-criteria.model';
import { CalcResult } from '../../model/calc-result.model';

@Component({
  selector: 'app-calc',
  templateUrl: './emi.component.html',
  styleUrls: ['./emi.component.css']
})
export class EmiComponent implements OnInit {

  criteria: EmiCriteria = new EmiCriteria();
  result: CalcResult = new CalcResult();

  constructor(private calcService: CalcService, private router: Router) { }

  ngOnInit(): void {
  }

  doCalc() {
    this.calcService.calcEmi(this.criteria).subscribe({
      next: (data) => {
        console.log(data);
        this.result = data;
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  redirectToCalc() {
    this.router.navigate(['/calc/emi']);
  }

  onSubmit() {
    console.log(this.criteria);
    this.doCalc();
  }
}
