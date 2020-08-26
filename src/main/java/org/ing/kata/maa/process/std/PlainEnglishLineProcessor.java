package org.ing.kata.maa.process.std;

import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.model.Account;
import org.ing.kata.maa.process.LineProcessor;
import org.ing.kata.maa.service.AccountOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    public PlainEnglishLineProcessor(final AccountOperator accountOperator)
    {
        super();
        this.logger = LoggerFactory.getLogger(getClass());
        this.accountOperator = Objects.requireNonNull(accountOperator, "accountOperator == null");
        this.account = new Account("Jane", "Doe");
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
                if (stringTokenizer.hasMoreElements()) {
                    throw onSyntaxError("Too many parameters passed to command " + command);
                }
                printHelp(LogLevel.INFO);
                break;
            case "deposit":
                if (!stringTokenizer.hasMoreElements()) {
                    throw onSyntaxError("Missing mandatory amount parameter for command " + command);
                }
                final BigDecimal amount;
                final String amountString = stringTokenizer.nextToken();
                try {
                    amount = new BigDecimal(amountString);
                } catch (NumberFormatException e) {
                    throw new OperationFailedException("Invalid amount parameter specified: " + amountString, e);
                }
                if (stringTokenizer.hasMoreElements()) {
                    throw onSyntaxError("Too many parameters passed to command " + command);
                }
                this.logger.debug("Deposit of amount: {}", amount);
                this.account = this.accountOperator.deposit(this.account, amount);
                this.logger.info(
                    "New status for {} {}: {}",
                    this.account.getFirstName(),
                    this.account.getLastName(),
                    this.account.getStatus()
                );
                break;
            default:
                throw onSyntaxError("Unknown command specified: " + command);
        }
    }

    private void printHelp(LogLevel help)
    {
        help.log(this.logger, "Syntax:");
        help.log(this.logger, "    deposit <amount>");
        help.log(this.logger, "        Adds <amount> to the current account.");
        help.log(this.logger, "    help");
        help.log(this.logger, "        prints for this help");
    }

    private OperationFailedException onSyntaxError(String message)
    {
        printHelp(LogLevel.ERROR);
        return new OperationFailedException(message);
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
