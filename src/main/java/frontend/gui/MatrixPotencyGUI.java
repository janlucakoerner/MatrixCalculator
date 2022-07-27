package frontend.gui;

import middleware.ArithmeticOperationsBigDecimal;
import middleware.base.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
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
    public Object[][] matrixCalculation(Object[][] matrix, Object number) {
        if (matrix instanceof BigDecimal[][] && number instanceof BigDecimal &&
                DataType.current == DataType.BIG_DECIMAL)
            return ArithmeticOperationsBigDecimal.matrixPotency((BigDecimal[][]) matrix, (BigDecimal) number);
        else if (matrix instanceof Fraction[][] && number instanceof Fraction &&
                DataType.current == DataType.FRACTION)
            return ArithmeticOperationsFraction.matrixPotency((Fraction[][]) matrix, (Fraction) number);
        else
            return null;
    }
}
