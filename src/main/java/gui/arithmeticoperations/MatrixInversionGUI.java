package gui.arithmeticoperations;

import arithmeticoperations.ArithmeticOperations;
import arithmeticoperations.Calculation;
import gui.base.OneMatrixOperationGUI;

import java.math.BigDecimal;

public class MatrixInversionGUI implements Calculation {
    public MatrixInversionGUI() {
        var gui = new OneMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Inversion");
    }
    public BigDecimal[][] matrixCalculation(BigDecimal[][] matrix) {
        return ArithmeticOperations.matrixInversion(matrix);
    }
}
