package fr.ing.interview.bankaccountkata.dto;


import java.math.BigDecimal;

public class AccountBalanceDTO {
    private Long id;
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
