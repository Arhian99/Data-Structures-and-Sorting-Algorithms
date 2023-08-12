package Assignment3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Class which defines a custom HashMap called ArhianHashMap by extending ArhianMap
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianHashMap<K, V> implements ArhianMap<K, V>{
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
	
	// The number of current entries in the map
	private int size=0;
	
	// defines the array that will be used as the hash table.
	/*
	 *  The backing hash table of this hashMap is an array where each element is 
	 *  a linked list of map entries
	 */
	LinkedList<ArhianMap.Entry<K, V>>[] table;
	
	/**
	 * No-arg constructor,constructs a hashMap with the default initial capacity and max load factor
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/**
	 * One-arg constructor,constructs a hashMap with the specified initial capacity 
	 * and the default max load factor
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}
	
	/**
	 * Two-arg constructor,constructs a hashMap with the specified initial capacity 
	 * and the specified load factor threshold.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public ArhianHashMap(int initialCapacity, float loadFactorThreshold) {
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
	 * Removes all entries from the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public void clear() {
		size=0;
		// removes all entries from map
		removeEntries();
		
	}

	/**
	 * Returns true if the specified key is in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean containsKey(K key) {
		// if the get() method returns a key (and not null) then that key is in the map: return true
		if(get(key) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true if the specified value is in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean containsValue(V value) {
		// outer loop iterates through all the buckets in the hashTable array
		for(int i=0; i<capacity; i++) {
			// if the bucket is not empty
			if(table[i]!=null) {
				// get the current bucket and store it in bucket variable
				LinkedList<Entry<K, V>> bucket=table[i];
				// iterate through the current bucket (the linkedList) 
				for(Entry<K, V> entry : bucket) {
					// if the value in the current entry of the bucket matches the specified value, return true
					if(entry.getValue().equals(value)) {
						return true;
					}
				}
			}
		}
		// value was not found, return false
		return false;
	}

	/**
	 * Returns a set of the entries in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public Set<Entry<K, V>> entrySet() {
		// defines set that will be used to store the entries in this map
		Set<Entry<K, V>> entrySet = new HashSet<>();
		
		// this for loop iterates trough all elements in the hash table (the buckets)
		for(int i=0; i<capacity; i++) {
			// if the current bucket is not empty:
			if(table[i]!=null) {
				// store the current element in the hashTable (the current bucket) in the bucket variable
				LinkedList<Entry<K, V>> bucket = table[i];
				
				// this for loop iterates through all the entries in the current bucket and adds it to the entrySet
				for(Entry<K, V> entry : bucket) {
					entrySet.add(entry);
				}
			}
		}
		return entrySet;
	}

	/**
	 * Returns the value that matches the specified key, key
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public V get(K key) {
		// calls the hash() method which returns the index of the bucket associated with the specified key
		int bucketIndex = hash(key.hashCode());
		// if that bucket is not empty:
		if(table[bucketIndex] != null) {
			// get the bucket and store it in bucket 
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			// iterate through all entries in the bucket
			for(Entry<K, V> entry : bucket) {
				// when the key for the current entry matches the specified key, return the value for that entry
				if(entry.getKey().equals(key)) {
					return entry.getValue();
				}
			}
		}
		// no matches, return null
		return null;
	}

	/**
	 * Returns true if the map is empty
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	/**
	 * Returns a set of the entries in the map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public Set<K> keySet() {
		// defines and initializes a hashSet used to store they keys
		Set<K> keySet = new HashSet<>();
		
		// iterates through all buckets in the hashTable
		for(int i=0; i<capacity; i++) {
			// if the current bucket is not empty
			if(table[i] != null) {
				// store the current bucket in bucket
				LinkedList<Entry<K, V>> bucket = table[i];
				// iterate through all entries of the current bucket
				for(Entry<K, V> entry : bucket) {
					// add each key to the keySet
					keySet.add(entry.getKey());
				}
			}
		}
		return keySet;
	}

	/**
	 * Adds a key-value entry to the map. If there is already a value associated with the specified key, it replaces
	 * that value with the specified value and returns the old value. If there are no values associated with the specified key
	 * it creates a new entry and returns the new value.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public V put(K key, V value) {
		//If there is already a value associated with the specified key
		if(get(key) !=null) {
			// call the hash() method to get the index of the bucket that holds that key
			int bucketIndex = hash(key.hashCode());
			// get the bucket from the hash table associated with the specified key and store it in bucket
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			
			// iterate through all entries in that bucket
			for(Entry<K, V> entry : bucket) {
				// if the key of the current entry matches the specified key:
				if(entry.getKey().equals(key)) {
					// store the old value associated with that key
					V oldValue = entry.getValue();
					// replace the old value associated with that key with the new value
					entry.value= value;
					
					// return the old value
					return oldValue;
				}
			}
		}
		
		// if there is not a value already associated with that key, we must add a new entry to the map
		
		//Check if rehashing is necessary or if the hashMap has reached max capacity
		if(size >= capacity*loadFactorThreshold) {
			if(capacity == MAXIMUM_CAPACITY) {
				throw new RuntimeException("Exceeding maximim capacity.");
			}
			rehash();
		}
		
		// Get the bucket index associated with the specified key by calling the hash function
		int bucketIndex = hash(key.hashCode());
		
		// If the bucket is empty
		if(table[bucketIndex] == null) {
			// add a new LinkedList of entries to that bucket
			table[bucketIndex] = new LinkedList<Entry<K, V>>();
		}
		
		// add a new entry with the specified key-value pair to the linkedList in that bucket 
		table[bucketIndex].add(new ArhianMap.Entry<K, V>(key, value));
		
		// increment size
		size++;
		
		// return the new value
		return value;
	}

	/**
	 * Removes the entry from the map that is associated with the specified key.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public void remove(K key) {
		// Get the bucket index associated with the specified key by calling the hash function
		int bucketIndex = hash(key.hashCode());
		
		// if the bucket in that index is not empty:
		if(table[bucketIndex] != null) {
			// get the bucket in that index and store it in bucket
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			
			// iterate trhough all entries in the bucket
			for(Entry<K, V> entry : bucket) {
				// if the current entry's key matches the specified key:
				if(entry.getKey().equals(key)) {
					// remove the current entry from the bucket
					bucket.remove(entry);
					// decrement the size of the map and return
					size--;
					break;
				}
			}
		}
		
	}

	/**
	 * Returns the number of entries in this map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns a set of values in this map.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public Set<V> values() {
		// set where the values in the map will be store
		Set<V> valueSet = new HashSet<>();
		
		// iterates through all buckets in the hashTable
		for(int i=0; i< capacity; i++) {
			// if the curernt bucket is not empty:
			if(table[i] != null) {
				// get the current bucket and store it in bucket
				LinkedList<Entry<K, V>> bucket = table[i];
				
				// iterate trhough the entire bucket:
				for(Entry<K, V> entry : bucket) {
					// add the current entry's value to the valueSet
					valueSet.add(entry.getValue());
				}
			}
		}
		return valueSet;
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
	 *Removes all entries from each bucket
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void removeEntries() {
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
	 * Rehashes this map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	private void rehash() {
		// get all current entries by calling entrySet() method and store them in entrySet
		Set<Entry<K, V>> entrySet = entrySet();
		
		// set new capacity as twice the old capacity
		capacity <<=1; // same as capacity*=2
		
		// re-build new hash table with the new capacity
		table = new LinkedList[capacity];
		
		// reset the size to 0
		size=0;
		
		// iterate trhough all entries in the entrySet: 
		for(Entry<K, V> entry : entrySet) {
			// add each entry into the new hash table
			put(entry.getKey(), entry.getValue());
		}
	}
	
	/**
	 * returns a string of all entries in this map
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	@Override
	public String toString() {
		// declares and initializes string builder
		StringBuilder builder = new StringBuilder("[");
		
		// iterates through all buckets in the hash table
		for(int i=0; i<capacity; i++) {
			// if the current bucket is not empty: 
			if(table[i] != null && table[i].size() > 0) {
				// add the entry to the string builder instance
				for(Entry<K, V> entry : table[i]) {
					builder.append(entry);
				}
			}
		}
		
		builder.append("]");
		//return the string of the string builder instance
		return builder.toString();
	}

}
