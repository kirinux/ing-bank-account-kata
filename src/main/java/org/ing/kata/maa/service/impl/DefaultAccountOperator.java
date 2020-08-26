package org.ing.kata.maa.service.impl;

import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;
import org.ing.kata.maa.service.AccountOperator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Antoine Malliarakis
 */
@Component
public class DefaultAccountOperator
    implements AccountOperator
{

    private final BigDecimal minDepositValue;

    public DefaultAccountOperator()
    {
        super();
        minDepositValue = new BigDecimal("0.01");
    }

    @Override
    public Account deposit(final Account account, final BigDecimal amount)
        throws OperationFailedException
    {
        if (amount.compareTo(minDepositValue) < 0) {
            throw new OperationFailedException("Cannot make a deposit of " + amount + " < 0.01");
        }
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
        return account.setStatus(
            account
                .getStatus()
                .subtract(amount)
        );
    }
}
