package frontend.base.frame;

import middleware.base.DataType;
import middleware.base.ICalculation;
import frontend.base.panel.MatrixPanel;
import backend.Parser;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

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
            if (DataType.current == DataType.BIG_DECIMAL) {
                var matrix1 = Parser.instance_bigDecimal.getMatrixFromInline(matrixPanel.getInline());
                var matrixResult = calculation.matrixCalculation(matrix1);
                matrixPanelResult.updateInline((BigDecimal[][]) matrixResult);
            } else if (DataType.current == DataType.FRACTION) {
                var matrix1 = Parser.instance_fraction.getMatrixFromInline(matrixPanel.getInline());
                var matrixResult = calculation.matrixCalculation(matrix1);
                matrixPanelResult.updateInline((BigDecimal[][]) matrixResult);
            }
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
