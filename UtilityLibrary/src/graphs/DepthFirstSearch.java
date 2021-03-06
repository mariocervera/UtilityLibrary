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

	protected boolean[] discovered;
	protected boolean[] processed;
	protected int[] parent;
	
	protected boolean finish = false; // Enables premature search termination
	
	/*
	 * Invokes the Depth-First Search algorithm after an initialization of the variables
	 */
	protected void dfs(Graph graph, int vertex) {
		
		initializeSearch(graph);
		depthFirstSearch(graph, vertex);
	}
	
	/*
	 * This method implements the Depth-First Search algorithm. By using recursion, new vertices are
	 * visited as soon as they are available. This behavior could have also been implemented using a
	 * explicit stack data structure (instead of recursion).
	 */
	protected void depthFirstSearch(Graph graph, int vertex) {

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
	protected void initializeSearch(Graph graph) {

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
	 * This method implements the actions that must be taken when a vertex is discovered (before
	 * recursively exploring the vertex)
	 */
	protected void processVertexBefore(int vertex) {

		// TODO: Override this method
		
		System.out.println("Vertex (before): " + vertex);
	}

	/*
	 * This method contains the actions that must be taken when an edge is processed
	 */
	protected void processEdge(int x, int y) {

		// TODO: Override this method
		
		System.out.println("Edge: (" + x + "," + y + ")");
	}

	/*
	 * This method implements the actions that must be taken when a vertex is set as "processed"
	 * (after all its adjacent vertices are set as "processed")
	 */
	protected void processVertexAfter(int vertex) {

		// TODO: Override this method
		
		System.out.println("Vertex (after): " + vertex);
	}
}
