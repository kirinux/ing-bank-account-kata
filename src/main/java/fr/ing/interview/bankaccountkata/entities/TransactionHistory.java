package fr.ing.interview.bankaccountkata.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kso
 */
@Entity
@Table(name = "transaction_history")
public class TransactionHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_id")
    private int accountId;

    private Double amount;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "is_deposit")
    private boolean isDeposit;

    public TransactionHistory() {
    }


    public TransactionHistory(int accountId, double amount, LocalDateTime date, boolean isDeposit) {
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
        this.isDeposit = isDeposit;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public void setDeposit(boolean deposit) {
        isDeposit = deposit;
    }
}
