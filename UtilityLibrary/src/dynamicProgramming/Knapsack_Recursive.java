package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements a recursive solution for the knapsack problem. The knapsack
 * problem is the following: given a set of items, each with a weight and a value,
 * determine the items to be included in a collection so that the total weight is
 * less than or equal to a given capacity and the total value is as large as possible.
 * 
 * The solution implemented in this class, unlike the iterative dynamic-programming
 * solution, only computes subproblems on an "as needed" basis; therefore, it can be
 * applied to larger instances of the problem. The results of the subproblems are
 * cached in a two-dimensional hash table.
 * 
 * @author Mario Cervera
 */
public class Knapsack_Recursive {

	/**
	 * The Hash Table for caching partial results.
	 */
	private static Map<Key, Long> cacheTable;
	
	/**
	 * @param items, a collection of items (value-weight pairs). The first item must be at index 1.
	 * @param capacity, the capacity of the knapsack
	 * 
	 * @return the maximum possible total value
	 */
	public static long knapsack(int[][] items, int capacity) {
		
		int numItems = items.length;
		
		cacheTable = new HashMap<Key, Long>();

		// Boundary conditions: maximum value for zero capacity and zero items is zero.
		
		for(int i = 0; i < numItems; i++) {
			cacheTable.put(new Key(i,0), 0l);
		}
		
		for(int i = 0; i <= capacity; i++) {
			cacheTable.put(new Key(0,i), 0l);
		}
		
		// Compute result recursively
		
		return knapsack_recursive(items, numItems-1, capacity);
	}
	
	/**
	 * Recursive solution for the knapsack problem
	 */
	private static long knapsack_recursive(int[][] items, int item, int weight) {
		
		Long value = cacheTable.get(new Key(item, weight));
		
		if(value != null) {
			return value; // The subproblem is already computed
		}
		else { // Compute the new subproblem
			
			int itemValue = items[item][0];
			int itemWeight = items[item][1];
			
			if(weight-itemWeight < 0) { 
				// Item does not fit in the knapsack
				cacheTable.put(new Key(item, weight), knapsack_recursive(items, item-1, weight));
			}
			else {
				// Item fits in the knapsack
				Long caseNotIncluded = knapsack_recursive(items, item-1, weight);
				Long caseIncluded = knapsack_recursive(items, item-1, weight-itemWeight) + itemValue;
						
				cacheTable.put(new Key(item, weight), Math.max(caseNotIncluded, caseIncluded));
			}
			
			return cacheTable.get(new Key(item,weight));	
		}
	}
	
	/**
	 * This class is a wrapper of two-dimensional keys
	 * 
	 * @author Mario Cervera
	 */
	private static class Key {

	    private final int x;
	    private final int y;

	    public Key(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

		@Override
		public boolean equals(Object o) {
			
			if (this == o) {
				return true;
			}
			if (!(o instanceof Key)) {
				return false;
			}
			
			Key key = (Key) o;
			
			return x == key.x && y == key.y;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}
	}
}
