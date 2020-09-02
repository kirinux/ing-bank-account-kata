package fr.ing.bank.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "operation")
public class OperationEntity {

	    @Id
	    @GeneratedValue( strategy = GenerationType.IDENTITY )
	    @Column
	    private Long    numeroOperation;
	    
	    
	    // Associations
	    @ManyToOne
	    @JoinColumn( name = "CODE_CPTE" )
	    @Column
	    private AccountEntity  account;
	   
	    
	    public Long getNumeroOperation() {
			return numeroOperation;
		}

		public void setNumeroOperation(Long numeroOperation) {
			this.numeroOperation = numeroOperation;
		}

	    
	    @JsonIgnore
	    @XmlTransient
	    public AccountEntity getCompte() {
	        return account;
	    }

	    public void setCompte( AccountEntity account ) {
	        this.account= account;
	    }

	    
}
