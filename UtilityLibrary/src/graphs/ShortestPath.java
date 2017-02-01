package graphs;

/**
 * This class implements two shortest path algorithms: Dijkstra's and Floyd-Warshall's.
 * 
 * Dijkstra's algorithm computes shortest paths in non-negative weighted graphs. Given
 * a particular starting vertex s, it finds the distance from s to every other vertex
 * in the graph. The implementation of Dijkstra's algorithm that is given in this class
 * runs in O(nm) time, where n is the number of vertices of the graph and m is the number
 * of edges. Faster running times can be achieved by improving this algorithm by means of
 * a heap data structure.
 * 
 * Unlike Dijkstra's algorithm, Floyd-Warshall's algorithm finds shortest paths between
 * all pairs of vertices. It supports both positive and negative edge weights (but no
 * negative cycles). Floyd-Warshall's algorithm runs in O(n^3) time.
 * 
 * @author Mario Cervera
 */
public class ShortestPath {

	/*
	 * Dijkstra's shortest paths algorithm. It returns an array that contains the distances
	 * from the starting vertex to every other vertex in the graph.
	 */
	public static long[] dijkstra(Graph graph, int start) {

		// Initialize variables

		int n = graph.getNumVertices();
		EdgeNode[] edges = graph.getEdges();

		long[] distance = new long[n + 1];
		boolean[] inTree = new boolean[n + 1];

		for(int i = 0; i <= n; i++) {
			distance[i] = Long.MAX_VALUE;
			inTree[i] = false;
		}

		// The distance to the starting vertex from the starting vertex is 0

		distance[start] = 0;

		// While there are vertices outside of the shortest paths tree ...

		int node = start;

		while(!inTree[node]) {

			inTree[node] = true;

			// Iterate outgoing edges of current vertex to update distances

			EdgeNode adjacentNode = edges[node];

			while(adjacentNode != null) {

				long w = adjacentNode.getWeight();
				long currentDistance = distance[adjacentNode.getY()];
				long newDistance = distance[node] + w;

				if(currentDistance > newDistance) {
					distance[adjacentNode.getY()] = newDistance;
				}

				adjacentNode = adjacentNode.getNext();
			}

			// Select minimum distance

			node = start;

			long minDist = Long.MAX_VALUE;

			for(int i = 1; i <= n; i++) {
				if(!inTree[i] && distance[i] < minDist) {
					minDist = distance[i];
					node = i;
				}
			}
		}

		return distance;
	}
	
	/*
	 * Floyd-Warshall's algorithm. It updates the input adjacency matrix with all n^2 distances
	 * between all pairs of vertices.
	 */
	public static void floydWarshall(long[][] matrix, int N) {
        
		for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(matrix[i][k] != Long.MAX_VALUE && matrix[k][j] != Long.MAX_VALUE) {    
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }
    }
}
