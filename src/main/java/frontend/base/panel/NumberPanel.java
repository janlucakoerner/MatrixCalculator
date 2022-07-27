package frontend.base.panel;

import middleware.base.DataType;
import middleware.base.Fraction;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class NumberPanel extends JPanel {
    private final JLabel label = new JLabel();
    private final JTextField textField = new JTextField();
    public NumberPanel(JFrame parent) {
        label.setText("Number:");
        textField.setColumns(30);

        setLayout(new GridBagLayout());
        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,5,0,0);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(label, gbc);
        gbc.insets = new Insets(0,0,0,5);
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);
    }

    public void setText(String text) {
        label.setText(text);
    }

    public void setEditable(boolean editable) {
        textField.setEditable(editable);
    }
    public Object getValue() {
        if (DataType.current == DataType.BIG_DECIMAL)
            return new BigDecimal(textField.getText());
        else if (DataType.current == DataType.FRACTION)
            return new Fraction(textField.getText());
        else
            return null;
    }
}
