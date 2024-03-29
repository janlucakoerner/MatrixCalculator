package middleware;

import middleware.base.Fraction;

import javax.swing.*;
import java.util.Objects;

/**
 * This class provides matrix and vector operations for the datatype Fraction.
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/27)
 */
public abstract class ArithmeticOperationsFraction {
    /**
     * This method multiplies two matrices.
     * @param matricesA The first matrix.
     * @param matricesB The second matrix.
     * @return The multiplication of both matrices.
     */
    public static Fraction[][] matrixMultiplication(Fraction[][] matricesA, Fraction[][] matricesB) {
        if (matricesA == null || matricesB == null) {
            JOptionPane.showMessageDialog(null, "Matrices equals null!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (matricesA.length != matricesB[0].length) {
            JOptionPane.showMessageDialog(null, "Matrices have false dimensions!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        Fraction[][] result = new Fraction[matricesA.length][matricesB[0].length];
        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[0].length; y++) {
                result[x][y] = Fraction.ZERO;
            }
        }
        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result.length; y++) {
                for (int j = 0; j < matricesB.length; j++) {
                    result[x][y] = result[x][y].add(matricesA[x][j].multiply(matricesB[j][y]));
                }
            }
        }
        return result;
    }
    /**
     * This method calculates the potency of one matrix with an Integer exponent.
     * @param pMatrix The matrix.
     * @param pExponent The exponent.
     * @return The Potency of the matrix with the exponent.
     */
    public static Fraction[][] matrixPotency(Fraction[][] pMatrix, Fraction pExponent) {
        if (pMatrix.length != pMatrix[0].length) {
            JOptionPane.showMessageDialog(null, "Matrix must be a square matrix!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        if (pExponent.compareTo(Fraction.ONE) > 0) {
            Fraction[][] result = pMatrix;
            for (Fraction i = Fraction.ONE; i.compareTo(pExponent) < 0; i = i.add(Fraction.ONE)) {
                result = matrixMultiplication(result, pMatrix);
            }
            return result;
        } else if (pExponent.equals(Fraction.ONE)) {
            return pMatrix;
        } else if (pExponent.equals(Fraction.ZERO)) {
            return getIdentityMatrix(pMatrix.length);
        } else if (pExponent.equals(Fraction.MINUS_ONE)) {
            return matrixInversion(pMatrix);
        } else {
            return matrixInversion(matrixPotency(pMatrix, pExponent.abs()));
        }
    }
    /**
     * This method creates an identity matrix of the given dimension.
     * @param pDimension The dimension.
     * @return The identity matrix.
     */
    private static Fraction[][] getIdentityMatrix(int pDimension) {
        Fraction[][] result = new Fraction[pDimension][pDimension];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (i == j) {
                    result[j][i] = Fraction.ONE;
                } else {
                    result[j][i] = Fraction.ZERO;
                }
            }
        }
        return result;
    }
    /**
     * This method inverse the given matrix.
     * @param pMatrix The matrix.
     * @return The inverse of the matrix.
     */
    public static Fraction[][] matrixInversion(Fraction[][] pMatrix) {
        if (pMatrix == null) {
            JOptionPane.showMessageDialog(null, "Matrices equals null!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (pMatrix.length != pMatrix[0].length) {
            JOptionPane.showMessageDialog(null, "Matrix must be a square matrix!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (Fraction.ZERO.equals(determinant(pMatrix, pMatrix.length, pMatrix.length)) ||
                determinant(pMatrix, pMatrix.length, pMatrix.length) == null) {
            JOptionPane.showMessageDialog(null, "Matrix must be a non singular matrix!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        Fraction[][] inverse = new Fraction[pMatrix.length][pMatrix.length];
        if (pMatrix.length == 2) {
            inverse[0][0] = (Fraction.ONE.divide(Objects.requireNonNull(determinant(pMatrix, pMatrix.length, pMatrix.length)))).multiply(pMatrix[1][1]);
            inverse[0][1] = (Fraction.ONE.divide(Objects.requireNonNull(determinant(pMatrix, pMatrix.length, pMatrix.length)))).multiply(Fraction.MINUS_ONE.multiply(pMatrix[0][1]));
            inverse[1][0] = (Fraction.ONE.divide(Objects.requireNonNull(determinant(pMatrix, pMatrix.length, pMatrix.length)))).multiply(Fraction.MINUS_ONE.multiply(pMatrix[1][0]));
            inverse[1][1] = (Fraction.ONE.divide(Objects.requireNonNull(determinant(pMatrix, pMatrix.length, pMatrix.length)))).multiply(pMatrix[0][0]);
        } else {
            // minors and cofactors
            for (int i = 0; i < pMatrix.length; i++) {
                for (int j = 0; j < pMatrix[i].length; j++) {
                    Fraction[][] minor = minor(pMatrix, i, j);
                    inverse[i][j] = Fraction.MINUS_ONE.pow(i + j).multiply(Objects.requireNonNull(determinant(minor, minor.length, minor.length)));
                }
            }

            // adjugate and determinant
            Fraction det = Fraction.ONE.divide(Objects.requireNonNull(determinant(pMatrix, pMatrix.length, pMatrix.length)),
                    Fraction.MAX_PRECISION, Fraction.NO_ROUNDING);
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j <= i; j++) {
                    Fraction temp = inverse[i][j];
                    inverse[i][j] = inverse[j][i].multiply(det);
                    inverse[j][i] = temp.multiply(det);
                }
            }
        }
        return inverse;
    }
    /**
     * This method calculates the minor of the given matrix.
     * @param pMatrix The matrix.
     * @param pRow The row.
     * @param pColumn The column.
     * @return The minor of the matrix
     */
    private static Fraction[][] minor(Fraction[][] pMatrix, int pRow, int pColumn) {
        Fraction[][] minor = new Fraction[pMatrix.length - 1][pMatrix.length - 1];
        for (int i = 0; i < pMatrix.length; i++)
            for (int j = 0; i != pRow && j < pMatrix[i].length; j++)
                if (j != pColumn)
                    minor[i < pRow ? i : i - 1][j < pColumn ? j : j - 1] = pMatrix[i][j];
        return minor;
    }
    /**
     * This method retrieves the determinant of the matrix.
     * @param pMatrix The matrix.
     * @param current_dimension The current dimension of the matrix.
     * @param start_dimension The start dimension of the matrix.
     * @return The determinant of the matrix.
     */
    public static Fraction determinant(Fraction[][] pMatrix, int current_dimension, int start_dimension) {
        if (pMatrix == null) {
            JOptionPane.showMessageDialog(null, "Matrices equals null!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (pMatrix.length != pMatrix[0].length) {
            JOptionPane.showMessageDialog(null, "Matrix must be a square matrix!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        if (current_dimension == 1)
            return pMatrix[0][0];

        Fraction result = Fraction.ZERO;
        Fraction[][] cofactors = new Fraction[start_dimension][start_dimension];
        Fraction sign = Fraction.ONE;

        for (int i = 0; i < current_dimension; i++) {
            cofactor(pMatrix, cofactors, i, current_dimension);
            result = result.add(sign.multiply(pMatrix[0][i]).multiply(Objects.requireNonNull(determinant(cofactors, current_dimension - 1, start_dimension))));
            sign = Fraction.MINUS_ONE.multiply(sign);
        }
        return result;
    }
    /**
     * This method calculates the cofactor of the matrix.
     * @param pMatrix The matrix.
     * @param cofactors The cofactors.
     * @param y The value.
     * @param dimension The dimension.
     */
    private static void cofactor(Fraction[][] pMatrix, Fraction[][] cofactors, int y, int dimension) {
        int i = 0, j = 0;
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) {
                if (row != 0 && column != y) {
                    cofactors[i][j++] = pMatrix[row][column];
                    if (j == dimension - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    /**
     * This method calculates the inverse multiplication of both matrices.
     * @param matricesA The first matrix.
     * @param matricesB The second matrix.
     * @return The inverse multiplication of both matrices.
     */
    public static Fraction[][] matrixInverseMultiplication(Fraction[][] matricesA, Fraction[][] matricesB) {
        if (matricesA == null || matricesB == null) {
            JOptionPane.showMessageDialog(null, "Matrices equals null!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (matrixInversion(matricesB) == null) {
            JOptionPane.showMessageDialog(null, "Matrix could not be inverted!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        return matrixMultiplication(matricesA, matrixInversion(matricesB));
    }
    /**
     * This method subtracts two matrices.
     * @param matricesA The first matrix.
     * @param matricesB The second matrix.
     * @return The subtraction of both matrices.
     */
    public static Fraction[][] matrixSubtraction(Fraction[][] matricesA, Fraction[][] matricesB) {
        if (matricesA == null || matricesB == null) {
            JOptionPane.showMessageDialog(null, "Matrices equals null!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (!(matricesA.length == matricesB.length && matricesA[0].length == matricesB[0].length)) {
            JOptionPane.showMessageDialog(null, "Matrices must have the same dimension!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        Fraction[][] result = new Fraction[matricesA.length][matricesB.length];
        for (int i = 0; i < matricesA[0].length; i++) {
            for (int j = 0; j < matricesA.length; j++) {
                result[j][i] = matricesA[j][i].subtract(matricesB[j][i]);
            }
        }
        return result;
    }
    /**
     * This method adds two matrices.
     * @param matricesA The first matrix.
     * @param matricesB The second matrix.
     * @return The addition of both matrices.
     */
    public static Fraction[][] matrixAddition(Fraction[][] matricesA, Fraction[][] matricesB) {
        if (matricesA == null || matricesB == null) {
            JOptionPane.showMessageDialog(null, "Matrices equals null!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        } else if (!(matricesA.length == matricesB.length && matricesA[0].length == matricesB[0].length)) {
            JOptionPane.showMessageDialog(null, "Matrices must have the same dimension!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        Fraction[][] result = new Fraction[matricesA.length][matricesA[0].length];
        for (int i = 0; i < matricesA[0].length; i++) {
            for (int j = 0; j < matricesA.length; j++) {
                result[j][i] = matricesA[j][i].add(matricesB[j][i]);
            }
        }
        return result;
    }
    /**
     * This method adds two vectors.
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The addition of both vectors.
     */
    public static Fraction[] vectorAddition(Fraction[] vector1, Fraction[] vector2) {
        if (vector1.length != vector2.length) {
            JOptionPane.showMessageDialog(null, "Vector dimension is different!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        var result = new Fraction[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i].add(vector2[i]);
        }
        return result;
    }
    /**
     * This method subtracts two vectors.
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The subtraction of both vectors.
     */
    public static Fraction[] vectorSubtraction(Fraction[] vector1, Fraction[] vector2) {
        if (vector1.length != vector2.length) {
            JOptionPane.showMessageDialog(null, "Vector dimension is different!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        var result = new Fraction[vector1.length];
        for (int i = 0; i < vector1.length; i++) {
            result[i] = vector1[i].subtract(vector2[i]);
        }
        return result;
    }
    /**
     * This method retrieves the scalar multiplication of two vectors.
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The scalar multiplication of both vectors.
     */
    public static Fraction vectorScalarMultiplication(Fraction[] vector1, Fraction[] vector2) {
        if (vector1.length != vector2.length) {
            JOptionPane.showMessageDialog(null, "Vector dimension is different!",
                    "Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
            return null;
        }
        var result = Fraction.ZERO;
        for (int i = 0; i < vector1.length; i++) {
            var sum = vector1[i].multiply(vector2[i]);
            result = result.add(sum);
        }
        return result;
    }
}
