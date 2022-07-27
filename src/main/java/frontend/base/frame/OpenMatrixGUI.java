package frontend.base.frame;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class OpenMatrixGUI extends JFrame {
    /**
     * This method opens a JFrame for representation a matrix.
     * The JTextFields contains the value of each cell.
     * All Components are not editable.
     * @param parent The parent Frame for hiding and showing.
     * @param matrix The multidimensional style of a matrix.
     */
    public OpenMatrixGUI(JFrame parent, BigDecimal[][] matrix) {
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
            setVisible(false);
            parent.setVisible(true);
        });

        add(panel, BorderLayout.CENTER);
        add(button_close, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);
    }
}
