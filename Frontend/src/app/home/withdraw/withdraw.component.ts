import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/Models/transaction';

import { AccountService } from 'src/app/service/account.service';


@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {
  withdrawForm: FormGroup;

 
  transactionDate: FormControl;
  transactionType: FormControl;
  transactionAmount: FormControl;
  transactionMode: FormControl;
  chequeNumber: FormControl;
  ifsc: FormControl;
  accountId: number= localStorage.accountId;
  submitted: boolean;
  showMsg: boolean;

   /** Initializing FormControls and FormGroup */
  constructor(builder:FormBuilder, private service:AccountService,private router:Router) {

    this.transactionDate = new FormControl ("",[Validators.required]);
    this.transactionType = new FormControl;
    this.transactionMode = new FormControl ("",[Validators.required]);
    this.transactionAmount = new FormControl ("",[Validators.required, Validators.min(100), Validators.max(200000)]);
    this.chequeNumber = new FormControl ("",[Validators.required,Validators.min(100000), Validators.max(999999)]);
    this.ifsc = new FormControl ("",[Validators.required,Validators.min(1000000000), Validators.max(9999999999)]);

    this.withdrawForm=builder.group({
     
      transactionDate:this.transactionDate,
      transactionType:this.transactionType,
      transactionMode:this.transactionMode,
      transactionAmount:this.transactionAmount,
      chequeNumber:this.chequeNumber,
      ifsc:this.ifsc
      
    });

   }

  withdrawnMoney:Transaction = new Transaction();

  ngOnInit(): void {
  }

   /** Triggered when submit form button is clicked */
   onSubmit(){
    this.submitted=true;
    this.save();
  }
  save() {
    
    
    this.withdrawnMoney.transactionDate = this.transactionDate.value;
    this.withdrawnMoney.transactionType = "debit";
    this.withdrawnMoney.transactionMode = this.transactionMode.value;
    this.withdrawnMoney.transactionAmount=this.transactionAmount.value;
    this.withdrawnMoney.chequeNumber = this.chequeNumber.value;
    this.withdrawnMoney.ifsc = this.ifsc.value;
    this.withdrawnMoney.accountId = this.accountId;
      console.log(this.withdrawnMoney.transactionMode);
    if(this.withdrawnMoney.transactionMode== "cheque" ){
  this.service.debitUsingCheque(this.withdrawnMoney).subscribe(
    data=>{
      this.showMsg=true
      ,error=>console.log(error)  
    });
  }


  else{
    this.service.debitUsingSlip(this.withdrawnMoney).subscribe(
      data=>{
        this.showMsg=true
        ,error=>console.log(error)  
      

    });
  }
}

 /**Resets the form on closing of successfull message modal*/
 onClose(){
  this.withdrawForm.reset();
  this.showMsg=false;
}

}
