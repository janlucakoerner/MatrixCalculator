package frontend.gui;

import frontend.base.frame.VectorVectorToNumberOperationGUI;
import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;

import java.math.BigDecimal;

/**
 * This class defines the JFrame and arithmetic operation for vector scalar multiplication.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/29)
 */
public class VectorScalarMultiplicationGUI implements ICalculation {
    /**
     * Constructor which provides some settings to the frame class.
     */
    public VectorScalarMultiplicationGUI() {
        var gui = new VectorVectorToNumberOperationGUI(this);
        gui.setJFrameTitle("Vector Scalar Multiplication");
        gui.setJButtonText("Scalar Multiply");
    }
    /**
     * Inherited method from ICalculation which is used as arithmetic operation for vector scalar multiplication.
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The scalar multiplication of both vectors.
     */
    @Override
    public Object numberCalculation(Object[] vector1, Object[] vector2) {
        if (vector1 instanceof BigDecimal[] && vector2 instanceof BigDecimal[] &&
                DataType.current == DataType.BIG_DECIMAL)
            return ArithmeticOperationsBigDecimal.vectorScalarMultiplication((BigDecimal[]) vector1, (BigDecimal[]) vector2);
        else if (vector1 instanceof Fraction[] && vector2 instanceof Fraction[] &&
                DataType.current == DataType.FRACTION)
            return ArithmeticOperationsFraction.vectorScalarMultiplication((Fraction[]) vector1,(Fraction[]) vector2);
        else
            return null;
    }
}
