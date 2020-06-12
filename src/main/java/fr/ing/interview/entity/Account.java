package fr.ing.interview.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private long id;

    private BigDecimal balance;

}
