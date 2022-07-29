package backend;

import middleware.base.Fraction;

import javax.swing.*;

/**
 * This class contains all static methods which are necessary
 * to parse a matrix from string or a string from matrix.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public class ParserFraction {
    /**
     * This method parses a matrix to string. It needs the methods
     * toString(Fraction[]) and insertLinebreak(String[]) in order to do that.
     * @param matrix A matrix.
     * @return The given matrix as String.
     */
    public static String toString(Fraction[][] matrix) {
        if (matrix == null) {
            return "";
        }
        var lines = new String[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            lines[i] = toString(matrix[i]);
        }
        return insertLinebreak(lines);
    }
    /**
     * This method inserts between two fraction values a semicolon.
     * @param row An array with BigDecimal values.
     * @return The changed values as String.
     */
    private static String toString(Fraction[] row) {
        var sb = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            if (i < row.length - 1) {
                sb.append(row[i].toString()).append(";");
            } else {
                sb.append(row[i].toString());
            }
        }
        return sb.toString();
    }
    /**
     * This method inserts between every line an escaped linebreak.
     * @param lines An array with String values
     * @return The changed lines as String
     */
    private static String insertLinebreak(String[] lines) {
        var sb = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (i < lines.length - 1) {
                sb.append(lines[i]).append("\\n");
            } else {
                sb.append(lines[i]);
            }
        }
        return sb.toString();
    }
    /**
     * This method parse a multidimensional array matrix from the inline style.
     * @param matrix The inline style of a matrix
     * @return The multidimensional style of a matrix
     */
    public static Fraction[][] getMatrixFromInline(String matrix) {
        if (!matrix.equals("")) {
            var lines = matrix.split("\\\\n");
            var rowCount = lines.length;
            var columnCount = lines[0].split(";").length;
            var string_matrix = new String[rowCount][columnCount];
            for (int i = 0; i < rowCount; i++) {
                var values = lines[i].split(";");
                if (columnCount != values.length) {
                    JOptionPane.showMessageDialog(null,"Matrix does not have the same column count!",
                            "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                    return null;
                }
                string_matrix[i] = values;
            }
            var fraction_matrix = new Fraction[rowCount][columnCount];
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    fraction_matrix[row][column] = new Fraction(string_matrix[row][column]);
                }
            }
            return fraction_matrix;
        }
        return null;
    }
    /**
     * This method parse a one dimensional array vector from the inline style.
     * @param vector The inline style of a vector
     * @return The multidimensional style of a vector
     */
    public static Fraction[] getVectorFromInline(String vector) {
        if (!vector.equals("")) {
            var lines = vector.split("\\\\n");
            var rowCount = lines.length;
            var fraction_vector = new Fraction[rowCount];
            for (int i = 0; i < rowCount; i++) {
                fraction_vector[i] = new Fraction(lines[i]);
            }
            return fraction_vector;
        }
        return null;
    }
    /**
     * This method parses a vector to string. It needs the method
     * insertLinebreak(String[]) in order to do that.
     * @param vector A vector.
     * @return The given vector as String.
     */
    public static String vectorToString(Fraction[] vector) {
        if (vector == null) {
            return "";
        }
        var lines = new String[vector.length];
        for (int i = 0; i < vector.length; i++) {
            lines[i] = vector[i].toString();
        }
        return insertLinebreak(lines);
    }
}
