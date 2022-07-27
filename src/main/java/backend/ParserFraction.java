package backend;

import middleware.base.Fraction;

import javax.swing.*;

/**
 * This class contains all static methods which are necessary
 * to parse a matrix from string or a string from matrix.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class ParserFraction {
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
}
