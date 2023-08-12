package Assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class which defines a custom unweighed graph called ArhianUnweightedGraph by extending ArhianGraph
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianUnweightedGraph<V> implements ArhianGraph<V> {
	// array list that stores vertices
	protected List<V> vertices = new ArrayList<>();;
	/*
	 * Adjacency Edge Lists. Each entry in neighbors stores a list of edge objects 
	 * that are adjacent to the vertex associated with that index.
	 */
	protected List<List<Edge>> neighbors = new ArrayList<>();
	
	/**
	 *No-arg constructor. Constructs empty graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	protected ArhianUnweightedGraph() {
		vertices= new ArrayList<>();
	}
	
	/**
	 * Constructs a graph from vertices and edges stored in arrays
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	protected ArhianUnweightedGraph(V[] vertices, int[][] edges) {
		// adds all vertices to vertices list by calling the addVertex method.
		for(int i=0; i<vertices.length; i++) {
			addVertex(vertices[i]);
		}
		// creates Edge adjacency list by calling the method below and passing in the edges 2D array
		createAdjacencyLists(edges, vertices.length);
	}
	
	/**
	 * Constructs a graph from vertices and edges stored in a list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	protected ArhianUnweightedGraph(List<V> vertices, List<Edge> edges) {
		// adds all vertices to vertices list by calling the addVertex method.
		for(int i=0; i<vertices.size(); i++) {
			addVertex(vertices.get(i));
		}
		// creates Edge adjacency list by calling the method below and passing in the edges list
		createAdjacencyLists(edges, vertices.size());
	}
	
	/**
	 * Constructs a graph for integer vertices 0,1,2 and edge list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	protected ArhianUnweightedGraph(List<Edge> edges, int numberOfVertices) {
		// adds all vertices to vertices list by calling the addVertex method.
		for(int i=0; i<numberOfVertices; i++) {
			addVertex((V)(Integer.valueOf(i)));
		}
		// creates Edge adjacency list by calling the method below and passing in the edges list
		createAdjacencyLists(edges, numberOfVertices);
	}
	
	/**
	 * Constructs a graph for integer vertices 0,1,2 and edge array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	protected ArhianUnweightedGraph(int[][] edges, int numberOfVertices) {
		// adds all vertices to vertices list by calling the addVertex method.
		for(int i=0; i<numberOfVertices; i++) {
			addVertex((V)(Integer.valueOf(i)));
		}
		// creates Edge adjacency list by calling the method below and passing in the edges list
		createAdjacencyLists(edges, numberOfVertices);
	}
	
	/**
	 * Creates Adjacency list for each vertex edges stored in an array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
		// iterates through the specified edges array and adds the edges to edges adjacency list accordingly
		for(int i=0; i<edges.length; i++) {
			addEdge(edges[i][0], edges[i][1]);
		}
	}
	
	/**
	 * Creates Adjacency list for each vertex edges stored in a list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
		// iterates through edges list and adds each edge to edges adjacency list
		for(Edge edge : edges) {
			addEdge(edge.u, edge.v);
		}
	}

	/**
	 *  Returns the number of vertices in the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public int getSize() {
		return vertices.size();
	}

	/**
	 * Returns a list with all the vertices in the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public List<V> getVertices() {
		return vertices;
	}

	/**
	 * Returns the object for the specified vertex index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public V getVertex(int index) {
		return vertices.get(index);
	}

	/**
	 * Returns the index for the specified vertex object
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public int getIndex(V vertex) {
		return vertices.indexOf(vertex);
	}

	/**
	 * Returns a list with the indexes of the neighbors of the vertex with the specified index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public List<Integer> getNeighbors(int index) {
		// stores the neighbors
		List<Integer> result = new ArrayList<>();
		// Iterates through list of edge objects that are connected to vertex with specified index:
		for(Edge edge : neighbors.get(index)) {
			// adds the vertex v (which is on the opposite side of the edge from the vertex with the specified index)
			// by definition this makes the two vertices neighbors
			result.add(edge.v);
		}
		return result;
	}

	/**
	 * Returns the degree for a vertex with the specified index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public int getDegree(int v) {
		// the number of edges incident on a vertex is by definition is equal to its degree
		return neighbors.get(v).size();
	}

	/**
	 * Prints the edges
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public void printEdges() {
	// iterates through neighbors list
		for(int u=0;u<neighbors.size(); u++) {
			// prints vertex with index u and u
			System.out.print(getVertex(u) + " (" + u + "): ");
			// iterates through all edges incident on the vertex printed above (the vertex with index u)
			for(Edge e : neighbors.get(u)) {
				//prints the two vertices associated with current edge
				System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
			}
			System.out.println();
		}
		
	}

	/**
	 * Clears the graph
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public void clear() {
		vertices.clear();
		neighbors.clear();
		
	}

	/**
	 * Adds a vertex to the graph. Returns true if the vertex was added successfully
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean addVertex(V vertex) {
		// if vertex is not already in the graph:
		if(!vertices.contains(vertex)) {
			// add vertex to the vertices list
			vertices.add(vertex);
			// add new entry (new arrayList) to the neighbors list
			neighbors.add(new ArrayList<Edge>());
			// vertex successfully added
			return true;
		}
		// if vertex is already in the graph: return false
		return false;
	}
	
	/**
	 * Adds a specified edge to the graph.
	 * Returns true if the edge was added successfully
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean addEdge(Edge e) {
		// check bound of field u in Edge e (check if there are vertices for edge e)
		if(e.u<0 || e.u>getSize() - 1) {
			throw new IllegalArgumentException("No such index: "+e.u);
		}
		// check bound of field v in Edge e (check if there are vertices for edge e)
		if(e.v<0 || e.v>getSize() - 1) {
			throw new IllegalArgumentException("No such index: "+e.v);
		}
		// If the edge is NOT already in the graph:
		if(!neighbors.get(e.u).contains(e)) {
			// add the edge to the graph
			neighbors.get(e.u).add(e);
			// edge added successfully, return true
			return true;
		}
		// if edge is already in the graph. Cannot add duplicates. Return false
		return false;
	}

	/**
	 * Adds an edge to the graph between vertices with specified indices (u and v).
	 * Returns true if the edge was added successfully
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean addEdge(int u, int v) {
		return addEdge(new Edge(u, v));
	}

	/**
	 * Removes vertex v from the graph, returns true if successful
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean remove(V v) {
		// if the vertex is NOT in the graph, return false
		if(!vertices.contains(v)) {
			return false;
		} else {
			// gets all the edges incident on the specified vertex v
			List<Edge> edges = neighbors.get(getIndex(v));
			// iterates through all edges incident on vertex v
			for(Edge edge : edges) {
				// removes the edge from the adjacency list of the vertices connected to vertex v
				// if current edge is in vertex v adjacency list: 
				if(edge.u == getIndex(v)) {
					// remove from connected vertex adjacency list
					neighbors.get(edge.v).remove(edge);
				} else {
					// removes current edge from connected vertex adjacency list
					neighbors.get(edge.u).remove(edge);
				}
			}
			// removes all edges from vertex v adjacency edge list
			neighbors.remove(getIndex(v));
			// removes the vertex itself 
			vertices.remove(getIndex(v));
			return true;
		}
	}
	
	/**
	 * Removes an edge (u, v) from the graph, returns true if successful
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean remove(int u, int v) {
		// even if this is a new edge, it equals the edge to be removed because the
		// equals method for edge class says two edges are equal if their u and v fields are equal. 
		Edge edge = new Edge(u, v);
		
		//if the edge is NOT in the graph
		if(!neighbors.get(u).contains(edge)) {
			return false;
		}
		
		// removes the edge from the adjacency list of both vertices 
		neighbors.get(u).remove(edge);
		neighbors.get(v).remove(edge);
		
		return true;
	}
	
	
	/**
	 * Obtain a DFS tree starting from vertex v
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public SearchTree dfs(int v) {
		// DFS first visits a vertex, then visits all other vertices adjacent to that vertex. Keeping track of vertices already visited.
		/*
		 *  search starts at vertex v, then visits all unvisited neighbors of v, u. If v has no unvisited neighbors, then we visit all unvisited
		 *  neighbors of the vertex that we visited before v. 
		 */
		// this list stores the order in which vertices were visited
		List<Integer> searchOrder = new ArrayList<>();
		
		// stores the parent of each vertex. For each index i stores the parent of the vertex with index i.
		int[] parent = new int[vertices.size()];
		
		// initializes the parent array with -1. Initially, no vertices have parents
		for(int i=0; i< parent.length; i++) {
			parent[i] = -1;
		}
		// denotes whether a vertex has been visited (indices in this array correspond to indices of vertices)
		boolean[] isVisited = new boolean[vertices.size()];
		
		//recursive call
		dfs(v, parent, searchOrder, isVisited);
		
		return new SearchTree(v, parent, searchOrder);
	}
	
	/**
	 * Recursive method for dfs search
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
		// the search starts from v, therefore add v to searchOrder array
		searchOrder.add(v);
		
		// v has now been visited
		isVisited[v]=true;
		
		// iterate through all edges incident on v
		for(Edge edge : neighbors.get(v)) {
			// w is a neighbor of v (a vertex on the other side of an edge from v)
			int w=edge.v;
			// if w has NOT been visited:
			if(!isVisited[w]) {
				// set v as the parent of w
				parent[w] = v;
				// repeat for w.
				dfs(w, parent, searchOrder, isVisited);
			}
		}
	}
	
	/**
	 * Obtain a BFS tree starting from vertex v
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public SearchTree bfs(int v) {
		/*
		 * First visits vertex v, then all vertices adjacent to v, the all vertices adjacent to those, so on,
		 * skipping the vertices that have already been visited.
		 */
		
		// stores the order in which vertices where visited
		List<Integer> searchOrder = new ArrayList<>();
		// stores the parent of each vertex in the search tree
		int[] parent = new int[vertices.size()];
		
		//initializes parent array with -1. Initially, no vertices have parents
		for(int i=0;i<parent.length;i++) {
			parent[i] = -1;
		}
		
		// stores vertices TO BE visited
		LinkedList<Integer> queue = new LinkedList<>();
		// stores whether a vertex has already been visited or not
		boolean[] isVisited = new boolean[vertices.size()];
		
		// adds current vertex to the queue 
		queue.offer(v);
		
		// marks current vertex as visited
		isVisited[v] = true;
		
		// while there are vertices to be visited:
		while(!queue.isEmpty()) {
			// grab the current vertex to be visited from the queue
			int u=queue.poll();
			// adds the current vertex to searchOrder (it has been visited)
			searchOrder.add(u);
			// iterates through all edges adjacent to the current vertex
			for(Edge e : neighbors.get(u)) {
				// gets the neighbor vertex to the current vertex
				int w=e.v;
				// checks if that neighbor has not been visited. if not:
				if(!isVisited[w]) {
					// adds the neighbor to the queue
					queue.offer(w);
					//sets the current vertex as the parent to its neighbor
					parent[w] = u;
					// sets that neighbor as visited
					isVisited[w] = true;
				}
			}
		}
		// create a search tree after queue is empty (we've visited all vertices)
		return new SearchTree(v, parent, searchOrder);
	}
	
	/**
	 * SearchTree Inner class
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public class SearchTree {
		// stores the root of the tree
		private int root;
		// stores the parent of each vertex
		private int[] parent;
		//stores the order in which vertices were visited
		private List<Integer> searchOrder;
		
		// constructor, sets the fields above accordingly
		public SearchTree(int root, int[] parent, List<Integer> searchOrder) {
			this.root=root;
			this.parent=parent;
			this.searchOrder=searchOrder;
		}
		
		// returns the root
		public int getRoot() {
			return root;
		}
		
		// returns the index of the parent of the vertex with specified node, v
		public int getParent(int v) {
			return parent[v];
		}
		
		// returns the search order
		public List<Integer> getSearchOrder(){
			return searchOrder;
		}
		
		// returns the number of vertices found
		public int getNumberOfVerticesFound() {
			return searchOrder.size();
		}
		
		// returns an list with the path from the vertex with the specified index to the root
		public List<V> getPath(int index){
			//stores the path
			ArrayList<V> path = new ArrayList<>();
			
			do {
				// adds the the current vertex to the list
				path.add(vertices.get(index));
				// gets the parent of the current vertex
				index=parent[index];
			} while (index != -1); // repeat until a vertex has no parents (you reached the root)
			
			// return list
			return path;
		}
		
		// prints a path from specified vertex to the root
		public void printPath(int index) {
			// stores the path
			List<V> path = getPath(index);
			
			// prints out each element in the above list
			System.out.print("A path from "+vertices.get(root)+" to "+ vertices.get(index)+ ": ");
			for(int i=path.size()-1; i>=0; i--) {
				System.out.print(path.get(i)+ " ");
			}
		}
		
		// Prints the entire searchTree
		public void printTree() {
			System.out.println("Root is: "+vertices.get(root));
			System.out.print("Edges: ");
			for(int i=0; i<parent.length; i++) {
				if(parent[i] != -1) {
					System.out.print("("+vertices.get(parent[i])+", "+vertices.get(i)+") ");
				}
			}
			System.out.println();
		}
	}
}
