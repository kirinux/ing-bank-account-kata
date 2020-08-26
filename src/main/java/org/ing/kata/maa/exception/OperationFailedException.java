package org.ing.kata.maa.exception;

/**
 * @author Antoine Malliarakis
 */
public final class OperationFailedException
    extends Exception
{

    public OperationFailedException(final String message)
    {
        super(message);
    }

    public OperationFailedException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
