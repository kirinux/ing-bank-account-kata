package fr.ing.bank.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column
    private long codeClient;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "client")
	@Column
	private List<AccountEntity> accounts;

	
	public long getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(long codeClient) {
		this.codeClient = codeClient;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}
	
	
	
	
}
