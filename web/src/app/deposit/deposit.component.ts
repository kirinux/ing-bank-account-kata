import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {BankServiceService} from "../services/bank-service.service";


export interface Account {
  id: number;
  solde: number;
}

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {


  idAccount: number;
    amount: number;
    result:any;
    err:any;

  constructor(private bankServiceService:BankServiceService) { }

  ngOnInit() {
  }

  depositMoney(idAccount,amount){
    this.err=null;
    this.amount =null;
    this.idAccount=null;
    this.bankServiceService.depositMoney(idAccount,amount).subscribe(
      data => {this.result = data; },
      err => { this.err = err; console.log(err);}

    )
  }

}

