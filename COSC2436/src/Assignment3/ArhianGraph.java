package Assignment3;

import java.util.List;

/**
 * This interface defines a custom Graph interface called ArhianGraph. 
 * It defines operations common to all Graphs in this use case,
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public interface ArhianGraph<V> {
	/**
	 *  Returns the number of vertices in the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int getSize();
	
	/**
	 * Returns a list with all the vertices in the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public List<V> getVertices();
	
	/**
	 * Returns the object for the specified vertex index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public V getVertex(int index);
	
	/**
	 * Returns the index for the specified vertex object
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int getIndex(V vertex);
	
	/**
	 * Returns a list with the indexes of the neighbors of the vertex with the specified index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public List<Integer> getNeighbors(int index);
	
	/**
	 * Returns the degree for a vertex with the specified index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int getDegree(int v);
	
	/**
	 * Prints the edges
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void printEdges();
	
	/**
	 * Clears the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void clear();
	
	/**
	 * Adds a vertex to the graph. Returns true if the vertex was added successfully
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean addVertex(V vertex);
	
	/**
	 * Adds an edge to the graph between vertices with specified indices (u and v).
	 * Returns true if the edge was added successfully
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean addEdge(int u, int v);
	
	/**
	 * Adds a specified edge to the graph.
	 * Returns true if the edge was added successfully
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean addEdge(Edge e);
	
	/**
	 * Removes vertex v from the graph, returns true if successful
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean remove(V v);
	
	/**
	 * Removes an edge (u, v) from the graph, returns true if successful
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean remove(int u, int v);
	
	/**
	 * Obtains depth-first search tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianUnweightedGraph<V>.SearchTree dfs(int v);
	
	/**
	 * Obtains breadth-first search tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianUnweightedGraph<V>.SearchTree bfs(int v);

	/**
	 * Defines inner class for an Edge in the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public static class Edge {
		int u;
		int v;
		
		public Edge(int u, int v) {
			this.u=u;
			this.v=v;
		}
		
		public boolean equals(Object o) {
			return u == ((Edge)o).u && v== ((Edge)o).v;
		}
	}
	
	/**
	 * Defines inner class for a WeightedEdge in the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public static class WeightedEdge extends Edge implements Comparable<WeightedEdge>{
		public double weight;
		
		public WeightedEdge(int u, int v, double weight) {
			super(u, v);
			this.weight=weight;
		}
		
		public double getWeight() {
			return weight;
		}
		
		// compares two edges on weights
		@Override
		public int compareTo(WeightedEdge edge) {
			if(weight>edge.weight) {
				return 1;
			} else if(weight==edge.weight) {
				return 0;
			} else {
				return -1;
			}
		}
	}
}
