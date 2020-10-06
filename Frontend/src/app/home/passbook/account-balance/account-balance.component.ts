import { Component, OnInit } from '@angular/core';
import { PassbookService, Account } from '../passbook.service'
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-account-balance',
  templateUrl: './account-balance.component.html',
  styleUrls: ['./account-balance.component.css'],
  providers: [DatePipe]
})
export class AccountBalanceComponent implements OnInit {
  account: Account[];
  acc: any;
  accountId=localStorage.accountId;
result:any;
confirm:boolean=false;
amount:any;
  constructor(private service: PassbookService, private router: Router) {
    
  }
                                                                                                                    
  ngOnInit(): void {
    
  }

  balance() {
    this.service.accountValidation(localStorage.accountId).subscribe((data) => {
      this.result = data;
      if (this.result == "true") {
        this.service.accountBalance(localStorage.accountId).subscribe((data) => {
          this.amount = data;
          this.confirm=true;
          console.log(this.amount);
          //alert(this.amount);
        });
      }
      else {
        alert("No AccountId Present!!");
      }
    });
    }

  }
