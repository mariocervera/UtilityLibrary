package math;

/**
 * This class implements operations on matrices, such as multiplication and exponentiation.
 * 
 * @author Mario Cervera
 */
public class Matrix {

	/*
	 * This method implements the straight-forward (cubic) algorithm for matrix multiplication.
	 */
	public static int[][] multiplication(int[][] A, int[][] B) {
		
		if(A == null || B == null || A.length == 0 || B.length == 0 || A[0].length != B.length)
			throw new IllegalArgumentException("Incompatible matrices");
		
		int[][] result = new int[A.length][B[0].length];
		
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < B[0].length; j++) {
				for(int k = 0; k < B.length; k++) {
					result[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		
		return result;
	}
	
	/*
	 * This method implements the fast (logarithmic) algorithm for matrix exponentiation
	 * based on squaring.
	 */
	public static int[][] exponentiation(int[][] A, int n) {
		
		if(n < 1)
			// Should return identity matrix when exponent is 0
			throw new IllegalArgumentException("Incorrect exponent: " + n);
			
		if(n == 1)
			return A;
		
		if(n % 2 != 0)
			return multiplication(A, exponentiation(A, n-1));
		
		int[][] partialMatrix = exponentiation(A, n/2);
		
		return multiplication(partialMatrix, partialMatrix);
	}
}
