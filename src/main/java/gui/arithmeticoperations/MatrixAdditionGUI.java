package gui.arithmeticoperations;

import arithmeticoperations.ArithmeticOperations;
import arithmeticoperations.Calculation;
import gui.base.TwoMatrixOperationGUI;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public class MatrixAdditionGUI implements Calculation {
    public MatrixAdditionGUI() {
        var gui = new TwoMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Addition");
        gui.setJButtonText("Add");
    }
    @Override
    public BigDecimal[][] matrixCalculation(BigDecimal[][] matrix1, BigDecimal[][] matrix2) {
        return ArithmeticOperations.matrixAddition(matrix1, matrix2);
    }
}
