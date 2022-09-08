package com.ing.kata.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Document
public class Account {

    @Id
    private String id;

    @NotBlank(message = "name field is required")
    private String name;

    private BigDecimal balance;

    @DBRef
    @NotBlank(message = "bank field is required")
    private Client client;
}
