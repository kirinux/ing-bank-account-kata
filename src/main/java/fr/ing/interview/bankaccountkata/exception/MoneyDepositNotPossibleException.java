package fr.ing.interview.bankaccountkata.exception;

public class MoneyDepositNotPossibleException extends RuntimeException {

    public MoneyDepositNotPossibleException() {
        super("Le montant déposé doit être supérieur à 0.01€ !");
    }

}
