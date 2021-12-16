package fr.ing.interview.bankaccountkata.dto;

import java.time.LocalDate;

/**
 * ClientDto
 *
 * @author Abir ZEFZEF
 */
public class ClientDto {
	
	
    private Long id;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;

	

	public ClientDto() {
		super();
	}

	public ClientDto(Long id, String lastName, String firstName, String rib, LocalDate birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
