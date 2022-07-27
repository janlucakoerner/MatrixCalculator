package frontend.base.frame;

import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.panel.MatrixPanel;
import backend.Parser;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/21)
 */
public class TwoMatrixOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public TwoMatrixOperationGUI(ICalculation calculation) {
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
            if (DataType.current == DataType.BIG_DECIMAL) {
                var matrix1 = Parser.instance_bigDecimal.getMatrixFromInline(matrixPanel1.getInline());
                var matrix2 = Parser.instance_bigDecimal.getMatrixFromInline(matrixPanel2.getInline());
                var matrixResult = calculation.matrixCalculation(matrix1, matrix2);
                matrixPanelResult.updateInline((BigDecimal[][]) matrixResult);
            } else if (DataType.current == DataType.FRACTION) {
                var matrix1 = Parser.instance_fraction.getMatrixFromInline(matrixPanel1.getInline());
                var matrix2 = Parser.instance_fraction.getMatrixFromInline(matrixPanel2.getInline());
                var matrixResult = calculation.matrixCalculation(matrix1, matrix2);
                matrixPanelResult.updateInline((Fraction[][]) matrixResult);
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
