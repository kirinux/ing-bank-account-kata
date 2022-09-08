package com.ing.kata.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Document
public class Transaction {
    @Id
    private String id;

    @NotBlank(message = "date field is required")
    private Date date;

    @NotBlank(message = "transactionType field is required")
    private TransactionType transactionType;

    @NotBlank(message = "amount field is required")
    private BigDecimal amount;

    @DBRef
    @NotBlank(message = "account field is required")
    private Account account;
}
