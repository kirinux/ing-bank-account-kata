package fr.ing.bank.Service;

public interface OperationService {

	public void deposit(long accountNumber, double amount) throws Exception;
	
	public void pull(long accountNumber, double montant);
	
	public double consult_balance(long accountNumber);
	
}
