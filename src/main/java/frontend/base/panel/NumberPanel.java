package frontend.base.panel;

import middleware.base.DataType;
import middleware.base.Fraction;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
/**
 * This class creates a panel to be added for each number calculation.
 * The panel comes with a text field for a number declaration.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class NumberPanel extends JPanel {
    private final JLabel label = new JLabel();
    private final JTextField textField = new JTextField();
    /**
     * Constructor which creates a JPanel customized for numbers.
     */
    public NumberPanel() {
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
    /**
     * This method sets the text of the JLabel.
     * @param text The text which should be displayed in the JLabel.
     */
    public void setText(String text) {
        label.setText(text);
    }
    /**
     * This method sets the inline textfield editable or not.
     * @param editable Boolean value which represent the editable state.
     */
    public void setEditable(boolean editable) {
        textField.setEditable(editable);
    }
    /**
     * This method returns the value of the number.
     * @return Value of the number.
     */
    public Object getValue() {
        if (DataType.current == DataType.BIG_DECIMAL)
            return new BigDecimal(textField.getText());
        else if (DataType.current == DataType.FRACTION)
            return new Fraction(textField.getText());
        else
            return null;
    }
    /**
     * This method sets the value of the number by a BigDecimal number.
     * @param value The value as a BigDecimal.
     */
    public void setValue(BigDecimal value) {
        textField.setText(value.toString());
    }
    /**
     * This method sets the value of the number by a Fraction number.
     * @param value The value as a Fraction.
     */
    public void setValue(Fraction value) {
        textField.setText(value.toString());
    }
}
