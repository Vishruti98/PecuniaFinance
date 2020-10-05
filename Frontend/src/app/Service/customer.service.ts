import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { SignUp } from '../Models/SignUp';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Customer } from '../Models/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) { }
  baseUrl: string = "http://localhost:8098";

   /*
   This service method is used to send request to the given url and perform the sign up operation based on the form data entered by the customer
   */
  signUp(sign_up:SignUp){
    return this.http.post<any>(this.baseUrl+"/customer/addCustomer",sign_up,{responseType:"text" as "json"}).pipe(catchError(this.handleError));
  } 

  updateCustomer(update:Customer){
    return this.http.put<any>(this.baseUrl+"/customer/updateCustomer",update,{responseType:"text" as "json"}).pipe(catchError(this.handleError));
  } 

  getCustomerById(customerId:number){ 
    return this.http.get<any>(this.baseUrl+"/customer/getCustomerById/"+customerId);
  }

  deleteCustomer(customerId:number,accountId:number){
    return this.http.delete<any>(this.baseUrl+"/customer/deleteCustomer/"+customerId+"/"+accountId);
  }


  /*
  This  method is used to handle the exceptions that might be received
  */
 private handleError(errorResponse: HttpErrorResponse) {
  return throwError(errorResponse);
}
}
