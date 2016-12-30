package graphs;

/**
 * This class provides an implementation of Prim's algorithm, which computes Minimum
 * Spanning Trees in connected weighted graphs. Given a particular starting vertex s,
 * the algorithm finds a subset of the edges that connect all of the vertices together,
 * without any cycles and with the minimum total edge weight.
 * 
 * This implementation of Prim's algorithm runs in O(nm) time, where n is the number
 * of vertices of the graph and m is the number of edges. Faster running times can be
 * achieved by improving this algorithm by means of a heap data structure.
 * 
 * @author Mario Cervera
 */
public class MinimumSpanningTree {

	/*
	 * Prim's algorithm. It returns an array that contains the edges that comprise the
	 * Minimum Spanning Tree of the graph.
	 */
	public static Edge[] prim(Graph graph, int start) {
		
		// Initialize variables

		int n = graph.getNumVertices();
		EdgeNode[] edges = graph.getEdges();
		Edge[] mst = new Edge[n-1];
		
		int[] distance = new int[n + 1];
		boolean[] inTree = new boolean[n + 1];
		int[] parent = new int[n + 1];

		for(int i = 0; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
			inTree[i] = false;
			parent[i] = -1;
		}

		// The distance to the starting vertex from the starting vertex is 0

		distance[start] = 0;

		// While there are vertices not yet added to the minimum spanning tree ...

		int node = start;
		int mstEdgeCounter = 0;

		while(!inTree[node]) {

			inTree[node] = true;

			// Iterate outgoing edges of current vertex to update distances

			EdgeNode adjacentNode = edges[node];

			while(adjacentNode != null) {

				// Unlike Dijkstra's algorithm, distance only considers the current edge
				int w = adjacentNode.getWeight();
				int currentDistance = distance[adjacentNode.getY()];
				int newDistance = w;

				if(!inTree[adjacentNode.getY()] && currentDistance > newDistance) {
					distance[adjacentNode.getY()] = newDistance;
					parent[adjacentNode.getY()] = node;
				}

				adjacentNode = adjacentNode.getNext();
			}

			// Select minimum distance

			node = start;

			int minDist = Integer.MAX_VALUE;

			for(int i = 1; i <= n; i++) {
				if(!inTree[i] && distance[i] < minDist) {
					minDist = distance[i];
					node = i;
				}
			}
			
			// Update Minimum Spanning Tree array
			
			if(minDist != Integer.MAX_VALUE) {
				mst[mstEdgeCounter] = new Edge(parent[node], node, minDist);
				mstEdgeCounter++;
			}
		}

		return mst;
	}
}
