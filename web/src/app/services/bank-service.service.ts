import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class BankServiceService {

  url: string = "http://localhost:8080/api";

  constructor(private httpClient:HttpClient) { }



  getHistory(idAccount,idCustomer){

     const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };

    return this.httpClient
      .get(this.url+"/accounts/transactions-history?idCustomer="+idCustomer+"&idAcount="+idAccount,optionRequete)

  }

  depositMoney(idAccount,amount){
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };
    let date: Date = new Date();
    return this.httpClient.post(this.url+"/accounts/deposit?amount="+amount+"&id="+idAccount,optionRequete)

  }

  withdrawMoney(idAccount,amount){
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*'
      })
    };
    let date: Date = new Date();
    return this.httpClient.post(this.url+"/accounts/withdraw?amount="+amount+"&id="+idAccount,optionRequete)

  }

  getBalance(idAccount){

    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*'
      })
    };
    let date: Date = new Date();
    return this.httpClient.get(this.url+"/accounts/balance/"+idAccount,optionRequete)

  }
  getHistoryList(idAccount){

    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };

    return this.httpClient
      .get(this.url+"/accounts/"+idAccount+"/history",optionRequete)
  }

}
