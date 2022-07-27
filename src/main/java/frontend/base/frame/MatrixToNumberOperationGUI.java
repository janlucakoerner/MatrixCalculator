package frontend.base.frame;

import middleware.base.ICalculation;
import frontend.base.panel.MatrixPanel;
import backend.Parser;

import javax.swing.*;
import java.awt.*;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/23)
 */
public class MatrixToNumberOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public MatrixToNumberOperationGUI(ICalculation calculation) {
        var matrixPanel1 = new MatrixPanel(this);
        var textField_result = new JTextField();
        var panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        matrixPanel1.setText("Matrix");
        panel.add(matrixPanel1);
        panel.add(textField_result);
        add(panel, BorderLayout.CENTER);
        button.setText("Calculate");
        button.addActionListener(e -> {
            var matrix1 = Parser.getMatrixFromInline(matrixPanel1.getInline());
            var result = calculation.numberCalculation(matrix1);
            textField_result.setText(result.toString());
        });
        add(button, BorderLayout.SOUTH);
        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void setJFrameTitle(String jFrameTitle) {
        setTitle(jFrameTitle);
    }
    public void setJButtonText(String jButtonText) {
        button.setText(jButtonText);
    }
}
