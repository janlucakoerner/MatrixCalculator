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
public class MatrixInverseMultiplicationGUI implements Calculation {
    public MatrixInverseMultiplicationGUI() {
        var gui = new TwoMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Inverse Multiplication");
        gui.setJButtonText("Inverse multiply");
    }
    @Override
    public BigDecimal[][] matrixCalculation(BigDecimal[][] matrix1, BigDecimal[][] matrix2) {
        return ArithmeticOperations.matrixInverseMultiplication(matrix1, matrix2);
    }
}
