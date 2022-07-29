package frontend.base.frame;

import backend.ParserBigDecimal;
import backend.ParserFraction;
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

public class VectorVectorToVectorOperationGUI extends JFrame {
    private final JButton button = new JButton();

    public VectorVectorToVectorOperationGUI(ICalculation calculation) {
        var vectorPanel1 = new VectorPanel(this);
        var vectorPanel2 = new VectorPanel(this);
        var vectorPanelResult = new VectorPanel(this);
        var panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        vectorPanel1.setText("Vector 1");
        vectorPanel2.setText("Vector 2");
        vectorPanelResult.setText("Result");
        panel.add(vectorPanel1);
        panel.add(vectorPanel2);
        panel.add(vectorPanelResult);
        add(panel, BorderLayout.CENTER);
        button.setText("Calculate");
        button.addActionListener(e -> {
            if (DataType.current == DataType.BIG_DECIMAL) {
                var vector1 = ParserBigDecimal.getVectorFromInline(vectorPanel1.getInline());
                var vector2 = ParserBigDecimal.getVectorFromInline(vectorPanel2.getInline());
                var vectorResult = (BigDecimal[]) calculation.vectorCalculation(vector1, vector2);
                vectorPanelResult.updateInline(vectorResult);
            } else if (DataType.current == DataType.FRACTION) {
                var vector1 = ParserFraction.getVectorFromInline(vectorPanel1.getInline());
                var vector2 = ParserFraction.getVectorFromInline(vectorPanel2.getInline());
                var vectorResult = (Fraction[]) calculation.vectorCalculation(vector1, vector2);
                vectorPanelResult.updateInline(vectorResult);
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
