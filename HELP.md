# Read Me First
la solution proposée est une application SpringBoot qui fournit une API REST :

## les endpoints suivants pour le parametrage  : 

POST  /customers  
POST /customers/{customerId}/accounts  

## User Story 1
POST /accounts/{accountId}/transactions => avec type de transaction DEPOSIT  
## User Story 2
POST /accounts/{accountId}/transactions => avec type de transaction WITHDRAW  
## User Story 3
GET customers/{customerId}/accounts
## User Story 4
GET accounts/{accountId}/transactions
GET customers/{customerId}/transactions


## Persistence

utilisation d'une base de données mémoire h2



