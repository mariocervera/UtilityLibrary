package graphs;

/**
 * This class provides a generic method to traverse all vertices and edges of a graph using Depth-First
 * Search. The method is generic because it relies on a set of method stubs that must be completed to
 * obtain the final algorithm adapted to the problem at hand.
 * 
 * During the graph traversal, vertices go through three states: undiscovered, discovered, and processed.
 * The algorithm uses a "parent" array that enables the reconstruction of the Depth-First Search tree.
 * 
 * 
 * @author Mario Cervera
 *
 */
public class DepthFirstSearch {

	private static boolean[] discovered;
	private static boolean[] processed;
	private static int[] parent;
	
	private static boolean finish = false; // Enables premature search termination
	
	/*
	 * Public method that invokes the Depth-First Search algorithm
	 */
	public static void dfs(Graph graph, int vertex) {
		
		initializeSearch(graph);
		depthFirstSearch(graph, vertex);
	}
	
	/*
	 * This method implements the Depth-First Search algorithm. By using recursion, we visit a new
	 * vertex as soon as one is available. We could also implement this behavior explicitly using a
	 * stack data structure (instead of recursion).
	 */
	private static void depthFirstSearch(Graph graph, int vertex) {

		if(finish) return;
		
		discovered[vertex] = true;
		processVertexBefore(vertex);
		
		EdgeNode adjacentNode = graph.getEdges()[vertex];
		
		while(adjacentNode != null) { // Iterate adjacent vertices
			
			int y = adjacentNode.getY();
			
			if(!discovered[y]) {
				parent[y] = vertex;
				processEdge(vertex, y);
				depthFirstSearch(graph, y);
			}
			else if(!processed[y] && parent[vertex] != y) {
				// If this condition is true, a cycle has been found
				processEdge(vertex, y);
			}
			
			if(finish) return;
			
			adjacentNode = adjacentNode.getNext();
		}
		
		processVertexAfter(vertex);
		processed[vertex] = true;
	}
	
	/*
	 * This method initializes the variables that are used in the Depth-First Search algorithm
	 */
	private static void initializeSearch(Graph graph) {

		int N = graph.getNumVertices();

		discovered = new boolean[N+1]; // Vertices are numbered from 1 to N
		processed = new boolean[N+1];
		parent = new int[N+1];

		for (int i = 0; i <= N; i++) {
			discovered[i] = false;
			processed[i] = false;
			parent[i] = -1;
		}
		
		finish = false;
	}
	
	/*
	 * This method implements the actions that must be taken when a vertex is discovered (before recursively
	 * exploring the vertex)
	 */
	private static void processVertexBefore(int vertex) {

		// TODO: Complete this method
		
		System.out.println("Vertex (before): " + vertex);
	}

	/*
	 * This method contains the actions that must be taken when an edge is processed
	 */
	private static void processEdge(int x, int y) {

		// TODO: Complete this method
		
		System.out.println("Edge: (" + x + "," + y + ")");
	}

	/*
	 * This method implements the actions that must be taken when a vertex is set as "processed" (after all its adjacent
	 * vertices are set as "processed")
	 */
	private static void processVertexAfter(int vertex) {

		// TODO: Complete this method
		
		System.out.println("Vertex (after): " + vertex);
	}
}
