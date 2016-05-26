package searching;

import java.util.List;

/**
 * This class provides a backtracking method to iterate through all possible configurations of a search space.
 * This method uses other methods that are implemented here as empty stubs. These methods must be completed to
 * obtain the final algorithm. The solution in each state of the search space is represented here as an array
 * of integers. This data type can be changed as well.
 * 
 * 
 * @author Mario Cervera
 *
 */
public class Backtracking {

	public static boolean finish = false; 
	
	/*
	 * This method implements the backtracking algorithm
	 */
	public static void backtrack(List<Integer> solution, Object inputData) {
	
		if(isSolution(solution, inputData)) {
			processSolution(solution, inputData);
		}
		else {
			
			List<Integer> candidates = getCandidates(solution, inputData);
			
			for(Integer candidate : candidates) {	
				doMove(candidate, solution, inputData);
				backtrack(solution, inputData);
				undoMove(candidate, solution, inputData);
				
				if(finish) return; // Premature termination
			}
		}
	}
	
	// ****************************************************************************
	// ******* Private methods --> Implement them to complete the algorithm *******
	// ****************************************************************************
	
	/*
	 * This method checks whether a given partial solution is actually a valid final solution
	 */
	private static boolean isSolution(List<Integer> solution, Object inputData) {
		
		//TODO: Complete this method
		
		return false;
	}
	
	/*
	 * This method implements the actions that must be taken when a valid solution is found
	 */
	private static void processSolution(List<Integer> solution, Object inputData) {
		
		//TODO: Complete this method
	}
	
	/*
	 * This method fills a list of possible candidates for the next empty position (k) of the
	 * current solution
	 */
	private static List<Integer> getCandidates(List<Integer> solution, Object inputData) {
		
		//TODO: Complete this method
		
		return null;
	}
	
	/*
	 * This method typically adds a candidate to the current solution, but it may also perform
	 * other actions, such as modifying another data structure
	 */
	private static void doMove(Integer candidate, List<Integer> solution, Object inputData) {
		
		//TODO: Complete this method
	}
	
	/*
	 * This method typically removes the candidate from the current solution, but it may also perform
	 * other actions, such as modifying another data structure
	 */
	private static void undoMove(Integer candidate, List<Integer> solution, Object inputData) {
		
		//TODO: Complete this method
	}
}
