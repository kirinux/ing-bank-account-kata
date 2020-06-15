package fr.ing.bank.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    private double balance;

    @ManyToOne
    private Customer customer;
}
