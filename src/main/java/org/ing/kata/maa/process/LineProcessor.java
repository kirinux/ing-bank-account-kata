package org.ing.kata.maa.process;

import org.ing.kata.maa.exception.OperationFailedException;

/**
 * Implementations of this interface shall be consuming lines read from the console.
 *
 * @author Antoine Malliarakis
 */
public interface LineProcessor
{

    /**
     * Performs the specified command.
     *
     * @param line the line read from the console, never <code>null</code>
     * @throws OperationFailedException if there was any unexpected error while trying to process the given line
     *                                  parameter
     */
    void perform(String line)
        throws OperationFailedException;

}
