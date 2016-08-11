package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides an implementation of the MergeSort algorithm. All of the objects of
 * the input array must implement the Comparable interface. Thus, the algorithm can be used
 * as a general-purpose comparison-based sorting algorithm.
 * 
 * This implementation of MergeSort is not in-place; that is, it uses an auxiliary array. The
 * merging subroutine takes linear time, for a total of O(n log n) to perform the sorting.
 * 
 * @author Mario Cervera
 */
public class MergeSort {

	/*
	 * The only public method of this class. It initializes the auxiliary array before calling
	 * the MergeSort subroutine.
	 */
	public static <T extends Comparable<? super T>> void mergeSort(List<T> a, int low, int high) {
		
		// Initialize auxiliary array
		
		int numElements = high - low + 1;
		
		List<T> aux = new ArrayList<T>(numElements);
		
		for (int i = 0; i < numElements; i++)
			aux.add(i, null);
		
		// Invoke MergeSort
		
		ms(a, aux, low, high);
	}
	
	/*
	 * Implementation of MergeSort. This method sorts the elements of the input array.
	 */
	private static <T extends Comparable<? super T>> void ms(List<T> a, List<T> aux, int low, int high) {
		
		if(low < high) {
			
			int mid = (low + high) / 2;
			
			ms(a, aux, low, mid); // Orders the first half of the array recursively
			ms(a, aux, mid + 1, high); // Orders the second half of the array recursively
			
			merge(a, aux, low, mid, high); // Merges the two sorted halves to obtain the fully sorted array
		}
	}
	
	/*
	 * The Merge subroutine. It merges two sorted arrays in linear time.
	 */
	private static <T extends Comparable<? super T>> void merge(List<T> a, List<T> aux, int low, int mid, int high) {
		
		int i = low;
		int j = mid + 1;
		
		int posAux = low;
		
		// Merge elements from both arrays
		
		while(i <= mid && j <= high) {
			if(a.get(i).compareTo(a.get(j)) <= 0) {
				aux.set(posAux, a.get(i));
				i++;
			}
			else {
				aux.set(posAux, a.get(j));
				j++;
			}
			posAux++;
		}
		
		// One array may not be fully traversed. Copy remaining elements.
		
		if(i <= mid) {
			for(int k = i; k <= mid; k++) {
				aux.set(posAux, a.get(k));
				posAux++;
			}
		}
		if(j <= high) {
			for(int k = j; k <= high; k++) {
				aux.set(posAux, a.get(k));
				posAux++;
			}
		}
		
		// Transfer the elements from auxiliary array to main array
		
		for(int k = low; k <= high; k++) {
			a.set(k, aux.get(k));
		}
	}
}
