package frontend.gui;


import middleware.base.DataType;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    public static final MainGUI instance = new MainGUI();
    private MainGUI() {
        setLayout(new GridLayout(6,2));
        setTitle("Matrix / Vector Calculator");
        var menuBar = new JMenuBar();
        var menu = new JMenu("Datatype: BigDecimal");
        var menuItem = new JMenuItem("Change to BigDecimal");
        menuItem.addActionListener(e -> {
            DataType.current = DataType.BIG_DECIMAL;
            menu.setText("Datatype: BigDecimal");
        });
        var menuItem2 = new JMenuItem("Change to Fraction");
        menuItem2.addActionListener(e -> {
            DataType.current = DataType.FRACTION;
            menu.setText("Datatype: Fraction");
        });
        menu.add(menuItem);
        menu.add(menuItem2);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        var button_matrixAddition = new JButton("Matrix Addition");
        button_matrixAddition.addActionListener(e -> {
            new MatrixAdditionGUI();
            setVisible(false);
        });
        add(button_matrixAddition);

        var button_matrixSubtraction = new JButton("Matrix Subtraction");
        button_matrixSubtraction.addActionListener(e -> {
            new MatrixSubtractionGUI();
            setVisible(false);
        });
        add(button_matrixSubtraction);

        var button_matrixMultiplication = new JButton("Matrix Multiplication");
        button_matrixMultiplication.addActionListener(e -> {
            new MatrixMultiplicationGUI();
            setVisible(false);
        });
        add(button_matrixMultiplication);

        var button_matrixDeterminant = new JButton("Matrix Determinant");
        button_matrixDeterminant.addActionListener(e -> {
            new MatrixDeterminantGUI();
            setVisible(false);
        });
        add(button_matrixDeterminant);

        var button_matrixInversion = new JButton("Matrix Inversion");
        button_matrixInversion.addActionListener(e -> {
            new MatrixInversionGUI();
            setVisible(false);
        });
        add(button_matrixInversion);

        var button_matrixPotency = new JButton("Matrix Potency");
        button_matrixPotency.addActionListener(e -> {
            new MatrixPotencyGUI();
            setVisible(false);
        });
        add(button_matrixPotency);

        var button_matrixInverseMultiplication = new JButton("Matrix Inverse Multiplication");
        button_matrixInverseMultiplication.addActionListener(e -> {
            new MatrixInverseMultiplicationGUI();
            setVisible(false);
        });
        add(button_matrixInverseMultiplication);

        var button_linearSystemOfEquation = new JButton("Linear System Of Equations");
        button_linearSystemOfEquation.addActionListener(e -> {
            // todo
        });
        add(button_linearSystemOfEquation);

        var button_vectorAddition = new JButton("Vector Addition");
        button_vectorAddition.addActionListener(e -> {
            // todo
        });
        add(button_vectorAddition);

        var button_vectorSubtraction = new JButton("Vector Subtraction");
        button_vectorSubtraction.addActionListener(e -> {
            // todo
        });
        add(button_vectorSubtraction);

        var button_vectorScalarMultiplication = new JButton("Vector Scalar Multiplication");
        button_vectorScalarMultiplication.addActionListener(e -> {
            // todo
        });
        add(button_vectorScalarMultiplication);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);
    }
}
