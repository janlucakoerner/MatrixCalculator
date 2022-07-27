package backend;

import middleware.base.Fraction;

import javax.swing.*;
import java.math.BigDecimal;

/**
 * This class contains all static methods which are necessary
 * to parse a matrix from string or a string from matrix.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class Parser {
    //------------------------------------------------------------------------------------------------------------------
    private Parser() {}
    /**
     * BigDecimalhis method parses a matrix to string. It needs the methods
     * toString(BigDecimal[]) and insertLinebreak(String[]) in order to do that.
     * @param matrix A matrix.
     * @return The given matrix as String.
     */
    public static String toString(BigDecimal[][] matrix) {
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
     * This method inserts between to BigDecimal values a semicolon.
     * @param row An array with BigDecimal values.
     * @return The changed values as String.
     */
    private static String toString(BigDecimal[] row) {
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

    //------------------------------------------------------------------------------------------------------------------

    /**
     * This method parse a multidimensional array matrix from the inline style.
     * @param matrix The inline style of a matrix
     * @return The multidimensional style of a matrix
     */
    public static BigDecimal[][] getMatrixFromInline(String matrix) {
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
            var bigDecimal_matrix = new BigDecimal[rowCount][columnCount];
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    bigDecimal_matrix[row][column] = new BigDecimal(string_matrix[row][column]);
                }
            }
            return bigDecimal_matrix;
        }
        return null;
    }
}
