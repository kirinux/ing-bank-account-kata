package org.ing.kata.maa.service.impl;

import org.assertj.core.api.Assertions;
import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.math.BigDecimal;

/**
 * @author Antoine Malliarakis
 */
public final class DefaultAccountOperatorTest
{

    @Test
    public void if_deposit_is_lower_than_threshold_operation_is_rejected(TestInfo testInfo)
    {
        Account account = new Account(
            "firstName-for-" + testInfo.getDisplayName(),
            "lastName-for-" + testInfo.getDisplayName()
        );
        Assertions
            .assertThatCode(
                () -> new DefaultAccountOperator()
                          .deposit(account, BigDecimal.ZERO)
            )
            .as("An error should have been raised since amount was way to low.")
            .hasMessage("Cannot make a deposit of 0 < 0.01")
            .isInstanceOf(OperationFailedException.class);
    }

    @Test
    public void deposit_should_increase_value(final TestInfo testInfo)
        throws OperationFailedException
    {
        Account account = new Account(
            "firstName-for-" + testInfo.getDisplayName(),
            "lastName-for-" + testInfo.getDisplayName()
        );
        account.setStatus(BigDecimal.valueOf(4));
        final Account result =
            new DefaultAccountOperator()
                .deposit(account, BigDecimal.valueOf(7));
        Assertions
            .assertThat(result)
            .as("deposit operation should have returned account parameter.")
            .isSameAs(account);
        Assertions
            .assertThat(result.getStatus())
            .as("Unexpected value found for the status of the account after deposit operation.")
            .isEqualTo(BigDecimal.valueOf(11));
    }



    @Test
    public void if_withdraw_amount_is_greater_than_status_operation_is_rejected(TestInfo testInfo)
    {
        Account account = new Account(
            "firstName-for-" + testInfo.getDisplayName(),
            "lastName-for-" + testInfo.getDisplayName()
        );
        account.setStatus(BigDecimal.TEN);
        Assertions
            .assertThatCode(
                () -> new DefaultAccountOperator()
                          .withdraw(account, BigDecimal.valueOf(100))
            )
            .as("An error should have been raised since amount was way to low.")
            .hasMessage("Cannot withdraw 100 ( > 10)")
            .isInstanceOf(OperationFailedException.class);
    }

    @Test
    public void withdraw_should_decrease_value(final TestInfo testInfo)
        throws OperationFailedException
    {
        Account account = new Account(
            "firstName-for-" + testInfo.getDisplayName(),
            "lastName-for-" + testInfo.getDisplayName()
        );
        account.setStatus(BigDecimal.valueOf(11));
        final Account result =
            new DefaultAccountOperator()
                .withdraw(account, BigDecimal.valueOf(4));
        Assertions
            .assertThat(result)
            .as("deposit operation should have returned account parameter.")
            .isSameAs(account);
        Assertions
            .assertThat(result.getStatus())
            .as("Unexpected value found for the status of the account after withdrawal operation.")
            .isEqualTo(BigDecimal.valueOf(7));
    }

}
