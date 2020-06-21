import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BankServiceService} from "../services/bank-service.service";

@Component({
  selector: 'app-histoy',
  templateUrl: './histoy.component.html',
  styleUrls: ['./histoy.component.css']
})
export class HistoyComponent implements OnInit {
  history :any;
  idAccount;
  constructor(private bankServiceService:BankServiceService) { }

  ngOnInit() {
  }

  getHistory(idAccount){
    let date: Date = new Date();
    this.bankServiceService.getHistory(idAccount)
      .subscribe( data =>{
        this.history = data;
      },err => {console.log(err)}
      )
    console.log(this.history)

  }

}


export class historyLine {
  public transactionType: string;
  public amount: number;
  public operationDate: Date;
  constructor( type: string,  amount: number,  operationDate: Date )
  { this.transactionType= type
    this.amount=amount
      this.operationDate=operationDate}
}

