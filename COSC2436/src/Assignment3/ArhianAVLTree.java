package Assignment3;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class which defines a custom AVL Tree called ArhianAVLTree by extending ArhianBinarySearchTree
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianAVLTree<E> extends ArhianBinarySearchTree<E>{
	
	/**
	 * No-arg constructor creates empty AVL with natural order comparator by implicitly calling 
	 * the no-arg constructor of the ArhianBinarySearchTree class.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianAVLTree() {
	}
	
	/**
	 * Creates BST with the specified comparator
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianAVLTree(Comparator<E> c) {
		super(c);
	}
	
	/**
	 * Creates AVL Tree from an array of objects using natural order comparator
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianAVLTree(E[] objects) {
		super(objects);
	}
	
	/**
	 * Protected inner class defines an AVL TreeNode which is the same as a BST
	 * Tree node but has a height field.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	protected static class AVLTreeNode<E> extends TreeNode<E>{
		// height field
		protected int height=0;
		
		//Constructor
		public AVLTreeNode(E object) {
			super(object);
		}
	}
	
	
	/**
	 * Overrides the createNewNode() method defined in AhrianBST. This one returns
	 * an AVL Tree node.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public AVLTreeNode<E> createNewNode(E e){
		return new AVLTreeNode<E>(e);
	}
	
	/**
	 * Overrides the insert() method defined in AhrianBST. It inserts element e and re-balances the tree
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean insert(E e) {
		// super.insert() returns true if element was inserted successfully
		boolean successful = super.insert(e);
		if(!successful) {
			return false; // element not inserted, e is already in the tree
		} else {
			// if element was inserted, re-balance the path from root to e
			balancePath(e);
		}	
		return true; // e was inserted successfully
	}
	
	/**
	 * Update the height of a specified node
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void updateHeight(AVLTreeNode<E> node) {
		// if the node has no subtrees, the node is a leaf (height is 0)
		if(node.left==null && node.right==null) {
			node.height = 0;
		} else if (node.left==null) {
			/*
			 * if the node has no left subtree, its height is the height of its right subtree plus 1
			 */
			node.height = 1+((AVLTreeNode<E>)(node.right)).height;
		} else if(node.right==null) {
			/*
			 * if the node has no right subtree, its height is the height of its left subtree plus 1
			 */
			node.height=1+((AVLTreeNode<E>)(node.left)).height;
		} else {
			/*
			 * if the node has both subtrees, its height is the height of its larger subtree plus 1
			 */
			node.height = 1+Math.max(((AVLTreeNode<E>)(node.right)).height, ((AVLTreeNode<E>)(node.left)).height);
		}
	}
	
	
	/**
	 * Balance the nodes in the path from the specified node e to the root if necessary
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void balancePath(E e) {
		// calls path method from ArhianBST class which returns a arrayList of nodes with the path from e to the root
		ArrayList<TreeNode<E>> path = path(e);
		/*
		 *  This loop iterates through the path list, starting at the specified node e, and working down to the root node.
		 *  It updates the height of the node, gets its parent (the node before in the path list), and re-balances the node
		 *  according to its balance factor
		 */
		for(int i=path.size()-1; i>=0; i--) {
			// gets one node at a time and stores it in the variable A
			AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
			// updates the height of the current node 
			updateHeight(A);
			// stores the parent of A in the variable, if A is the root its parent is null, otherwise it is the node before A in the path list
			AVLTreeNode<E> parentOfA = (A==root) ? null : (AVLTreeNode<E>)(path.get(i-1));
			
			/* 
			 * Balancing depends on the balanceFactor of A and the balance factor of its right and left children.
			 */
			switch(balanceFactor(A)) {
			/*
			 *  if the balance factor of A is -2 and the balance factor of its left child is less than or equal to 0: perform an LL rotation
			 *  otherwise if the balance factor of its left child is greater than 0, perform an LR rotation.
			 */
			case -2:
				if(balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
					balanceLL(A, parentOfA); // LL rotation on A
				} else {
					balanceLR(A, parentOfA); // LR rotation on A
				}
				break;
				/*
				 * if the balance factor of A is +2 and the balance factor of its right child is greater than or equal to 0: perform RR rotation.
				 * Otherwise, if the balance factor of its right child is negative, perform an RL rotation.
				 */
			case +2:
				if(balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
					balanceRR(A, parentOfA); // RR rotation on A
				} else {
					balanceRL(A, parentOfA); // RL rotation on A
				}
			}
		}
	}
	
	/**
	 * Returns the balanceFactor of specified node, node
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private int balanceFactor(AVLTreeNode<E> node) {
		// balance factor is the difference in height between the right and left subtree of node 
		//if node does not have a right subtree, it will be left heavy (negative load factor)
		if(node.right == null) {
			return -node.height;
		} 
		//if node does not have a left subtree, it will be right heavy (positive load factor)
		else if(node.left == null) {
			return +node.height;
		} else {
			// if node has both children, the load factor is the difference in height between its subtrees
			return ((AVLTreeNode<E>)node.right).height - ((AVLTreeNode<E>)node.left).height;
		}
	}
	
	/**
	 * Implementation of LL rotation
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
		/*
		 * In an LL rotation, A is left heavy and B (the left child of A) is left heavy.
		 * In this rotation; 1) B takes the place of A (so B becomes connected to parentOfA, in the place of A.
		 * Then, 2) the right child of B becomes the left child of A. And 3) A becomes the right child of B 
		 */
		
		// B is the left subtree of A. In this rotation, A and B are both left heavy
		TreeNode<E> B = A.left;
		
		// Step 1: B takes the place of A, so B becomes connected to parentOfA, in the place of A
		// if A was the root, B becomes the new root.
		if(A == root) {
			root=B;
		} else {
			// Check whether A was a right child or left child and B takes its place by connecting to parentOfA
			if(parentOfA.left == A) {
				parentOfA.left = B;
			} else {
				parentOfA.right = B;
			}
		}
		
		//Step 2: The right child of B (B.right) becomes the left child of A.
		A.left = B.right;
		// Step 3: A becomes the right child of B
		B.right = A;
		
		// update the height of both A and B
		updateHeight((AVLTreeNode<E>) A);
		updateHeight((AVLTreeNode<E>) B);
	}
	
	/**
	 * Implementation of RR rotation
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
		/* 
		 * In an RR rotation, A is right heavy and the right child of A (B) is also right heavy. This rotation is the mirror
		 * image of an LL rotation. First, B takes the place of A (so B becomes connected to parentOfA, replacing A). 
		 * Second, the left child of B becomes the right child of A. Third, A becomes the left child of B. 
		 */
		// In this rotation, B is the right child of A and both A and B are right heavy
		TreeNode<E> B = A.right;
		
		// Step 1: B takes the place of A. So B becomes connected to parentOfA, in the place of A.
		// If A was the root, B is now the root
		if(A == root) {
			root=B;
		} else {
			// checks whether A was a right child or left child. B takes its place in either case. 
			if(parentOfA.right == A) {
				parentOfA.right = B;
			} else {
				parentOfA.left = B;
			}
		}
		
		// Step 2: The left child of B (B.left) becomes the right child of A
		A.right = B.left;
		// Step3: A becomes the left child of B.
		B.left = A;
		
		// update their heights after rotating.
		updateHeight((AVLTreeNode<E>) A);
		updateHeight((AVLTreeNode<E>) B);
	}

	/**
	 * Implementation of LR rotation
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
		/*
		 * In this rotation A is left heavy and the left child of A (B) is right heavy. First we perform an RR rotation at B
		 * Then, an LL rotation at A. Where B 
		 * 
		 * Step 1: C takes the place of A. C becomes connected to parentOfA in the place of A. 
		 * Step 2: the right child of C becomes the left child of A.
		 * Step 3: the left child of C becomes the right child of B.
		 * Step 4: B becomes the left child of C.
		 * Step 5: A becomes the right child of C.
		 */
		
		// B is the left child of A. A is left heavy and B is right heavy.
		TreeNode<E> B = A.left;
		// C is the RIGHT child of A
		TreeNode<E> C = B.right;
		
		//Step1
		if(A==root) {
			root=C;
		} else {
			
			if(parentOfA.left == A) {
				parentOfA.left = C;
			} else {
				parentOfA.right = C;
			}
		}
		
		//step2
		A.left = C.right;
		// step 3
		B.right = C.left;
		// step 4
		C.left = B;
		//step 5
		C.right = A;
		
		updateHeight((AVLTreeNode<E>) A);
		updateHeight((AVLTreeNode<E>) B);
		updateHeight((AVLTreeNode<E>) C);
			
	}
	
	/**
	 * Implementation of RL rotation
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
		/*
		 * In this rotation A is right heavy and the right child of A (B) is left heavy. 
		 * First, we perform an LL rotation at B, then an RR rotation at A.
		 * 
		 * Step 1: C takes the place of A. So C becomes connected to parentOfA in the place of A
		 * Step 2: the left child of C becomes the right child of A.
		 * Step 3: the right child of C becomes the left child of B.
		 * Step 4: A becomes the left child of C.
		 * Step 5: B becomes the right child of C.
		 */
		
		// B is the right child of A. C is the left child of B. A is right heavy. B is left heavy
		TreeNode<E> B = A.right;
		TreeNode<E> C = B.left;
		
		//Step 1
		if(A == root) {
			root = C;
		} else {
			if(parentOfA.left == A) {
				parentOfA.left = C;
			} else {
				parentOfA.right = C;
			}
		}
		
		// step 2
		A.right = C.left;
		// step 3
		B.left = C.right;
		// step 4
		C.left = A;
		// step 5
		C.right = B;
		
		updateHeight((AVLTreeNode<E>) A);
		updateHeight((AVLTreeNode<E>) B);
		updateHeight((AVLTreeNode<E>) C);
	}
	
	/**
	 * Deletes a specified element from the binary tree. Returns true if element was deleted or 
	 * false if element is not in the tree. Re-balances the tree after deleting the element
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean delete(E element) {
		// if tree is empty there are no elements to delete.
		if(root == null) {
			return false;
		}
		
		// to find the element to be deleted and its parent, its needed to traverse the tree.
		// stores parent of current
		TreeNode<E> parent=null;
		//stores current node being traversed through in the tree, starting at the root
		TreeNode<E> current = root;
		
		/*
		 * Compare element with the current node being traversed in the tree. If element is lesser;
		 * continue looking on the left subtree of the current node. If element is greater ; continue looking
		 * on the right subtree of the current node. If they are equal break from the 
		 * while loop (current points to the element)
		 */
		while(current != null) {
			if(c.compare(element, current.element) < 0) {
				parent = current;
				current = current.left;
			} else if(c.compare(element, current.element) > 0) {
				parent = current;
				current=current.right;
			} else {
				break;
			}
		}
		
		/*
		 *  if it exits the tree without finding an element (current will be null), 
		 *  return false since element is not in the tree
		 */
		if(current == null) {
			return false;
		}
		
		/*
		 * 2 cases possible after finding the element. 
		 * Case 1: current has no left children ==> To delete current, connect parent with the right child of current
		 * and re-balance the path from parent up to the root.
		 * 
		 * Case 2: current has a left child. 
		 * 	a) Search for the rightMost node (and its parent: parentOfRightMost) on the left subtree of current.
		 * 	b) Replace the element field in current with that of rightMost. 
		 * 	c) Delete rightMost by connecting the left child of rightMost with the parentOfRightMost, taking the place where rightMost was connected.
		 * 		By definition, rightMost has NO right child, so it is done reconnecting the tree.
		 */
		
		//Case 1: current has no left child
		if(current.left == null) {
			// if current is the root, the right child of current is the new root
			if(parent == null) {
				root = current.right;
			} else {
				// otherwise; the right child of current is attached to parent, depending whether it is lesser than (on the left side) or larger than (on the right side) of parent. 
				if(c.compare(element, parent.element) < 0) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
				// re-balances path from root to parent
				balancePath(parent.element);
			}
		} 
		// Case 2: current DOES have a left child.
		else {
			// to locate the rightMost node in the left subtree of current we traverse the tree to the right as far as possible.
			// stores current rightMost node, starting at current.left (we want the rightMost node IN the left subtree of current)
			TreeNode<E> rightMost = current.left;
			// stores the parent of rightMost
			TreeNode<E> parentOfRightMost = current;
			
			//2a
			// Traverses the tree to the right as far as possible
			while(rightMost.right != null) {
				parentOfRightMost=rightMost;
				// keeps going to the right
				rightMost=rightMost.right;
			}
			
			// once it breaks from the while loop, rightMost will point to the rightMost (largest element) on the left subtree of current
			//2b) replace the element in current with the element in rightMost
			current.element = rightMost.element;
			
			//2c
			if(parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else {
				parentOfRightMost.left = rightMost.left;
			}
			balancePath(parentOfRightMost.element);
		}
		// element deleted and tree re-balanced successfully
		size--;
		return true;
	}
}
