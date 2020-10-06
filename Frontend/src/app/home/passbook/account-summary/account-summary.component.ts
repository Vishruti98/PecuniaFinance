import { Component, OnInit } from '@angular/core';
import { PassbookService, Transactions } from '../passbook.service'
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-account-summary',
  templateUrl: './account-summary.component.html',
  styleUrls: ['./account-summary.component.css'],
  providers: [DatePipe]
})
export class AccountSummaryComponent implements OnInit {
  transactions: Transactions[];
  transac: any;
  accountId= localStorage.accountId;
  StartDate: Date;
  EndDate: Date;
  message: string;
  result: string;
  myDate = new Date();
  date:any;
  isValidDate: boolean;

  constructor(private service: PassbookService, private router: Router, private datePipe: DatePipe) {
    this.date = this.datePipe.transform(this.myDate, 'yyyy-MM-dd');
  }
                                                                                                                    

  ngOnInit(): void {
  }


  summary() {
    this.isValidDate = this.validateDates(this.StartDate, this.EndDate);
    if (this.isValidDate) {
      this.service.accountValidation(localStorage.accountId).subscribe((data) => {
        this.result = data;
        if (this.result == "true") {
          this.service.accountSummary(localStorage.accountId, this.StartDate, this.EndDate).subscribe((data) => {
            this.transac = data;
            this.transactions = this.transac;
            if (this.transac == 0) {
              alert("No Transactions Present!!");
            }
            else {
              this.router.navigate(['/home/passbook/response'], { queryParams: { accountId: localStorage.accountId, transactions: JSON.stringify(this.transactions), } });
            }
          });

        }
        else {
          alert("No AccountId Present!!");
        }
      });

    }

  }
  validateDates(sDate: any, eDate: any): any {
    this.isValidDate = true;
    this.accountId=this.accountId;
    if ((sDate == null) || (eDate == null)||(this.accountId==null)) {
      this.isValidDate = false;
      alert("all fields are required");
    }
    else {
      if (((eDate) < (sDate))) {
        this.isValidDate = false;
        alert("End date should be greater then start date.")
      }
      else if (eDate > this.date) {
        alert("end date has exceeded todays date");
      }

      else {
        return this.isValidDate = true;
      }

    }

  }
}