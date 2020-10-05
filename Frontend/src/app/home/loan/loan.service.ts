import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Loan } from './loan';
@Injectable({
  providedIn: 'root'
})
export class LoanService {

  private getLoanHistoryUrl = "http://localhost:8098/pecuniafinance/showLoanHistory";
  private addLoanRequestUrl = "http://localhost:8098/pecuniafinance/addLoanRequest";
  private loanDisbursalUrl = "http://localhost:8098/pecuniafinance/loanDisbursal";
  private showBalanceUrl = "http://localhost:8098/bank/showBalance";
  constructor(private httpClient: HttpClient) { }

  getLoanHistory(id: any): Observable<any> {
    return this.httpClient.get(`${this.getLoanHistoryUrl}/${id}`)
  }
  addLoanRequest(loan: Loan,id:any): Observable<any> {
    return this.httpClient.post(`${this.addLoanRequestUrl}/${id}`, loan);
  }

  loanDisbursal(loan: Object): Observable<any> {
    return this.httpClient.put(`${this.loanDisbursalUrl}/`,loan,{responseType:'text'});
  }

  getBalance(id: any): Observable<any> {
    return this.httpClient.get(`${this.showBalanceUrl}/${id}`)
  }
}
