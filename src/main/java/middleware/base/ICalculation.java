package middleware.base;

/**
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/04)
 */
public interface ICalculation<T> {
    default T[][] matrixCalculation(T[][] matrix1, T[][] matrix2) {
        return null;
    }
    default T[][] matrixCalculation(T[][] matrix) {
        return null;
    }
    default T[][] matrixCalculation(T[][] matrix, T[] vector) {
        return null;
    }
    default T[] vectorCalculation(T[][] matrix) {
        return null;
    }
    default T[] vectorCalculation(T[] vector1, T[] vector2) {
        return null;
    }
    default T numberCalculation(T[][] matrix) { return null; }
    default T[][] matrixCalculation(T[][] matrix, T number) {
        return null;
    }
}
