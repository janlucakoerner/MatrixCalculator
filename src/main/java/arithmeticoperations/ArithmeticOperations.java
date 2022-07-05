package arithmeticoperations;

import java.math.BigDecimal;

/**
 * 
 * This class <b>Arithmetic_operations_bigdecimals</b> contains all executable arithmetic operations.
 * They should <b>only</b> be used outside this class, also it cannot create instances of the class because this class possesses these following modifiers: {@code abstract} .<br>
 * All methods of this class possess these following modifiers: {@code static} .
 * @author Jan-Luca K�rner
 * @version 1.0
 * @since 2021.01.31
 */
@SuppressWarnings("BigDecimalMethodWithoutRoundingCalled")
public abstract class ArithmeticOperations {
	/**
	 * This method <b>matrixMultiplication</b> multiplies two matrices:<br>
	 * &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp {@code matricesA} x {@code matricesB} = {@code result}
	 * @param matricesA 	of data type {@code BigDecimal[][]}
	 * @param matricesB 	of data type {@code BigDecimal[][]} <br>
	 * 						Both parameters should contain a matrix.						
	 * @return <b>result</b>	of data type {@code BigDecimal[][]} delivers a matrix.
	 */
	public static BigDecimal[][] matrixMultiplication(BigDecimal[][] matricesA, BigDecimal[][] matricesB) {
		BigDecimal[][] result = null;
		if (matricesA != null && matricesB != null) {
			if (matricesA.length == matricesB[0].length) {
				result = new BigDecimal[matricesA.length][matricesB[0].length];
				for (int x = 0; x < result.length; x++) {
					for (int y = 0; y < result[0].length; y++) {
						result[x][y] = new BigDecimal(0);
					}
				}
				for (int x = 0; x < result.length; x++) {
					for (int y = 0; y < result.length; y++) {
						for (int j = 0; j < matricesB.length; j++) {
							result[x][y] = result[x][y].add(matricesA[x][j].multiply(matricesB[j][y]));
						}
					}
				}
			} else {
				// todo keine gueltige berechnung
			}
		}
		return result;
	}
	/**
	 * This method <b>matrixPotency</b> exponentiate {@code pMatrix} by {@code pExponent}.<br>
	 * - If {@code pExponent} is greater than <b>1</b> it calculates the potency of {@code pMatrix} by {@code pExponent}.<br>
	 * - If {@code pExponent} is equal than <b>1</b>  the method delivers {@code pMatrix}.<br>
	 * - If {@code pExponent} is equal than <b>O</b> the method delivers a identity matrix.<br>
	 * - If {@code pExponent} is equal than <b>-1</b> the method delivers the inverse of {@code pMatrix}.<br>
	 * - If {@code pExponent} is less than <b>-1</b> it first calculates the potency of {@code pMatrix} by the absolute value of {@code pExponent} 
	 * and second it calculates the inverse of this potency.<br>
	 * <br>
	 * <b>Uses:</b> <br>
	 * + {@code Operations.getIdentityMatrix()}<br>
	 * + {@code Arithmetic_operations_bigdecimals.matrixMultiplication()}
	 * @param pMatrix of data type {@code BigDecimal[][]} should contain a square matrix 
	 * @param pExponent of data type {@code Integer} should contain a exponent
	 * @return<b>result</b> of data type {@code BigDecimal[][]} delivers a matrix.
	 * @throws NullPointerException
	 */
	public static BigDecimal[][] matrixPotency(BigDecimal[][] pMatrix, int pExponent) {
		BigDecimal[][] result = null;
		if (pMatrix.length == pMatrix[0].length) {
			if (pExponent > 1) {
				result = pMatrix;
				for (int i = 1; i < pExponent; i++) {
					result = matrixMultiplication(result, pMatrix);
				}
			} else if (pExponent == 1) {
				result = pMatrix;
			} else if (pExponent == 0) {
				// result = Operations_bigdecimals.getIdentityMatrix(pMatrix.length);
			} else if (pExponent == -1) {
				result = matrixInversion(pMatrix);
			} else if (pExponent < -1) {
				result = matrixInversion(matrixPotency(pMatrix, Math.abs(pExponent)));
			}
		} else {
			// Statics.ERROR_MESSAGE = "Err: Keine g�ltige Berechnung! (Eingabe == Quadratmatrix)";
			// todo keine gueltige berechnung eingabe muss quadratmatrix sein
		}
		return result;
	}
	/**
	 * This method <b>matrixInversion</b> calculates the inverse of {@code pMatrix}.<br>
	 * <br>
	 * <b>Uses:</b> <br>
	 * - {@code Arithmetic_operations_bigdecimals.determinant()}<br>
	 * - {@code Arithmetic_operations_bigdecimals.minor()}<br>
	 * @param pMatrix of data type {@code BigDecimal[][]} should contain a (not singular) square matrix.
	 * @return <b>inverse</b> of data type {@code BigDecimal[][]} delivers a matrix.
	 */
	public static BigDecimal[][] matrixInversion(BigDecimal[][] pMatrix) {
		BigDecimal[][] inverse = null;
		if (pMatrix.length == pMatrix[0].length) {
			if (!determinant(pMatrix).equals(BigDecimal.ZERO)) {
				try {
					inverse = new BigDecimal[pMatrix.length][pMatrix.length];
					if (pMatrix.length == 2) {
						inverse[0][0] = (new BigDecimal(1).divide(determinant(pMatrix))).multiply(pMatrix[1][1]);
						inverse[0][1] = (new BigDecimal(1).divide(determinant(pMatrix))).multiply(new BigDecimal(-1).multiply(pMatrix[0][1]));
						inverse[1][0] = (new BigDecimal(1).divide(determinant(pMatrix))).multiply(new BigDecimal(-1).multiply(pMatrix[1][0]));
						inverse[1][1] = (new BigDecimal(1).divide(determinant(pMatrix))).multiply(pMatrix[0][0]);
					} else {
						// minors and cofactors
				        for (int i = 0; i < pMatrix.length; i++)
				            for (int j = 0; j < pMatrix[i].length; j++)
				                inverse[i][j] = new BigDecimal(-1).pow(i+j).multiply(determinant(minor(pMatrix, i, j)));

				        // adjugate and determinant
				        BigDecimal det = new BigDecimal(1).divide(determinant(pMatrix));
				        for (int i = 0; i < inverse.length; i++) {
				            for (int j = 0; j <= i; j++) {
				                BigDecimal temp = inverse[i][j];
				                inverse[i][j] = inverse[j][i].multiply(det);
				                inverse[j][i] = temp.multiply(det);
				            }
				        }
					}
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
			}else {
				// Statics.ERROR_MESSAGE = "Err: Keine g�ltige Berechnung! (Eingabe darf keine Singul�re Matrix sein)";
				// todo: keine gueltige berechnung matrix ist singulär
			}
		} else {
			// Statics.ERROR_MESSAGE = "Err: Keine g�ltige Berechnung! (Eingabe muss eine Quadratmatrix sein)";
			// todo: keine gueltige berechnung eingabe muss quadratmatrix sein
		}
        return inverse;
    }
	/**
	 * This method <b>determinant</b> calculates the determinant of {@code pMatrix}.<br>
	 * <br>
	 * <b>Uses:</b> <br>
	 * - {@code Arithmetic_operations_bigdecimals.determinant()}<br>
	 * - {@code Arithmetic_operations_bigdecimals.minor()}
	 * @param pMatrix of data type {@code BigDecimal[][]} should contain a square matrix.
	 * @return <b>det</b> of data type {@code BigDecimal} delivers a determinant.
	 */
	private static BigDecimal determinant(BigDecimal[][] pMatrix) {
        if (pMatrix.length != pMatrix[0].length)
        	System.out.println("Error");
        if (pMatrix.length == 2)
            return pMatrix[0][0].multiply(pMatrix[1][1]).subtract(pMatrix[0][1]).multiply(pMatrix[1][0]);

        BigDecimal det = new BigDecimal(0);
        for (int i = 0; i < pMatrix[0].length; i++)
            det = det.add(new BigDecimal(Math.pow(-1, i)).multiply(pMatrix[0][i]).multiply(determinant(minor(pMatrix, 0, i)))) ;
        return det;
    }
	/**
	 * This method <b>minor</b> calculates the minor of {@code pMatrix}.<br>
	 * 
	 * @param pMatrix of data type {@code BigDecimal[][]} should contain a square matrix.
	 * @param pRow of data type {@code int} should contain a row
	 * @param pColumn of data type {@code int} should contain a column
	 * @return <b>minor</b> of data type {@code BigDecimal[][]} delivers a minor square matrix .
	 */
	private static BigDecimal[][] minor(BigDecimal[][] pMatrix, int pRow, int pColumn) {
        BigDecimal[][] minor = new BigDecimal[pMatrix.length - 1][pMatrix.length - 1];

        for (int i = 0; i < pMatrix.length; i++)
            for (int j = 0; i != pRow && j < pMatrix[i].length; j++)
                if (j != pColumn)
                    minor[i < pRow ? i : i - 1][j < pColumn ? j : j - 1] = pMatrix[i][j];
        return minor;
    }
	/**
	 * This method <b>matrixInverseMultiplication</b> multiplies {@code matricesA} with the inverse of {@code matricesB}.<br>
	 * <br>
	 * <b>Uses:</b> <br>
	 * + {@code Arithmetic_operations_bigdecimals.matrixInversion()}<br>
	 * @param matricesA of data type {@code BigDecimal[][]} should contain a square matrix.
	 * @param matricesB of data type {@code BigDecimal[][]} should contain a square matrix.
	 * @return <b>result</b> of data type {@code BigDecimal[][]} delivers a square matrix.
	 */
	public static BigDecimal[][] matrixInverseMultiplication(BigDecimal[][] matricesA, BigDecimal[][] matricesB) {
		BigDecimal[][] result = null;
		try {
			result = matrixMultiplication(matricesA, matrixInversion(matricesB));
		} catch (NullPointerException e) {
			// TODO: handle exception
			//Statics.ERROR_MESSAGE = "Err: Bitte geben Sie eine Matrix an!";
			// todo bitte geben sie eine matrix ein
		}
		return result;
	}
	/**
	 * This method <b>matrixSubtraction</b> subtracts {@code matricesA} with {@code matricesB}.<br>
	 * 
	 * @param matricesA of data type {@code BigDecimal[][]} should contain a matrix.
	 * @param matricesB of data type {@code BigDecimal[][]} should contain a matrix.
	 * @return <b>result</b> of data type {@code BigDecimal[][]} delivers a matrix.
	 */
	public static BigDecimal[][] matrixSubtraction(BigDecimal[][] matricesA, BigDecimal[][] matricesB) {
		BigDecimal[][] result = new BigDecimal[matricesA.length][matricesB.length];
		if (matricesA.length == matricesB.length && matricesA[0].length == matricesB[0].length) {
			for (int i = 0; i < matricesA[0].length; i++) {
				for (int j = 0; j < matricesA.length; j++) {
					result[j][i] = matricesA[j][i].subtract(matricesB[j][i]);
				}
			}
		} else {
			// Statics.ERROR_MESSAGE = "Err: Keine g�ltige Berechnung! (Matrizen m�ssen die gleiche Gr��e haben)";
			// todo keine gueltige berechnung matrix müssen die gleiche größe haben
		}
		return result;
	}
	/**
	 * This method <b>matrixAddition</b> adds {@code matricesA} with {@code matricesB}.<br>
	 * 
	 * @param matricesA of data type {@code BigDecimal[][]} should contain a matrix.
	 * @param matricesB of data type {@code BigDecimal[][]} should contain a matrix.
	 * @return <b>result</b> of data type {@code BigDecimal[][]} delivers a matrix.
	 */
	public static BigDecimal[][] matrixAddition(BigDecimal[][] matricesA, BigDecimal[][] matricesB) {
		BigDecimal[][] result = new BigDecimal[matricesA.length][matricesA[0].length];
		if (matricesA.length == matricesB.length && matricesA[0].length == matricesB[0].length) {
			for (int i = 0; i < matricesA[0].length; i++) {
				for (int j = 0; j < matricesA.length; j++) {
					result[j][i] = matricesA[j][i].add(matricesB[j][i]);
				}
			}
		} else {
			// Statics.ERROR_MESSAGE = "Err: Keine g�ltige Berechnung! (Matrizen m�ssen die gleiche Gr��e haben)";
			// todo keine gueltige berechnung matrix müssen die gleiche größe haben
		}
		return result;
	}
	/**
	 * This method <b>solveLinearSystemOfEquations</b> solves a linear sytem of equations with a square matrix {@code pMatrix} and a vector {@code pVector}.<br>
	 * First it calculates the inverse of {@code pMatrix}. Then it multply the inverse of {@code pMatrix} with the vector {@code pVector}.
	 * <br><br>
	 * <b>Uses:</b> <br>
	 * + {@code Arithmetic_operations_bigdecimals.matrixInversion()}<br>
	 * 
	 * @param pMatrix of data type {@code BigDecimal[][]} should contain a square matrix.
	 * @param pVector of data type {@code BigDecimal[]} should contain a vector.
	 * @return <b>result</b> of data type {@code BigDecimal[]} delivers a vector.
	 */
	public static BigDecimal[] solveLinearSystemOfEquations(BigDecimal[][] pMatrix, BigDecimal[] pVector) {
		BigDecimal[] result = null;
		if (pMatrix.length == pVector.length) {
			result = new BigDecimal[pVector.length];
			BigDecimal[][] inverse = matrixInversion(pMatrix);
			for (int i = 0; i < result.length; i++) {
				for (int j = 0; j < result.length; j++) {
					result[i] = result[i].add(inverse[i][j].multiply(pVector[j]));
				}
			}
		}
		return result;
	}
}
