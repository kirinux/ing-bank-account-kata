package fr.ing.interview.bankaccountkata.entity;

import java.time.LocalDate;
import javax.persistence.*;

import com.sun.istack.NotNull;

/**
 * Client Entity represent 
 *
 * @author Abir ZEFZEF
 */

@Entity
@Table(name="client")
public class Client {
	
	
    @Id
	@SequenceGenerator(name = "seq_client", sequenceName = "seq_client_ID_SEQ",allocationSize=1)
    @GeneratedValue(generator = "seq_client")
    @Column(name = "id_client")
    private Long id;
    
    @NotNull
    @Column(name = "lastname")
	private String lastName;
    
    @NotNull
    @Column(name = "firstname")
	private String firstName;
    
    
    @NotNull
    @Column(name = "birth_date")
	private LocalDate birthDate;
    
    

	public Client() {
		super();
	}



	public Client(Long id, String lastName, String firstName, String rib, LocalDate birthDate) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public LocalDate getBirthDate() {
		return birthDate;
	}



	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
	
}