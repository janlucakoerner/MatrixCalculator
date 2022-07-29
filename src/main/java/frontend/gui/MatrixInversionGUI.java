package frontend.gui;

import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.frame.MatrixToMatrixOperationGUI;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public class MatrixInversionGUI implements ICalculation {
    public MatrixInversionGUI() {
        var gui = new MatrixToMatrixOperationGUI(this);
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
