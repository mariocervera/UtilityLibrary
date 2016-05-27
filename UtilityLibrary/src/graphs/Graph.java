package graphs;

/**
 * A graph represented by means of adjacency lists.
 * 
 * @author Mario Cervera
 *
 */
public class Graph {

	private int numVertices; // Number of vertices of the graph
	private EdgeNode[] edges; // Adjacency lists
	private int[] degree; // The degree of each vertex

	/*
	 * Constructor
	 */
	public Graph(int numVertices) {

		this.numVertices = numVertices;
		this.edges = new EdgeNode[numVertices + 1]; // Vertices are numbered from 1 to N
		this.degree = new int[numVertices + 1];

		for (int i = 0; i <= numVertices; i++) {
			this.edges[i] = null;
			this.degree[i] = 0;
		}
	}
	
	public int getNumVertices() {
		return numVertices;
	}

	public EdgeNode[] getEdges() {
		return edges;
	}
	

	/*
	 * This method adds a new edge to the graph (weighted). Returns true if the
	 * insertion is successful; false otherwise
	 */
	public boolean insertEdge(int x, int y, int weight, boolean undirected) {

		if (x < 1 || x > numVertices || y < 1 || y > numVertices)
			return false;

		if (edges[x] == null) {
			edges[x] = new EdgeNode(y, weight);
		}
		else {
			EdgeNode newNode = new EdgeNode(y, weight);
			newNode.setNext(edges[x]);
			edges[x] = newNode;
		}

		degree[x]++;

		if (undirected) { // If the graph is undirected, insert also the edge (y,x)

			return insertEdge(y, x, weight, false);
		}

		return true;
	}

	/*
	 * This method adds a new edge to the graph (unweighted --> weight = 1)
	 */
	public boolean insertEdge(int x, int y, boolean undirected) {

		return this.insertEdge(x, y, 1, undirected);
	}

}
