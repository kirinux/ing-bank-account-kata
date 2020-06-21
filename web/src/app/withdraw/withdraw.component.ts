import { Component, OnInit } from '@angular/core';
import {BankServiceService} from "../services/bank-service.service";

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {

  idAccount: number;
  amount: number;
  result:any;
  err:any;

  constructor(private bankServiceService:BankServiceService) { }

  ngOnInit() {
  }
  withrawMoney(idAccount,amount){
    this.err=null;
    this.amount =null;
    this.idAccount=null;
    this.bankServiceService.withdrawMoney(idAccount,amount).subscribe(
      data => {this.result = data;this.err=null;},
      err => { this.err = err; console.log(err);}

    )
  }
}
