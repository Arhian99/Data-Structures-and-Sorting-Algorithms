package Assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class which defines a custom HashSet called ArhianHashSet and implements Collection interface
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianHashSet<E> implements Collection<E>{
	// Defines the default/initial hash-table size. (must be a power of 2 for max efficiency)
	private static int DEFAULT_INITIAL_CAPACITY = 4;
	
	// Defines the maximum hash-table size. (1<<30 = 2^30)
	private static int MAXIMUM_CAPACITY = 1<<30;
	
	// current hash-table capacity. (must be a power of 2)
	private int capacity;
	
	// Defines the default maximum load factor for the hash table
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;
	
	// Specify a load factor to be used in the hash table;
	private float loadFactorThreshold;
	
	// The current number of elements in this set
	private int size=0;
	
	// defines the array that will be used as the hash table.
	/*
	 *  The backing hash table of this hashSet is an array where each element is 
	 *  a linked list of elements in the set
	 */
	LinkedList<E>[] table;
	
	/**
	 * No-arg constructor,constructs a hashSet with the default initial capacity and max load factor
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianHashSet() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/**
	 * One-arg constructor,constructs a hashSet with the specified initial capacity 
	 * and the default max load factor
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianHashSet(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/**
	 * Two-arg constructor,constructs a hashSet with the specified initial capacity 
	 * and the specified load factor threshold.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianHashSet(int initialCapacity, float loadFactorThreshold) {
		/*
		 *  checks if initial capacity is greater than max allowed capacity. if not it sets
		 *  capacity as the initial capacity
		 */
		if(initialCapacity > MAXIMUM_CAPACITY) {
			this.capacity = MAXIMUM_CAPACITY;
		} else {
			this.capacity = trimToPowerOf2(initialCapacity);
		}
		
		this.loadFactorThreshold = loadFactorThreshold;
		// creates hashTable with the capacity of the initial capacity
		table = new LinkedList[capacity];
	}

	/**
	 * Removes all elements from the set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public void clear() {
		size=0;
		// removes all entries from map
		removeElements();
		
	}
	
	/**
	 * Returns true if the specified element is in the set.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean contains(Object e) {
		int bucketIndex = hash(((E) e).hashCode());
		if(table[bucketIndex] != null) {
			LinkedList<E> bucket = table[bucketIndex];
			return bucket.contains((E) e);
		}
		return false;
	}
	
	/**
	 * Adds specified element e to the hashSet. Returns true if the element is added successfully.
	 * Returns false if element is already in the set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean add(E e) {
		// if element is already in the set, return false (duplicate element not added)
		if(contains(e)) return false;
		
		// check if adding a new element will require a rehash or will put the set passed max capacity
		if(size+1 > capacity*loadFactorThreshold) {
			// if the set is already at max capacity, throw exception (new element cannot be added)
			if(capacity==MAXIMUM_CAPACITY) {
				throw new RuntimeException("Exceeding maximum capacity");
			}
			 // otherwise rehash
			rehash();
		}
		
		// call the hash() method to get the index of the bucket that will hold the specified element
		int bucketIndex = hash(e.hashCode());
		// if the bucket is empty: 
		if(table[bucketIndex] == null) {
			// create new linked list for that bucket
			table[bucketIndex] = new LinkedList<E>();	
		}
		// add the specified element to the linked list in the bucket
		table[bucketIndex].add(e);
		
		// Increment size and return true (element added successfully)
		size++;
		return true;
	}
	
	/**
	 * Removes the element from the set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean remove(Object o) {
		// if the set does not contain the specified element, return false
		if(!contains((E) o)) {
			return false;
		}
		
		// call the hash() method to get the index of the bucket that will hold the specified element
		int bucketIndex = hash(((E) o).hashCode());
		
		// if the specified bucket is not empty:
		if(table[bucketIndex] != null) {
			// get the specified bucket and store it in bucket
			LinkedList<E> bucket = table[bucketIndex];
			// remove the specified element from the bucket
			bucket.remove((E) o);
		}
		// decrease size and return specified true (element removed successfully)
		size--;
		return true;
	}
	
	/**
	 * Returns true if the set is empty
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	/**
	 * Returns the number of elements in this set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Returns an iterator for the elements in this set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArhianHashSetIterator(this);
	}
	
	private class ArhianHashSetIterator implements Iterator<E>{
		// this list will store all the elements in the set
		private ArrayList<E> list;
		// Points to the 'index' of the current element being iterated through from list
		private int current=0;
		// stores the set used for this iterator
		private ArhianHashSet<E> set;
		
		/**
		 * Constructor creates a list from the set passed in
		 * @author Arhian Albis Ramos
		 * @version 0.0.1
		 */
		public ArhianHashSetIterator(ArhianHashSet<E> set) {
			this.set = set;
			list = setToList();
		}
		
		/**
		 * next element for traversing ?
		 * @author Arhian Albis Ramos
		 * @version 0.0.1
		 */
		@Override
		public boolean hasNext() {
			return current < list.size();
		}
		
		/**
		 * Returns the current element and moves the cursor (Current) to the next
		 * @author Arhian Albis Ramos
		 * @version 0.0.1
		 */
		@Override
		public E next() {
			return list.get(current++);
		}
		
		/**
		 * Returns the current element returned by the last next()
		 * @author Arhian Albis Ramos
		 * @version 0.0.1
		 */
		@Override
		public void remove() {
			// if next() has not been called, no element to remove
			if(current==0) {
				throw new IllegalStateException();
			}
			
			// remove the current element returned by the last next() call from the set
			ArhianHashSet.this.remove(list.get(--current));
			
			// clear the list
			list.clear();
			
			// rebuild the list from the new set (without the specified element)
			list = setToList();
		}
	}
	
	/**
	 * Hash function. Takes a hash code and calls the supplemental Hash function and returns an integer
	 * which serves as an index for the hash table
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity - 1);
	}
	
	/**
	 * Supplemental Hash function. Ensures hashing is evenly distributed
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private static int supplementalHash(int h) {
		h ^= (h>>>20) ^ (h>>>12);
		
		return h ^ (h>>>7) ^ (h>>>4);
	}
	
	/**
	 * Takes an integer as a parameter and returns a power of 2 for initialCapacity
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		// multiplies capacity times 2 while capacity is less than the initial capacity passed in
		while(capacity < initialCapacity) {
			capacity<<=1; // same as capacity = capacity*2
		}
		return capacity;
	}
	
	/**
	 *Removes all elements from each bucket
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void removeElements() {
		// iterates through all buckets in the hash table
		for(int i=0; i<capacity;i++) {
			// if the current bucket is not already empty:
			if(table[i] !=null) {
				// clear the current bucket (calls clear() method of the LinkedList)
				table[i].clear();
			}
		}
	}
	
	/**
	 * Rehashes this set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void rehash() {
		// get a list from all current elements in this set calling setToList() method
		ArrayList<E> list = setToList();
		
		// set new capacity as twice the old capacity
		capacity <<=1; // same as capacity*=2
		
		// re-build new hash table with the new capacity
		table = new LinkedList[capacity];
		
		// reset the size to 0
		size=0;
		
		// iterate through all elements in the list: 
		for(E element : list) {
			// add each element into the new hash table
			add(element);
		}
	}
	
	/**
	 * Copy all elements in the hash set to an array list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArrayList<E> setToList() {
		// declares and initializes arrayList which stores all elements in this set
		ArrayList<E> list = new ArrayList<>();
		
		// iterates through all buckets in hashTable
		for(int i=0; i< capacity; i++) {
			// if current bucket is not empty:
			if(table[i] != null) {
				// iterate through all elements in current bucket:
				for(E element : table[i]) {
					// add current element to list
					list.add(element);
				}
			}
		}
		return list;
	}
	
	/**
	 * returns a string of all elements in this set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public String toString() {
		// stores all elements in this set as a list named list
		ArrayList<E> list = setToList();
		// declares and initializes string builder
		StringBuilder builder = new StringBuilder("[");
		
		// iterates thrugh all buckets in the hash table
		for(int i=0; i<list.size()-1; i++) {
			builder.append(list.get(i)+ ", ");
		}
		
		// add the last element in the list to the string builder
		if(list.size() == 0) {
			builder.append("]");
		} else {
			builder.append(list.get(list.size() - 1)+ "]");
		}
		//return the string of the string builder instance
		return builder.toString();
	}
	
	/**
	 * Returns true if all the elements in the collection c
	 * are also present in this set.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object element : c) {
			if(!this.contains(element)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * adds all the elements in collection c to this set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		for(Object element : c) {
			add((E) element);
		}
		//returns true if the set has changed as a result of the call
		if(c.size() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * removes all elements in c from this set
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		for(Object element : c) {
			remove(element);
		}
		//returns true if the tree has changed as a result of the call
		if(c.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * this method keeps the elements in this set only if they are also
	 * present in Collection c and removes all elements in this set that are 
	 * NOT present in Collection c.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		// this flag is changed to true if the set is changed as a result of this method call
		boolean flag = false;
		/* Iterates through all the elements in this set and removes the elements
		 * NOT present in Collection c
		 */
		Iterator<E> iterator = this.iterator();
		while(iterator.hasNext()) {
			E current = iterator.next();
			if(!c.contains(current)) {
				iterator.remove();
				flag=true;
			}
		}
		return flag;
	}

	/**
	 * This method converts the current set to a new array and returns the array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override 
	public Object[] toArray(){
		// creates new array which will store all elements
		E[] array = (E[]) new Object[size()];
		// keeps current index of the array above
		int index=0;
		//Iterates through all the elements in this set and adds them to the array
		for(E element : this) {
			array[index] = element;
			index++;
		}
		return array;
	}

	/**
	 * This method takes an array as a parameter and adds all the elements in this
	 * set to the array that was passed in
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public <T> T[] toArray(T[] array) {
		// keeps current index of the array used to store the elements
		int index = 0;
		// iterates through all the elements in the set and adds them to subsequent indexes in array
		for(E element : this) {
			array[index] = (T) element;
			index++;
		}
		return array;
	}
}
