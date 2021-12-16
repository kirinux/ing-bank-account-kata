package fr.ing.interview.bankaccountkata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * Compte Entity represent 
 *
 * @author Abir ZEFZEF
 */

@Entity
@Table(name="account")
public class Account {
	
	
	@Id
	@SequenceGenerator(name = "seq_account", sequenceName = "seq_account_ID_SEQ",allocationSize=1)
    @GeneratedValue(generator = "seq_account")
    @Column(name = "id_account")
    private Long id;
    @Column(name = "account_number")
    private String accountNumber;
    @NotNull

    @Column(name = "balance")
    private double balance;

    @ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;
    
	public Account() {
		super();
	}



	public Account(Long id, String accountNumber, double balance) {
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



	public void setBalance(double amount) {
		this.balance = amount;
	}

	
}
