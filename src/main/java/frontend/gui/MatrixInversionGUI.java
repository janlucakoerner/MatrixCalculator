package frontend.gui;

import middleware.ArithmeticOperationsBigDecimal;
import middleware.base.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.frame.OneMatrixOperationGUI;

import java.math.BigDecimal;

public class MatrixInversionGUI implements ICalculation {
    public MatrixInversionGUI() {
        var gui = new OneMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Inversion");
    }
    public Object[][] matrixCalculation(Object[][] matrix) {
        if (matrix instanceof BigDecimal[][] && DataType.current == DataType.BIG_DECIMAL)
            return ArithmeticOperationsBigDecimal.matrixInversion((BigDecimal[][]) matrix);
        else if (matrix instanceof Fraction[][] && DataType.current == DataType.FRACTION)
            return ArithmeticOperationsFraction.matrixInversion((Fraction[][]) matrix);
        else
            return null;
    }
}
