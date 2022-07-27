package gui.arithmeticoperations;

import arithmeticoperations.ArithmeticOperations;
import arithmeticoperations.Calculation;
import gui.base.MatrixToNumberOperationGUI;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/23)
 */
public class MatrixDeterminantGUI implements Calculation {
    public MatrixDeterminantGUI() {
        var gui = new MatrixToNumberOperationGUI(this);
        gui.setJFrameTitle("Matrix Determinant");
        gui.setJButtonText("Calculate Determinant");
    }
    @Override
    public BigDecimal numberCalculation(BigDecimal[][] matrix) {
        return ArithmeticOperations.determinant(matrix, matrix.length, matrix.length);
    }
}
