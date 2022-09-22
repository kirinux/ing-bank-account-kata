package fr.ing.interview.bankaccountkata.exception;

public class MoneyAmountException extends RuntimeException {

    public MoneyAmountException() {
        super("La somme d'argent ne doit pas être négative !");
    }

}
