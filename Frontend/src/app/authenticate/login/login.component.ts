import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { newUser } from './newUser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  customerId:FormControl;
  password:FormControl;
  submitCalled:Boolean=false;
  showFailMsg: boolean;
  message:string;
  user:newUser=new newUser();
  isUserLoggedIn: boolean=true;

  /** Initializing FormGroup and FormControls  */
  constructor(builder:FormBuilder, private router:Router, private service:LoginService) { 
    this.customerId=new FormControl("",[Validators.required]);
    this.password=new FormControl("",[Validators.required]);
    this.loginForm=builder.group({
      loginName:this.customerId,
      password:this.password,
    });
  }

  ngOnInit(): void {
    if(localStorage.customerId || localStorage.password){
      localStorage.removeItem("customerId");
      localStorage.removeItem("password");
      
    }
  }
   /**Validated User loginName and password. If valid will route to User Profile page. */
  checkLogin() {
    this.user.customerId=this.customerId.value;
    this.user.password=this.password.value;
    localStorage.customerId=this.user.customerId;
    localStorage.password=this.user.password;

   
    this.service.login(this.user).subscribe(
      data=>{
        this.message=data;
        if(this.message=="Login Successful"){
         this.router.navigate(['/home'])
        }
        else  this.isUserLoggedIn=false;
      
      (error) => console.log(error)
    });
    
 
  }

  onRegister(){
    this.router.navigate(['/authenticate/signup'])
  }

 
  
}
