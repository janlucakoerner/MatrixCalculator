package middleware.base;

import java.math.BigDecimal;

public interface INumberDatatype {
    // methods
    INumberDatatype add(INumberDatatype augend);
    INumberDatatype subtract(INumberDatatype subtrahend);
    INumberDatatype multiply(INumberDatatype multiplicand);
    INumberDatatype divide(INumberDatatype divisor);
    INumberDatatype divide(INumberDatatype divisor, int scale, int roundingMode);
    INumberDatatype abs();
    boolean equals(INumberDatatype val);
    int compareTo(INumberDatatype val);
    String toString();

    // only for datatype internal usage
    default BigDecimal getNumerator() {
        return null;
    }
    default BigDecimal getDenominator() {
        return null;
    }
    default INumberDatatype shorten() {
        return null;
    }
}
