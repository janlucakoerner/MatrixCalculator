package frontend.gui;

import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.frame.MatrixToNumberOperationGUI;

import java.math.BigDecimal;

/**
 * This class defines the JFrame and arithmetic operation for matrix determinant calculation.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/23)
 */
public class MatrixDeterminantGUI implements ICalculation {
    /**
     * Constructor which provides some settings to the frame class.
     */
    public MatrixDeterminantGUI() {
        var gui = new MatrixToNumberOperationGUI(this);
        gui.setJFrameTitle("Matrix Determinant");
        gui.setJButtonText("Calculate Determinant");
    }
    /**
     * Inherited method from ICalculation which is used as arithmetic operation for matrix determinant calculation.
     * @param matrix The matrix.
     * @return The determinant of the matrix.
     */
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
