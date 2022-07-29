package frontend.gui;

import frontend.base.frame.MatrixVectorToVectorOperationGUI;
import middleware.ArithmeticOperationsBigDecimal;
import middleware.ArithmeticOperationsFraction;
import middleware.base.DataType;
import middleware.base.Fraction;
import middleware.base.ICalculation;

import java.math.BigDecimal;

public class LinearSystemOfEquationGUI implements ICalculation {
    public LinearSystemOfEquationGUI() {
        var gui = new MatrixVectorToVectorOperationGUI(this);
        gui.setJFrameTitle("Solve Linear System Of Equation");
        gui.setJButtonText("Solve");
    }
    @Override
    public Object[] vectorCalculation(Object[][] matrix, Object[] vector) {
        if (matrix instanceof BigDecimal[][] && vector instanceof BigDecimal[] &&
                DataType.current == DataType.BIG_DECIMAL)
            return ArithmeticOperationsBigDecimal.solveLinearSystemOfEquations((BigDecimal[][]) matrix, (BigDecimal[]) vector);
        else if (matrix instanceof Fraction[][] && vector instanceof Fraction[] &&
                DataType.current == DataType.FRACTION)
            return ArithmeticOperationsFraction.solveLinearSystemOfEquations((Fraction[][]) matrix,(Fraction[]) vector);
        else
            return null;
    }
}
