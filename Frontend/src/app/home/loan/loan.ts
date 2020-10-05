export class Loan
{
    loanId: Number;
    loanAmount: Number;
    tenure:Number;
    creditScore: Number;
    rateOfInterest: Number;
    loanStatus:String;
    loanType: String;
    account:{
        accountId: number;
        amount: number;
    };
}