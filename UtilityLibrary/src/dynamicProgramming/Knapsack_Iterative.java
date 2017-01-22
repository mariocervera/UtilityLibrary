package dynamicProgramming;

/**
 * This class implements an iterative dynamic-programming solution for the knapsack
 * problem. The knapsack problem is the following: given a set of items, each with a
 * weight and a value, determine the items to be included in a collection so that the
 * total weight is less than or equal to a given capacity and the total value is as
 * large as possible.
 * 
 * The solution implemented in this class runs in pseudo-polynomial time: the algorithm
 * runs fast when the capacity C is an small integer (roughly C <= 10000).
 * 
 * @author Mario Cervera
 */
public class Knapsack_Iterative {

	/**
	 * @param items, a collection of items (value-weight pairs). The first item must be at index 1.
	 * @param capacity, the capacity of the knapsack
	 * 
	 * @return the maximum possible total value
	 */
	public static long knapsack(int[][] items, int capacity) {
		
		int numItems = items.length;
		
		// Initialize dynamic programming table
		
		long[][] dpTable = new long[numItems][capacity + 1];
		
		for(int i = 0; i < numItems; i++) {
			dpTable[i][0]= 0;
		}
		
		for(int i = 0; i <= capacity; i++) {
			dpTable[0][i]= 0;
		}
		
		// Recurrence relation
		
		for(int i = 1; i < numItems; i++) { // For each item ...
			
			int itemValue = items[i][0];
			int itemWeight = items[i][1];
			
			for(int j = 1; j <= capacity; j++) { // For each capacity ...
				
				if(j-itemWeight < 0) { 
					// Case 1: the item does not fit in the knapsack, so I do not take it.
					dpTable[i][j] = dpTable[i-1][j];
				}
				else {
					// Case 2: the item fits in the knapsack. I choose the maximum of two possibilities:
					// not taking the item and taking it.
					dpTable[i][j] = Math.max(dpTable[i-1][j], dpTable[i-1][j-itemWeight] + itemValue);
				}
			}
		}
		
		return dpTable[numItems-1][capacity];
	}
}
