package frontend.base.frame;

import backend.ParserBigDecimal;
import backend.ParserFraction;
import frontend.base.panel.MatrixPanel;
import frontend.base.panel.VectorPanel;
import frontend.gui.MainGUI;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

public class MatrixVectorToVectorOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public MatrixVectorToVectorOperationGUI(ICalculation calculation) {
        var matrixPanel = new MatrixPanel(this);
        var vectorPanel = new VectorPanel(this);
        var resultPanel = new VectorPanel(this);
        var panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        matrixPanel.setText("Matrix");
        panel.add(matrixPanel);
        vectorPanel.setText("Vector");
        panel.add(vectorPanel);
        resultPanel.setText("Result");
        panel.add(resultPanel);
        add(panel, BorderLayout.CENTER);
        button.setText("Calculate");
        button.addActionListener(e -> {
            if (DataType.current == DataType.BIG_DECIMAL) {
                var matrix = ParserBigDecimal.getMatrixFromInline(matrixPanel.getInline());
                var vector = ParserBigDecimal.getVectorFromInline(vectorPanel.getInline());
                var result = (BigDecimal[]) calculation.vectorCalculation(matrix, vector);
                resultPanel.updateInline(result);
            } else if (DataType.current == DataType.FRACTION) {
                var matrix = ParserFraction.getMatrixFromInline(matrixPanel.getInline());
                var vector = ParserFraction.getVectorFromInline(vectorPanel.getInline());
                var result = (Fraction[]) calculation.vectorCalculation(matrix, vector);
                resultPanel.updateInline(result);
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
