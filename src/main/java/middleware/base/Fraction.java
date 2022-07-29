package middleware.base;

import java.math.BigDecimal;

/**
 * This class Fraction represent a fraction number datatype to provide more precision than a simple BigDecimal.
 *
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 * @see middleware.base.INumberDatatype
 * @see java.math.BigDecimal
 * @see java.lang.Comparable
 */
public class Fraction implements INumberDatatype<Fraction>, Comparable<Fraction> {
    //------------------------------------------------------------------------------------------------------------------
    //  class constants
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Constant for a Fraction with value zero => 0/1.
     */
    public static final Fraction ZERO = new Fraction(BigDecimal.ZERO);
    /**
     * Constant for a Fraction with value one => 1/1.
     */
    public static final Fraction ONE = new Fraction(BigDecimal.ONE);
    /**
     * Constant for a Fraction with value minus one => -1/1.
     */
    public static final Fraction MINUS_ONE = new Fraction(new BigDecimal(-1));
    /**
     * Constant for rounding information by division.
     */
    public static int NO_ROUNDING = 0;
    /**
     * Constant for precision information by division.
     */
    public static int MAX_PRECISION = 0;
    //------------------------------------------------------------------------------------------------------------------
    //  instance variables
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Variable for numerator of the fraction.
     */
    private final BigDecimal numerator;
    /**
     * Variable for denominator of the fraction.
     */
    private final BigDecimal denominator;
    //------------------------------------------------------------------------------------------------------------------
    //  constructors
    //------------------------------------------------------------------------------------------------------------------
    /**
     * Constructor for creating a new fraction by
     * a separated numerator and denominator value.
     * @param numerator The numerator of the fraction.
     * @param denominator The denominator of the fraction.
     */
    public Fraction(BigDecimal numerator, BigDecimal denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    /**
     * Constructor for creating a new fraction by
     * a separated numerator. The denominator will always be set to one.
     * @param value The numerator of the fraction.
     */
    public Fraction(BigDecimal value) {
        numerator = value;
        denominator = BigDecimal.ONE;
    }
    /**
     * Constructor for creating a new fraction by
     * a numerator and denominator which is divided by a slash.
     * @param value The fraction in following format (1/2).
     */
    public Fraction(String value) {
        if (value.contains("/")) {
            var fraction = value.split("/");
            numerator = new BigDecimal(fraction[0]);
            denominator = new BigDecimal(fraction[1]);
        } else {
            numerator = new BigDecimal(value);
            denominator = BigDecimal.ONE;
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //  methods: other operations
    //------------------------------------------------------------------------------------------------------------------
    /**
     * This method shortens the fraction.
     * @return The fraction in its shorten form.
     */
    public Fraction shorten() {
        var numerator = this.numerator.divide(gcd(this.numerator, this.denominator));
        var denominator = this.denominator.divide(gcd(this.numerator, this.denominator));
        return new Fraction(numerator, denominator);
    }
    /**
     * This method calculates the greatest common divisor of the two values.
     * @param value1 The first value.
     * @param value2 The second value.
     * @return The greatest common divisor of both values.
     */
    private BigDecimal gcd(BigDecimal value1, BigDecimal value2) {
        if (value1.equals(BigDecimal.ZERO))
            return value2;
        while (!value2.equals(BigDecimal.ZERO)) {
            if (value1.compareTo(value2) > 0)
                value1 = value1.subtract(value2);
            else
                value2 = value2.subtract(value1);
        }
        return value1;
    }
    /**
     * This method returns the numerator of the fraction.
     * @return The numerator of the fraction.
     */
    public BigDecimal getNumerator() {
        return this.numerator;
    }
    /**
     * This method returns the denominator of the fraction.
     * @return The denominator of the fraction.
     */
    public BigDecimal getDenominator() {
        return this.denominator;
    }
    /**
     * This method returns the absolut value of the fraction.
     * @return The absolut value of the fraction.
     */
    @Override
    public Fraction abs() {
        return new Fraction(this.numerator.abs(), this.denominator.abs());
    }
    /**
     * This method compares this fraction and another fraction on its equality.
     * @param val The other fraction.
     * @return Boolean representing the equality of both fractions.
     */
    @Override
    public boolean equals(Object val) {
        if (!(val instanceof Fraction frac))
            throw new IllegalArgumentException("Argument val not instance of Fraction!");
        return numerator.equals(frac.getNumerator()) && denominator.equals(frac.getDenominator());
    }
    /**
     * This method compares this fraction and another fraction on its equality or retrieves the lower or higher value.
     * @param o The other fraction.
     * @return -1 if first fraction is lower, 0 if both fractions are equal, 1 if first fraction is higher.
     */
    @Override
    public int compareTo(Fraction o) {
        var numeratorLeft = this.numerator.multiply(o.getDenominator());
        var numeratorRight = o.getNumerator().multiply(this.denominator);
        return numeratorLeft.compareTo(numeratorRight);
    }
    /**
     * This method returns the String formatted fraction.
     * @return The fraction as a String
     */
    @Override
    public String toString() {
        if (denominator.equals(BigDecimal.ONE))
            return numerator.toString();
        else
            return numerator.toString() + '/' + denominator.toString();
    }
    //------------------------------------------------------------------------------------------------------------------
    //  methods: arithmetic operations
    //------------------------------------------------------------------------------------------------------------------
    /**
     * This method adds both fraction values.
     * @param augend The other fraction.
     * @return The fraction addition.
     */
    @Override
    public Fraction add(Fraction augend) {
        var numeratorLeft = this.numerator.multiply(augend.getDenominator());
        var numeratorRight = augend.getNumerator().multiply(this.denominator);
        var numerator = numeratorLeft.add(numeratorRight);
        var denominator = this.denominator.multiply(augend.getDenominator());
        return new Fraction(numerator, denominator).shorten();
    }
    /**
     * This method subtracts both fraction values.
     * @param subtrahend The other fraction.
     * @return The fraction subtraction.
     */
    @Override
    public Fraction subtract(Fraction subtrahend) {
        var numeratorLeft = this.numerator.multiply(subtrahend.getDenominator());
        var numeratorRight = subtrahend.getNumerator().multiply(this.denominator);
        var numerator = numeratorLeft.subtract(numeratorRight);
        var denominator = this.denominator.multiply(subtrahend.getDenominator());
        return new Fraction(numerator, denominator).shorten();
    }
    /**
     * This method multiplies both fraction values.
     * @param multiplicand The other fraction.
     * @return The fraction multiplication.
     */
    @Override
    public Fraction multiply(Fraction multiplicand) {
        var numerator = this.numerator.multiply(multiplicand.getNumerator());
        var denominator = this.denominator.multiply(multiplicand.getDenominator());
        return new Fraction(numerator, denominator).shorten();
    }
    /**
     * This method divides both fraction values.
     * @param divisor The other fraction.
     * @return The fraction division.
     */
    @Override
    public Fraction divide(Fraction divisor) throws ArithmeticException {
        if (divisor.shorten().equals(ZERO))
            throw new ArithmeticException("Division by zero");
        var numerator = this.numerator.divide(divisor.getDenominator());
        var denominator = this.denominator.divide(divisor.getNumerator());
        return new Fraction(numerator, denominator);
    }
    /**
     * This method divides both fraction values.
     * @param divisor The other fraction.
     * @param scale The precision of the division.
     * @param roundingMode The rounding mode of the division.
     * @return The fraction division.
     */
    @SuppressWarnings("BigDecimalLegacyMethod")
    @Override
    public Fraction divide(Fraction divisor, int scale, int roundingMode) throws ArithmeticException {
        if (divisor.shorten().equals(ZERO))
            throw new ArithmeticException("Division by zero");
        var numerator = this.numerator.divide(divisor.getDenominator(), scale, roundingMode);
        var denominator = this.denominator.divide(divisor.getNumerator(), scale, roundingMode);
        return new Fraction(numerator, denominator);
    }
    /**
     * This method calculates the potency of a fraction with an Integer exponent.
     * @param exponent The exponent.
     * @return The fraction potency.
     */
    @Override
    public Fraction pow(int exponent) {
        if (exponent == 0)
            return Fraction.ONE;
        else if (exponent > 0)
            return new Fraction(this.numerator.pow(exponent), this.denominator.pow(exponent));
        else
            return null;
    }
}
