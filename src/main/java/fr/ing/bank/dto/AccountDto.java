package fr.ing.bank.dto;

import lombok.Data;

@Data
public class AccountDto {

    private long id;

    private String label;

    private double balance;
}
