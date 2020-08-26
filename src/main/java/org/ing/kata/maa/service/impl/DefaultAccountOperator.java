package org.ing.kata.maa.service.impl;

import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;
import org.ing.kata.maa.model.Transaction;
import org.ing.kata.maa.service.AccountOperator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antoine Malliarakis
 */
@Component
public class DefaultAccountOperator
    implements AccountOperator
{

    private final BigDecimal minDepositValue;

    private final Map<Account, List<Transaction>> transactions;

    public DefaultAccountOperator()
    {
        super();
        minDepositValue = new BigDecimal("0.01");
        this.transactions = new HashMap<>();
    }

    @Override
    public Account deposit(final Account account, final BigDecimal amount)
        throws OperationFailedException
    {
        if (amount.compareTo(minDepositValue) < 0) {
            throw new OperationFailedException("Cannot make a deposit of " + amount + " < 0.01");
        }
        getTransactionsOf(account).add(Transaction.deposit(amount));
        return account.setStatus(account
                                     .getStatus()
                                     .add(amount));
    }

    @Override
    public Account withdraw(final Account account, final BigDecimal amount)
        throws OperationFailedException
    {
        if (amount.compareTo(account.getStatus()) >= 0) {
            throw new OperationFailedException("Cannot withdraw " + amount + " ( > " + account.getStatus() + ")");
        }
        getTransactionsOf(account).add(Transaction.withdrawal(amount));
        return account.setStatus(
            account
                .getStatus()
                .subtract(amount)
        );
    }

    @Override
    public List<Transaction> historyFor(final Account account)
    {
        return Collections.unmodifiableList(getTransactionsOf(account));
    }

    private List<Transaction> getTransactionsOf(Account account)
    {
        return this.transactions.computeIfAbsent(account, (a) -> new ArrayList<>());
    }
}
