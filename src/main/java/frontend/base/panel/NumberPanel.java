package frontend.base.panel;

import javax.swing.*;
import java.awt.*;

public class NumberPanel<T> extends JPanel {
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
    public T getValue() {
        return new T(textField.getText());
    }
}
