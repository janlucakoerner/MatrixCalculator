package backend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ParserBigDecimalTest extends ParserBigDecimal {

    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final BigDecimal THREE = new BigDecimal(3);
    private static final BigDecimal FOUR = new BigDecimal(4);
    private static final BigDecimal FIVE = new BigDecimal(5);
    private static final BigDecimal SIX = new BigDecimal(6);
    private static final BigDecimal SEVEN = new BigDecimal(7);
    private static final BigDecimal EIGHT = new BigDecimal(8);
    private static final BigDecimal NINE = new BigDecimal(9);

    private static final String[] inlineMatrices = {
            "1;2;3",
            "1\\n2\\n3",
            "1;2\\n3;4",
            "1;2;3\\n4;5;6\\n7;8;9"
    };

    private static final Matrix[] matrices = {
            new Matrix(new BigDecimal[][] {{ONE, TWO, THREE}}),
            new Matrix(new BigDecimal[][] {{ONE}, {TWO}, {THREE}}),
            new Matrix(new BigDecimal[][] {{ONE, TWO}, {THREE, FOUR}}),
            new Matrix(new BigDecimal[][] {{ONE, TWO, THREE}, {FOUR, FIVE, SIX}, {SEVEN, EIGHT, NINE}})
    };

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getMatrixFromInline() {
        for (int i = 0; i < inlineMatrices.length; i++) {
            var matrix = getMatrixFromInline(inlineMatrices[i]);
            assert !matrices[i].equals(matrix);
        }
    }
}
class Matrix{
    BigDecimal[][] matrix;
    public Matrix(BigDecimal[][] matrix) {
        this.matrix = matrix;
    }
    public boolean equals(BigDecimal[][] matrix) {
        return Arrays.deepEquals(this.matrix, matrix);
    }
}