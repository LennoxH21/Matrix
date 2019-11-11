package edu.bloomu.matrix;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Creates a Matrix object which is a n-by-m array of numbers.
 *
 * @author Lennox Haynes
 */

public class Matrix implements Comparable<Matrix>
{
    private int rows; // Stores the amount of rows in the Matrix
    private int columns; // Stores the amount of columns in the Matrix
    private double[][] matrix; // The Matrix

    /**
     * Creates a Matrix.
     *
     * @param rows The amount of rows in the Matrix.
     * @param columns The amount of columns in the Matrix.
     * @param doubleArray A double[][] to store entries into the Matrix.
     */
    public Matrix(int rows, int columns, double[][] doubleArray)
    {
        this.rows = rows; // The amount of rows in the Matrix
        this.columns = columns; // The amount of columns in the Matrix
        this.matrix = doubleArray;
    }

    /**
     * DEFAULT CONSTRUCTOR
     *
     * Creates an empty Matrix.
     */
    public Matrix()
    {
        this.rows = 0;
        this.columns = 0;
        this.matrix = new double[this.rows][this.columns];
    }

    /**
     * Takes the position (row, column) in a matrix and a double and sets the entry to
     * the position in the Matrix.
     *
     * @param row The row where you want to place the entry.
     * @param column The column where you want to place the entry.
     * @param entry The entry you want to place in the Matrix.
     */
    public void setEntry(int row, int column, double entry)
    {
        this.matrix[row][column] = entry; // Sets index at (row, column) equal to entry
    }

    /**
     * An Add method that finds the sum of this matrix (the matrix that
     * calls/activates the method) and another matrix.
     *
     * @throws DimensionMismatch If the dimensions of two matrices are not equal.
     *
     * @return The sum of two Matrices.
     *
     * @param matrix2  Matrix to be added to this Matrix.
     */
    public Matrix add(Matrix matrix2) throws DimensionMismatch
    {
        // Checks to see if the Matrices being added have different dimensions
        if (this.rows != matrix2.rows || this.columns != matrix2.columns)
        {
            throw new DimensionMismatch("THESE MATRICES DON'T HAVE THE APPROPRIATE " +
                    "DIMENSION!");
        }

        // Creates a Matrix to store the sums of the entries from the two Matrices
        Matrix matricesSum = new Matrix(matrix2.rows, matrix2.columns,
                new double[matrix2.rows][matrix2.columns]);

        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[i].length; j++)
            {
                // Indexes through the Matrices and adds the entries to the i, j index
                matricesSum.setEntry(i, j,this.matrix[i][j] + matrix2.matrix[i][j]);
            }
        }
        // A Matrix containing the sum of entries of the Matrices
        return matricesSum;
    }

    /**
     * A Subtract method that finds the difference of this matrix (the matrix that
     * calls/activates the method) and another matrix.
     *
     * @throws DimensionMismatch If the dimensions of two matrices are not equal.
     *
     * @return The difference of two Matrices.
     *
     * @param matrix2  Matrix to be subtracted from this Matrix.
     */
    public Matrix subtract(Matrix matrix2) throws DimensionMismatch
    {
        // Checks to see if the Matrices being subtracted have the different dimensions
        if (this.rows != matrix2.rows || this.columns != matrix2.columns)
        {
            throw new DimensionMismatch("THESE MATRICES DON'T HAVE THE APPROPRIATE " +
                    "DIMENSION!");
        }

        // Creates a Matrix to store the difference of the entries from the two Matrices
        Matrix matricesDifference = new Matrix(matrix2.rows, matrix2.columns,
                new double[matrix2.rows][matrix2.columns]);

        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[i].length; j++)
            {
                // Indexes through the Matrices and subtracts the entries at the i, j index
                matricesDifference.setEntry(i, j,
                        this.matrix[i][j] - matrix2.matrix[i][j]);
            }
        }
        // A Matrix containing the difference of entries of the Matrices
        return matricesDifference;
    }

    /**
     * A Multiply method that finds the product of this matrix (the matrix that
     * calls/activates the method) and another matrix.
     *
     * @throws DimensionMismatch If the dimensions of the matrices are not appropriate.
     *
     * @return The product of two Matrices.
     *
     * @param matrix2  Matrix to be multiplied with this Matrix.
     */
     public Matrix multiply(Matrix matrix2) throws DimensionMismatch
     {
         // Checks to see if the Matrices being multiplied have appropriate dimensions
         if (this.columns != matrix2.rows)
         {
             throw new DimensionMismatch("THESE MATRICES DON'T HAVE THE APPROPRIATE " +
                     "DIMENSION!");
         }

         // Creates a Matrix to store the sums of the entries from the two Matrices
         Matrix matricesProduct = new Matrix(this.rows, matrix2.columns,
                 new double[this.rows][matrix2.columns]);

         // Multiplies the entries and gets the value(s)/product of the Matrices
         for (int i = 0; i < this.rows; i++) // Rows of the first Matrix
         {
             for (int j = 0; j < matrix2.columns; j++) // Columns of the second Matrix
             {
                 for (int k = 0; k < this.columns; k++) // Columns of first Matrix
                 {
                     // Multiples, Adds, the stores the values in prodArray
                     matricesProduct.matrix[i][j] +=
                             this.matrix[i][k] * matrix2.matrix[k][j];

                 }
             }
         }
         // A Matrix containing the product of entries of the Matrices
         return matricesProduct;
     }

    /**
     * Checks to see if two Matrices are equal.
     *
     * @return true if both Matrices have the same dimensions and their corresponding
     *         entries are equal, returns false otherwise.
     *
     * @param matrix2 Matrix to be compared to this Matrix.
     */
    public boolean equals(Matrix matrix2)
    {
        // Iterates through the matrices and returns false if any entries aren't equal.
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[i].length; j++)
            {
                // Checks to see if the Matrices have the same dimensions and entries
                if ((this.rows != matrix2.rows || this.columns != matrix2.columns) ||
                        (this.matrix[i][j] != matrix2.matrix[i][j])) {
                    return false;
                }
            }
        }
        return true; // If dimensions and all entries are equal.
    }

    /**
     * The Transpose of an n-by-m matrix is an m-by-n matrix whose entry at the (i, j)
     * position is the value at (j, i) of the original matrix.
     *
     * @return A transposed (m-by-n) version of a Matrix.
     */
    public Matrix transpose()
    {
        // Makes a m by n Matrix for the n-by-m Matrix
        Matrix transposedMatrix = new Matrix(this.columns, this.rows,
                new double[this.columns][this.rows]);
        /*
         * Iterates through the Matrices and put the entries from the n-by-m
         * into the transposedMatrix as m-by-n
         */
        for (int i = 0; i < this.columns; i++)
        {
            for (int j = 0; j < this.rows; j++)
            {
                /*
                 * Adds the (i, j) entries of the Matrix into the (j, i) position in
                 * the transposedMatrix
                 */
                transposedMatrix.matrix[i][j] = this.matrix[j][i];
            }
        }
        // A Matrix containing the Transposed (m-by-n) version of a (n-by-m) Matrix
        return transposedMatrix;
    }

    /**
     * Adds the absolute values of all the entries in the Matrix.
     *
     * @return The sum of absolute values of the entries in the Matrix.
     */
    public int matrixAbsTotal()
    {
        int absValueMatrix = 0; // Store the sum of abs values of the entries in matrix

        // Adds the absolute values of the entries in matrix
        for (double[] row : this.matrix)
        {
            for (double value : row)
            {
                absValueMatrix += Math.abs(value);
            }
        }
        // The sum absolute values
        return absValueMatrix;
    }

    /**
     * Fills Matrix with random integers in range (-30, 30).
     */
    public void fillMatrix()
    {
        // Used to get Random values
        ThreadLocalRandom rand = ThreadLocalRandom.current();

        // Iterates through and adds a random number into the Matrix until it is full
        for (int i = 0; i < this.matrix.length; i++)
        {
            for (int j = 0; j < this.matrix[i].length; j++)
            {
                // Creates a random number and adds it to the i,j index of the Matrix
                this.matrix[i][j] =  rand.nextInt(-30, 30);
            }
        }
    }

    /**
     * @return an ascending ordered Array of Matrices.
     *
     * @param matrix2  Matrix to be compared to this Matrix.
     */
    @Override
    public int compareTo(Matrix matrix2)
    {
        // Returns the larger value of the two Matrices
        return Integer.compare(this.matrixAbsTotal(), matrix2.matrixAbsTotal());
    }

    /**
     * @return The String Representation of a Matrix.
     */
    @Override
    public String toString()
    {
        // Stores the entries from the matrix into a string builder
        StringBuilder matrixString = new StringBuilder();

        // Concatenates the entries from the matrix onto the matrixString
        for (double[] doubles : this.matrix)
        {
            for (double aDouble : doubles)
            {
                matrixString.append(aDouble).append(" ");
            }
            matrixString.append("\n");
        }
        return String.valueOf(matrixString); // Returns the String Value of matrixString
    }
}
