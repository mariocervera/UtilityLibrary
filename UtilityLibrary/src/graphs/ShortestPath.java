package graphs;

/**
 * This class provides an implementation of Dijkstra's algorithm, which computes shortest
 * paths in non-negative weighted graphs. Given a particular starting vertex s, it finds
 * the distance from s to every other vertex in the graph.
 * 
 * This implementation of Dijkstra's algorithm runs in O(nm) time, where n is the number
 * of vertices of the graph and m is the number of edges. Faster running times can be
 * achieved by improving this algorithm by means of a heap data structure.
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
}
