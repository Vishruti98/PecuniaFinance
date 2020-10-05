import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NumberValueAccessor, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Account } from 'src/app/Models/Account';
import { Customer } from 'src/app/Models/Customer';
import { SignUp } from 'src/app/Models/SignUp';
import { CustomerService } from 'src/app/Service/customer.service';
import { CustomValidator } from 'src/app/Validator/custom-validator';
import { CustomValidator2 } from 'src/app/Validator/custom-validator2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  registrationForm: FormGroup;

  customer:Customer={
    customerId:undefined,
    name:undefined,
    password:undefined,
    contact:undefined,
    aadhaar:undefined,
    pan:undefined,
    dob:undefined,
    gender:undefined,
    account:undefined
  }
  account:Account={
    accountId:undefined,
    branch:undefined,
    accountType:undefined,
    amount:undefined

  }
  customer1:Customer={
    customerId:undefined,
    name:undefined,
    password:undefined,
    contact:undefined,
    aadhaar:undefined,
    pan:undefined,
    dob:undefined,
    gender:undefined,
    account:undefined
  }
  signup:SignUp={
    customer:undefined,
    account:undefined
  }
  submitted:boolean=false;
  showMsg: boolean = false;
  custId: any;
  accountNo: number; 
  errormsg: String;
  result:any;
  
  constructor(private formBuilder:FormBuilder,private router:Router,private customerService:CustomerService) { }

  ngOnInit(): void {
    this.registrationForm=this.formBuilder.group({
      name: ['', Validators.compose([
        Validators.required,
        CustomValidator.patternValidator(/^([A-Z][a-z0-9?=.*/'":;<>~|[\]{}\\!@#$%^&()]*((\s[A-Za-z])?[a-z0-9?=.*/'":;<>~|[\]{}\\!@#$%^&()])*)$/, { hasCapitalCase: true }),
        CustomValidator2.patternValidator(/\d/, { hasNumber: true }),
        CustomValidator2.patternValidator(/[?=.*/'":;<>~|[\]{}\\!@#$%^&()]/, { hasSpecialCharacters: true })
      ])],
      password: ['', Validators.compose([
        Validators.required,
        Validators.minLength(8),
        CustomValidator.patternValidator(/\d/, { hasNumber: true }),
        CustomValidator.patternValidator(/[A-Z]/, { hasCapitalCase: true }),
        CustomValidator.patternValidator(/[a-z]/, { hasSmallCase: true }),
        CustomValidator.patternValidator(/[?=.*/'":;<>~|[\]{}\\!@#$%^&()]/, { hasSpecialCharacters: true })
      ])],
      contact:['', Validators.compose([
        Validators.required,
        Validators.maxLength(10),
        CustomValidator.patternValidator(/[6-9][0-9]{9}/,{ hasPattern: true })
      ])],
      aadhaar: ['',Validators.required],
      pan: ['',Validators.required],
      gender: ['',Validators.required],
      dob: ['',Validators.required],
      branch:  ['',Validators.required],
      accountType: ['',Validators.required]
    });
  }
  /** Triggered when submit form button is clicked */
  onSubmit(){
    this.submitted=true;
    this.save();
  }

  save(){
    this.submitted=true;
    if(this.registrationForm.invalid){
      return;
    }
    else {
          this.customer.name=this.registrationForm.controls.name.value;
          this.customer.contact=this.registrationForm.controls.contact.value;
          this.customer.gender=this.registrationForm.controls.gender.value;
          this.customer.password=this.registrationForm.controls.password.value;
          this.customer.aadhaar=this.registrationForm.controls.aadhaar.value;
          this.customer.pan=this.registrationForm.controls.pan.value;
          this.customer.dob=this.registrationForm.controls.dob.value;

         
          this.account.accountType=this.registrationForm.controls.accountType.value;
          this.account.branch=this.registrationForm.controls.branch.value;
          this.signup.account=this.account;
          this.signup.customer=this.customer;
         
          (async () => {
          this.customerService.signUp(this.signup).subscribe(data => {
           
            this.custId=data;           
            console.log(this.custId);
            this.transaction1();
          },
            err => {
              this.errormsg=err.error;
              alert(this.errormsg);
            });
            await this.delay(1000);
          })();
      }
  }
  delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
  transaction1(){
    this.customerService.getCustomerById(this.custId).subscribe(data => {
      this.result=data;
      console.log(this.result);
    },
    err =>{
      console.log(err.stack);
    })
  }

  /**Resets the form on closing of successfull message modal*/
  onClose(){
    this.registrationForm.reset();
    this.showMsg=false;
  }

}
