package fr.ing.interview.bankaccountkata.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class ClientDTO {

    private String id;
    private String lastName;
    private String firstName;
    private List<AccountDTO> accounts;
}
