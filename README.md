please run the class INGBankKataApplication start the project
this are the current roots:

Balance:
GET  http://localhost:8080/v1/operations/{accountID}
-----------
Deposit:
POST http://localhost:8080/v1/deposit
{
	"accountId":1,
	"amount":"55",
	"operationType":"DEPOSIT"
}
-----------
Withdraw:
POST http://localhost:8080/v1/withdraw
{
	"accountId":1,
	"amount":"1000",
	"operationType":"WITHDRAW"
}
-----------
Operations:
GET  http://localhost:8080/v1/operations/{accountID}


Thanks
