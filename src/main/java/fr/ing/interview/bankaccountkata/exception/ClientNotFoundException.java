package fr.ing.interview.bankaccountkata.exception;

public class ClientNotFoundException extends RuntimeException {

    private String clientId;

    public ClientNotFoundException(String clientId) {
        super("Le client avec l'id [ " + clientId + " ] est introuvable ");
        this.clientId = clientId;
    }

    public String getClientId() {
        return this.clientId;
    }
}
