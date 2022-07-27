package frontend.gui;

import middleware.ArithmeticOperations;
import middleware.base.ICalculation;
import frontend.base.frame.TwoMatrixOperationGUI;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/21)
 */
public class MatrixAdditionGUI<T> implements ICalculation<T> {
    public MatrixAdditionGUI() {
        var gui = new TwoMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Addition");
        gui.setJButtonText("Add");
    }
    @Override
    public T[][] matrixCalculation(T[][] matrix1, T[][] matrix2) {
        return ArithmeticOperations.matrixAddition(matrix1, matrix2);
    }
}
