package fr.ing.interview.bankaccountkata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Integer id;

    private Double balance;
}
