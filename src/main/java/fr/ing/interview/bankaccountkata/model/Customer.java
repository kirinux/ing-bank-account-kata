package fr.ing.interview.bankaccountkata.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Customer implements Serializable{

    @Id
    @GeneratedValue
    private int idCustomer;

    private String firstName;
    private String lastName;
    private String adress;
    private String email;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(int idCustomer, String email, List<Account> accounts) {
        this.idCustomer = idCustomer;
        this.email = email;
        this.accounts = accounts;
    }

    public Customer(int idCustomer, String firstName, String lastName, String adress, String email, Account account, List<Account> accounts) {
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.email = email;

        this.accounts = accounts;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idustomer) {
        this.idCustomer = idustomer;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
