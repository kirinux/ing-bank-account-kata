package ing.kata.BankAccount.Entities;

import ing.kata.BankAccount.Enums.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double balance;
    private Currency currency;
    private double overdraft;

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions = Set.of();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

}
