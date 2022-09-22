package fr.ing.interview.bankaccountkata.exception;

public class ClientCreationNotPossibleException extends RuntimeException {

    private String clientId;

    public ClientCreationNotPossibleException(String clientId) {
        super("Le client avec l'id [ " + clientId + " ] existe déjà !");
        this.clientId = clientId;
    }

    public String getClientId() {
        return this.clientId;
    }
}
