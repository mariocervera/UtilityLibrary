package graphs;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class provides an implementation of Karger's randomized algorithm for
 * minimum-cut connectivity. This algorithm is based on graph contraction.
 * Contracting an edge (x,y) in a graph G merges the two incident vertices into
 * one, removing self-loops but leaving multi-edges. Any sequence of such
 * contractions can raise (but not lower) the minimum cut in G, and leaves the
 * cut unchanged if no edge of the cut is contracted. It is therefore recommended
 * to run this algorithm several times in order to obtain the minimum cut possible
 * with very high probability.
 * 
 * In each iteration of the algorithm, a new edge is selected at random. When this
 * edge is contracted, the number of vertices goes down by one. The algorithm
 * terminates when there are only two vertices remaining. The contraction of the
 * edges is implemented by means of a union-find data structure.
 * 
 * @author Mario Cervera
 */
public class MinimumCut {

	/*
	 * Karger's graph contraction algorithm: it returns a cut in the given connected
	 * graph. If this method is invoked a quadratic number times, the probability of
	 * failing to find a minimum cut is 1/n (being n the number of vertices of the graph)
	 * 
	 * The input graph is represented as a list of edges.
	 */
	public static int graphContraction(List<Edge> edges, int n) {
		
		int m = edges.size(); // "n" is the number of vertices and "m" the number of edges
		
		// Initialize union-find data structure
		
		int[] unionFind = new int[n+1]; // Vertex "1" in index "1" and so on
		
		for(int i = 0; i <= n; i++) {
			unionFind[i] = i;
		}
		
		// Obtain a random permutation of the list of edges
		
		long seed = System.nanoTime();
		Collections.shuffle(edges, new Random(seed));
		
		// Iterate until only two vertices remain
		
		int remainingVertices = n;
		int nextEdge = 0;
		
		while(remainingVertices > 2) {
			
			Edge edge = edges.get(nextEdge);
			int x = edge.getX();
			int y = edge.getY();
			
			// Make sure that the edge is not a self-loop
			
			if(unionFind[x] != unionFind[y]) {
				
				// Perform contraction
				
				contract(unionFind, unionFind[x], unionFind[y]);
				remainingVertices--;
			}
			
			nextEdge++;
		}
		
		// Count the number of edges in the cut
		
		int counter = 0;
		
		for(int i = 0; i < m; i++) {
			Edge edge = edges.get(i);
			
			if(unionFind[edge.getX()] != unionFind[edge.getY()]) {
				counter++;
			}
		}
		
		return counter;
	}
	
	private static void contract(int[] unionFind, int x, int y) {
		
		int len = unionFind.length;
		
		for(int i = 1; i < len; i++) {
			
			if(unionFind[i] == x) {
				unionFind[i] = y;
			}
		}
	}
}
