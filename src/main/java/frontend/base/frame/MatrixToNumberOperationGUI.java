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
import java.math.BigDecimal;

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
            if (DataType.current == DataType.BIG_DECIMAL) {
                var matrix1 = ParserBigDecimal.getMatrixFromInline(matrixPanel1.getInline());
                var result = (BigDecimal) calculation.numberCalculation(matrix1);
                textField_result.setText(result.toString());
            } else if (DataType.current == DataType.FRACTION) {
                var matrix1 = ParserFraction.getMatrixFromInline(matrixPanel1.getInline());
                var result = (Fraction) calculation.numberCalculation(matrix1);
                textField_result.setText(result.toString());
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
