# Bank Account Kata (FR)
> Ceci est un exercice assez libre d'implémentation à faire chez vous, dans un temps restreint, il a pour objectif d'ouvrir la discussion technique lors d'un futur entretien.


## Le Kata

### verrouillage de compte pour deposit/withdraw
#### avant d'utiliser la solution de persistance

Pour verrouiller le compte pour faire un deposit/withdraw j'ai utilisé synchronized sur les deux méthodes suivantes :
```
public class Account {

    private double balance;
    
    public synchronized void deposit(double amount) {
        balance = balance + amount;
    }
    public synchronized void withdraw(double amount) {
        balance = balance - amount; 
    }
}
```

#### avec la solution de persistance
Pour verrouiller le compte pour faire un deposit/withdraw j'ai utilisé PESSIMISTIC_WRITE de jpa :
```
@Lock(LockModeType.PESSIMISTIC_WRITE)
Account findByCustomerIdAndId(long customerId, long accountId);
```

Le Test AccountServiceH2Test permet de valider ce comportement.

### Build and start
* mvn clean install
* mvn spring-boot:run
* swagger-ui : (http://localhost:8080/swagger-ui.html)

### Persistence
* Spring data jpa.
* Une base de données H2.
* Pour les tests via swagger-ui la base est initialisée au démarage via le script data-h2.sql
    
    ```
    insert into customer (id, name) values (1, 'bob');
    
    insert into account (id, balance, label, customer_id) values (1, 10.0, 'COMPTE_COURANT', 1);
    insert into account (id, balance, label, customer_id) values (2, 20.0, 'LIVRET_A', 1);
     ```
