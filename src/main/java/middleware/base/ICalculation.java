package middleware.base;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public interface ICalculation {
    default Object[][] matrixCalculation(Object[][] matrix1, Object[][] matrix2) {
        return null;
    }
    default Object[][] matrixCalculation(Object[][] matrix) {
        return null;
    }
    default Object[][] matrixCalculation(Object[][] matrix, Object[] vector) {
        return null;
    }
    default Object[] vectorCalculation(Object[][] matrix) {
        return null;
    }
    default Object[] vectorCalculation(Object[] vector1, Object[] vector2) {
        return null;
    }
    default Object[] vectorCalculation(Object[][] matrix, Object[] vector) {
        return null;
    }
    default Object numberCalculation(Object[][] matrix) { return null; }
    default Object numberCalculation(Object[] vector1, Object[] vector2) {
        return null;
    }
    default Object[][] matrixCalculation(Object[][] matrix, Object number) {
        return null;
    }
}
