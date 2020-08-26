package org.ing.kata.maa.app;

import org.ing.kata.maa.process.LineProcessor;
import org.ing.kata.maa.process.std.PlainEnglishLineProcessor;
import org.ing.kata.maa.service.impl.DefaultAccountOperator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.Console;

/**
 * @author Antoine Malliarakis
 */
@SpringBootApplication()
@Import(
    {
        PlainEnglishLineProcessor.class,
        DefaultAccountOperator.class
    }
)
public class AccountKataMain
{


    public static void main(String[] args)
    {
        SpringApplication.run(AccountKataMain.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(LineProcessor lineProcessor)
    {
        final Console console = System.console();
        if (null == console) {
            throw new IllegalStateException("Cannot run without a console.");
        }
        return new KataCommandLineRunner(lineProcessor, console :: readLine);
    }

}
