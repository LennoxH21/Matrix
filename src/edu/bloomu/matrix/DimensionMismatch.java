package edu.bloomu.matrix;

/**
 * When Dimensions of a Matrices don't match.
 *
 * @author Lennox Haynes
 */

public class DimensionMismatch extends RuntimeException
{

    /**
     * Throws a DimensionMismatch Exception
     * @param message The message you put to appear for the exception
     */
    public DimensionMismatch(String message)
    {
        super(message);
    }

    /**
     * Default Constructor
     */
    public DimensionMismatch()
    {
        System.out.println("DimensionMismatch Exception");
    }
}
