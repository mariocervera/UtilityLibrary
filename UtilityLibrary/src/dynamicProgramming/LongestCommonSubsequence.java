package dynamicProgramming;

/**
 * This class provides a dynamic-programming-based solution for the Longest Common Subsequence (LCS)
 * problem. The LCS problem is: given two strings S and T of lengths N and M, respectively, find
 * the length of longest subsequence present in both strings. A subsequence is a sequence that
 * appears in the same relative order, but that it is not necessarily contiguous. For example,
 * "ace" is a subsequence of "abcde", while "cde" is both a subsequence and a substring.
 * 
 * The solution given in this class runs in quadratic time O(NM), and it uses the same amount of
 * extra storage.
 * 
 * @author Mario Cervera
 */
public class LongestCommonSubsequence {

	/*
	 * This method returns the length of the longest common subsequence of the given strings
	 */
	public static int longestCommonSubsequence(String s, String t) {
		
		int lenS = s.length();
		int lenT = t.length();
		
		int[][] dp = new int[lenS+1][lenT+1];
		
		char[] sArr = s.toCharArray();
		char[] tArr = t.toCharArray();
		
		for (int i = 1; i <= lenS; i++) {
			for (int j = 1; j <= lenT; j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if (sArr[i-1] == tArr[j-1])
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
			}
		}
		
		return dp[lenS][lenT];
	}
}
