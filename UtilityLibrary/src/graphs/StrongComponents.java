package graphs;

/**
 * This class provides an implementation of Kosaraju's algorithm, which computes
 * strongly connected components (SCCs) in directed graphs. The algorithm performs
 * two steps:
 * 
 * Step 1: a DFS, starting from an arbitrary vertex and labeling each vertex
 * in order of its completion. This step is performed on the reverse version
 * of the graph (in other words, edges are traversed backwards).
 * 
 * Step 2: a second DFS, starting from the highest numbered vertex. If this
 * search does not completely traverse the graph, continue with the highest
 * numbered unvisited vertex. Each DFS tree is a SCC.
 * 
 * @author Mario Cervera
 */
public class StrongComponents extends DepthFirstSearch {

	private int[] finishingTime;
	private int[] leader;
	
	private int t; // time
	private int l; // leader
	
	private boolean firstPass;
	
	/*
	 * Kosaraju's algorithm for Strongly Connected Components. This method returns
	 * an array whose indices represent vertices and whose values represent the 
	 * leaders of these vertices. All vertices in the same SCC will have the same
	 * leader; therefore, this array allows you to compute not only the number of
	 * SCCs of the graph, but also the exact vertices that belong to each SCC.
	 */
	public int[] kosaraju(Graph graph) {
		
		int n = graph.getNumVertices();
		
		// Initialize variables
		
		finishingTime = new int[n+1];
		leader = new int[n+1];
		
		t = 0;
		l = 0;
		
		for(int i = 0; i <= n; i++) {
			finishingTime[i] = 0;
			leader[i] = 0;
		}
		
		firstPass = true;
		
		// First pass --> Compute finishing time in reverse graph
		
		Graph reverseGraph = getReverseGraph(graph);
		initializeSearch(reverseGraph);
		
		for(int i = n; i > 0; i--) {
			if(!discovered[i]) {
				depthFirstSearch(reverseGraph, i);
			}
		}
		
		// Second pass --> Compute leaders
		
		initializeSearch(graph);
		firstPass = false;
		
		for(int i = n; i > 0; i--) {
			int vertex = finishingTime[i];
			if(!discovered[vertex]) {
				l = vertex;
				depthFirstSearch(graph, vertex);
			}
		}
		
		return leader;
	}
	
	/*
	 * This method returns the reverse of a given graph. The reverse of a directed graph has
	 * the same set of vertices but all of the edges are reversed compared to the orientation
	 * of the original graph.
	 */
	private Graph getReverseGraph(Graph g) {
		
		int n = g.getNumVertices();
		Graph reverseGraph = new Graph(n);
		
		EdgeNode[] edges = g.getEdges();
		
		for(int x = 1; x <= n; x++) {
			
			EdgeNode adjNode = edges[x];
			
			while(adjNode != null) {
				
				int y = adjNode.getY();
				reverseGraph.insertEdge(y, x, false);
				
				adjNode = adjNode.getNext();
			}
		}
		
		return reverseGraph;
	}
	
	// ********************************************************
	// **** Methods overridden from DepthFirstSearch class ****
	// ********************************************************
	
	@Override
	protected void processVertexBefore(int vertex) {
		
		if(!firstPass) {
			leader[vertex] = l;
		}
	}
	
	@Override
	protected void processVertexAfter(int vertex) {
		
		if(firstPass) {
			t++;
			finishingTime[t] = vertex;
		}
	}
	
	@Override
	protected void processEdge(int x, int y) {
		
		// Do nothing
	}
}
