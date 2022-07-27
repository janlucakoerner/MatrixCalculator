package frontend.gui;

import middleware.ArithmeticOperationsBigDecimal;
import middleware.base.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.frame.MatrixToNumberOperationGUI;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/23)
 */
public class MatrixDeterminantGUI implements ICalculation {
    public MatrixDeterminantGUI() {
        var gui = new MatrixToNumberOperationGUI(this);
        gui.setJFrameTitle("Matrix Determinant");
        gui.setJButtonText("Calculate Determinant");
    }
    @Override
    public Object numberCalculation(Object[][] matrix) {
        if (matrix instanceof BigDecimal[][] && DataType.current == DataType.BIG_DECIMAL)
            return ArithmeticOperationsBigDecimal.determinant((BigDecimal[][]) matrix, matrix.length, matrix.length);
        else if (matrix instanceof Fraction[][] && DataType.current == DataType.FRACTION)
            return ArithmeticOperationsFraction.determinant((Fraction[][]) matrix, matrix.length, matrix.length);
        else
            return null;
    }
}
