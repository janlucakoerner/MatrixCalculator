package gui;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 * This class delivers opportunities to show a matrix
 * in a multidimensional style to the users screen.
 * All methods are static dialogs inherited from JFrame.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class MatrixRepresentation {

    private static BigDecimal[][] matrix;
    /**
     * This method opens a JFrame for representation a matrix.
     * The JTextFields contains the value of each cell.
     * All Components are not editable.
     * @param parent The parent Frame for hiding and showing.
     * @param matrix The multidimensional style of a matrix.
     */
    public static void showOpenDialog(JFrame parent, BigDecimal[][] matrix) {
        if (matrix == null) {
            JOptionPane.showMessageDialog(null,"Matrix does not contain data!",
                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        } else if (matrix[0] == null) {
            JOptionPane.showMessageDialog(null,"Matrix does not contain data!",
                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        parent.setVisible(false);
        var rowCount = matrix.length;
        var columnCount = matrix[0].length;

        var frame = new JFrame();
        var panel = new JPanel();
        var button_close = new JButton();
        var textFields_matrix = new JTextField[rowCount * columnCount];

        for (int i = 0; i < rowCount * columnCount; i++) {
            textFields_matrix[i] = new JTextField();
            textFields_matrix[i].setEditable(false);
            panel.add(textFields_matrix[i]);
        }

        var x = 0;
        for (BigDecimal[] rows: matrix) {
            for (BigDecimal value: rows) {
                textFields_matrix[x].setText(value.toString());
                x++;
            }
        }

        panel.setLayout(new GridLayout(rowCount, columnCount));

        button_close.setText("Close");
        button_close.addActionListener(e -> {
            frame.setVisible(false);
            parent.setVisible(true);
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(button_close, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        frame.setVisible(true);
    }

    public static BigDecimal[][] showOpenAndEditDialog(JFrame parent, BigDecimal[][] matrix) {
        MatrixRepresentation.matrix = null;
        if (matrix == null) {
            JOptionPane.showMessageDialog(null, "Matrix does not contain data!",
                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (matrix[0] == null) {
            JOptionPane.showMessageDialog(null, "Matrix does not contain data!",
                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        parent.setVisible(false);
        var rowCount = matrix.length;
        var columnCount = matrix[0].length;

        var frame = new JFrame();
        var panel = new JPanel();
        var button_close = new JButton();
        var textFields_matrix = new JTextField[rowCount * columnCount];

        for (int i = 0; i < rowCount * columnCount; i++) {
            textFields_matrix[i] = new JTextField();
            panel.add(textFields_matrix[i]);
        }

        var x = 0;
        for (BigDecimal[] rows : matrix) {
            for (BigDecimal value : rows) {
                textFields_matrix[x].setText(value.toString());
                x++;
            }
        }

        panel.setLayout(new GridLayout(rowCount, columnCount));
        button_close.setText("Close");
        button_close.addActionListener(e -> {
            frame.setVisible(false);
            parent.setVisible(true);
            var y = 0;
            MatrixRepresentation.matrix = new BigDecimal[rowCount][columnCount];
            for (int row = 0; row < rowCount; row++) {
                for (int column = 0; column < columnCount; column++) {
                    try {
                        MatrixRepresentation.matrix[row][column] = new BigDecimal(textFields_matrix[y].getText());
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Matrix could not be parsed!",
                                "Parse Error", JOptionPane.ERROR_MESSAGE, null);
                        return;
                    }
                }
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(button_close, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
        frame.setVisible(true);

        return MatrixRepresentation.matrix;
    }
}