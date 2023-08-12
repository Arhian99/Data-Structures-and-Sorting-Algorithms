package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class which defines a custom weighted graph called ArhianWeightedGraph by extending ArhianUnweightedGraph
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianWeightedGraph<V> extends ArhianUnweightedGraph<V> {
	/**
	 * No-arg constructor constructs empty weighted graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianWeightedGraph() {
	}
	
	/**
	 * Constructs weighted graph from vertices and edges in arrays
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianWeightedGraph(V[] vertices, int[][]edges) {
		createWeightedGraph(Arrays.asList(vertices), edges);
	}
	
	/**
	 * Constructs weighted graph from vertices 0,1,2,3... and edges in arrays
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianWeightedGraph(int[][]edges, int numberOfVertices) {
		// list stores vertices
		List<V> vertices = new ArrayList<>();
		
		// initializes each entry in vertices list
		for(int i=0; i<numberOfVertices; i++) {
			vertices.add((V)(Integer.valueOf(i)));
		}
		createWeightedGraph(vertices, edges);
	}
	
	/**
	 * Constructs weighted graph from vertices and edges in list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianWeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
		createWeightedGraph(vertices, edges);
	}
	
	/**
	 * Constructs weighted graph from vertices 0,1,2,.. and edges in list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianWeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
		//stores vertices
		List<V> vertices = new ArrayList<>();
		
		// initializes each entry in vertices list
		for(int i=0; i<numberOfVertices; i++) {
			vertices.add((V)(Integer.valueOf(i)));
		}
		createWeightedGraph(vertices, edges);
	}
	
	/**
	 * Creates Adjacency List from Edge arrays and sets vertices list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void createWeightedGraph(List<V> vertices, int[][] edges) {
		// sets vertices array list
		this.vertices=vertices;
		
		// creates adjacency edge list for each vertex
		for(int i=0; i<vertices.size(); i++) {
			neighbors.add(new ArrayList<Edge>());
		}
		
		// adds the edges to the appropriate adjacency lists
		for(int i=0; i<edges.length; i++) {
			// retrieves the adjacency list for current vertex and adds appropriate edge from edge array
			neighbors.get(edges[i][0]).add(new WeightedEdge(edges[i][0], edges[i][2], edges[i][2]));
		}
	}
	
	/**
	 * Creates Adjacency List from Edge list and sets vertices list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void createWeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
		// sets vertices array list
		this.vertices=vertices;
		
		// creates adjacency edge list for each vertex
		for(int i=0; i<vertices.size(); i++) {
			neighbors.add(new ArrayList<Edge>());
		}
		
		// iterates through all edges in edges list:
		for(WeightedEdge edge : edges) {
			// retrieves the adjacency list for current vertex and adds current edge from edge list
			neighbors.get(edge.u).add(edge);
		}
	}
	
	/**
	 * Returns the weight of the edge (u ,v)
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public double getWeight(int u, int v) throws Exception {
		for (Edge edge : neighbors.get(u)) {
			if(edge.v == v) {
				return ((WeightedEdge) edge).weight;
			}
		}
		throw new Exception("Edge does not exist.");
	}
	
	/**
	 * Prints edges with weight
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void printWeightedEdges() {
		// outer loop used to display/ print the current vertex
		for(int i=0; i< getSize(); i++) {
			System.out.print(getVertex(i)+ " ("+i+"): ");
			// prints edges and weight associated with current vertex
			for(Edge edge : neighbors.get(i)) {
				System.out.print("("+ edge.u+ ", "+ edge.v+ ", "+((WeightedEdge) edge).weight+ ") ");
			}
		}
	}
	
	/**
	 * Adds weighted edge. Returns true if successful
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean addEdge(int u, int v, double weight) {
		return addEdge(new WeightedEdge( u, v, weight));
	}
	
	/**
	 * Returns Minimum Spanning Tree rooted at vertex 0
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public MST getMinimumSpanningTree() {
		return getMinimumSpanningTree(0);
	}
	
	/**
	 * Returns Minimum Spanning Tree rooted at specifed vertex
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public MST getMinimumSpanningTree(int startingVertex) {
		// stores cost of adding each vertex to the tree. cost[v] stores the cost by adding v to the tree
		double[] cost = new double[getSize()];
		
		// initializes above array with initial cost
		for(int i=0; i<cost.length; i++) {
			cost[i] = Double.POSITIVE_INFINITY;// initial cost
		}
		// the source is already in the tree so cost is 0
		cost[startingVertex]=0;
		
		//stores the parent of each vertex
		int[] parent = new int[getSize()];
		
		// the root/ source of the tree has no parent
		parent[startingVertex] = -1;
		
		// stores total weight of the tree thus far
		double totalWeight=0;
		
		// stores the elements of the tree
		List<Integer> T = new ArrayList<>();
		
		//expand T
		while(T.size() < getSize()) {
			// find the smallest cost u in V - T
			 int u=-1; // vertex to be determined
			 double currentMinCost = Double.POSITIVE_INFINITY; // initial cost
			 
			 // iterate through all vertices
			 for(int i=0; i<getSize(); i++) {
				// if T does not contain the current vertex, and the cost of the current vertex is less than the current min cost:
				 if(!T.contains(i) && cost[i] < currentMinCost) {
					 // set the current cost as the currntMinCost
					 currentMinCost = cost[i];
					 // set current vertex as vertex with smallest cost
					 u=i;
				 }
			 }
			 // there are no more vertex with smaller costs
			 if(u==-1) break;
			 else {
				 // add u (the new vertex) to T
				 T.add(u);
			 }
			 // increment total weight (add cost[u] to the tree)
			 totalWeight+=cost[u];
			 
			 // iterate through all edges incident on current vertex u
			 for(Edge edge : neighbors.get(u)) {
				 // if there is a neighbor of u that has not been visited and whose cost is greater than the weight of current edge:
				 if(!T.contains(edge.v) && cost[edge.v] > ((WeightedEdge) edge).weight) {
					 // adjust the cost of v
					 cost[edge.v] = ((WeightedEdge) edge).weight;
					 // set u as the parent of v
					 parent[edge.v] =u;
				 }
			 }
		}
		return new MST(startingVertex, parent, T, totalWeight);
	}
	
	/**
	 * Inner MST class. A subclass of SearchTree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public class MST extends SearchTree {
		//stores total weight of all edges in the tree
		private double totalWeight;
		
		// cosntructor
		public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight) {
			// calls searchTree cosntructor
			super(root, parent, searchOrder);
			this.totalWeight = totalWeight;
		}
		
		public double getTotalWeight() {
			return totalWeight;
		}
	}
	
	/**
	 * Find single-source shortest paths
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ShortestPathTree getShortestPath(int sourceVertex) {
		// stores cost of adding each vertex to the tree. cost[v] stores the cost by adding v to the tree
		double[] cost = new double[getSize()];
		
		// initializes above array with initial cost
		for(int i=0; i<cost.length; i++) {
			cost[i] = Double.POSITIVE_INFINITY;// initial cost set to infinity
		}
		// the source is already in the tree so cost is 0
		cost[sourceVertex]=0;
		
		//stores the parent of each vertex. parent[v] stores the previous vertex of v in the path
		int[] parent = new int[getSize()];
		
		// the root/ source of the tree has no parent
		parent[sourceVertex] = -1;
		
		
		// stores the elements in the path so far
		List<Integer> T = new ArrayList<>();
		
		//fills T
		while(T.size() < getSize()) {
			// find the smallest cost u in V - T
			 int u=-1; // vertex to be determined
			 double currentMinCost = Double.POSITIVE_INFINITY; // initial cost
			 
			 // iterate through all vertices
			 for(int i=0; i<getSize(); i++) {
				// if T does not contain the current vertex, and the cost of the current vertex is less than the current min cost:
				 if(!T.contains(i) && cost[i] < currentMinCost) {
					 // set the current cost as the currntMinCost
					 currentMinCost = cost[i];
					 // set current vertex as vertex with smallest cost
					 u=i;
				 }
			 }
			 // there are no more vertex with smaller costs
			 if(u==-1) break;
			 else {
				 // add u (the new vertex) to T
				 T.add(u);
			 }
			 
			 // iterate through all edges incident on current vertex u
			 for(Edge edge : neighbors.get(u)) {
				 // if there is a neighbor of u that has not been visited and whose cost is greater than the weight of current edge:
				 if(!T.contains(edge.v) && cost[edge.v] > ((WeightedEdge) edge).weight) {
					 // adjust the cost of v
					 cost[edge.v] = cost[u] + ((WeightedEdge) edge).weight;
					 // set u as the parent of v
					 parent[edge.v] =u;
				 }
			 }
		}
		return new ShortestPathTree(sourceVertex, parent, T, cost);
	}
	
	/**
	 * ShortestPathTree is an inner class of WeightedGraph. Extends SearchTree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public class ShortestPathTree extends SearchTree {
		//stores cost of all vertices in the tree. cost[v] stores the cost from v to source
		private double[] cost;
		
		// Construct a path
		public ShortestPathTree(int source, int[] parent, List<Integer> searchOrder, double[] cost) {
			// calls searchTree constructor
			super(source, parent, searchOrder);
			this.cost = cost;
		}
		
		// returns specific cost
		public double getcost(int v) {
			return cost[v];
		}
		
		// prints paths from all vertices to the source
		public void printAllPaths() {
			System.out.println("All shortest paths from "+ vertices.get(getRoot())+ " are: ");
			for(int i=0; i<cost.length; i++) {
				printPath(i); // print path from i to the source
				System.out.println("(cost: "+ cost[i]+")"); // print the costa
			}
		}
	}
}
