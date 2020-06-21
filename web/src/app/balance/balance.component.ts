import { Component, OnInit } from '@angular/core';
import {BankServiceService} from "../services/bank-service.service";

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent implements OnInit {
  idAccount: number;
  result:any;
  err:any;

  constructor(private bankServiceService:BankServiceService) { }

  ngOnInit() {
  }

  getBalance(idAccount,amount){
    this.err=null;
    this.idAccount=null;
    this.bankServiceService.getBalance(idAccount).subscribe(
      data => {this.result = data; },
      err => { this.err = err; console.log(err);}

    )
  }

}
