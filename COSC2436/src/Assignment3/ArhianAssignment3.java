package Assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Assignment3.ArhianBinarySearchTree.TreeNode;
import Assignment3.ArhianGraph.WeightedEdge;
import Assignment3.ArhianUnweightedGraph.SearchTree;
import Assignment3.ArhianWeightedGraph.MST;
import Assignment3.ArhianWeightedGraph.ShortestPathTree;


public class ArhianAssignment3 {
	public static void main(String[] args) throws Exception {
		// defines scanner for input stream
		Scanner input=null;
		
		try {
			// creates scanner input from the specified File instance 
			input = new Scanner(new File("Assignment3Data.txt"));
			
			// in case the specified file is not found
		} catch(FileNotFoundException e) {
			System.out.println("ERROR: File does not exist.");
			System.out.println(e.getMessage());
		}
		
		// stores list of cities from data read from the file
		ArrayList<String> city1 = new ArrayList<>();
		ArrayList<String> city2 = new ArrayList<>();
		ArrayList<Double> distance = new ArrayList<>();
		
		// while the file has more data
		while(input.hasNext()) {
			// takes the next line from the file
			String line = input.nextLine();
			// splits the line into tokens, delimited by the tab character
			String[] tokens = line.split("\t");
			
			// adds the tokens to their respective storage
			city1.add(tokens[0]);
			city2.add(tokens[1]);
			distance.add(Double.parseDouble(tokens[2]));
		}
		
		// turns distance array list into array to be taken by BST and AVL constructors
		Double[] distArray = distance.toArray(new Double[distance.size()]);
		
		// creates instance of ArhianBinarySearchTree from distArray
		ArhianBinarySearchTree<Double> MyArhianBST = new ArhianBinarySearchTree<>(distArray);
		
		// creates instance of ArhianAVLTree from distArray
		ArhianAVLTree<Double> MyArhianAVLT = new ArhianAVLTree<>(distArray);
		
		// creates instance of ArhianHashSet with city1.size() as capacity
		ArhianHashSet<String> MyArhianHS = new ArhianHashSet<>(city1.size());
		// adds all elements in city1 to MyArhianHS
		MyArhianHS.addAll(city1);
		
		// creates instance of ArhianHashMap with city2.size() as capacity
		ArhianHashMap<String, Double> MyArhianHM = new ArhianHashMap<>(city2.size());
		// adds the entries to MyArhianHM from city 2 and distance
		for(int i=0; i<city2.size(); i++) {
			MyArhianHM.put(city2.get(i), distance.get(i));
		}
		
		// Reassigns makes copy of city1 array list and stores it in vertices
		ArrayList<String> tempVerticesList = (ArrayList<String>) city1.clone();
		// combines both list onto vertices. Now vertices contains all the cities (including repeats)
		tempVerticesList.addAll(city2);
		// creates set from the verticesList to eliminate repeats
		Set<String> verticesSet = new HashSet<>(tempVerticesList);
		// re-creates vertices list, not including repeats
		List<String> finalVerticesList = new ArrayList<String>(verticesSet);
		// creates empty edges array list
		List<WeightedEdge> edges = new ArrayList<>();
		// instantiates graph
		ArhianWeightedGraph<String> MyArhianWG = new ArhianWeightedGraph<>(finalVerticesList , edges);
		// adds all the edges 
		for(int i=0; i<city1.size(); i++) {
			// gets the indices where the first two cities are stored in the inner vertices list inside the graph
			int city1Index = MyArhianWG.getIndex(city1.get(i));
			int city2Index = MyArhianWG.getIndex(city2.get(i));
			
			// creates edge between the two cities with the distance as the weight
			MyArhianWG.addEdge(city1Index, city2Index, distance.get(i));
		}
		
		System.out.println("-------------------------BST Method Demonstrations: -------------------------------");
		System.out.println("inorder(): ");
		MyArhianBST.inorder();
		System.out.println("\n");
		
		System.out.println("preorder(): ");
		MyArhianBST.preorder();
		System.out.println("\n");
		
		System.out.println("postorder(): ");
		MyArhianBST.postorder();
		System.out.println("\n");
		
		System.out.println("search(268): ");
		if(MyArhianBST.search(268.0)) {
			System.out.println("268.0 is present in MyArhianBST \n");
		};
		
		System.out.println("insert(40.0): ");
		MyArhianBST.insert(40.0);
		MyArhianBST.inorder();
		System.out.println("\n");
		
		System.out.println("delete(40.0): ");
		MyArhianBST.delete(40.0);
		MyArhianBST.inorder();
		System.out.println("\n");
		
		System.out.println("getSize(): ");
		System.out.print("The number of nodes in the tree is: "+MyArhianBST.getSize());
		System.out.println("\n");
		
		System.out.println("path(212.0): ");
		ArrayList<TreeNode<Double>> path = MyArhianBST.path(212.0);
		for(TreeNode<Double> node : path) {
			System.out.print(node.element+" ");
		}
		System.out.println("\n");
		
		System.out.println("getRoot(): ");
		System.out.print("The root of the tree is: "+MyArhianBST.getRoot().element);
		System.out.println("\n");
		
		System.out.println("iterator(): ");
		Iterator<Double> bstIterator = MyArhianBST.iterator();
		for(int i=0; i<5; i++) {
			if(bstIterator.hasNext()) {
				System.out.print(bstIterator.next()+" ");
			}
		}
		bstIterator.remove();
		System.out.println("\n");
		System.out.println("bstIterator.remove() which should remove 212.0: ");
		MyArhianBST.inorder();
		System.out.println("\n");
		
		System.out.println("clear(): ");
		MyArhianBST.clear();
		MyArhianBST.inorder();
		System.out.println("\n");
		System.out.println();
		
		System.out.println("-------------------------AVL Tree Method Demonstrations: -------------------------------");
		System.out.println("inorder(): ");
		MyArhianAVLT.inorder();
		System.out.println("\n");
		
		System.out.println("preorder(): ");
		MyArhianAVLT.preorder();
		System.out.println("\n");
		
		System.out.println("postorder(): ");
		MyArhianAVLT.postorder();
		System.out.println("\n");
		
		System.out.println("search(268): ");
		if(MyArhianAVLT.search(268.0)) {
			System.out.println("268.0 is present in MyArhianAVLT \n");
		};
		
		System.out.println("insert(40.0): ");
		MyArhianAVLT.insert(40.0);
		MyArhianAVLT.inorder();
		System.out.println("\n");
		
		System.out.println("delete(40.0): ");
		MyArhianAVLT.delete(40.0);
		MyArhianAVLT.inorder();
		System.out.println("\n");
		
		System.out.println("getSize(): ");
		System.out.print("The number of nodes in the tree is: "+MyArhianAVLT.getSize());
		System.out.println("\n");
		
		System.out.println("path(212.0): ");
		ArrayList<TreeNode<Double>> AVLpath = MyArhianAVLT.path(212.0);
		for(TreeNode<Double> node : AVLpath) {
			System.out.print(node.element+" ");
		}
		System.out.println("\n");
		
		System.out.println("getRoot(): ");
		System.out.print("The root of the tree is: "+MyArhianAVLT.getRoot().element);
		System.out.println("\n");
		
		System.out.println("iterator(): ");
		Iterator<Double> AVLIterator = MyArhianAVLT.iterator();
		for(int i=0; i<5; i++) {
			if(AVLIterator.hasNext()) {
				System.out.print(AVLIterator.next()+" ");
			}
		}
		AVLIterator.remove();
		System.out.println("\n");
		System.out.println("AVLIterator.remove() which should remove 212.0: ");
		MyArhianAVLT.inorder();
		System.out.println("\n");
		
		System.out.println("clear(): ");
		MyArhianAVLT.clear();
		MyArhianAVLT.inorder();
		System.out.println("\n");
		System.out.println();
		
		System.out.println("-------------------------HashSet Method Demonstrations: -------------------------------");
		System.out.println("toString(): ");
		System.out.println(MyArhianHS.toString());
		System.out.println();
		
		System.out.println("size(): ");
		System.out.print("The number of elements in this set is: "+MyArhianHS.size());
		System.out.println("\n");
		
		System.out.println("contains(Orlando): ");
		if(MyArhianHS.contains("Orlando")) {
			System.out.println("MyArhianHS contains Orlando. \n");
		};
		
		System.out.println("add(AAAAAAAAA): look after Minneapolis");
		MyArhianHS.add("AAAAAAAAA");
		System.out.println(MyArhianHS.toString());
		System.out.println();
		
		System.out.println("remove(AAAAAAAAA): ");
		MyArhianHS.remove("AAAAAAAAA");
		System.out.println(MyArhianHS.toString());
		System.out.println();
		
		System.out.println("iterator(): ");
		Iterator<String> HSIterator = MyArhianHS.iterator();
		for(int i=0; i<5; i++) {
			if(HSIterator.hasNext()) {
				System.out.print(HSIterator.next()+" ");
			}
		}
		HSIterator.remove();
		System.out.println("\n");
		System.out.println("HSIterator.remove() which should remove Orlando ");
		System.out.println(MyArhianHS.toString());
		System.out.println();
		
		System.out.println("addAll():");
		System.out.println("See line 55 \n");
		
		System.out.println("containsAll(Seattle, Omaha): ");
		String[] myArr = {"Seattle", "Omaha"};
		List<String> containsList = Arrays.asList(myArr);
		if(MyArhianHS.containsAll(containsList)) {
			System.out.println("MyArhianHS contains Seattle and Omaha. \n");
		};
		
		System.out.println("retainAll(Seattle, Omaha, San Diego, Austin): ");
		String[] myArr2 = {"Seattle", "Omaha", "San Diego", "Austin"};
		List<String> retainsList = Arrays.asList(myArr2);
		MyArhianHS.retainAll(retainsList);
		System.out.println(MyArhianHS.toString());
		System.out.println();
		
		System.out.println("removeAll(Seattle, Omaha, San Diego): ");
		String[] myArr3 = {"Seattle", "Omaha", "San Diego"};
		List<String> removeList = Arrays.asList(myArr3);
		MyArhianHS.removeAll(removeList);
		System.out.println(MyArhianHS.toString());
		System.out.println();
		
		System.out.println("isEmpty() and clear()");
		if(!MyArhianHS.isEmpty()) {
			MyArhianHS.clear();
		}
		System.out.println(MyArhianHS.toString());
		System.out.println("\n");
		
		System.out.println("-------------------------HashMap Method Demonstrations: -------------------------------");
		System.out.println("toString(): ");
		System.out.println(MyArhianHM.toString());
		System.out.println();
		
		System.out.println("containsKey(Orlando): ");
		if(MyArhianHM.containsKey("Orlando")) {
			System.out.println("MyArhianHM contains the key Orlando. \n");
		};
		
		System.out.println("containsValue(981.0): ");
		if(MyArhianHM.containsValue(981.0)) {
			System.out.println("MyArhianHM contains the value 981.0 \n");
		};
		
		System.out.println("get(Wichita): ");
		System.out.println("The value associated with the key 'Whichita' is: "+MyArhianHM.get("Wichita"));
		
		System.out.println("size(): ");
		System.out.print("The number of entries in this map is: "+MyArhianHM.size());
		System.out.println("\n");
		
		System.out.println("remove(Albuquerque): ");
		MyArhianHM.remove("Albuquerque");
		System.out.println(MyArhianHM.toString());
		System.out.println();
		
		System.out.println("put():");
		System.out.println("See line 62 \n");
		
		System.out.println("entrySet(): ");
		Set<ArhianMap.Entry<String, Double>> entrySet = MyArhianHM.entrySet();
		for(ArhianMap.Entry<String, Double> entry : entrySet) {
			System.out.print(entry.toString()+" ");
		}
		System.out.println("\n");
		
		System.out.println("keySet(): ");
		Set<String> keySet = MyArhianHM.keySet();
		for(String key : keySet) {
			System.out.print(key+" ");
		}
		System.out.println("\n");
		
		System.out.println("values(): ");
		Set<Double> valueSet = MyArhianHM.values();
		for(Double value : valueSet) {
			System.out.print(value+" ");
		}
		System.out.println("\n");
		
		System.out.println("isEmpty() and clear()");
		if(!MyArhianHM.isEmpty()) {
			MyArhianHM.clear();
		}
		System.out.println(MyArhianHM.toString());
		System.out.println("\n");
		
		System.out.println("-------------------------WeightedGraph Method Demonstrations: -------------------------------");
		System.out.println("getIndex():");
		System.out.println("See line 77 and 78 \n");
		
		System.out.println("addEdge():");
		System.out.println("See line 81 \n");
		
		System.out.println("getSize(): ");
		System.out.print("The number of vertices in this graph is: "+MyArhianWG.getSize());
		System.out.println("\n");
		
		System.out.println("getVertices(): ");
		List<String> verticesDemo = MyArhianWG.getVertices();
		for(String vertex: verticesDemo) {
			System.out.print(vertex+" ");
		}
		System.out.println("\n");
		
		System.out.println("getVertex(3): ");
		System.out.println(MyArhianWG.getVertex(3));
		System.out.println();
		
		System.out.println("getNeighbors(3): ");
		List<Integer> neighborsOfAustin = MyArhianWG.getNeighbors(3);
		for(Integer neighbor: neighborsOfAustin) {
			System.out.print(MyArhianWG.getVertex(neighbor)+" ");
		}
		System.out.println("\n");
		
		System.out.println("getDegree(2): ");
		System.out.println("The degree of "+MyArhianWG.getVertex(2)+" is: "+MyArhianWG.getDegree(2));
		System.out.println();
		
		System.out.println("addVertex(AAAAAA): look at the end");
		MyArhianWG.addVertex("AAAAAA");
		verticesDemo = MyArhianWG.getVertices();
		for(String vertex: verticesDemo) {
			System.out.print(vertex+" ");
		}
		System.out.println("\n");
		
		System.out.println("remove(AAAAAA): look at the end");
		MyArhianWG.remove("AAAAAA");
		verticesDemo = MyArhianWG.getVertices();
		for(String vertex: verticesDemo) {
			System.out.print(vertex+" ");
		}
		System.out.println("\n");
		
		System.out.println("printWeightedEdges(): ");
		MyArhianWG.printWeightedEdges();
		System.out.println("\n");
		
		System.out.println("printEdges(): ");
		MyArhianWG.printEdges();
		System.out.println();
		
		System.out.println("getWeight(): ");
		System.out.println("Weight from Austin to Fort Worth is: "+ MyArhianWG.getWeight(MyArhianWG.getIndex("Austin"), MyArhianWG.getIndex("Fort Worth")));
		System.out.println();
		
		System.out.println("dfs()");
		SearchTree dfsTree = MyArhianWG.dfs(4);
		dfsTree.printTree();
		System.out.println();
		
		System.out.println("getMinimumSpanningTree()");
		MST msTree = MyArhianWG.getMinimumSpanningTree(5);
		msTree.printTree();
		System.out.println();
		
		System.out.println("getTotalWeight()");
		System.out.println("The total weight for the MST is: "+ msTree.getTotalWeight());
		System.out.println();
		
		System.out.println("bfs()");
		SearchTree bfsTree = MyArhianWG.bfs(7);
		bfsTree.printTree();
		System.out.println();
		
		System.out.println("getShortestPath()");
		ShortestPathTree spTree = MyArhianWG.getShortestPath(3);
		spTree.printTree();
		System.out.println();

		System.out.println("clear(): ");
		MyArhianWG.clear();
		verticesDemo = MyArhianWG.getVertices();
		for(String vertex: verticesDemo) {
			System.out.print(vertex+" ");
		}
		System.out.println("\n");
	}
}
