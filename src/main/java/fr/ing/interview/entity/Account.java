package fr.ing.interview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private long id;

    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
