package fr.ing.interview.bankaccountkata.dto;


/**
 * CompteDto
 *
 * @author Abir ZEFZEF
 */
public class AccountDto {
	
	
    private Long id;
    private String accountNumber;
    private double balance;
    
    
	public AccountDto() {
		super();
	}
	public AccountDto(Long id, String accountNumber, double balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;

	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double montant) {
		this.balance = montant;
	}

    
   
}
