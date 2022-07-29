package frontend.gui;

import frontend.base.frame.VectorVectorToVectorOperationGUI;
import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;

import java.math.BigDecimal;

/**
 * This class defines the JFrame and arithmetic operation for vector subtraction.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/29)
 */
public class VectorSubtractionGUI implements ICalculation {
    /**
     * Constructor which provides some settings to the frame class.
     */
    public VectorSubtractionGUI() {
        var gui = new VectorVectorToVectorOperationGUI(this);
        gui.setJFrameTitle("Vector Subtraction");
        gui.setJButtonText("Subtract");
    }
    /**
     * Inherited method from ICalculation which is used as arithmetic operation for vector subtraction.
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The subtraction of both vectors.
     */
    @Override
    public Object[] vectorCalculation(Object[] vector1, Object[] vector2) {
        if (vector1 instanceof BigDecimal[] && vector2 instanceof BigDecimal[] &&
                DataType.current == DataType.BIG_DECIMAL)
            return ArithmeticOperationsBigDecimal.vectorSubtraction((BigDecimal[]) vector1, (BigDecimal[]) vector2);
        else if (vector1 instanceof Fraction[] && vector2 instanceof Fraction[] &&
                DataType.current == DataType.FRACTION)
            return ArithmeticOperationsFraction.vectorSubtraction((Fraction[]) vector1,(Fraction[]) vector2);
        else
            return null;
    }
}
