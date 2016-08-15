package graphs;

/**
 * A edge implementation that enables the representation of graphs as an array of edges.
 * This representation is useful, for example, in Karger's graph contraction algorithm.
 * 
 * @author Mario Cervera
 *
 */
public class Edge {

	// Attributes

	private int x;
	private int y;
	private int weight;

	// Constructors

	public Edge(int x, int y) {
		this(x, y, 1);
	}
	
	public Edge(int x, int y, int weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}
	
	// Getters and setters

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
