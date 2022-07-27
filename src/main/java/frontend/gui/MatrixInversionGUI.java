package frontend.gui;

import middleware.ArithmeticOperations;
import middleware.base.ICalculation;
import frontend.base.frame.OneMatrixOperationGUI;

import java.math.BigDecimal;

public class MatrixInversionGUI implements ICalculation {
    public MatrixInversionGUI() {
        var gui = new OneMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Inversion");
    }
    public BigDecimal[][] matrixCalculation(BigDecimal[][] matrix) {
        return ArithmeticOperations.matrixInversion(matrix);
    }
}
