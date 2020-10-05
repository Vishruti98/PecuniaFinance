import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoanService } from '../loan.service';
import { Router } from '@angular/router';
import { Loan } from '../loan';
import { Observable } from 'rxjs';
import { asLiteral } from '@angular/compiler/src/render3/view/util';
@Component({
  selector: 'app-loan-request',
  templateUrl: './loan-request.component.html',
  styleUrls: ['./loan-request.component.css']
})
export class LoanRequestComponent implements OnInit {
  accountid:number=localStorage.accountId;
  loanRequestForm: FormGroup;

  loanAmount:FormControl;
  tenure:FormControl;
  creditScore:FormControl;
  loanType:FormControl;

  loan:Loan=new Loan();
  receivedloan:Loan=new Loan();
  loanStatus:any;
  
  submitted=false;
  showFailAddMsg:boolean = false;
  showFailureDisbursalMsg:boolean=false;
  creditScoreApproval:boolean=false;
  loanTenureApproval:boolean=false;
  loanAmountApproval:boolean=false;

  
  constructor(builder:FormBuilder, private loanservice:LoanService, private router:Router) {
    
     /** Initializing FormControls and FormGroup */
    this.loanAmount=new FormControl("",[Validators.required,Validators.min(0)]);
    this.tenure=new FormControl("",[Validators.required,Validators.min(0), Validators.max(500), Validators.pattern("^[0-9]*$") ]);
    this.creditScore=new FormControl("",[Validators.required, Validators.min(300), Validators.max(900), Validators.pattern("^[0-9]*$")]);
    this.loanType=new FormControl('Personal Loan',[Validators.required]);
   
    this.loanRequestForm=builder.group({
      loanAmount:this.loanAmount,
      tenure:this.tenure,
      creditScore:this.creditScore,
      loanType:this.loanType
    });
   }

  ngOnInit(): void {
  }

/** Triggered when send for approval button is clicked */
  onSubmit(){
    this.submitted=true;
    this.save();
  }

/**Saves loan request application details */
  save(){

     this.loan.loanAmount = this.loanAmount.value;
     this.loan.loanType= this.loanType.value;
     this.loan.tenure = this.tenure.value;
     this.loan.creditScore = this.creditScore.value;

   this.checkForDisbursal();

   (async () => {
     this.loanservice.addLoanRequest(this.loan,this.accountid).subscribe(
        (data) => {
          if(data==null)
        this.showFailAddMsg=true;
        else
         this.receivedloan=data;
        },
        (error) => console.log(error)
      );

    await this.delay(10000);

    if(this.showFailAddMsg!=true){
      this.loanDisbursal(); 
     }
  })();
  }

  /**Checks which condition is true or false for loan disbursal only for view purpose */
  checkForDisbursal(){
    if(this.loan.creditScore>=670)
    this.creditScoreApproval=true;
    if(this.loan.tenure>=12 && this.loan.tenure<=240)
    this.loanTenureApproval=true;
    if(this.loan.loanAmount>=1000 && this.loan.loanAmount<=2500000)
    this.loanAmountApproval=true;
  }
 /**Disburses loan and updates loan status and account balance */
  loanDisbursal(){
    console.log(this.receivedloan)
    this.loanservice.loanDisbursal(this.receivedloan).subscribe(
      data=>{
        if(data==null)
        this.showFailureDisbursalMsg=true
        else
         this.loanStatus=data
        ,
        error=>console.log(error)  
    });
  }

  /**Resets the form on closing of successfull message modal*/
  onClose(){
    this.loanRequestForm.reset();
    this.submitted=false;
    this.showFailAddMsg= false;
    this.showFailureDisbursalMsg=false;
    this.creditScoreApproval=false;
    this.loanTenureApproval=false;
    this.loanAmountApproval=false;
    this.loanType.setValue('Personal Loan');
    this.loanStatus='';
  }
  delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
}
