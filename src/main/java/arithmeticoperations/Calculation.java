package arithmeticoperations;

import java.math.BigDecimal;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public interface Calculation {
    default BigDecimal[][] matrixCalculation(BigDecimal[][] matrix1, BigDecimal[][] matrix2) {
        return null;
    }
    default BigDecimal[][] matrixCalculation(BigDecimal[][] matrix) {
        return null;
    }
    default BigDecimal[][] matrixCalculation(BigDecimal[][] matrix, BigDecimal[] vector) {
        return null;
    }
    default BigDecimal[] vectorCalculation(BigDecimal[][] matrix) {
        return null;
    }
    default BigDecimal[] vectorCalculation(BigDecimal[] vector1, BigDecimal[] vector2) {
        return null;
    }
    default BigDecimal matrixToNumberCalculation(BigDecimal[][] matrix) { return null; }
}
