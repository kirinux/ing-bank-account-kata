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
  idCustomer:number;
  constructor(private bankServiceService:BankServiceService) { }

  ngOnInit() {
  }

  getHistory(idAccount,idCustomer){
    let date: Date = new Date();
    this.bankServiceService.getHistory(idAccount,idCustomer)
      .subscribe( data =>{
        this.history = data;
      },err => {console.log(err)}
      )
    console.log(this.history)

  }

}




