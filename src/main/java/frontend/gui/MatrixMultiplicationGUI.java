package frontend.gui;

import middleware.ArithmeticOperations;
import middleware.base.ICalculation;
import frontend.base.frame.TwoMatrixOperationGUI;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/22)
 */
public class MatrixMultiplicationGUI<T> implements ICalculation<T> {
    public MatrixMultiplicationGUI() {
        var gui = new TwoMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Multiplication");
        gui.setJButtonText("Multiply");
    }
    @Override
    public T[][] matrixCalculation(T[][] matrix1, T[][] matrix2) {
        return ArithmeticOperations.matrixMultiplication(matrix1, matrix2);
    }
}
