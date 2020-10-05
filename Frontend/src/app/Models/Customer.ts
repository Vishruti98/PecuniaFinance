import { Account } from './Account';

export class Customer{
    customerId:number;
    name:String;
    password:String;
    contact:number;
    aadhaar:number;
    pan:String;
    dob:Date;
    gender:String;
    account: Account;
}