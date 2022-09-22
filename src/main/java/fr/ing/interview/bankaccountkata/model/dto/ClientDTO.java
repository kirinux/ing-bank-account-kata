package fr.ing.interview.bankaccountkata.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String id;
    private String lastName;
    private String firstName;
    private List<AccountDTO> accounts;
}
