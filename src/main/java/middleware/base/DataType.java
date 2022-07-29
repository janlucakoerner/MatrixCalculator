package middleware.base;

/**
 * This class saves the current information about the
 * used datatype for further arithmetic operations.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public class DataType {
    /**
     * Constant for BigDecimal datatype usage.
     */
    public static final int BIG_DECIMAL = 0;
    /**
     * Constant for Fraction datatype usage.
     */
    public static final int FRACTION = 1;
    /**
     * Variable for current datatype usage.
     */
    public static int current = BIG_DECIMAL;
}
