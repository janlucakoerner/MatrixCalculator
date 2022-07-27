package middleware;

import javax.swing.*;
import java.math.RoundingMode;

/**
 * This class <b>Arithmetic_operations_Ts</b> contains all executable arithmetic operations.
 * They should <b>only</b> be used outside this class, also it cannot create instances of the class because this class possesses these following modifiers: {@code abstract} .<br>
 * All methods of this class possess these following modifiers: {@code static} .
 * @author janlucakoerner
 * @version 1.0
 * @since 1.0 (2022/07/26)
 */
@SuppressWarnings("TMethodWithoutRoundingCalled")
public abstract class ArithmeticOperations<T> {
	/**
	 * This method <b>matrixMultiplication</b> multiplies two matrices:<br>
	 * &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp {@code matricesA} x {@code matricesB} = {@code result}
	 * @param matricesA 	of data type {@code T[][]}
	 * @param matricesB 	of data type {@code T[][]} <br>
	 * 						Both parameters should contain a matrix.						
	 * @return <b>result</b>	of data type {@code T[][]} delivers a matrix.
	 */
	public static T[][] matrixMultiplication(T[][] matricesA, T[][] matricesB) {
		if (matricesA == null || matricesB == null) {
			JOptionPane.showMessageDialog(null, "Matrices equals null!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		} else if (matricesA.length != matricesB[0].length) {
			JOptionPane.showMessageDialog(null, "Matrices have false dimensions!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
		T[][] result = new T[matricesA.length][matricesB[0].length];
		for (int x = 0; x < result.length; x++) {
			for (int y = 0; y < result[0].length; y++) {
				result[x][y] = new T(0);
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
	 * This method <b>matrixPotency</b> exponentiate {@code pMatrix} by {@code pExponent}.<br>
	 * - If {@code pExponent} is greater than <b>1</b> it calculates the potency of {@code pMatrix} by {@code pExponent}.<br>
	 * - If {@code pExponent} is equal than <b>1</b>  the method delivers {@code pMatrix}.<br>
	 * - If {@code pExponent} is equal than <b>O</b> the method delivers an identity matrix.<br>
	 * - If {@code pExponent} is equal than <b>-1</b> the method delivers the inverse of {@code pMatrix}.<br>
	 * - If {@code pExponent} is less than <b>-1</b> it first calculates the potency of {@code pMatrix} by the absolute value of {@code pExponent} 
	 * and second it calculates the inverse of this potency.<br>
	 *
	 * @param pMatrix of data type {@code T[][]} should contain a square matrix 
	 * @param pExponent of data type {@code Integer} should contain an exponent
	 */
	public static T[][] matrixPotency(T[][] pMatrix, T pExponent) {
		if (pMatrix.length != pMatrix[0].length) {
			JOptionPane.showMessageDialog(null, "Matrix must be a square matrix!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
		if (pExponent.compareTo(new T(1)) > 0) {
			T[][] result = pMatrix;
			for (T i = T.ONE; i.compareTo(pExponent) < 0; i = i.add(T.ONE)) {
				result = matrixMultiplication(result, pMatrix);
			}
			return result;
		} else if (pExponent.equals(T.ONE)) {
			return pMatrix;
		} else if (pExponent.equals(T.ZERO)) {
			return getIdentityMatrix(pMatrix.length);
		} else if (pExponent.equals(new T(-1))) {
			return matrixInversion(pMatrix);
		} else {
			return matrixInversion(matrixPotency(pMatrix, pExponent.abs()));
		}
	}
	/**
	 * This method <b>getIdentityMatrix</b> creates an identity matrix of the dimension {@code pDimension}
	 * @param pDimension of data type {@code Integer} should contain the dimension of the matrix
	 * @return <b>result</b> of data type {@code T[][]} delivers a matrix
	 */
	private static T[][] getIdentityMatrix(int pDimension) {
		T[][] result = new T[pDimension][pDimension];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				if (i == j) {
					result[j][i] = new T(1);
				} else {
					result[j][i] = new T(0);
				}
			}
		}
		return result;
	}
	/**
	 * This method <b>matrixInversion</b> calculates the inverse of {@code pMatrix}.<br>
	 *
	 * @param pMatrix of data type {@code T[][]} should contain a (not singular) square matrix.
	 * @return <b>inverse</b> of data type {@code T[][]} delivers a matrix.
	 */
	public static T[][] matrixInversion(T[][] pMatrix) {
		if (pMatrix == null) {
			JOptionPane.showMessageDialog(null, "Matrices equals null!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		} else if (pMatrix.length != pMatrix[0].length) {
			JOptionPane.showMessageDialog(null, "Matrix must be a square matrix!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		} else if (T.ZERO.equals(determinant(pMatrix, pMatrix.length, pMatrix.length))) {
			JOptionPane.showMessageDialog(null, "Matrix must be a non singular matrix!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
		T[][] inverse = new T[pMatrix.length][pMatrix.length];
		if (pMatrix.length == 2) {
			inverse[0][0] = (new T(1).divide(determinant(pMatrix, pMatrix.length, pMatrix.length))).multiply(pMatrix[1][1]);
			inverse[0][1] = (new T(1).divide(determinant(pMatrix, pMatrix.length, pMatrix.length))).multiply(new T(-1).multiply(pMatrix[0][1]));
			inverse[1][0] = (new T(1).divide(determinant(pMatrix, pMatrix.length, pMatrix.length))).multiply(new T(-1).multiply(pMatrix[1][0]));
			inverse[1][1] = (new T(1).divide(determinant(pMatrix, pMatrix.length, pMatrix.length))).multiply(pMatrix[0][0]);
		} else {
			// minors and cofactors
			for (int i = 0; i < pMatrix.length; i++) {
				for (int j = 0; j < pMatrix[i].length; j++) {
					T[][] minor = minor(pMatrix, i, j);
					inverse[i][j] = new T(-1).pow(i + j).multiply(determinant(minor, minor.length, minor.length));
				}
			}

			// adjugate and determinant
			T det = T.ONE.divide(determinant(pMatrix, pMatrix.length, pMatrix.length), (int) Math.pow(2, 5), RoundingMode.HALF_UP);
			for (int i = 0; i < inverse.length; i++) {
				for (int j = 0; j <= i; j++) {
					T temp = inverse[i][j];
					inverse[i][j] = inverse[j][i].multiply(det);
					inverse[j][i] = temp.multiply(det);
				}
			}
		}
        return inverse;
    }
	/**
	 * This method <b>minor</b> calculates the minor of {@code pMatrix}.<br>
	 *
	 * @param pMatrix of data type {@code T[][]} should contain a square matrix.
	 * @param pRow of data type {@code int} should contain a row
	 * @param pColumn of data type {@code int} should contain a column
	 * @return <b>minor</b> of data type {@code T[][]} delivers a minor square matrix .
	 */
	private static T[][] minor(T[][] pMatrix, int pRow, int pColumn) {
		T[][] minor = new T[pMatrix.length - 1][pMatrix.length - 1];
		for (int i = 0; i < pMatrix.length; i++)
			for (int j = 0; i != pRow && j < pMatrix[i].length; j++)
				if (j != pColumn)
					minor[i < pRow ? i : i - 1][j < pColumn ? j : j - 1] = pMatrix[i][j];
		return minor;
	}
	public static T determinant(T[][] pMatrix, int current_dimension, int start_dimension) {
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

		T result = T.ZERO;
		T[][] cofactors = new T[start_dimension][start_dimension];
		T sign = T.ONE;

		for (int i = 0; i < current_dimension; i++) {
			cofactor(pMatrix, cofactors, i, current_dimension);
			result = result.add(sign.multiply(pMatrix[0][i]).multiply(determinant(cofactors, current_dimension - 1, start_dimension)));
			sign = new T(-1).multiply(sign);
		}
		return result;
    }

	private static void cofactor(T[][] pMatrix, T[][] cofactors, int y, int dimension) {
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
	 * This method <b>matrixInverseMultiplication</b> multiplies {@code matricesA} with the inverse of {@code matricesB}.<br>
	 *
	 * @param matricesA of data type {@code T[][]} should contain a square matrix.
	 * @param matricesB of data type {@code T[][]} should contain a square matrix.
	 * @return <b>result</b> of data type {@code T[][]} delivers a square matrix.
	 */
	public static T[][] matrixInverseMultiplication(T[][] matricesA, T[][] matricesB) {
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
	 * This method <b>matrixSubtraction</b> subtracts {@code matricesA} with {@code matricesB}.<br>
	 * 
	 * @param matricesA of data type {@code T[][]} should contain a matrix.
	 * @param matricesB of data type {@code T[][]} should contain a matrix.
	 * @return <b>result</b> of data type {@code T[][]} delivers a matrix.
	 */
	public static T[][] matrixSubtraction(T[][] matricesA, T[][] matricesB) {
		if (matricesA == null || matricesB == null) {
			JOptionPane.showMessageDialog(null, "Matrices equals null!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		} else if (!(matricesA.length == matricesB.length && matricesA[0].length == matricesB[0].length)) {
			JOptionPane.showMessageDialog(null, "Matrices must have the same dimension!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
		T[][] result = new T[matricesA.length][matricesB.length];
		for (int i = 0; i < matricesA[0].length; i++) {
			for (int j = 0; j < matricesA.length; j++) {
				result[j][i] = matricesA[j][i].subtract(matricesB[j][i]);
			}
		}
		return result;
	}
	/**
	 * This method <b>matrixAddition</b> adds {@code matricesA} with {@code matricesB}.<br>
	 * 
	 * @param matricesA of data type {@code T[][]} should contain a matrix.
	 * @param matricesB of data type {@code T[][]} should contain a matrix.
	 * @return <b>result</b> of data type {@code T[][]} delivers a matrix.
	 */
	public static T[][] matrixAddition(T[][] matricesA, T[][] matricesB) {
		if (matricesA == null || matricesB == null) {
			JOptionPane.showMessageDialog(null, "Matrices equals null!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		} else if (!(matricesA.length == matricesB.length && matricesA[0].length == matricesB[0].length)) {
			JOptionPane.showMessageDialog(null, "Matrices must have the same dimension!",
					"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
			return null;
		}
		T[][] result = new T[matricesA.length][matricesA[0].length];
		for (int i = 0; i < matricesA[0].length; i++) {
			for (int j = 0; j < matricesA.length; j++) {
				result[j][i] = matricesA[j][i].add(matricesB[j][i]);
			}
		}
		return result;
	}
	/**
	 * This method <b>solveLinearSystemOfEquations</b> solves a linear system of equations with a square matrix {@code pMatrix} and a vector {@code pVector}.<br>
	 * First it calculates the inverse of {@code pMatrix}. Then it multiply the inverse of {@code pMatrix} with the vector {@code pVector}.<br>
	 *
	 * @param pMatrix of data type {@code T[][]} should contain a square matrix.
	 * @param pVector of data type {@code T[]} should contain a vector.
	 * @return <b>result</b> of data type {@code T[]} delivers a vector.
	 */
	public static T[] solveLinearSystemOfEquations(T[][] pMatrix, T[] pVector) {
		T[] result = null;
		if (pMatrix.length == pVector.length) {
			result = new T[pVector.length];
			T[][] inverse = matrixInversion(pMatrix);
			if (inverse == null)  {
				JOptionPane.showMessageDialog(null, "Matrix could not be inverted!",
						"Arithmetic Error", JOptionPane.ERROR_MESSAGE, null);
				return null;
			}
			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result.length; j++) {
					result[i] = result[i].add(inverse[i][j].multiply(pVector[j]));
				}
			}
		}
		return result;
	}
}
