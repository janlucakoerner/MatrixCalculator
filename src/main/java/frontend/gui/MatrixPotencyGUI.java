package frontend.gui;

import middleware.ArithmeticOperations;
import middleware.base.ICalculation;
import frontend.base.frame.MatrixAndNumberOperationGUI;

import java.math.BigDecimal;

public class MatrixPotencyGUI implements ICalculation {
    public MatrixPotencyGUI() {
        var gui = new MatrixAndNumberOperationGUI(this);
        gui.setJFrameTitle("Matrix Potency");
        gui.setJButtonText("Potentiate Matrix");
    }
    @Override
    public BigDecimal[][] matrixCalculation(BigDecimal[][] matrix, BigDecimal number) {
        return ArithmeticOperations.matrixPotency(matrix, number);
    }
}
