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
    public static final Fraction ZERO = new Fraction(BigDecimal.ZERO);
    public static final Fraction ONE = new Fraction(BigDecimal.ONE);
    //------------------------------------------------------------------------------------------------------------------
    //  instance variables
    //------------------------------------------------------------------------------------------------------------------
    private final BigDecimal numerator;
    private final BigDecimal denominator;
    //------------------------------------------------------------------------------------------------------------------
    //  constructors
    //------------------------------------------------------------------------------------------------------------------
    public Fraction(BigDecimal numerator, BigDecimal denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public Fraction(BigDecimal value) {
        numerator = value;
        denominator = BigDecimal.ONE;
    }
    public Fraction(String value) {

    }
    //------------------------------------------------------------------------------------------------------------------
    //  methods: other operations
    //------------------------------------------------------------------------------------------------------------------
    public Fraction shorten() {
        var numerator = this.numerator.divide(ggt(this.numerator, this.denominator));
        var denominator = this.denominator.divide(ggt(this.numerator, this.denominator));
        return new Fraction(numerator, denominator);
    }
    private BigDecimal ggt(BigDecimal value1, BigDecimal value2) {
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
    public BigDecimal getNumerator() {
        return this.numerator;
    }
    public BigDecimal getDenominator() {
        return this.denominator;
    }
    @Override
    public Fraction abs() {
        return new Fraction(this.numerator.abs(), this.denominator.abs());
    }
    @Override
    public boolean equals(Object val) {
        if (!(val instanceof Fraction frac))
            throw new IllegalArgumentException("Argument val not instance of Fraction!");
        return numerator.equals(frac.getNumerator()) && denominator.equals(frac.getDenominator());
    }
    @Override
    public int compareTo(Fraction o) {
        var numeratorLeft = this.numerator.multiply(o.getDenominator());
        var numeratorRight = o.getNumerator().multiply(this.denominator);
        return numeratorLeft.compareTo(numeratorRight);
    }
    @Override
    public String toString() {
        return numerator.toString() + '/' + denominator.toString();
    }
    //------------------------------------------------------------------------------------------------------------------
    //  methods: arithmetic operations
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public Fraction add(Fraction augend) {
        var numeratorLeft = this.numerator.multiply(augend.getDenominator());
        var numeratorRight = augend.getNumerator().multiply(this.denominator);
        var numerator = numeratorLeft.add(numeratorRight);
        var denominator = this.denominator.multiply(augend.getDenominator());
        return new Fraction(numerator, denominator).shorten();
    }
    @Override
    public Fraction subtract(Fraction subtrahend) {
        var numeratorLeft = this.numerator.multiply(subtrahend.getDenominator());
        var numeratorRight = subtrahend.getNumerator().multiply(this.denominator);
        var numerator = numeratorLeft.subtract(numeratorRight);
        var denominator = this.denominator.multiply(subtrahend.getDenominator());
        return new Fraction(numerator, denominator).shorten();
    }
    @Override
    public Fraction multiply(Fraction multiplicand) {
        var numerator = this.numerator.multiply(multiplicand.getNumerator());
        var denominator = this.denominator.multiply(multiplicand.getDenominator());
        return new Fraction(numerator, denominator).shorten();
    }
    @Override
    public Fraction divide(Fraction divisor) throws ArithmeticException {
        if (divisor.shorten().equals(ZERO))
            throw new ArithmeticException("Division by zero");
        var numerator = this.numerator.divide(divisor.getDenominator());
        var denominator = this.denominator.divide(divisor.getNumerator());
        return new Fraction(numerator, denominator);
    }
    @SuppressWarnings("BigDecimalLegacyMethod")
    @Override
    public Fraction divide(Fraction divisor, int scale, int roundingMode) throws ArithmeticException {
        if (divisor.shorten().equals(ZERO))
            throw new ArithmeticException("Division by zero");
        var numerator = this.numerator.divide(divisor.getDenominator(), scale, roundingMode);
        var denominator = this.denominator.divide(divisor.getNumerator(), scale, roundingMode);
        return new Fraction(numerator, denominator);
    }
}
