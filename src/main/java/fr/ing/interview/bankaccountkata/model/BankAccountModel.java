package fr.ing.interview.bankaccountkata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "accounts")
public class BankAccountModel {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

}
