package Assignment3;

import java.util.Set;

/**
 * This interface defines a custom Map interface called ArhianMap. 
 * It defines operations common to all maps in this use case
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public interface ArhianMap<K, V> {
	/**
	 * Removes all entries from this map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void clear();
	
	/**
	 * Returns true if the specified key is in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean containsKey(K key);
	
	/**
	 * Returns true if the specified value is in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean containsValue(V value);
	
	/**
	 * Returns a set of the entries in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public Set<Entry<K, V>> entrySet();
	
	/**
	 * Returns the value that matches the specified key, key
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public V get(K key);
	
	/**
	 * Returns true if the map is empty
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public boolean isEmpty();
	
	/**
	 * Returns a set of the entries in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public Set<K> keySet();
	
	/**
	 * Adds a key, value entry into the map and returns the value added.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public V put(K key, V value);
	
	/**
	 * Removes the entry from the map that is associated with the specified key.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void remove(K key);
	
	/**
	 * Returns the number of entries in this map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public int size();
	
	/**
	 * Returns a set of values in this map.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public Set<V> values();
	
	/**
	 * Defines inner class for an Entry in this map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public static class Entry<K, V> {
		K key;
		V value;
		
		// constructor
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		//returns the key
		public K getKey() {
			return key;
		}
		
		// returns the value
		public V getValue() {
			return value;
		}
		
		// to string method, returns the key and value in a string
		@Override
		public String toString() {
			return "["+key+", "+value+"]";
		}
	}
}
