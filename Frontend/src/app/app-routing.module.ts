import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticateComponent } from './authenticate/authenticate.component';
import { LoginComponent } from './authenticate/login/login.component';
import { SignupComponent } from './authenticate/signup/signup.component';
import { HomeComponent } from './home/home.component';
import { LoanHistoryComponent } from './home/loan/loan-history/loan-history.component';
import { LoanRequestComponent } from './home/loan/loan-request/loan-request.component';
import { ProfileComponent } from './home/profile/profile.component';
import { LoanComponent } from './home/loan/loan.component';
import { PassbookComponent } from './home/passbook/passbook.component';
import { AccountBalanceComponent } from './home/passbook/account-balance/account-balance.component';
import { AccountSummaryComponent } from './home/passbook/account-summary/account-summary.component';
import { ResponseComponent } from './home/passbook/response/response.component';
import { DepositComponent } from './home/deposit/deposit.component';
import { WithdrawComponent } from './home/withdraw/withdraw.component';
import { DisplayComponent } from './authenticate/display/display.component';


const routes: Routes = [
  { path: "",  redirectTo:'authenticate',pathMatch:'full' },
  {
    path: "authenticate", component: AuthenticateComponent,
     children: [{ path: "", component: DisplayComponent },
     { path: "display", component: DisplayComponent },
    { path: "login", component:LoginComponent },
    { path: "signup", component: SignupComponent },
    ]
  },
  {
    path: "home", component: HomeComponent,
     children: [{ path: "", component: ProfileComponent },
    { path: "profile", component:ProfileComponent },
    {path:'loan',component:LoanComponent,
    children: [{ path: "",  redirectTo:'loanrequest',pathMatch:'full' },
      { path: 'loanrequest', component: LoanRequestComponent},
    { path: 'loanhistory', component: LoanHistoryComponent },
   ]
   },
   {path:'passbook',component:PassbookComponent,
    children: [{ path: "",  redirectTo:'accountbalance',pathMatch:'full' },
      { path: 'accountbalance', component: AccountBalanceComponent},
    { path: 'accountsummary', component: AccountSummaryComponent },
    {path:'response',component:ResponseComponent},
   ]
   },
   { path: "deposit", component:DepositComponent },
   { path: "withdraw", component:WithdrawComponent },
    ]
  },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
