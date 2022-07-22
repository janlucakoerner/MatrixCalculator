package gui.base;

import arithmeticoperations.Calculation;
import parser.Parser;

import javax.swing.*;
import java.awt.*;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/21)
 */
public class TwoMatrixOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public TwoMatrixOperationGUI(Calculation calculation) {
        var matrixPanel1 = new MatrixPanel(this);
        var matrixPanel2 = new MatrixPanel(this);
        var matrixPanelResult = new MatrixPanel(this);
        var panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        matrixPanel1.setText("Matrix 1");
        matrixPanel2.setText("Matrix 2");
        matrixPanelResult.setText("Result");
        panel.add(matrixPanel1);
        panel.add(matrixPanel2);
        panel.add(matrixPanelResult);
        add(panel, BorderLayout.CENTER);
        button.setText("Calculate");
        button.addActionListener(e -> {
            var matrix1 = Parser.getMatrixFromInline(matrixPanel1.getInline());
            var matrix2 = Parser.getMatrixFromInline(matrixPanel2.getInline());
            var matrixResult = calculation.matrixCalculation(matrix1, matrix2);
            matrixPanelResult.updateInline(matrixResult);
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
