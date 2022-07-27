package frontend.gui;

import middleware.ArithmeticOperations;
import middleware.base.ICalculation;
import frontend.base.frame.MatrixAndNumberOperationGUI;


public class MatrixPotencyGUI implements ICalculation {
    public MatrixPotencyGUI() {
        var gui = new MatrixAndNumberOperationGUI(this);
        gui.setJFrameTitle("Matrix Potency");
        gui.setJButtonText("Potentiate Matrix");
    }
    @Override
    public T[][] matrixCalculation(T[][] matrix, T number) {
        return ArithmeticOperations.matrixPotency(matrix, number);
    }
}
