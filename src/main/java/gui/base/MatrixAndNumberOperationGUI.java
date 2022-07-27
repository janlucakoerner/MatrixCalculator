package gui.base;

import arithmeticoperations.Calculation;
import parser.Parser;

import javax.swing.*;
import java.awt.*;

public class MatrixAndNumberOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public MatrixAndNumberOperationGUI(Calculation calculation) {
        var matrixPanel = new MatrixPanel(this);
        var numberPanel = new NumberPanel(this);
        var matrixPanelResult = new MatrixPanel(this);
        var panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        matrixPanel.setText("Matrix");
        numberPanel.setText("Number");
        matrixPanelResult.setText("Result");
        panel.add(matrixPanel);
        panel.add(numberPanel);
        panel.add(matrixPanelResult);
        add(panel, BorderLayout.CENTER);
        button.setText("Calculate");
        button.addActionListener(e -> {
            var matrix = Parser.getMatrixFromInline(matrixPanel.getInline());
            var number = numberPanel.getValue();
            var matrixResult = calculation.matrixCalculation(matrix, number);
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
