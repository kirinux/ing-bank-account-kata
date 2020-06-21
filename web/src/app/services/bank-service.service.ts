import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class BankServiceService {



  constructor(private httpClient:HttpClient) { }



  getHistory(idAccount){

    let headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');

    headers.append('Access-Control-Allow-Origin', 'http://localhost:4200');


   // headers = headers.set('Content-Type', 'application/json; charset=utf-8').append('Access-Control-Allow-Origin','*');
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };
    let date: Date = new Date();
    return this.httpClient
      .get("http://localhost:8080/api/accounts/transactions-history?idCustomer=1&idAcount="+idAccount,optionRequete)

  }

  depositMoney(idAccount,amount){
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*',
        'mon-entete-personnalise':'maValeur'
      })
    };
    let date: Date = new Date();
    return this.httpClient.post("http://localhost:8080/api/accounts/deposit?amount="+amount+"&id="+idAccount,optionRequete)

  }

  withdrawMoney(idAccount,amount){
    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*'
      })
    };
    let date: Date = new Date();
    return this.httpClient.post("http://localhost:8080/api/accounts/withdraw?amount="+amount+"&id="+idAccount,optionRequete)

  }

  getBalance(idAccount){

    const optionRequete = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*'
      })
    };
    let date: Date = new Date();
    return this.httpClient.get("http://localhost:8080/api/accounts/balance/1",optionRequete)

  }
}
