package frontend.base.frame;

import middleware.base.DataType;
import middleware.base.Fraction;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public class OpenVectorGUI extends JFrame {
    /**
     * This method opens a JFrame for representation a vector.
     * The JTextFields contains the value of each cell.
     * All Components are not editable.
     * @param parent The parent Frame for hiding and showing.
     * @param vector The multidimensional style of a vector.
     */
    public OpenVectorGUI(JFrame parent, Object[] vector) {
        if (vector == null) {
            JOptionPane.showMessageDialog(null,"Vector does not contain data!",
                    "Parse Error", JOptionPane.ERROR_MESSAGE, null);
            return;
        }
        parent.setVisible(false);
        var rowCount = vector.length;
        var panel = new JPanel();
        var button_close = new JButton();
        var textFields_vector = new JTextField[rowCount];

        for (int i = 0; i < rowCount; i++) {
            textFields_vector[i] = new JTextField();
            textFields_vector[i].setEditable(false);
            panel.add(textFields_vector[i]);
        }

        var x = 0;
        if (DataType.current == DataType.BIG_DECIMAL && vector instanceof BigDecimal[]) {
            for (BigDecimal rows: (BigDecimal[]) vector) {
                textFields_vector[x].setText(rows.toString());
                x++;
            }
        } else if (DataType.current == DataType.FRACTION && vector instanceof Fraction[]) {
            for (Fraction rows: (Fraction[]) vector) {
                textFields_vector[x].setText(rows.toString());
                x++;
            }
        }

        panel.setLayout(new GridLayout(rowCount, 1));

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
