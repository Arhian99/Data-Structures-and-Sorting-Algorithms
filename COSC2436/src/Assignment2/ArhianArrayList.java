package Assignment2;

import java.util.Iterator;

/**
 * Class which defines and implements a custom ArrayList called ArhianArrayList
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianArrayList<E> implements ArhianList<E>{
	public static final int INITIAL_CAPACITY = 16;
	private E[] data = (E[]) new Object[INITIAL_CAPACITY];
	//number of elements currently in the list
	private int size = 0;
	
	/**
	 * No-arg constructor creates empty list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianArrayList() {
	}
	
	/**
	 * creates a list from an array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianArrayList(E[] array) {
		for(int i=0; i<array.length; i++) {
			add(array[i]);
		}
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
	 * returns true if this list contains the element, or false otherwise
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean contains(Object o) {
		for(int i=0; i<size; i++) {
			if(o.equals(data[i])) {
				return true;
			};
		}
		return false;
	}

	/**
	 * Returns an instance of iterator class
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
	
	/**
	 * Defines the iterator class for this class which is used by the iterator method above
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private class ArrayListIterator implements Iterator<E> {
		private int current=0;
		
		// returns true of there are more elements in data, false otherwise
		public boolean hasNext() {
			return current<size;
		}

		//returns next element in data array
		public E next() {
			return data[current++];
		}
		
		// Remove the element returned by the last next() call
		public void remove() {
			if(current==0) {
				throw new IllegalStateException();
			}
			ArhianArrayList.this.remove(--current);
		}
		
	}
	
	/**
	 * creates new, empty array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void clear() {
		data = (E[]) new Object[INITIAL_CAPACITY];
		size=0;
		
	}

	/**
	 * Adds a new element e at specified index
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void add(int index, E e) {
		// throws exception if index is out of bounds (not in this list)
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
		
		ensureCapacity();
		
		// move all elements to the right AFTER the specified index
		for(int i=size-1; i>=index; i--) {
			data[i+1]= data[i];
		}
		//insert new element at specified index
		data[index]=e;
		
		//incremenet size
		size++;
		
	}

	/**
	 * If array is full it creates new array double the size of the old array and copies all
	 * elements of old array to new array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void ensureCapacity() {
		if(size>= data.length) {
			//Create a new larger array, double the current size + 1
			E[] newData = (E[]) new Object[size*2 + 1];
			// copies old data to newData
			System.arraycopy(data, 0, newData, 0, size);
			// makes newData the new data array
			data=newData;
		}
	}
	
	/**
	 * Returns element at the specified index. 
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E get(int index) {
		// throws exception if index is out of bounds (not in this list)
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
		return data[index];
	}

	/**
	 * Returns the index of the first matching element in this list or -1 if no matches are found
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int indexOf(Object e) {
		for(int i=0; i<size; i++) {
			if(e.equals(data[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the index of the last matching element in this list or -1 if no matches are found
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int lastIndexOf(E e) {
		for(int i=size-1; i>=0; i--) {
			if(e.equals(data[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Remove the element at the specified position
	 * in this list. Shift any subsequent elements to the left.
	 * Return the element that was removed from the list.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E remove(int index) {
		// throws exception if index is out of bounds (not in this list)
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
		//element to be removed (and returned)
		E element = data[index];
		
		//shift data to the left starting at index to be removed
		for(int i=index; i<size-1; i++) {
			data[i]=data[i+1];
		}
		
		/*
		 * since an element was removed after all the data has been moved left, 
		 * the last space is empty
		 */
		data[size-1]=null;
		size--;
		return element;
	}

	/**
	 * replaces the element at the specified index with a new element that is passed in
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public E set(int index, E e) {
		// throws exception if index is out of bounds (not in this list)
		if(index<0 || index>size) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		}
		// grabs old element 
		E oldElement = data[index];
		// replaces with new element
		data[index] = e;
		//returns old element
		return oldElement;
	}
	
	/**
	 * This method prints all the elements in this list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void ArhianPrint() {
		System.out.print("ARRAYLIST = [");
		// iterates through entire list and prints each element
		for(int i=0; i<size;i++) {
			// if its the last element it does NOT append a comma to the end
			if(i==size-1) {
				System.out.print(data[i]);
			} else {
				System.out.print(data[i]+", ");
			}
		}
		System.out.print("]");
		System.out.println();
	}

}
