package middleware.base;

/**
 * This interface INumberDatatype provides arithmetic operations and other operations.
 *
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public interface INumberDatatype<T> {
    // methods
    T add(T augend);
    T subtract(T subtrahend);
    T multiply(T multiplicand);
    T divide(T divisor);
    T divide(T divisor, int scale, int roundingMode);
    T abs();
    boolean equals(Object val);
    String toString();

    T pow(int exponent);
}
