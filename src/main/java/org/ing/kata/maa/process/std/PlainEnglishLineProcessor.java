package org.ing.kata.maa.process.std;

import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;
import org.ing.kata.maa.model.Transaction;
import org.ing.kata.maa.process.LineProcessor;
import org.ing.kata.maa.service.AccountOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * @author Antoine Malliarakis
 */
@Component
public final class PlainEnglishLineProcessor
    implements LineProcessor
{

    private final AccountOperator accountOperator;

    private final Logger logger;

    private Account account;

    @Autowired
    public PlainEnglishLineProcessor(final AccountOperator accountOperator)
    {
        this(accountOperator, LoggerFactory.getLogger(PlainEnglishLineProcessor.class));
    }

    PlainEnglishLineProcessor(final AccountOperator accountOperator, Logger logger)
    {
        this(
            accountOperator,
            logger,
            new Account("Jane", "Doe")
        );
    }

    PlainEnglishLineProcessor(final AccountOperator accountOperator, Logger logger, Account initialAccount)
    {
        super();
        this.logger = Objects.requireNonNull(logger, "logger == null");
        this.accountOperator = Objects.requireNonNull(accountOperator, "accountOperator == null");
        this.account = Objects.requireNonNull(initialAccount, "initialAccount == null");
    }

    @Override
    public void perform(final String line)
        throws OperationFailedException
    {
        final StringTokenizer stringTokenizer = new StringTokenizer(line.trim());
        if (!stringTokenizer.hasMoreElements()) {
            printHelp(LogLevel.INFO);
            return;
        }
        final String command = stringTokenizer.nextToken();
        this.logger.debug("Command: {}", command);
        switch (command) {
            case "help":
                ensureNoMoreParameters(stringTokenizer, command);
                printHelp(LogLevel.INFO);
                break;
            case "balance":
                ensureNoMoreParameters(stringTokenizer, command);
                reportAccountBalance();
                break;
            case "withdraw": {
                final BigDecimal amount = readMandatoryAmountParameter(stringTokenizer, command);
                ensureNoMoreParameters(stringTokenizer, command);
                this.logger.debug("Withdraw of amount: {}", amount);
                this.account = this.accountOperator.withdraw(this.account, amount);
            }
            break;
            case "deposit": {
                final BigDecimal amount = readMandatoryAmountParameter(stringTokenizer, command);
                ensureNoMoreParameters(stringTokenizer, command);
                this.logger.debug("Deposit of amount: {}", amount);
                this.account = this.accountOperator.deposit(this.account, amount);
            }
            break;
            case "history": {
                ensureNoMoreParameters(stringTokenizer, command);
                this.logger.info("Transaction history for {} {}: ", account.getFirstName(), account.getLastName());
                this.accountOperator
                    .historyFor(account)
                    .stream()
                    .sorted(Comparator
                                .comparing(Transaction :: getInstant)
                                .reversed())
                    .forEach(t -> this.logger.info(
                        "  - {}: {} {} €",
                        DateTimeFormatter.RFC_1123_DATE_TIME.format(t.getInstant().atZone(ZoneId.systemDefault())),
                        t.getTransactionType(),
                        t.getAmount()
                    ));
                break;
            }
            default:
                throw onSyntaxError("Unknown command specified: " + command);
        }
    }

    private void ensureNoMoreParameters(final StringTokenizer stringTokenizer, final String command)
        throws OperationFailedException
    {
        if (stringTokenizer.hasMoreElements()) {
            throw onSyntaxError("Too many parameters passed to command " + command);
        }
    }

    private BigDecimal readMandatoryAmountParameter(final StringTokenizer stringTokenizer, final String command)
        throws OperationFailedException
    {
        if (!stringTokenizer.hasMoreElements()) {
            throw onSyntaxError("Missing mandatory amount parameter for command " + command);
        }
        final BigDecimal amount;
        final String amountString = stringTokenizer.nextToken();
        try {
            amount = new BigDecimal(amountString);
        } catch (NumberFormatException e) {
            throw onSyntaxError("Invalid amount parameter specified: " + amountString, e);
        }
        return amount;
    }

    private void reportAccountBalance()
    {
        this.logger.info(
            "Account balance for {} {}: {}",
            this.account.getFirstName(),
            this.account.getLastName(),
            this.account.getStatus()
        );
    }

    private void printHelp(LogLevel help)
    {
        help.log(this.logger, "Syntax:");
        help.log(this.logger, "    deposit <amount>");
        help.log(this.logger, "        Adds <amount> to the current account. <amount> must not be lower than '0.1'.");
        help.log(this.logger, "    balance");
        help.log(this.logger, "        Displays the balance for the current account.");
        help.log(this.logger, "    history");
        help.log(this.logger, "        Displays the transaction history for the current account.");
        help.log(this.logger, "    withdraw <amount>");
        help.log(this.logger, "        Withdraws <amount> from the current account.");
        help.log(this.logger, "    help");
        help.log(this.logger, "        prints for this help");
    }

    private OperationFailedException onSyntaxError(String message)
    {
        printHelp(LogLevel.ERROR);
        return new OperationFailedException(message);
    }

    private OperationFailedException onSyntaxError(String message, Throwable throwable)
    {
        printHelp(LogLevel.ERROR);
        return new OperationFailedException(message, throwable);
    }

    private enum LogLevel
    {
        ERROR() {
            @Override
            void log(final Logger logger, final String message)
            {
                logger.error(message);
            }
        },
        INFO() {
            @Override
            void log(final Logger logger, final String message)
            {
                logger.info(message);
            }
        };

        abstract void log(Logger logger, String message);
    }
}
