package org.ing.kata.maa.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Instances of this class shall gather information on a bank account.
 *
 * @author Antoine Malliarakis
 */
public final class Account
{

    private final String firstName;

    private final String lastName;

    private BigDecimal status;

    /**
     * Creates a new instance of this class with an initial status of zero.
     *
     * @param firstName the first name of the created instance's customer, should never be <code>null</code>
     * @param lastName  the last name of the created instance's customer, should never be <code>null</code>
     */
    public Account(String firstName, String lastName)
    {
        super();
        this.firstName = Objects.requireNonNull(firstName, "firstName == null");
        this.lastName = Objects.requireNonNull(lastName, "lastName == null");
        setStatus(BigDecimal.ZERO);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public BigDecimal getStatus()
    {
        return status;
    }

    public Account setStatus(final BigDecimal status)
    {
        this.status = Objects.requireNonNull(status, "status == null");
        return this;
    }
}
