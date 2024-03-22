import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmiCriteria } from '../model/emi-criteria.model';
import { CalcResult } from '../model/calc-result.model';

@Injectable({
  providedIn: 'root'
})
export class CalcService {

  private baseUrl = "http://localhost:8080/calc/emi"

  constructor(private httpClient: HttpClient) {
  }

  calcEmi(criteria: EmiCriteria): Observable<CalcResult> {
    return this.httpClient.post<CalcResult>(`${this.baseUrl}`, criteria);
  }

}
