package searching;

/**
 * This class provides a method to efficiently search an integer in an integer array.
 * This method is based on Binary Search; therefore, the input array must be sorted.
 * 
 * @author Mario Cervera
 *
 */
public class BinarySearch {

	/*
	 * Implementation of binary search. Runs in O(log n). Returns the index of the
	 * array where "key" can be found. Returns -1 when the search is unsuccessful.
	 */
	public static int binarySearch(int[] a, int key, int low, int high) {
		
		if(low > high) return -1;
		
		int middle = (low + high) / 2;
		
		if(a[middle] == key) return middle;
		
		if(a[middle] > key) {
			
			return binarySearch(a, key, low, middle-1);
		}
		else {
			return binarySearch(a, key, middle+1, high);
		}
	}
}
