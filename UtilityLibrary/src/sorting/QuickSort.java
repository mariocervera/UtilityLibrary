package sorting;

import java.util.Collections;
import java.util.List;

/**
 * This class provides an implementation of the QuickSort algorithm. All of the objects of
 * the input array must implement the Comparable interface. Thus, the algorithm can be used
 * as a general-purpose comparison-based sorting algorithm.
 * 
 * This implementation of QuickSort uses Median of Three for pivot selection. This heuristic
 * increases the likelihood of partitioning the array into roughly equal pieces (thereby
 * decreasing running time). Additionally, the algorithm adds an initial step where it randomly
 * permutes the order of the input elements. This randomized version of QuickSort guarantees
 * that we can expect O(n log n) time regardless of what the initial input is.
 * 
 * @author Mario Cervera
 *
 */
public class QuickSort {
	
	/*
	 * The only public method of this class. Randomly permutes the input array before
	 * calling the QuickSort subroutine.
	 */
	public static <T extends Comparable<T>> void quickSort(List<T> a, int low, int high) {
		
		Collections.shuffle(a);
		
		qs(a, low, high);
	}
	
	/*
	 * Implementation of QuickSort. This method sorts the elements of the input array.
	 */
	private static <T extends Comparable<T>> void qs(List<T> a, int low, int high) {
		
		if(high - low <= 0) return;
		
		int pivot = choosePivot(a, low, high); // "pivot" is an index of the array
		
		swap(a, pivot, low); // always put the pivot in the "low" position
		
		pivot = partition(a, low, high); // partition subroutine assumes the pivot is the "low" position
		
		qs(a, low, pivot - 1);
		qs(a, pivot + 1, high);
	}
	
	/*
	 * Median of three
	 */
	private static <T extends Comparable<T>> int choosePivot(List<T> a, int low, int high) {
				
		int mid = (int) Math.floor((low + high) / 2);

		if (a.get(low).compareTo(a.get(high)) > 0) {

			if (a.get(high).compareTo(a.get(mid)) > 0) {
				return high;
			}
			else if (a.get(low).compareTo(a.get(mid)) > 0) {
				return mid;
			}
			else {
				return low;
			}
		}
		else {
			if (a.get(low).compareTo(a.get(mid)) > 0) {
				return low;
			}
			else if (a.get(mid).compareTo(a.get(high)) > 0) {
				return high;
			}
			else {
				return mid;
			}
		}
	}
	
	/*
	 * Implements the partition subroutine of QuickSort in linear time
	 */
	private static <T extends Comparable<T>> int partition(List<T> a, int low, int high) {
		
		int firstHigh = low+1;
		int pivot = low;
		
		for(int i = low+1; i <= high; i++) {
			if(a.get(i).compareTo(a.get(pivot)) < 0) {
				swap(a, i, firstHigh);
				firstHigh++;
			}
		}
		
		swap(a, firstHigh-1, pivot);
		
		return firstHigh-1;
	}
		
	/*
	 * Swaps two elements of the input array
	 */
	private static <T extends Comparable<T>> void swap(List<T> a, int i, int j) {

		T aux = a.get(i);
		a.set(i, a.get(j));
		a.set(j, aux);
	}
}