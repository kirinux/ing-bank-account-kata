import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { DepositComponent } from './deposit/deposit.component';
import { AppRoutingModule } from './/app-routing.module';

import { FormsModule } from '@angular/forms';
import { WithdrawComponent } from './withdraw/withdraw.component';
import { BalanceComponent } from './balance/balance.component';
import { HistoyComponent } from './histoy/histoy.component';
import {HttpClientModule} from "@angular/common/http";
import {BankServiceService} from "./services/bank-service.service";

@NgModule({
  declarations: [
    AppComponent,
    DepositComponent,
    WithdrawComponent,
    BalanceComponent,
    HistoyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [BankServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
