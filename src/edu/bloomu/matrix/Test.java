package edu.bloomu.matrix;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to test the Methods from the Matrix Class.
 *
 * @author Lennox Haynes
 */

public class Test {

    public static void main(String[] args) {

        // ADD
        System.out.println("ADD EXAMPLE:");
        Matrix matrix1 = new Matrix(3, 2, new double[3][2]);
        matrix1.setEntry(0, 0, 2);
        matrix1.setEntry(0, 1, 5);
        matrix1.setEntry(1, 0, -3);
        matrix1.setEntry(1, 1, 8);
        matrix1.setEntry(2, 0, 7);
        matrix1.setEntry(2, 1, 5);

        Matrix matrix2 = new Matrix(3, 2, new double[3][2]);
        matrix2.setEntry(0, 0, -3);
        matrix2.setEntry(0, 1, 10);
        matrix2.setEntry(1, 0, 2);
        matrix2.setEntry(1, 1, 8);
        matrix2.setEntry(2, 0, 9);
        matrix2.setEntry(2, 1, 0);

        System.out.println("matrix1 =\n" + matrix1 );
        System.out.println("matrix2 =\n" + matrix2 );
        System.out.println("matrix1 + matrix2 =\n" + matrix1.add(matrix2));

        // SUBTRACT
        System.out.println("SUBTRACT:");
        System.out.println("matrix1 - matrix2 =\n" + matrix1.subtract(matrix2));

        // MULTIPLY
        System.out.println("MULTIPLY EXAMPLE:");
        Matrix matrix3 = new Matrix(2, 3, new double[2][3]);
        matrix3.setEntry(0, 0, 1);
        matrix3.setEntry(0, 1, 2);
        matrix3.setEntry(0, 2, 3);
        matrix3.setEntry(1, 0, 3);
        matrix3.setEntry(1, 1, 2);
        matrix3.setEntry(1, 2, 1);

        Matrix matrix4 = new Matrix(3, 2, new double[3][2]);
        matrix4.setEntry(0, 0, 2);
        matrix4.setEntry(1, 0, -3);
        matrix4.setEntry(2, 0, 7);
        matrix4.setEntry(0, 1, 5);
        matrix4.setEntry(1, 1, 8);
        matrix4.setEntry(2, 1, 5);

        System.out.println("matrix3 =\n" + matrix3);
        System.out.println("matrix4 =\n" + matrix4);
        System.out.println("matrix3 * matrix4 =\n" + matrix3.multiply(matrix4));

        // EQUALS
        System.out.println("EQUALS EXAMPLE:\n" +
                        "matrix1 == matrix2 = " + matrix1.equals(matrix2) + "\n" + // FALSE
                        "matrix2 == matrix2 = " + matrix2.equals(matrix2) + "\n" + // TRUE
                        "matrix1 == matrix3 = " + matrix1.equals(matrix3) + "\n"   // FALSE
                );

        // TRANSPOSE
        System.out.println("TRANSPOSE EXAMPLE:");
        System.out.print(matrix1 + "Transposed is:\n" + matrix1.transpose() + "\n");

        // Makes an Array of completely full Matrices
        Matrix[] matrices = new Matrix[5]; // An Array of Matrices
        ThreadLocalRandom rand = ThreadLocalRandom.current(); // Used to receive negative values
        for (int i = 0; i < matrices.length; i++)
        {
            int row = rand.nextInt(1, 5);
            int col = rand.nextInt(1, 5);
            // Creates a Matrix to put in the matrices Array
            matrices[i] = new Matrix(row, col, new double[row][col]);
            // Completely fills the Matrix with Values
            matrices[i].fillMatrix();
        }

        // Prints the unsorted array of Matrices
        System.out.println("Unordered Matrices:");
        for (Matrix value : matrices)
        {
            System.out.print(value + "\n");
        }

        // Orders and Prints using the COMPARABLE Arrays.sort(); Ascending
        System.out.println("Comparable Ordered Matrices: Ascending Order");
        Arrays.sort(matrices);

        for (Matrix matrix : matrices)
        {
            System.out.print(matrix + "\n");
        }

        // Orders and Prints using the Comparator Lamba; Descending
        System.out.println("Comparator Ordered Matrices: Descending Order");
        Arrays.sort(matrices, (m1, m2) ->
                Integer.compare(m2.matrixAbsTotal(), m1.matrixAbsTotal()));

        for (Matrix matrix : matrices)
        {
            System.out.print(matrix + "\n");
        }

        /*
         * TRY-CATCH STATEMENTS
         * Throws DimensionMismatch exception if the dimensions are not appropriate.
         */
        System.out.println("TRY-CATCH STATEMENT EXAMPLE:");
        try {matrix1.add(matrix3);
        }catch (DimensionMismatch dimensionMismatch)
        {
            System.out.println("This is the add() try-catch statement on Line 119");
        }

        try {matrix2.subtract(matrix3);
        }catch (DimensionMismatch dimensionMismatch)
        {
            System.out.println("This is the subtract() try-catch statement on Line 125");
        }

        try {matrix1.multiply(matrix4);
        }catch (DimensionMismatch dimensionMismatch)
        {
            System.out.println("This is the multiply() try-catch statement on Line 131");
        }
    }
}
