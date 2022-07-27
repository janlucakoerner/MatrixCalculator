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
public class MatrixSubtractionGUI<T> implements ICalculation<T> {
    public MatrixSubtractionGUI() {
        var gui = new TwoMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Subtraction");
        gui.setJButtonText("Subtract");
    }
    @Override
    public T[][] matrixCalculation(T[][] matrix1, T[][] matrix2) {
        return ArithmeticOperations.matrixSubtraction(matrix1, matrix2);
    }
}
