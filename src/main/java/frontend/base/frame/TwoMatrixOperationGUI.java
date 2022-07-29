package frontend.base.frame;

import backend.ParserFraction;
import frontend.gui.MainGUI;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.panel.MatrixPanel;
import backend.ParserBigDecimal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
                var matrix1 = ParserBigDecimal.getMatrixFromInline(matrixPanel1.getInline());
                var matrix2 = ParserBigDecimal.getMatrixFromInline(matrixPanel2.getInline());
                var matrixResult = (BigDecimal[][]) calculation.matrixCalculation(matrix1, matrix2);
                matrixPanelResult.updateInline(matrixResult);
            } else if (DataType.current == DataType.FRACTION) {
                var matrix1 = ParserFraction.getMatrixFromInline(matrixPanel1.getInline());
                var matrix2 = ParserFraction.getMatrixFromInline(matrixPanel2.getInline());
                var matrixResult = (Fraction[][]) calculation.matrixCalculation(matrix1, matrix2);
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    public void setJFrameTitle(String jFrameTitle) {
        setTitle(jFrameTitle);
    }
    public void setJButtonText(String jButtonText) {
        button.setText(jButtonText);
    }
}
