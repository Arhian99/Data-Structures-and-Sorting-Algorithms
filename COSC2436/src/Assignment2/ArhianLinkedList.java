package Assignment2;

import java.util.Iterator;

/**
 * Class which defines and implements a custom LinkedList called ArhianLinkedList
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianLinkedList<E> implements ArhianList<E>{
	/**
	 * Inner class that defines a Node, the building blocks of a linkedList
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private static class Node<E>{
		//holds the data in the node
		E element;
		//points to the next node in the list
		Node<E> next;
		
		public Node(E e) {
			element = e;
		}
	}
	//points to the first node in the list
	private Node<E> head;
	//points to the last node in the list
	private Node<E> tail;
	//number of elements in the list
	private int size=0;
	
	/**
	 * No-arg constructor creates empty list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianLinkedList() {};
	
	/**
	 * creates a list from an array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianLinkedList(E[] objects) {
		// iterates through all elements in the array passed in and adds them to the list, calling add() method
		for(E element : objects) {
			add(element);
		}
	}
	
	/**
	 * Returns the first element in the list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E getFirst() {
		// if list is empty return null, otherwise return the data in the head node
		if(size==0) {
			return null;
		}
		return head.element;
	}
	
	/**
	 * Returns the first element in the list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E getLast() {
		// if list is empty return null, otherwise return the data in the tail node
		if(size==0) {
			return null;
		}
		return tail.element;
	}
	
	/**
	 * Adds an element to the beginning of the list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void addFirst(E e) {
		//creates new node instance with the data passed in
		Node<E> newNode = new Node(e);
		
		//the current head will become the second element in the list, therefore the next field for the newNode will point to the current head
		newNode.next = head;
		// the newNode will be at the beginning of the list, so it becomes the head
		head = newNode;
		// increment size
		size++;
		
		// if the list was empty before adding a new element, there is only one element in the list after adding newNode, so both head and tail point to it
		if(tail==null) {
			tail=head;
		}
	}
	
	/**
	 * Adds an element to the end of the list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void addLast(E e) {
		//creates new node instance with the data passed in
		Node<E> newNode = new Node(e);
		
		// if the list is empty, the only element in the list will be the newNode so both tail and head point to it
		if(tail==null) {
			tail=head=newNode;
		} 
		//otherwise, the current tail will become the second to last node so its next field will point to the newNode
		else {
			tail.next = newNode;
			//the newNode will be come the last element in the list (the tail)
			tail=newNode;
		}
		//Increment size
		size++;
	}
	
	/**
	 * Adds a new element e at specified index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void add(int index, E e) {
		//if index is 0, element is added to the front of the list, callind addFirst() method
		if(index==0) {
			addFirst(e);
		} 
		//if index is greater than or equal to size, element is added to the back of the list
		else if(index>=size){
			addLast(e);
		} 
		// otherwise; element is added to the middle of the list somewhere.
		else {
			// creates newNode to be added to list
			Node<E> newNode = new Node(e);
			// the current node is used to scan the list to find the index where we want to add the newNode, starts scanning at head node
			Node<E> current = head;
			/*
			 * iterates through the list, each time current points to the next elment in the list until the loop finishes, at which point
			 * current will be pointing to the node one index less than where we want to add our newNode
			 */
			for(int i=1; i<index;i++) {
				current=current.next;
			}
			// temp now points to the index where we want to add our newNode
			// newNode will be added between the elements current and temp
			Node<E> temp = current.next;
			// add the new node by making the next field in current (the element at index-1) points to the newNode
			current.next=newNode;
			// finishes adding the element by linking the next field in newNode with the next node in the list (temp)
			newNode.next=temp;
			//increments size
			size++;
		}
	}
	
	/**
	 * Removes the head node and returns the object that is contained in the removed node
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E removeFirst() {
		//checks if list is empty
		if(size==0) {
			return null;
		} else {
			// saves the element in the current head (to be returned)
			E removedE = head.element;
			// makes the second node the new head.
			head=head.next;
			//decrement size
			size--;
			// checks if list is empty after removing element
			if(head==null) {
				tail=null;
			}
			//returns deleted element;
			return removedE;
		}
	}
	
	/**
	 * Removes the tail node and returns the object that is contained in the removed node
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E removeLast() {
		// if list is empty or has only one element call removeFirst()
		if(size==0 || size==1) {
			return removeFirst();
		} else {
			// save current element in tail to be returned (as the deleted element)
			E removedE = tail.element;
			// the following 3 lines iterates through the entire of the list, at the end current will point to second to last node in the list 
			Node<E> current = head;
			for(int i=0; i<size-2; i++) {
				current=current.next;
			}
			// the node before the current tail becomes the new tail (since were deleting the current tail)
			tail=current;
			// since current becomes new tail, its next field must be null (it is the last element in the list)
			current.next=null;
			// Decrement size and return deleted element
			size--;
			return removedE;
			
		}
	}
	
	
	/**
	 * Removes the element at the specified position and returns it
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E remove(int index) {
		// checks if index is out of bounds
		if(index<0 || index>=size) {
			return null;
		} 
		// if index is 0 call removeFirst()
		else if(index==0) {
			return removeFirst();
		} 
		// if index is last call removeLast()
		else if(index==size-1) {
			return removeLast();
		} else {
			//the next 4 lines of code iterate through the list and stops when previous is at the index that is ONE LESS than the index we want to remove
			Node<E> previous = head;
			for(int i=1; i<index; i++) {
				previous=previous.next;
			}
			// sets current as the node following previous (the node we want to remove)
			Node<E> current = previous.next;
			// saves the element of the node we want to remove for return later
			E removedE = current.element;
			// the next field of the node BEFORE the node we want to remove now points to the element AFTER the node we want to remove. Current is being skipped (its removed from the list)
			previous.next=current.next;
			
			//decrements size and returns deleted element
			size--;
			return removedE;
		}
	}
	
	/**
	 * Clears/empties the list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void clear() {
		//list is empty
		size=0;
		head=tail=null;
	}
	
	
	/**
	 * returns true if this list contains the element, or false otherwise
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean contains(Object o) {
		// the following 3 lines iterates through the entire list and checks if each each element is equal to o, it returns true if this is the case at least once
		Node<E> current = head;
		for(int i=0; i<size; i++) {
			if(current.element.equals(o)) {
				return true;
			}
			current=current.next;
		}
		return false;
	}
	
	/**
	 * Returns element at the specified index. 
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E get(int index) {
		//checks if index is out of bounds, the first or last index and acts accordingly
		if(index<0 || index>=size) {
			return null;
		} else if(index==0) {
			return head.element;
		} else if(index==size-1) {
			return tail.element;
		} else {
			// the following 3 lines iterates through the list and stops at the indicated index. At the end of the loop current will point to the node with the desired index
			Node<E> current = head;
			for(int i=0; i<index; i++) {
				current=current.next;
			}
			return current.element;
		}

	}
	
	/**
	 * Returns the index of the first matching element in this list or -1 if no matches are found
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int indexOf(Object e) {
		// the following 3 lines iterates through the list and checks if each each element is equal to e, it returns the index of the first instance it finds this condition true
		Node<E> current = head;
		for(int i=0; i<size; i++) {
			// checks if current element is equal to the element passed in
			if(current.element.equals(e)) {
				// returns index if match is found
				return i;
			}
			// moves to the next node in the list
			current=current.next;
		}
		//returns -1 if no matches are found
		return -1;
	}
	
	/**
	 * Returns the index of the last matching element in this list or -1 if no matches are found
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int lastIndexOf(E e) {
		int lastIndex=-1;
		
		Node<E> current = head;
		/*
		 * iterates through entire list and checks if each successive element is equal to the element that was passed in, if so it saves the index of the current element, 
		 * each time overriding the previous index.
		 */
		
		for(int i=0; i<size; i++) {
			//checks if the current element is equal to the element passed in, if so, it saves the index
			if(current.element.equals(e)) {
				lastIndex=i;
			}
			// moves to the next node in the list
			current = current.next;
		}
		
		return lastIndex;
	}
	
	/**
	 * Replaces the element at the specified index with a new elemnet that is passed in and returns the old element
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E set(int index, E e) {
		if(index<0 || index>=size) {
			return null;
		} else {
			//creates newNode with passed in value
			Node<E> newNode = new Node(e);
			Node<E> previous = head;
			
			//iterates through the list and stops at the node that is one bbefore the node we want to replace
			for(int i=1; i<index; i++) {
				previous=previous.next;
			}
			// since previous is one node BEFORE the node we want to replace, now current points to the node we want to replce
			Node<E> current = previous.next;
			//previous poihts to newNode (bypasses current)
			previous.next=newNode;
			//the next field in newNode now points to the node after current (fully eliminates current from the list)
			newNode.next=current.next;
			
			// returns the old element in current (which is eliminate from the list
			return current.element;
		}
	}
	
	/**
	 * Defines the iterator class for this class which is used by the iterator method above
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private class LinkedListIterator implements Iterator<E> {
		private Node<E> current=head;
		private Node<E> previous = null;
		//returns true if there is a next element in the list
		public boolean hasNext() {
			return (current!=null);
		}
		
		public E next() {
			E element = current.element;
			previous=current;
			current=current.next;
			return element;
		}
		
		// removes the element returned by the last next() call (the element before current)
		public void remove() {
			if(previous!=null) {
				previous.next=current.next;
			} else {
				head=current.next;
			}
		}
	}

	/**
	 * Returns an instance of iterator class
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}
	
	/**
	 * returns the size field which represents the number of elements in the list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This method prints all the elements in this list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void ArhianPrint() {
		Node<E> current=head;
		System.out.print("LINKEDLIST = [");
		for(int i=0; i<size; i++) {
			// if its the last element it does NOT append a comma to the end
			if(i==size-1) {
				System.out.print(current.element);
			} else {
				System.out.print(current.element+", ");
			}
			current=current.next;
			
		}
		
		System.out.print("] \n");
		System.out.println();
	}
	
	/**
	 * This method prints all the elements in this list, its meant to be used for Queues that use LinkedList as backing data structure
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void ArhianPrintQueueHelper() {
		Node<E> current=head;
		for(int i=0; i<size; i++) {
			// if its the last element it does NOT append a comma to the end
			if(i==size-1) {
				System.out.print(current.element);
			} else {
				System.out.print(current.element+", ");
			}
			current=current.next;
			
		}
	}
	

}
