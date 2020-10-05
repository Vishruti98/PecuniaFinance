import { Component, OnInit } from '@angular/core';
import {  FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from 'src/app/Models/Customer';
import { CustomerService } from 'src/app/Service/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  updateForm: FormGroup;
  editMode: boolean = false;
  showMsg: boolean = false;
  showStatus: number = 3;
  deleteMsg: any;
  deleteStatus: boolean = false;
   user:Customer={
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
  name:FormControl;
  password:FormControl;
  contact:FormControl;
  aadhaar:FormControl;
  pan:FormControl;
  dob:FormControl;
  gender:FormControl;
  branch: String;
  accountType: String;
  accountNo:number;
  //user:Customer;
  constructor(private formBuilder: FormBuilder, private service: CustomerService,private router: Router) {
    this.name = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
    ]);
    this.password = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,Validators.minLength(8)
    ]);
    this.contact = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,Validators.minLength(10)
    ]);
    this.aadhaar = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
    ]);
    this.pan = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
    ]);
    this.gender = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
    ]);
    this.dob = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
    ]);
    
    this.updateForm=formBuilder.group({
      name:this.name,
      password:this.password,
      contact:this.contact,
     aadhaar:this.aadhaar,
     pan:this.pan,
     dob:this.dob,
     gender:this.gender,
    
    });
   }

  ngOnInit(): void {
    if(localStorage.accountId ){
      localStorage.removeItem("accountId");
      
    }
    this.onReloadData();
  }
  /** Reloads UserProfile data from userId and sets value into form controls */
  onReloadData() {
    
    (async () => {
      this.service.getCustomerById(localStorage.customerId).subscribe(
        (data) => {
          this.user = data;
          console.log(this.user);
          this.accountNo=this.user.account.accountId;
          localStorage.accountId=this.accountNo;
          console.log(localStorage.accountId);
          this.branch=this.user.account.branch;
          this.accountType=this.user.account.accountType;
          console.log(this.accountNo);
        },
        (error) => console.log(error)
      );

      await this.delay(2000);

      console.log("user data------" + this.user);
      this.updateForm.get("name").setValue(this.user.name);
      this.updateForm.get("password").setValue(this.user.password);
      this.updateForm.get("contact").setValue(this.user.contact);
      this.updateForm.get("aadhaar").setValue(this.user.aadhaar);
      this.updateForm.get("gender").setValue(this.user.gender);
      this.updateForm.get("dob").setValue(this.user.dob);
      this.updateForm.get("pan").setValue(this.user.pan);
     
    })();
  }

  /** Enables the form controls in edit mode */
  onEdit() {
    this.editMode = true;
    this.updateForm.get("name").enable();
    this.updateForm.get("contact").enable();
    this.updateForm.get("password").enable();
  }

  /** Updates UserProfile data */
  onUpdate() {
   
    this.user.name = this.name.value;
    this.user.password = this.password.value;
    this.user.contact = this.contact.value;
   
    console.log(this.user);
    (async () => {
      this.service.updateCustomer(this.user).subscribe(
        (data) => {
          this.showMsg = true;
          this.onCancel();
        },
        (error) => console.log(error)
      );

      await this.delay(1000);

      this.user = new Customer();
      this.onCancel();
    })();
  }
  /** Cancels updation, returns form to it's previous state */
  onCancel() {
    this.onReloadData();
    this.editMode = false;
    this.updateForm.get("name").disable();
    this.updateForm.get("contact").disable();
    this.updateForm.get("password").disable();
  }
  /** Redirects to logout on clodse of delete modal */
  onClose() {
    this.router.navigate(["/authenticate"]);
  }
  /** Deletes User Account as well as Wallet Account */
  onDelete() {
    (async () => {
      this.service
        .deleteCustomer(
          46,67584202
        )
        .subscribe(
          (data) => {
            this.deleteMsg = data;
          },
          (error) => console.log(error)
        );

      await this.delay(1000);

      if (this.deleteMsg == "User Removed") {
        this.deleteStatus = true;
      } else this.deleteStatus = false;
    })();
  }
  delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }

}
