package fr.ing.interview.bankaccountkata.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operations")
public class OperationModel {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal balance;

    @NotNull
    private Date operationDate;

    @NotNull
    private String type;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccountModel account;

}
