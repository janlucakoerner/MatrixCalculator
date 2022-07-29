package frontend.base.frame;

import backend.ParserFraction;
import frontend.gui.MainGUI;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.panel.MatrixPanel;
import frontend.base.panel.NumberPanel;
import backend.ParserBigDecimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public class MatrixNumberToMatrixOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public MatrixNumberToMatrixOperationGUI(ICalculation calculation) {
        var matrixPanel = new MatrixPanel(this);
        var numberPanel = new NumberPanel();
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
            if (DataType.current == DataType.BIG_DECIMAL) {
                var matrix = ParserBigDecimal.getMatrixFromInline(matrixPanel.getInline());
                var number = numberPanel.getValue();
                var matrixResult = (BigDecimal[][]) calculation.matrixCalculation(matrix, number);
                matrixPanelResult.updateInline(matrixResult);
            } else if (DataType.current == DataType.FRACTION) {
                var matrix = ParserFraction.getMatrixFromInline(matrixPanel.getInline());
                var number = numberPanel.getValue();
                var matrixResult = (Fraction[][]) calculation.matrixCalculation(matrix, number);
                matrixPanelResult.updateInline(matrixResult);
            }
        });
        add(button, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainGUI.instance.setVisible(true);
                setVisible(false);
            }
        });
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
