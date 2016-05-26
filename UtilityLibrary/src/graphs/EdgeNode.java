package graphs;

/**
 * A node implementation that enables the representation of weighted graphs by means of adjacency
 * lists. An edge (x,y) of a graph will be represented by an EdgeNode y in x's adjacency list.
 * 
 * @author Mario Cervera
 *
 */
public class EdgeNode {

	// Attributes

	private int y;
	private int weight;
	private EdgeNode next;

	// Constructors

	public EdgeNode(int y) {
		this.y = y;
		this.weight = 1;
		this.next = null;
	}

	public EdgeNode(int y, int weight) {
		this.y = y;
		this.weight = weight;
		this.next = null;
	}
	
	// Getters and setters

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

	public EdgeNode getNext() {
		return next;
	}

	public void setNext(EdgeNode next) {
		this.next = next;
	}
}
