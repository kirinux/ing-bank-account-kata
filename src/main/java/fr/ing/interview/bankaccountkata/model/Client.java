package fr.ing.interview.bankaccountkata.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document
public class Client implements Serializable {
    private static final long serialVersionUID = -1101304494210661604L;

    @Id
    private String id;
    private String lastName;
    private String firstName;
    private List<Account> accounts;
}
