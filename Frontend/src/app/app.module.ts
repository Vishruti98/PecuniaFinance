import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';

import { AuthenticateComponent } from './authenticate/authenticate.component';
import { SignupComponent } from './authenticate/signup/signup.component';
import { LoginComponent } from './authenticate/login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './home/profile/profile.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoanComponent } from './home/loan/loan.component';
import { LoanHistoryComponent } from './home/loan/loan-history/loan-history.component';
import { LoanRequestComponent } from './home/loan/loan-request/loan-request.component';
import { PassbookComponent } from './home/passbook/passbook.component';
import { AccountBalanceComponent } from './home/passbook/account-balance/account-balance.component';
import { AccountSummaryComponent } from './home/passbook/account-summary/account-summary.component';
import { ResponseComponent } from './home/passbook/response/response.component';
import { WithdrawComponent } from './home/withdraw/withdraw.component';
import { DepositComponent } from './home/deposit/deposit.component';
import { DisplayComponent } from './authenticate/display/display.component';



@NgModule({
  declarations: [
    AppComponent,
    AuthenticateComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,
    ProfileComponent,
    LoanComponent,
    LoanHistoryComponent,
    LoanRequestComponent,
    PassbookComponent,
    AccountBalanceComponent,
    AccountSummaryComponent,
    ResponseComponent,
    WithdrawComponent,
    DepositComponent,
    DisplayComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
 
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
