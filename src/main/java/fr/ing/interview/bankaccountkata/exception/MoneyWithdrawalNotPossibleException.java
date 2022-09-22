package fr.ing.interview.bankaccountkata.exception;

public class MoneyWithdrawalNotPossibleException extends RuntimeException {

    public MoneyWithdrawalNotPossibleException() {
        super("Impossible de retirer la somme ! Vous ne pouvez pas utiliser le découvert");
    }

}
