package fr.ing.interview.bankaccountkata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class CustomerModel {

    @Id
    @GeneratedValue
    private long id;
    private String name;
}
