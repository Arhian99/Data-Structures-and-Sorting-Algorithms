package Assignment2;

import java.util.Collection;

public interface ArhianList<E> extends Collection<E>{
	/** Add a new element at the specified index in this list */
	public void add(int index, E e);
	
	/** Return the element from this list at the specified index */
	public E get(int index);
	
	/** Return the index of the first matching element in this list.
	 *  Return −1 if no match. */
	public int indexOf(Object e);
	
	/** Return the index of the last matching element in this list
	 *  Return −1 if no match. */
	public int lastIndexOf(E e);
	
	/** Remove the element at the specified position in this list 
	 *  Shift any subsequent elements to the left.
	 *  Return the element that was removed from the list. */
	public E remove(int index);
	
	/** Replace the element at the specified position in this list
	 *  with the specified element and returns the new set. */
	public E set(int index, E e);
	
	@Override/** Add a new element at the end of this list */
	public default boolean add(E e) {
		add(size(), e);
		return true;
	}
	
	@Override /** Return true if this list contains no elements */
	public default boolean isEmpty() {
		return size() == 0;
	}
	
	
	@Override /** Remove the first occurrence of the element e 
	 * from this list. Shift any subsequent elements to the left.
	 * Return true if the element is removed. */
	public default boolean remove(Object e) {
		if(indexOf(e) >=0) {
			remove(indexOf(e));
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Returns true if all the elements in the collection c that was
	 * passed in are also present in this list.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public default boolean containsAll(Collection<?> c) {
		for(Object element : c) {
			if(!this.contains(element)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * adds all the elements in collection c to this list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public default boolean addAll(Collection<? extends E> c) {
		for(Object element : c) {
			add((E) element);
		}
		//returns true if the list has changed as a result of the call
		if(c.size() != 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * removes all elements in c from this list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public default boolean removeAll(Collection<?> c) {
		for(Object element : c) {
			remove(element);
		}
		//returns true if the list has changed as a result of the call
		if(c.size() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * this method keeps the elements in this only if they are also
	 * present in c and removes all elements in this list that are NOT present in c
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override 
	public default boolean retainAll(Collection<?> c) {
		boolean flag = false;
		// Iterates through all the elements in this list and removes the elements NOT present in c
		while(this.iterator().hasNext()) {
			E element = this.iterator().next();
			if(!c.contains(element)) {
				remove(element);
				flag= true;
			}
		}
		// the flag represents whether the list was changed as a result of the call
		return flag;
	}
	
	/**
	 * it converts the current list to a new array and returns the array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override 
	public default Object[] toArray() {
		// creates new array which will store all elements
		E[] array = (E[]) new Object[size()];
		// keeps current index of the array above
		int i=0;
		//Iterates through all the elements in this list and adds them to the array
		for(E element : this) {
			array[i] = element;
			i++;
		}
		return array;
	}
	
	/**
	 * this method takes an array as a parameter and adds all the elements in this
	 * list to the array that was passed in
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public default <T> T[] toArray(T[] array) {
		// keeps current index of the array used to store the elements
		int i=0;
		// iterates through all the elements in the list and adds them to subsequent indexes in array
		for(E element : this) {
			array[i] = (T) element;
			i++;
		}
		return array;
	}
	
	/**
	 * This method prints all the elements in the list
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public default void ArhianPrint() {
		// variable used to keep track of when the iteration reaches the last element in the list
		int i=0;
		System.out.print("[");
		// iterates through entire list and prints each element
		for(E element : this) {
			if(i== this.size()-1) {
				System.out.print(element);
			} else {
				System.out.print(element+", ");
			}
			i++;
		}
		System.out.print("]");
		System.out.println();
	}
}
