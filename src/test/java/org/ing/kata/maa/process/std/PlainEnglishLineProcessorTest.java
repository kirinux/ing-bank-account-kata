package org.ing.kata.maa.process.std;

import org.assertj.core.api.Assertions;
import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;
import org.ing.kata.maa.service.AccountOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Antoine Malliarakis
 */
@ExtendWith(MockitoExtension.class)
public final class PlainEnglishLineProcessorTest
{

    @Mock
    private AccountOperator accountOperator;

    @Mock
    private Logger logger;

    @Captor
    private ArgumentCaptor<Account> accountArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> messageArgumentCaptor;

    @Captor
    private ArgumentCaptor<BigDecimal> amountArgumentCaptor;

    private Account account;

    private InOrder inOrder;

    @BeforeEach
    public void before(TestInfo testInfo)
    {
        this.inOrder = Mockito.inOrder(this.accountOperator, this.logger);
        this.account = new Account(
            "firstName for " + testInfo.getDisplayName(),
            "lastName for " + testInfo.getDisplayName()
        );
    }

    @Test
    public void an_empty_line_does_nothing_and_prints_help()
        throws OperationFailedException
    {
        createProcessor().perform("");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .info(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void a_help_command_prints_help()
        throws OperationFailedException
    {
        createProcessor().perform("help");
        this.inOrder
            .verify(this.logger, Mockito.times(1))
            .debug("Command: {}", "help");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .info(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void a_balance_command_prints_account_balance()
        throws OperationFailedException
    {
        this.account.setStatus(BigDecimal.TEN);
        createProcessor().perform("balance");
        this.inOrder
            .verify(this.logger, Mockito.times(1))
            .debug("Command: {}", "balance");
        this.inOrder
            .verify(this.logger)
            .info(
                this.messageArgumentCaptor.capture(),
                this.messageArgumentCaptor.capture(),
                this.messageArgumentCaptor.capture(),
                Mockito.eq(BigDecimal.TEN)
            );
        Assertions
            .assertThat(
                this.messageArgumentCaptor
                    .getAllValues()
                    .stream()
                    .collect(Collectors.joining(System.lineSeparator()))
            )
            .isEqualTo(
                Stream
                    .of(
                        "Account balance for {} {}: {}",
                        "firstName for a_balance_command_prints_account_balance()",
                        "lastName for a_balance_command_prints_account_balance()"
                    )
                    .collect(Collectors.joining(System.lineSeparator()))
            );
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_operation_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> createProcessor()
                          .perform("whatever")
            )
            .as(
                "An error should have been thrown since the operation was invalid"
            )
            .hasMessage("Unknown command specified: whatever")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "whatever");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .error(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_deposit_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> createProcessor()
                          .perform("deposit")
            )
            .as("An error should have been thrown since the deposit operation was missing parameters")
            .isInstanceOf(OperationFailedException.class)
            .hasMessage("Missing mandatory amount parameter for command deposit");
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "deposit");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .error(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_deposit_amount_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> createProcessor()
                          .perform("deposit something")
            )
            .as("Invalid error raised when deposit amount is invalid.")
            .hasMessage("Invalid amount parameter specified: something")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "deposit");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .error(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void deposit_with_too_many_parameters_are_rejected()
    {
        Assertions
            .assertThatCode(
                () -> createProcessor()
                          .perform("deposit 34 for the queen")
            )
            .as("Invalid error raised when deposit parameters are too many")
            .hasMessage("Too many parameters passed to command deposit")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "deposit");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .error(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
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
                () -> createProcessor().perform("deposit 34")
            )
            .doesNotThrowAnyException();
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "deposit");
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
            .isSameAs(this.account);
        Assertions
            .assertThat(this.amountArgumentCaptor.getValue())
            .as(
                "Invalid amount passed as argument to the deposit operation"
            )
            .isEqualTo(
                BigDecimal.valueOf(34)
            );
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_withdrawal_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> createProcessor()
                          .perform("withdraw")
            )
            .as("An error should have been thrown since the withdraw operation was missing parameters")
            .isInstanceOf(OperationFailedException.class)
            .hasMessage("Missing mandatory amount parameter for command withdraw");
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "withdraw");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .error(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void an_invalid_withdrawal_amount_gets_rejected_and_does_nothing()
    {
        Assertions
            .assertThatCode(
                () -> createProcessor()
                          .perform("withdraw something")
            )
            .as("Invalid error raised when withdraw amount is invalid.")
            .hasMessage("Invalid amount parameter specified: something")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "withdraw");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .error(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void withdraw_with_too_many_parameters_are_rejected()
    {
        Assertions
            .assertThatCode(
                () -> createProcessor()
                          .perform("withdraw 34 for the queen")
            )
            .as("Invalid error raised when withdraw parameters are too many")
            .hasMessage("Too many parameters passed to command withdraw")
            .isInstanceOf(OperationFailedException.class);
        this.inOrder
            .verify(this.logger, Mockito.calls(1))
            .debug("Command: {}", "withdraw");
        this.inOrder
            .verify(this.logger, Mockito.times(9))
            .error(this.messageArgumentCaptor.capture());
        Assertions
            .assertThat(this.messageArgumentCaptor
                            .getAllValues()
                            .stream()
                            .collect(Collectors.joining(System.lineSeparator())))
            .isEqualTo(standardHelpMessage());
        this.inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void valid_withdraw_command_is_appropriately_interpreted()
        throws OperationFailedException
    {
        Mockito
            .doAnswer(AdditionalAnswers.returnsFirstArg())
            .when(this.accountOperator)
            .withdraw(
                Mockito.any(),
                Mockito.any()
            );
        Assertions
            .assertThatCode(
                () -> createProcessor().perform("withdraw 42")
            )
            .doesNotThrowAnyException();
        this.inOrder
            .verify(this.accountOperator)
            .withdraw(
                this.accountArgumentCaptor.capture(),
                this.amountArgumentCaptor.capture()
            );
        Assertions
            .assertThat(
                this.accountArgumentCaptor.getValue()
            )
            .as("An unexpected parameter was passed for the account.")
            .isSameAs(this.account);
        Assertions
            .assertThat(this.amountArgumentCaptor.getValue())
            .as(
                "Invalid amount passed as argument to the withdraw operation"
            )
            .isEqualTo(
                BigDecimal.valueOf(42)
            );
        this.inOrder.verifyNoMoreInteractions();
    }

    private String standardHelpMessage()
    {
        return Stream
                   .of(
                       "Syntax:",
                       "    deposit <amount>",
                       "        Adds <amount> to the current account. <amount> must not be lower than '0.1'.",
                       "    balance",
                       "        Displays the balance for the current account.",
                       "    withdraw <amount>",
                       "        Withdraws <amount> from the current account.",
                       "    help",
                       "        prints for this help"
                   )
                   .collect(Collectors.joining(System.lineSeparator()));
    }

    private PlainEnglishLineProcessor createProcessor()
    {
        return new PlainEnglishLineProcessor(accountOperator, this.logger, this.account);
    }
}
