package ing.kata.BankAccount.Entities;

import ing.kata.BankAccount.Enums.TransactionStatus;
import ing.kata.BankAccount.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    private TransactionType type;
    private TransactionStatus status;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
