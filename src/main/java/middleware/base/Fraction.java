package middleware.base;

import java.math.BigDecimal;

public class Fraction implements INumberDatatype {
    public static final Fraction ZERO = new Fraction(BigDecimal.ZERO);
    public static final Fraction ONE = new Fraction(BigDecimal.ONE);
    private final BigDecimal numerator;
    private final BigDecimal denominator;

    public Fraction(BigDecimal numerator, BigDecimal denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public Fraction(BigDecimal value) {
        numerator = value;
        denominator = BigDecimal.ONE;
    }
    private Fraction shorten() {
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

    @Override
    public BigDecimal getDenominator() {
        return this.denominator;
    }

    @Override
    public INumberDatatype add(INumberDatatype augend) {
        return null;
    }

    @Override
    public INumberDatatype subtract(INumberDatatype subtrahend) {
        return null;
    }

    @Override
    public INumberDatatype multiply(INumberDatatype multiplicand) {
        var numerator = this.numerator.multiply(multiplicand.getNumerator());
        var denominator = this.denominator.multiply(multiplicand.getDenominator());
        return new Fraction(numerator, denominator).shorten();
    }

    @Override
    public INumberDatatype divide(INumberDatatype divisor) {
        var numerator = this.numerator.divide(divisor.getDenominator());
        var denominator = this.denominator.divide(divisor.getNumerator());
        return new Fraction(numerator, denominator);
    }

    @SuppressWarnings("BigDecimalLegacyMethod")
    @Override
    public INumberDatatype divide(INumberDatatype divisor, int scale, int roundingMode) {
        var numerator = this.numerator.divide(divisor.getDenominator(), scale, roundingMode);
        var denominator = this.denominator.divide(divisor.getNumerator(), scale, roundingMode);
        return new Fraction(numerator, denominator);
    }

    @Override
    public INumberDatatype abs() {
        return null;
    }

    @Override
    public boolean equals(INumberDatatype val) {
        return numerator.equals(val.getNumerator()) && denominator.equals(val.getDenominator());
    }

    @Override
    public int compareTo(INumberDatatype val) {
        return 0;
    }

    @Override
    public String toString() {
        return numerator.toString() + '/' + denominator.toString();
    }
}
