package strings;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class implements Huffman's algorithm, which - given a set of symbols and their weights
 * (usually probabilities) - returns a prefix-free code (i.e., a set of codewords) with minimum
 * expected codeword length. This type of code is commonly used for lossless data compression.
 * 
 * The algorithm iterates over the input symbols, merging the two of lowest weight until only one
 * symbol remains. This symbol represents the root of a binary tree, where the leaves correspond to
 * the input symbols and every path from the root to a leaf represents the codeword of this leaf:
 * left child pointers add 0 to the codeword and right child pointers add 1. To select the minimum-weight
 * symbols in each iteration, the algorithm uses a priority queue.
 * 
 * @author Mario Cervera
 */
public class HuffmanCoding {

	public static List<CodeWord> huffman(List<Symbol> symbols) {
		
		// Initialize priority queue
		
		PriorityQueue<SymbolNode> pQueue = new PriorityQueue<SymbolNode>();
		
		for(Symbol symbol : symbols) {
			pQueue.add(new SymbolNode(symbol));
		}
		
		// Calculate Huffman binary tree
		
		while(pQueue.size() > 1) {
			
			// Extract the two (lowest weight) symbols from the priority queue
			
			SymbolNode symbol1 = pQueue.poll();
			SymbolNode symbol2 = pQueue.poll();
			
			// Add up their weights and combine the labels
			
			long weightSum = symbol1.getSymbol().getWeight() + symbol2.getSymbol().getWeight();
			String combinedLabel = symbol1.getSymbol().getSymbol() + symbol2.getSymbol().getSymbol(); 
			
			// Add a combined symbol to the queue
			
			Symbol combinedSymbol = new Symbol(combinedLabel, weightSum);
			pQueue.add(new SymbolNode(combinedSymbol, symbol1, symbol2));
		}
		
		// Return codewords
		
		SymbolNode rootSymbol = pQueue.poll();
		
		return getCodeWords(rootSymbol);
	}
	
	/*
	 * This methods calculates the codewords given the root symbol of the binary tree
	 */
	private static List<CodeWord> getCodeWords(SymbolNode rootSymbol) {
		
		if(rootSymbol.getLeftChild() == null && rootSymbol.getRightChild() == null) {
			
			// Base case: leaf node --> Create a new empty codeword
			
			List<CodeWord> codewords = new ArrayList<CodeWord>();
			CodeWord cd = new CodeWord(rootSymbol.getSymbol().getSymbol(), "");
			codewords.add(cd);
			return codewords;
		}
		
		// Recursively obtain the codewords of the left and right subtrees
		
		List<CodeWord> leftCodeWords = getCodeWords(rootSymbol.getLeftChild());
		List<CodeWord> rightCodeWords = getCodeWords(rootSymbol.getRightChild());
		
		// Add a "0" to the codewords that are obtained from the left subtree
		
		for(CodeWord cd: leftCodeWords) {
			String newPattern = "0" + cd.getBitPattern();
			cd.setBitPattern(newPattern);
		}
		
		// Add a "1" to the codewords that are obtained from the right subtree
		
		for(CodeWord cd: rightCodeWords) {
			String newPattern = "1" + cd.getBitPattern();
			cd.setBitPattern(newPattern);
		}
		
		// Combine the codewords obtained from both subtrees
		
		List<CodeWord> groupedCodewords = new ArrayList<CodeWord>();
		groupedCodewords.addAll(leftCodeWords);
		groupedCodewords.addAll(rightCodeWords);
		
		return groupedCodewords;
	}
}

/**
 * A symbol and its associated weight
 * 
 * @author Mario Cervera
 */
class Symbol {

	/** Fields*/
	
	private String symbol;
	private long weight;
	
	/** Constructor */
	
	public Symbol(String s, long w) {
		
		this.symbol = s;
		this.weight = w;
	}
	
	/** Getter methods */
	
	public String getSymbol() {
		return symbol;
	}
	
	public long getWeight() {
		return weight;
	}
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "(" + this.symbol + ", " + this.weight + ")";
	}
}

/**
 * A symbol and the codeword that results from the execution of Huffman's algorithm
 * 
 * @author Mario Cervera
 */
class CodeWord {

	/** Fields*/
	
	private String symbol;
	private String bitPattern;
	
	/** Constructor */
	
	public CodeWord(String s, String bp) {
		
		this.symbol = s;
		this.bitPattern = bp;
	}
	
	/** Getter methods */
	
	public String getSymbol() {
		return symbol;
	}
	
	public String getBitPattern() {
		return bitPattern;
	}
	
	/** Setter methods */
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setBitPattern(String bitPattern) {
		this.bitPattern = bitPattern;
	}
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "(" + this.symbol + ", " + this.bitPattern + ")";
	}
}

/**
 * Symbol Nodes are the nodes of the binary tree that results from the execution of Huffman's algorithm.
 * 
 * @author Mario Cervera
 */
class SymbolNode implements Comparable<SymbolNode> {

	/** Fields*/
	
	private Symbol symbol;
	private SymbolNode leftChild;
	private SymbolNode rightChild;
	
	/** Constructors */
	
	public SymbolNode(Symbol s, SymbolNode lc, SymbolNode rc) {
		
		this.symbol = s;
		this.leftChild = lc;
		this.rightChild = rc;
	}
	
	public SymbolNode(Symbol s) {
		
		this(s, null, null);
	}
	
	/** Getter methods */
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	public SymbolNode getLeftChild() {
		return leftChild;
	}
	
	public SymbolNode getRightChild() {
		return rightChild;
	}
	
	/**
	 * Method that establishes an ordering upon symbol nodes. This ordering is based on the
	 * weights of the symbols that the nodes represent.
	 */
	@Override
	public int compareTo(SymbolNode sn) {
		
		if(this.getSymbol().getWeight() < sn.getSymbol().getWeight()) {
			return -1;
		}
		else if(this.getSymbol().getWeight() > sn.getSymbol().getWeight()) {
			return 1;
		}
		
		return 0;
	}
}
