package graphs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * This class provides a generic method to traverse all vertices and edges of a graph using Breadth-First
 * Search. The method is generic because it relies on a set of method stubs that must be completed to
 * obtain the final algorithm adapted to the problem at hand.
 * 
 * During the graph traversal, vertices go through three states: undiscovered, discovered, and processed.
 * The algorithm uses a "parent" array that enables the reconstruction of the Breadth-First Search tree.
 * 
 * 
 * @author Mario Cervera
 *
 */
public class BreadthFirstSearch {

	private static boolean[] discovered;
	private static boolean[] processed;
	private static int[] parent;
	
	/*
	 * This method traverses a graph from a given starting vertex using Breadth-First Search
	 */
	public static void bfs(Graph graph, int start) {

		initializeSearch(graph);

		Deque<Integer> bfsQueue = new ArrayDeque<Integer>();

		bfsQueue.addLast(start);
		discovered[start] = true;

		while (!bfsQueue.isEmpty()) {

			Integer vertex = bfsQueue.removeFirst();
			processed[vertex] = true;
			processVertexBefore(vertex);

			EdgeNode edge = graph.getEdges()[vertex]; // Iterate adjacent vertices

			while (edge != null) {

				int y = edge.getY();

				if (!processed[y])
					processEdge(vertex, y);

				if (!discovered[y]) {
					discovered[y] = true;
					parent[y] = vertex;
					bfsQueue.addLast(y);
				}
				edge = edge.getNext();
			}
			processVertexAfter(vertex);
		}
		
		System.out.println();
	}
	
	/*
	 * This method initializes the variables that are used in the Breadth-First Search algorithm
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
	}
	
	/*
	 * This method implements the actions that must be taken when a vertex reaches its "processed"
	 * state; that is, when it is removed from the Breadth-First Search queue (before processing
	 * its outgoing edges)
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
	 * This method implements the actions that must be taken after all the outgoing edges of a
	 * vertex have been processed
	 */
	private static void processVertexAfter(int vertex) {

		// TODO: Complete this method
		
		System.out.println("Vertex (after): " + vertex);
	}
}
