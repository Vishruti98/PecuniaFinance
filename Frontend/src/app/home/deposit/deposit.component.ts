import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/Models/transaction';
import { AccountService } from 'src/app/service/account.service';



@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {
  depositForm: FormGroup;

 
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
  constructor(builder:FormBuilder, private service:AccountService,private router:Router)
   { 
    
    this.transactionDate = new FormControl ("",[Validators.required]);
    this.transactionType = new FormControl;
    this.transactionMode = new FormControl ("",[Validators.required]);
    this.transactionAmount = new FormControl ("",[Validators.required, Validators.min(100), Validators.max(200000)]);
    this.chequeNumber = new FormControl ("",[Validators.required,Validators.min(100000), Validators.max(999999)]);
    this.ifsc = new FormControl ("",[Validators.required,Validators.min(1000000000), Validators.max(9999999999)]);
    

    this.depositForm=builder.group({
     
      transactionDate:this.transactionDate,
      transactionType:this.transactionType,
      transactionMode:this.transactionMode,
      transactionAmount:this.transactionAmount,
      chequeNumber:this.chequeNumber,
      ifsc:this.ifsc
      
    });

  }

  depositedMoney:Transaction = new Transaction();

  ngOnInit(): void {
  }

  /** Triggered when submit form button is clicked */
  onSubmit(){
    this.submitted=true;
    this.save();
  }
  save() {
    
      this.depositedMoney.transactionDate = this.transactionDate.value;
      this.depositedMoney.transactionType = "credit";
      this.depositedMoney.transactionMode = this.transactionMode.value;
      this.depositedMoney.transactionAmount=this.transactionAmount.value;
      this.depositedMoney.chequeNumber = this.chequeNumber.value;
      this.depositedMoney.ifsc = this.ifsc.value;
      this.depositedMoney.accountId = this.accountId;
        console.log(this.depositedMoney.transactionMode);
        console.log(this.depositedMoney.transactionAmount);
      if(this.depositedMoney.transactionMode== "cheque" ){
    this.service.creditUsingCheque(this.depositedMoney).subscribe(
      data=>{
        this.showMsg=true
        ,error=>console.log(error)  
      });

  }
  else{
    this.service.creditUsingSlip(this.depositedMoney).subscribe(
      data=>{
        this.showMsg=true
        ,error=>console.log(error)  
      

    });
  }
}

   /**Resets the form on closing of successfull message modal*/
   onClose(){
    this.depositForm.reset();
    this.showMsg=false;
  }

 }
