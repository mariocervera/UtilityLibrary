package sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A union-find (also known as disjoint-set) data structure. It stores a set of elements, partitioned
 * into disjoint subsets. It supports two main operations: find (the subset of a given element) and
 * union (of two different subsets into one single subset). The additional operation "make set" enables
 * the addition of new elements to the data structure.
 * 
 * In this union-find data structure, each subset is represented as a tree, in which each node holds a
 * reference to its parent node. This implementation of union-find data structures is called:
 * disjoint-set forests.
 * 
 * This class also implements union by rank and path compression; thus, it guarantees quasilinear time
 * for all of its supported operations. 
 * 
 * @author Mario Cervera
 */
public class UnionFind<T> {

	/**
	 * Collection of parent pointers
	 */
	private Map<T, T> parent;
	
	/**
	 * Size of each subtree
	 */
	private Map<T, Integer> size;
	
	/** Constructors*/
	
	public UnionFind() {
		
		this.parent = new HashMap<T, T>();
		this.size = new HashMap<T, Integer>();
	}
	
	public UnionFind(int initialCapacity) {
		
		this.parent = new HashMap<T, T>(initialCapacity);
		this.size = new HashMap<T, Integer>(initialCapacity);
	}
	
	/** Getter methods*/
	
	public Map<T, T> getParentCollection() {
		return parent;
	}
	
	public Set<T> getElements() {
		return parent.keySet();
	}
	
	/** Operations*/
	
	/*
	 * This method adds a new element of the set. The parent pointer of a new element is a self-loop,
	 * and the size of its subtree is 1.
	 */
	public void makeSet(T newElement) {
		
		parent.put(newElement, newElement);
		size.put(newElement, new Integer(1));
	}
	
	/*
	 * This method returns the representative of the set that contains a given element. This method
	 * implements the "path compression" technique.
	 */
	public T find(T element) {
		
		T parentElem = this.parent.get(element);
		if(!element.equals(parentElem)) {
			this.parent.put(element, find(parentElem));
		}
		return parentElem;
	}
	
	/*
	 * This method joins two subsets. It implements the "Union by rank" technique.
	 */
	public void union(T element1, T element2) {
		
		T parent1 = find(element1);
		T parent2 = find(element2);
		
		if(parent1.equals(parent2)) { //Already in the same set
			return;
		}
		else {
			Integer size1 = this.size.get(parent1);
			Integer size2 = this.size.get(parent2);
			
			if(size1 < size2) {
				this.parent.put(parent1, parent2);
				this.size.put(parent2, size1 + size2);
			}
			else {
				this.parent.put(parent2, parent1);
				this.size.put(parent1, size1 + size2);
			}
		}
	}
}
