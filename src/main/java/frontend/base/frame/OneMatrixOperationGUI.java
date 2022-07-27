package frontend.base.frame;

import middleware.base.ICalculation;
import frontend.base.panel.MatrixPanel;
import backend.Parser;

import javax.swing.*;
import java.awt.*;

public class OneMatrixOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public OneMatrixOperationGUI(ICalculation calculation) {
        var matrixPanel = new MatrixPanel(this);
        var matrixPanelResult = new MatrixPanel(this);
        var panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        matrixPanel.setText("Matrix");
        matrixPanelResult.setText("Result");
        panel.add(matrixPanel);
        panel.add(matrixPanelResult);
        add(panel, BorderLayout.CENTER);
        button.setText("Calculate");
        button.addActionListener(e -> {
            var matrix1 = Parser.getMatrixFromInline(matrixPanel.getInline());
            var matrixResult = calculation.matrixCalculation(matrix1);
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
