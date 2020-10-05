import { Component, OnInit } from '@angular/core';
import { Loan } from '../loan';
import { Observable } from 'rxjs';
import { LoanService } from '../loan.service';
@Component({
  selector: 'app-loan-history',
  templateUrl: './loan-history.component.html',
  styleUrls: ['./loan-history.component.css']
})
export class LoanHistoryComponent implements OnInit {
  loans:Observable<Loan[]>;
  accountId :number =localStorage.accountId;
  
  constructor(private service:LoanService) { }

 /**Loads loan history for particular accountId*/
  ngOnInit(): void {
    console.log("reload loan data");
    this.loans=this.service.getLoanHistory(this.accountId);
    console.log(this.loans)
  }

}
