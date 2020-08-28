# Bank Account Kata (EN)


Demo instructions ordered:

Launch application 
>$ mvn spring-boot:run

To list all customers (2 customers has been initially injected for Demo)
>$ curl http://localhost:8080/customers

request customer detail
>$ curl http://localhost:8080/customers/1

request a customer doesn't exist
>$ curl http://localhost:8080/customers/20

Display all accounts for customer(id = 1)
>$ curl http://localhost:8080/accounts/customer/1

Display account detail for a account doesn't exist
>$ curl http://localhost:8080/accounts/12

Display account detail for account(id =1) for customer(id=1)
>$ curl http://localhost:8080/accounts/1

Display account balance by account id
>$ curl http://localhost:8080/accounts/balance/1

Deposit for account(id=1)
>$ curl -v -X PUT http://localhost:8080/accounts/deposit/1 -H "Content-type:application/json" -d "600"

Deposit illegal amount for account(id=1)
>$ curl -v -X PUT http://localhost:8080/accounts/deposit/1 -H "Content-type:application/json" -d "0.005"

Display account balance by account id after deposit
>$ curl http://localhost:8080/accounts/balance/1

Withdraw negative amount for account(id=1)
>$ curl -v -X PUT http://localhost:8080/accounts/withdraw/1 -H "Content-type:application/json" -d "-600"

Withdraw amount superior than balance for account(id=1)
>$ curl -v -X PUT http://localhost:8080/accounts/withdraw/1 -H "Content-type:application/json" -d "1600"

Withdraw a amount from account(id=1) success
>$ curl -v -X PUT http://localhost:8080/accounts/withdraw/1 -H "Content-type:application/json" -d "200"

Request all transactions
>$ curl http://localhost:8080/transactions

Request all transactions of account(id=1)
>$ curl http://localhost:8080/transactions/1

Request all transactions of account does not exist
>$ curl http://localhost:8080/transactions/6

