package org.ing.kata.maa.app;

import org.ing.kata.maa.exception.OperationFailedException;
import org.ing.kata.maa.process.LineProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Antoine Malliarakis
 */
class KataCommandLineRunner
    implements CommandLineRunner
{

    private final LineProcessor lineProcessor;

    private final Logger logger;

    private final Supplier<String> console;

    KataCommandLineRunner(final LineProcessor lineProcessor, Supplier<String> lineSupplier)
    {
        this.lineProcessor = Objects.requireNonNull(lineProcessor, "lineProcessor == null");
        this.logger = LoggerFactory.getLogger(this.getClass());
        console = Objects.requireNonNull(lineSupplier, "lineSupplier == null");
    }

    @Override
    public void run(final String... args)
    {
        if (args.length != 0) {
            throw new IllegalArgumentException("No argument expected");
        }
        this.logger.info("Welcome to Account Kata !");
        try {
            this.lineProcessor.perform("help");
        } catch (OperationFailedException e) {
            this.logger.debug("help operation is not supported, oups !", e);
        }
        String line;
        while ((line = requestInput()) != null) {
            try {
                lineProcessor.perform(line);
            } catch (OperationFailedException e) {
                this.logger
                    .error(
                        "Failed performing {} because of an unexpected error: {}",
                        line,
                        e,
                        e
                    );
            }
        }
    }

    private String requestInput()
    {
        this.logger.info("Please tell me what you want to do now:");
        return console.get();
    }
}
