import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export class Transactions {
  transactionId: number;
  transactionDate: Date;
  transactionType:string;
  transactionAmount: number;
  transactionMode:string;
  chequeNumber: string;
  ifsc: string;
  accountId: number;

  constructor(transactionId:number,transactionDate:Date,transactionType:string,transactionAmount:number,transactionMode:string,chequeNumber:string,ifsc:string,accountId:number){
    this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.transactionMode = transactionMode;
		this.chequeNumber = chequeNumber;
		this.ifsc = ifsc;
		this.accountId = accountId;
  }
}
export class Account {
    accountId:number;
    amount:number;
    constructor(accountId:number,amount:number) {
      this.accountId=accountId;
      this.amount=amount;
    }
  }

@Injectable({
  providedIn: 'root'
})
export class PassbookService {

  constructor(private httpService: HttpClient) { }
  accountBalance(accountId:number){
    return this.httpService.get("http://localhost:8098/bank/showBalance/"+accountId ,{responseType: 'json'});
  }
  accountSummary(accountId:number,StartDate:Date,EndDate:Date){
    return this.httpService.get("http://localhost:8098/bank/accountSummary/"+accountId+"/"+StartDate+"/"+EndDate ,{responseType: 'json'});
  }
  accountValidation(accountId:number){
    return this.httpService.get("http://localhost:8098/bank/accountValidation/"+accountId ,{responseType: 'text'})
  }
}
