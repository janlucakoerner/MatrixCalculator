package frontend.gui;

import frontend.base.frame.VectorVectorToVectorOperationGUI;
import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;

import java.math.BigDecimal;

public class VectorSubtractionGUI implements ICalculation {
    public VectorSubtractionGUI() {
        var gui = new VectorVectorToVectorOperationGUI(this);
        gui.setJFrameTitle("Vector Subtraction");
        gui.setJButtonText("Subtract");
    }
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
