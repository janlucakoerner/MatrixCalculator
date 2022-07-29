package frontend.gui;

import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.frame.MatrixNumberToMatrixOperationGUI;

import java.math.BigDecimal;

/**
 * This class defines the JFrame and arithmetic operation for matrix potency.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public class MatrixPotencyGUI implements ICalculation {
    /**
     * Constructor which provides some settings to the frame class.
     */
    public MatrixPotencyGUI() {
        var gui = new MatrixNumberToMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Potency");
        gui.setJButtonText("Potentiate Matrix");
    }
    /**
     * Inherited method from ICalculation which is used as arithmetic operation for matrix potency.
     * @param matrix The matrix.
     * @param number The exponent.
     * @return The potency of both matrices.
     */
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
