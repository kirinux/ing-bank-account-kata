package org.ing.kata.maa.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * @author Antoine Malliarakis
 */
public final class Transaction
{

    private final BigDecimal amount;

    private final TransactionType transactionType;

    private final Instant instant;

    private Transaction(BigDecimal amount, TransactionType transactionType)
    {
        super();
        this.amount = Objects.requireNonNull(amount, "amount == null");
        this.transactionType = Objects.requireNonNull(transactionType, "transactionType == null");
        this.instant = Instant.now();
    }

    public static Transaction deposit(BigDecimal amount)
    {
        return new Transaction(Objects.requireNonNull(amount, "amount == null"), TransactionType.DEPOSIT);
    }

    public static Transaction withdrawal(BigDecimal amount)
    {
        return new Transaction(Objects.requireNonNull(amount, "amount == null"), TransactionType.WITHDRAWAL);
    }

    public Instant getInstant()
    {
        return this.instant;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public TransactionType getTransactionType()
    {
        return transactionType;
    }

}
