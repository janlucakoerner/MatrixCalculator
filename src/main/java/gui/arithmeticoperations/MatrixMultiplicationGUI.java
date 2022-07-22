package gui.arithmeticoperations;

import arithmeticoperations.ArithmeticOperations;
import arithmeticoperations.Calculation;
import gui.base.TwoMatrixOperationGUI;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/22)
 */
public class MatrixMultiplicationGUI implements Calculation {
    public MatrixMultiplicationGUI() {
        var gui = new TwoMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Multiplication");
        gui.setJButtonText("Multiply");
    }
    @Override
    public BigDecimal[][] matrixCalculation(BigDecimal[][] matrix1, BigDecimal[][] matrix2) {
        return ArithmeticOperations.matrixMultiplication(matrix1, matrix2);
    }
}
