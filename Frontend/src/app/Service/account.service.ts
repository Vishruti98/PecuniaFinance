import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Transaction } from 'src/app/Models/transaction';
import { Observable } from 'rxjs';

     
@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private creditUrl = "http://localhost:8098/account/creditCheque";
  private debitUrl = "http://localhost:8098/account/debitCheque";
  


  constructor(private httpClient: HttpClient) { }

  creditUsingCheque(transaction: Transaction): Observable<any> {
    return this.httpClient.put(`${this.creditUrl}`,transaction,{responseType:'text'})
  }

  debitUsingCheque(transaction: Transaction):Observable<any>{
    return this.httpClient.put(`${this.debitUrl}`,transaction,{responseType:'text'})
  }

  creditUsingSlip(transaction: Transaction):Observable<any>{
    return this.httpClient.put(`${this.creditUrl}`,transaction,{responseType:'text'})
  }

  debitUsingSlip(transaction: Transaction):Observable<any>{
    return this.httpClient.put(`${this.debitUrl}`,transaction,{responseType:'text'})
  }

}
