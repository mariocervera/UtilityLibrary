package arithmetic;

import java.math.BigInteger;

/**
 * This class provides an implementation of Karatsuba's divide-and-conquer algorithm, which computes
 * the product of two n-digit numbers. The algorithm supports arbitrary-precision arithmetic.
 * 
 * The algorithm performs five major steps:
 * 
 *    Step 1: compute (recursively) the product of the first half of the first operand (a) and the first
 *    half of the second operand (c).
 * 
 *    Step 2: compute (recursively) the product of the second half of the first operand (b) and the second
 *    half of the second operand (d).
 * 
 *    Step 3: compute (a+b)(c+d)
 * 
 *    Step 4: subtract the results obtained in the first two steps to the result obtained in the third step
 * 
 *    Step 5: combine results to obtain final product
 * 
 * 
 * @author Mario Cervera
 */
public class Karatsuba {

	/*
	 * Karatsuba's algorithm
	 */
	public static String karatsuba(String x, String y) {

		if (x.length() <= 1 || y.length() <= 1) {

			// Base case

			int xInt = Integer.parseInt(x);
			int yInt = Integer.parseInt(y);

			return Integer.toString(xInt * yInt);
		}
		else {

			// Calculate middle point

			int len = Math.max(x.length(), y.length());
			int halfLen = len / 2;

			// Step 1: compute a*c

			String a = x.substring(0, x.length() - halfLen);
			String c = y.substring(0, y.length() - halfLen);

			String firstPartialResult = karatsuba(a, c);

			// Step 2: compute b*d

			String b = x.substring(x.length() - halfLen);
			String d = y.substring(y.length() - halfLen);

			String secondPartialResult = karatsuba(b, d);

			// Step 3: compute (a+b)(c+d)

			String thirdPartialResult = karatsuba(sum(a, b), sum(c, d));

			// Step 4: compute (3) - (2) - (1)

			String aux = subtract(thirdPartialResult, firstPartialResult);
			thirdPartialResult = subtract(aux, secondPartialResult);

			// Step 5: compute final product

			String _10toThePowerOfN = "";
			for (int i = 0; i < halfLen * 2; i++) {
				_10toThePowerOfN += "0";
			}

			firstPartialResult += _10toThePowerOfN;
			thirdPartialResult += _10toThePowerOfN.substring(halfLen);

			String result = sum(firstPartialResult, sum(secondPartialResult, thirdPartialResult));

			return result;
		}
	}

	/*
	 * Arbitrary-precision addition operation
	 */
	private static String sum(String op1, String op2) {

		BigInteger big1 = new BigInteger(op1);
		BigInteger big2 = new BigInteger(op2);

		return big1.add(big2).toString();
	}

	/*
	 * Arbitrary-precision subtraction operation
	 */
	private static String subtract(String op1, String op2) {

		BigInteger big1 = new BigInteger(op1);
		BigInteger big2 = new BigInteger(op2);

		return big1.subtract(big2).toString();
	}
}
