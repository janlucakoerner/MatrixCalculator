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
public class MatrixSubtractionGUI implements Calculation {
    public MatrixSubtractionGUI() {
        var gui = new TwoMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Subtraction");
        gui.setJButtonText("Subtract");
    }
    @Override
    public BigDecimal[][] matrixCalculation(BigDecimal[][] matrix1, BigDecimal[][] matrix2) {
        return ArithmeticOperations.matrixSubtraction(matrix1, matrix2);
    }
}
