insert into bank(name) values ('ING');
insert into bank(name) values ('BNP PARIBAS');


insert into customer(name, bank_id) values ('John Doe', 1);
insert into customer(name, bank_id) values ('Steve Derick', 1);
insert into customer(name, bank_id) values ('Steven Smith', 2);
insert into customer(name, bank_id) values ('Ryan Green', 2);

insert into account(customer_id, balance) values (1, 8000);
insert into account(customer_id, balance) values (1, 3000);
insert into account(customer_id, balance) values (2, 4000);
insert into account(customer_id, balance) values (2, 5000);
insert into account(customer_id, balance) values (3, 10000);
insert into account(customer_id, balance) values (3, 7000);
insert into account(customer_id, balance) values (4, 6000);
insert into account(customer_id, balance) values (4, 5000);

insert into transaction_history(account_id, amount, date, is_deposit) values (1, 100, '2020-05-05', true);
insert into transaction_history(account_id, amount, date, is_deposit) values (2, 500, '2020-04-05', false);
insert into transaction_history(account_id, amount, date, is_deposit) values (3, 400, '2020-03-05', false);
insert into transaction_history(account_id, amount, date, is_deposit) values (4, 300, '2020-02-05', false);
insert into transaction_history(account_id, amount, date, is_deposit) values (5, 200, '2020-04-05', false);
insert into transaction_history(account_id, amount, date, is_deposit) values (6, 450, '2020-08-05', false);
insert into transaction_history(account_id, amount, date, is_deposit) values (7, 850, '2020-07-05', false);
insert into transaction_history(account_id, amount, date, is_deposit) values (8, 800, '2020-05-05', false);

