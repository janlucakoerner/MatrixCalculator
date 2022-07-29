package frontend.gui;

import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;
import frontend.base.frame.MatrixMatrixToMatrixOperationGUI;

import java.math.BigDecimal;

/**
 * This class defines the JFrame and arithmetic operation for matrix inverse multiplication.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/22)
 */
public class MatrixInverseMultiplicationGUI implements ICalculation {
    /**
     * Constructor which provides some settings to the frame class.
     */
    public MatrixInverseMultiplicationGUI() {
        var gui = new MatrixMatrixToMatrixOperationGUI(this);
        gui.setJFrameTitle("Matrix Inverse Multiplication");
        gui.setJButtonText("Inverse multiply");
    }
    /**
     * Inherited method from ICalculation which is used as arithmetic operation for matrix inverse multiplication.
     * @param matrix1 The first matrix.
     * @param matrix2 The second matrix.
     * @return The inverse multiplication of both matrices.
     */
    @Override
    public Object[][] matrixCalculation(Object[][] matrix1, Object[][] matrix2) {
        if (matrix1 instanceof BigDecimal[][] && matrix2 instanceof BigDecimal[][] &&
                DataType.current == DataType.BIG_DECIMAL)
            return ArithmeticOperationsBigDecimal.matrixInverseMultiplication((BigDecimal[][]) matrix1, (BigDecimal[][]) matrix2);
        else if (matrix1 instanceof Fraction[][] && matrix2 instanceof Fraction[][] &&
                DataType.current == DataType.FRACTION)
            return ArithmeticOperationsFraction.matrixInverseMultiplication((Fraction[][]) matrix1,(Fraction[][]) matrix2);
        else
            return null;
    }

}
