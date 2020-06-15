insert into customer (id, name) values (1, 'bob');
insert into customer (id, name) values (2, 'alice');

insert into account (id, balance, label, customer_id) values (1, 10.0, 'COMPTE_COURANT', 1);
insert into account (id, balance, label, customer_id) values (2, 20.0, 'LIVRET_A', 1);
insert into account (id, balance, label, customer_id) values (3, 100.0, 'COMPTE_COURANT', 2);
insert into account (id, balance, label, customer_id) values (4, 200.0, 'LIVRET_A', 2);
