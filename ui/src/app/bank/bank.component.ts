import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.css']
})
export class BankComponent implements OnInit {

  banks;
  selectedBank;
  customers;
  selectedCustomer;
  accounts;
  selectedAccount;

  transactions;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8090/banks")
      .subscribe((banks) => {
        this.banks = banks;
      });
  }

  depositMoney(deposit: HTMLButtonElement) {
    let id = this.selectedAccount.id;
    if (deposit.value) {
      this.http.post("http://localhost:8090/accounts/".concat(id, "/deposit-money"),
        {},
        {params: {amount: deposit.value}})
        .subscribe(value => {
          let indexOf = this.accounts.indexOf(this.selectedAccount);
          this.selectedAccount = value;
          deposit.value = '';
          this.accounts.splice(indexOf, 1, this.selectedAccount);
          if (this.transactions) {
            this.getTransactionsHistory();
          }
        })
    }
  }

  withdrawMoney(withdraw: HTMLButtonElement) {
    let id = this.selectedAccount.id;
    if (withdraw.value) {
      this.http.post("http://localhost:8090/accounts/".concat(id, "/withdraw-money"),
        {},
        {params: {amount: withdraw.value}})
        .subscribe(value => {
          let indexOf = this.accounts.indexOf(this.selectedAccount);
          this.selectedAccount = value;
          withdraw.value = '';
          this.accounts.splice(indexOf, 1, this.selectedAccount);
          if (this.transactions) {
            this.getTransactionsHistory();
          }
        })
    }
  }

  getTransactionsHistory() {
    let id = this.selectedAccount.id;
    console.log(id)
    this.http.get("http://localhost:8090/accounts/".concat(id, "/transactions-history"))
      .subscribe(transactions => {
        this.transactions = transactions;
      });
  }

  switchBank(bank: any) {
    this.selectedBank = bank;
    this.customers = this.selectedBank.customers;
    this.selectedCustomer = null;
  }

  switchCustomer(customer: any) {
    this.selectedCustomer = customer;
    this.accounts = customer.accounts;
    this.selectedAccount = null;
  }

  switchAccount(account: any) {
    this.selectedAccount = account;
    this.transactions = null;
  }
}
