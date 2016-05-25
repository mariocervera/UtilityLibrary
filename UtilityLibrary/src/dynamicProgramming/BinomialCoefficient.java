package dynamicProgramming;

/**
 * This class provides a method to calculate binomial coefficients C(n,k). Binomial coefficients
 * count the number of ways to choose k elements out of n possibilities. The method is based on
 * Dynamic Programming: it applies the recurrence relation that is defined by the Pascal's triangle;
 * thus, we avoid the need to calculate the coefficients using the formula n!/((n-k)!*k!), which is
 * inefficient and may cause arithmetic overflow.
 * 
 * 
 * @author Mario Cervera
 *
 */
public class BinomialCoefficient {

	private static final int MAX_VALUE = 100; // Change this value if necessary

	private static long[][] table = null;

	/*
	 * This method initializes the table that stores the binomial coefficients
	 */
	private static void init() {

		table = new long[MAX_VALUE][MAX_VALUE];

		for (int i = 0; i < MAX_VALUE; i++) {
			table[i][0] = table[i][i] = 1;
			for (int j = 1; j < i; j++) {
				table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]);
			}
		}
	}

	/*
	 * Returns C(n,k)
	 */
	public static long calculate(int n, int k) {

		if (table == null) init();

		if (n > MAX_VALUE || k > MAX_VALUE) return -1L;

		return table[n][k];
	}
}
