package org.ing.kata.maa.process.std;

import org.assertj.core.api.Assertions;
import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;
import org.ing.kata.maa.service.AccountOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

/**
 * @author Antoine Malliarakis
 */
@ExtendWith(MockitoExtension.class)
public final class PlainEnglishLineProcessorTest
{

    @Mock
    private AccountOperator accountOperator;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Captor
    private ArgumentCaptor<BigDecimal> amountArgumentCaptor;


    private InOrder inOrder;

    @BeforeEach
    public void before()
    {
        this.inOrder = Mockito.inOrder(this.accountOperator);
    }

    @Test
    public void an_empty_line_does_nothing()
        throws OperationFailedException
    {
        new PlainEnglishLineProcessor(this.accountOperator)
            .perform("");
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_operation_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> new PlainEnglishLineProcessor(accountOperator)
                          .perform("whatever")
            )
            .as(
                "An error should have been thrown since the operation was invalid"
            )
            .hasMessage("Unknown command specified: whatever")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_deposit_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> new PlainEnglishLineProcessor(accountOperator)
                          .perform("deposit")
            )
            .as("An error should have been thrown since the deposit operation was missing parameters")
            .isInstanceOf(OperationFailedException.class)
            .hasMessage("Missing mandatory amount parameter for command deposit");
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_deposit_amount_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> new PlainEnglishLineProcessor(accountOperator)
                          .perform("deposit something")
            )
            .as("Invalid error raised when deposit amount is invalid.")
            .hasMessage("Invalid amount parameter specified: something")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void deposit_with_too_many_parameters_are_rejected()
    {
        Assertions
            .assertThatCode(
                () -> new PlainEnglishLineProcessor(accountOperator)
                          .perform("deposit 34 for the queen")
            )
            .as("Invalid error raised when deposit parameters are too many")
            .hasMessage("Too many parameters passed to command deposit")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void valid_deposit_command_is_appropriately_interpreted()
        throws OperationFailedException
    {
        Mockito
            .doAnswer(AdditionalAnswers.returnsFirstArg())
            .when(this.accountOperator)
            .deposit(
                Mockito.any(),
                Mockito.any()
            );
        Assertions
            .assertThatCode(
                () -> new PlainEnglishLineProcessor(accountOperator).perform("deposit 34")
            )
            .doesNotThrowAnyException();
        this.inOrder
            .verify(this.accountOperator)
            .deposit(
                this.accountArgumentCaptor.capture(),
                this.amountArgumentCaptor.capture()
            );
        Assertions
            .assertThat(
                this.accountArgumentCaptor.getValue()
            )
            .as("An unexpected parameter was passed for the account.")
            .isNotEqualTo(null);
        Assertions
            .assertThat(this.amountArgumentCaptor.getValue())
            .as(
                "Invalid amount passed as argument to the deposit operation"
            )
            .isEqualTo(
                BigDecimal.valueOf(34)
            );
    }
}
