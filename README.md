# Bank Account Kata (FR)
> Ceci est un exercice assez libre d'implémentation à faire chez vous, dans un temps restreint, il a pour objectif d'ouvrir la discussion technique lors d'un futur entretien.


## Le Kata
### Build and start
* mvn clean install
* mvn spring-boot:run
* swagger-ui : (http://localhost:8080/swagger-ui.html)

## Persistence
* Spring data jpa.
* Une base de données H2.
* Pour les tests via swagger-ui la base est initialisée au démarage via le script data-h2.sql
    
    ```
    insert into customer (id, name) values (1, 'bob');
    insert into customer (id, name) values (2, 'alice');
    
    insert into account (id, balance, label, customer_id) values (1, 10.0, 'COMPTE_COURANT', 1);
    insert into account (id, balance, label, customer_id) values (2, 20.0, 'LIVRET_A', 1);
    insert into account (id, balance, label, customer_id) values (3, 100.0, 'COMPTE_COURANT', 2);
    insert into account (id, balance, label, customer_id) values (4, 200.0, 'LIVRET_A', 2);
    ```
