package com.ing.kata.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Client {

    @Id
    private String id;

    @NotBlank(message = "firstname field is required")
    private String firstname;

    @NotBlank(message = "lastname field is required")
    private String lastname;

    @DBRef
    @NotBlank(message = "bank field is required")
    private Bank bank;
}
