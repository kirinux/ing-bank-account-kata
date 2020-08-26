package org.ing.kata.maa.service;

import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;

import java.math.BigDecimal;

/**
 * Implementations of this interface will be actually performing functional operations.
 *
 * @author Antoine Malliarakis
 */
public interface AccountOperator
{

    /**
     * Performs the specified deposit operation on the given account parameter.
     *
     * @param account the account on which the deposit is to be performed, should never be <code>null</code>
     * @param amount  the amount to add, should never be <code>null</code>
     * @return the resulting account
     * @throws OperationFailedException if there was any unexpected error while trying to perform the specified
     *                                  operation.
     */
    Account deposit(Account account, BigDecimal amount)
        throws OperationFailedException;

}
