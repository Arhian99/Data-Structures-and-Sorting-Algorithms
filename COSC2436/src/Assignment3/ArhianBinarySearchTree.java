package Assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Class which defines a custom Binary Search Tree called ArhianBinarySearchTree
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianBinarySearchTree<E> {
	// Stores reference to the root node of the this tree
	protected TreeNode<E> root;
	//stores current number of elements in the tree
	protected int size=0;
	//defines comparator used to place elements into the tree accordingly
	protected Comparator<E> c;
	
	/**
	 * This inner static class defines a node in this Tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public static class TreeNode<E> {
		//stores the data in the node
		protected E element;
		// stores references to the left subtree of this node
		protected TreeNode<E> left;
		// stores references to the right subtree of this node
		protected TreeNode<E> right;
		
		// "Constructor" sets the element field of the tree as the specifed element e.
		public TreeNode(E e){
			element=e;
		}
	}
	
	/**
	 * This method creates and returns a new tree node with the specified element e
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public TreeNode<E> createNewNode(E e){
		return new TreeNode<E>(e);
	}
	
	/**
	 * No-arg constructor creates default BST with natural order comparator
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianBinarySearchTree() {
		// defines comparator of tree as natural order comparator
		this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Creates BST with the specified comparator
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianBinarySearchTree(Comparator<E> c) {
		//sets the comparator of this tree as the specified comparator
		this.c = c;
	}
	
	/**
	 * Creates BST from an array of objects using natural order comparator
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianBinarySearchTree(E[] objects) {
		// defines comparator of tree as natural order comparator
		this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
		// Iterates through objects array and adds each element in that array to this tree
		for(int i=0; i<objects.length; i++) {
			insert(objects[i]);
		}
	}

	/**
	 * Removes all elements from the key
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void clear() {
		root=null;
		size=0;
	}

	/**
	 * Returns true if the element e is in the tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean search(E e) {
		//start searching from the root node
		TreeNode<E> current = root;
		
		// Search from root to leafs (entire tree)
		while(current != null) {
			/*
			 * compare element e to the current node, if e is 
			 * lesser, continue searching on the left subtree of the 
			 * current node. If e is greater continue searching on the 
			 * right subtree of the current node. Otherwise, if they are equal
			 * return true (element e has been found in this tree)
			 */
			if(c.compare(e, current.element)<0) {
				current=current.left;
			} else if(c.compare(e, current.element)>0) {
				current=current.right;
			} else {
				return true;
			}
		}
		/*
		 *  If the search traverses the entire tree without returning, 
		 *  element is NOT in the tree (return false)
		 */
		
		return false;
	}

	/**
	 * Insert element e into the BST, return true if element is inserted successfully.
	 * False if the element is already in the tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean insert(E e) {
		// If there is no root (tree is empty) insert the new element as the root
		if(root==null) {
			root=createNewNode(e);
		} 
		// otherwise locate the parent node (for the new element e to be inserted)
		else {
			// start traversing the tree from the root
			TreeNode<E> current=root;
			// stores the parent of the current node (which will end up being the parent of the new node)
			TreeNode<E> parent=null;
			
			// Traverse the entire tree
			while(current != null) {
				/*
				 * Compare element e with the current node being traversed in the tree. If e is lesser;
				 * continue looking on the left subtree of the current node. If e is greater ; continue looking
				 * on the right subtree of the current node. If they are equal return false (no duplicate node inserted)
				 */
				if(c.compare(e, current.element)<0) {
					parent=current;
					current=current.left;
				} else if(c.compare(e, current.element)>0) {
					parent=current;
					current=current.right;
				} else {
					//if e equals the current node, return false and duplicate node NOT inserted
					return false;
				}
			}
			/*
			 *  Once parent is found (after traversing the tree), check if the element should go on the right or left
			 *  side of parent. If e is lesser; it should be the left child. If e is greater it should be the right child
			 */
			
			if(c.compare(e, parent.element)<0) {
				parent.left=createNewNode(e);
			} else {
				parent.right=createNewNode(e);
			}
		}
		
		size++;
		return true;
	}

	/**
	 * Delete the specified element e from the tree
	 * Returns true if  element e is deleted successfully.
	 * Returns False if the element is not in the tree 
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean delete(E e) {
		//locate the node to be deleted and the parent of that node
		//start in the root of the tree
		TreeNode<E> current=root;
		TreeNode<E> parent=null;
		
		// Traverse the entire tree if necessary
		while(current!= null) {
			
			/*
			 * Compare element e with the current node being traversed in the tree. If e is lesser;
			 * continue looking on the left subtree of the current node. If e is greater ; continue looking
			 * on the right subtree of the current node. If they are equal break from the 
			 * while loop (the node to be deleted has been found)
			 */
			if(c.compare(e, current.element)<0) {
				parent=current;
				current=current.left;
			} else if(c.compare(e, current.element)>0) {
				parent=current;
				current=current.right;
			} else {
				// if element e is the same as current element, break from the while loop
				break;
			}
		}
		
		/*
		 *  if it exits the while loop without finding a match current will be null and 
		 *  the element e is not in the tree. Return false from this method
		 */
		
		if(current==null) {
			return false;
		}
		
		/*
		 * If element has been found within the tree, there are 2 possible cases.
		 * Case1: current node has no left child. In this case attach the right child 
		 * of the current node to the parent node. (If current is a left child of parent,
		 * attach the right child of current to the left side of parent. OR vice versa- otherwise)
		 * 
		 */
		// Case 1: Current node has no left child (current.left is null)
		if(current.left==null) {
			/*
			 *  if current is the root (parent is null). The right child of current becomes the new root
			 *  and current is eliminated
			 */
			if(parent==null) {
				root=current.right;
			} else {
				/*
				 *  Check whether current.element was a right child or left child of parent (e is the same as current.element)
				 *   If e is lesser than parent.element attach the right child of current to the left side of parent
				 */
				if(c.compare(e, parent.element)<0) {
					parent.left=current.right;
				} 
				// Otherwise If e is greater than parent.element attach the right child of current to the right side of parent
				else {
					parent.right=current.right;
				}
			}
		} 
		/*
		 * Case 2: Current node DOES have a left child. In this case; find the rightMost node (and its parent) on the left subtree of the current node.
		 * Then replace the content (the element field) of the current node with that of the rightMost node. And delete the rightMost node by connecting
		 * its left child (by definition rightMost does NOT have a right child) to its parent (parentOfRightMost)
		 */
		else {
			// stores the riughtMost node and its parent
			// Search for the rightMost element on the left subtree of current
			TreeNode<E> rightMost=current.left;
			TreeNode<E> parentOfRightMost=current;
			
			// Traverse the tree while there are nodes to the right available
			while(rightMost.right != null) {
				parentOfRightMost=rightMost;
				// keep going to the right
				rightMost=rightMost.right;
			}
			// The content of the current node (to be deleted) is replaced with the content of rightMost 
			current.element=rightMost.element;
			
			//check whether rightMost was the right or the left child of its parent
			// if it was the right child: connect the left child of rightMost to the right side of its parent
			if(parentOfRightMost.right==rightMost) {
				parentOfRightMost.right=rightMost.left;
			} else {
				/*
				 * Special case: the left child of current does NOT have a right child, so current.left points to the 
				 * largest element in the left subtree of current (rightMost). Then, rightMost is current.left (and parentOfRightMost 
				 * is current). Therefore we have to reconnect the left child of rightMost with the parent of rightMost (current)
				 */
				parentOfRightMost.left=rightMost.left;
			}
		}
		//element deleted successfully
		size--;
		return true;
	}

	/**
	 * Returns the number of elements in the tree (size)
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns the root of the tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public TreeNode<E> getRoot(){
		return root;
	}
	
	/**
	 * Returns an array list of nodes with the path from the root leading to the specified element e
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArrayList<TreeNode<E>> path(E e){
		//creates the array list that will stores the nodes leading up to node e
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		// stores current node being traversed, start at the root
		TreeNode<E> current =root;
		
		//Traverse the entire tree (if necessary)
		while(current != null) {
			//add the current node to the list
			list.add(current);
			/*
			 * Compare element e with the current node being traversed in the tree. If e is lesser;
			 * continue looking on the left subtree of the current node. If e is greater ; continue looking
			 * on the right subtree of the current node. If they are equal break from the 
			 * while loop (the end of the path has been reached)
			 */
			if(c.compare(e, current.element)<0) {
				current=current.left;
			} else if(c.compare(e, current.element)>0) {
				current=current.right;
			} else break;
		}
		
		//return the list of nodes
		return list;
	}
	
	/**
	 * Overloaded inorder() method, inorder traversal from the root
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void inorder() {
		inorder(root);
	}
	
	/**
	 * Overloaded inorder() method, inorder traversal from a specified node
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void inorder(TreeNode<E> root){
		if(root == null) return;
		/*
		 * recursively visit left subtree first, then current node, then right subtree
		 */
		inorder(root.left);
		System.out.print(root.element+" ");
		inorder(root.right);
	}
	
	/**
	 * Overloaded postorder() method, postorder traversal from the root
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void postorder() {
		postorder(root);
	}
	
	/**
	 * Overloaded postorder() method, postorder traversal from a specified node
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void postorder(TreeNode<E> root) {
		if(root==null) return;
		/*
		 * Recursively visit left subtree first, then right subtree, THEN the current node
		 */
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element+" ");
	}
	
	/**
	 * Overloaded preorder() method, preorder traversal from the root
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void preorder() {
		preorder(root);
	}
	
	/**
	 * Overloaded preorder() method, preorder traversal from a specified node
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void preorder(TreeNode<E> root) {
		if(root==null) return;
		
		/*
		 * Recursively visit the current element first, then the left subtree, THEN the right subtree
		 */
		System.out.print(root.element+" ");
		preorder(root.left);
		preorder(root.right);
	}
	
	/**
	 * Obtain new instance of an inorder iterator
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public Iterator<E> iterator() {
		return new InorderIterator();
	}
	
	
	/**
	 * Inorder iterator inner class, defines the iterator for this tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private class InorderIterator implements Iterator<E>{
		// uses an array list to store all elements in the tree inorder
		private ArrayList<E> list = new ArrayList<>();
		// points to the current element being traversed from the list
		private int current=0;
		
		/*
		 *  constructor, calls inner inorder() method. Traverses binary 
		 *  tree inorder and adds elements to list
		 */
		public InorderIterator() {
			inorder();
		}
		
		// inorder traversal from the root
		private void inorder() {
			inorder(root);
		}
		
		// Inorder traversal from a specified node
		public void inorder(TreeNode<E> root){
			if(root == null) return;
			/*
			 * recursively visit left subtree first, then current node, then right subtree
			 */
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}
		
		// returns true if there are more elements from traversing in current iterator
		@Override
		public boolean hasNext() {
			if(current<list.size()) return true;
			
			return false;
		}

		// returns the current element and points to the next element
		@Override
		public E next() {
			return list.get(current++);
		}
		
		// removes the last element returned by the last next() call
		@Override
		public void remove() {
			// next() has not been called yet so can't remove anything
			if(current==0) {
				throw new IllegalStateException();
			}
			
			// delete last element (the one before current) from the tree
			delete(list.get(--current));
			// clear the list
			list.clear();
			// rebuild the list without the deleted element
			inorder();
		}
	}
}
