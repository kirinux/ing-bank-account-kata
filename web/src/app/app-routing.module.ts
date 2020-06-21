import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Routes,RouterModule} from '@angular/router';
import { DepositComponent } from './deposit/deposit.component';
import {WithdrawComponent} from './withdraw/withdraw.component';
import {BalanceComponent} from "./balance/balance.component";
import {HistoyComponent} from "./histoy/histoy.component";


const routes: Routes = [
  {
    path:"deposit", component:DepositComponent
  },  {
    path:"withdraw", component:WithdrawComponent
  },  {
    path:"balance", component:BalanceComponent
  },  {
    path:"history" , component:HistoyComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
