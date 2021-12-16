package fr.ing.interview.bankaccountkata.dto;

import java.time.LocalDateTime;

/**
 * TransactionDto
 *
 * @author Abir ZEFZEF
 */
public class TransactionDto {

	private Long id;
	private String label;
	private Double value;
	private LocalDateTime dateTransaction;
	private Long idClient;
	private Long idAccount;
	private String type;




	public TransactionDto() {
		super();
	}







	public TransactionDto(Long id, String label, Double value, LocalDateTime dateTransaction, Long idClient, Long idAccount, String type) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.dateTransaction = dateTransaction;
		this.idClient = idClient;
		this.idAccount = idAccount;
		this.type = type;
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



	public void setValeur(Double value) {
		this.value = value;
	}



	public LocalDateTime getDateTransaction() {
		return dateTransaction;
	}



	public void setDateTransaction(LocalDateTime dateTransaction) {
		this.dateTransaction = dateTransaction;
	}




	public Long getIdClient() {
		return idClient;
	}



	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}



	public Long getIdAccount() {
		return idAccount;
	}



	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}







	public String getType() {
		return type;
	}







	public void setType(String type) {
		this.type = type;
	}







	public void setValue(Double value) {
		this.value = value;
	}

	
}
