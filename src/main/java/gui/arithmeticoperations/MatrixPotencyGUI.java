package gui.arithmeticoperations;

import arithmeticoperations.ArithmeticOperations;
import arithmeticoperations.Calculation;
import gui.base.MatrixAndNumberOperationGUI;

import java.math.BigDecimal;

public class MatrixPotencyGUI implements Calculation {
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
