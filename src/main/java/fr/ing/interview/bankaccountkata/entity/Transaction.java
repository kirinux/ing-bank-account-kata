package fr.ing.interview.bankaccountkata.entity;

import java.time.LocalDateTime;

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
 * Transaction Entity represent 
 *
 * @author Abir ZEFZEF
 */


@Entity
@Table(name="transaction")
public class Transaction {

	
	@Id
	@SequenceGenerator(name = "seq_transaction", sequenceName = "seq_transaction_ID_SEQ",allocationSize=1)
    @GeneratedValue(generator = "seq_transaction")
	@Column(name = "id_transaction")
    private Long id;
	
    @NotNull
    @Column(name = "label")
	private String label;
    
    
    @NotNull
    @Column(name = "value")
	private Double value;
    
    
    @NotNull
    @Column(name = "date_transaction")
	private LocalDateTime dateTransaction;

   
    @NotNull
    @Column(name = "type")
	private String type;
    
    @ManyToOne
	@JoinColumn(name = "id_account")
	private Account account;

    @ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;
    

	public Transaction() {
		super();
	}



	public Transaction(Long id, String label, Double value, LocalDateTime dateTransaction,String type) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.dateTransaction = dateTransaction;
		this.type = type;
	}



	public Transaction(Long id, String label, Double value, LocalDateTime dateTransaction, Boolean isDeposit,
			Boolean isWithdraw, Account account, Client client) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.dateTransaction = dateTransaction;
		this.account = account;
		this.client = client;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public Double getValue() {
		return value;
	}



	public void setValue(Double valeur) {
		this.value = valeur;
	}



	public LocalDateTime getDateTransaction() {
		return dateTransaction;
	}



	public void setDateTransaction(LocalDateTime dateTransaction) {
		this.dateTransaction = dateTransaction;
	}


	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}
	
	
}
