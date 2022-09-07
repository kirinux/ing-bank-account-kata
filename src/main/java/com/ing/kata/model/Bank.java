package com.ing.kata.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Bank {

    @Id
    private String id;

    @NotBlank(message = "name field is required")
    private String name;
}
