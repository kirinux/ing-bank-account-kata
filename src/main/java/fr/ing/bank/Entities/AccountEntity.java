package fr.ing.bank.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
    private long               codeClient;
	
	@Column
	private long numAccount;
	
	@Column
	private double solde;

	public long getNumAccount() {
		return numAccount;
	}

	public void setNumAccount(long numAccount) {
		this.numAccount = numAccount;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	
	
}
