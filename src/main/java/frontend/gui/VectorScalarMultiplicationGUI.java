package frontend.gui;

import frontend.base.frame.VectorVectorToNumberOperationGUI;
import frontend.base.frame.VectorVectorToVectorOperationGUI;
import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;

import java.math.BigDecimal;

public class VectorScalarMultiplicationGUI implements ICalculation {
    public VectorScalarMultiplicationGUI() {
        var gui = new VectorVectorToNumberOperationGUI(this);
        gui.setJFrameTitle("Vector Scalar Multiplication");
        gui.setJButtonText("Scalar Multiply");
    }
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
