package fr.ing.interview.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDto {

    private Long accountId;

    private BigDecimal amount;

}
