package Assignment1;

//Imports the class needed to manipulate a file
import java.io.File;
//Imports the class needed to throw an exception if file with specified path is not found
import java.io.FileNotFoundException;
//Imports the class needed to create new LinkedHashMap instance used to create arhianMap
import java.util.LinkedHashMap;
//Imports the class needed to create new LinkedHashSet instance used to create arhianSet
import java.util.LinkedHashSet;
//Imports the class needed to create new LinkedList instance used to create arhianList
import java.util.LinkedList;
//Imports the class needed to create new List instance used to create List iterator for arhianList
import java.util.ListIterator;
//Imports the class needed to read data from the file
import java.util.Scanner;

/**
 * This class reads all the contents from a file and creates a linked list (ArhianList) containing each word read from the file.
 * Then mutates that list replacing each element with its capitalized version. Then creates a set (ArhianSet) from the mutated ArhianList.
 * Lastly it creates a map (ArhianMap) with the elements in ArhianSet as keys and the number of times said elements appear in ArhianList as values.
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianAssignment1 {

	/**
	 * The main method creates an object instance (handle) of the containing class (ArhianAssignment1) and uses it to call ArhianCreateList(), ArhianProcessList(),
	 * ArhianCreateSet(), and ArhianCreateMap(). 
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public static void main(String[] args) {
		// Creates object instance of ArhianAssignment1
		ArhianAssignment1 handle = new ArhianAssignment1();
		
		// Calls ArhianCreateList() method and stores the returned LinkedList in ArhianList
		LinkedList<String> ArhianList = handle.ArhianCreateList();

		/*
		 *  Calls ArhianProcessList() and passes in ArhianList as a parameter. As a result, ArhianProcessList
		 *  mutates ArhianList and replaces each of its elements with their capitalized versions
		 */
		handle.ArhianProcessList(ArhianList);

		/*
		 * calls ArhianCreateSet(), passes in ArhianList as a parameter. As a result, ArhianCreateSet() returns a 
		 * set from the given list. The returned set is stored in ArhianSet
		 */
		LinkedHashSet<String> ArhianSet = handle.ArhianCreateSet(ArhianList);

		/*
		 * calls ArhianCreateMap, passing in ArhianList and ArhianSet as parameters. As a result, ArhianCreateMap()
		 * returns a map (ArhianMap) with the elements of ArhianSet as keys and the number of times each of those elements
		 * appears in ArhianList as the values in ArhianMap.
		 */
		LinkedHashMap<String, Integer> ArhianMap = handle.ArhianCreateMap(ArhianList, ArhianSet);
	}
	
	/**
	 * This method takes a string as a parameter and returns that string in capital letters
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param myStr: a String
	 * @return myStr capitalized 
	 */
	public String ArhianRecursion(String myStr) {
		//This line of code takes the first character in myStr, makes it into upper case and stores it in firstLetter
		String firstLetter = myStr.substring(0, 1).toUpperCase();
		// this line of code takes the rest of the string (excluding the first character) and stores it in rest
		String rest = myStr.substring(1);
		// this conditional represents the base case. When rest is an empty string, we have reached the end of the string
		if(rest.equals("")) {
			return firstLetter;
		}
		// this is the recursive and reduction step, return the first letter in capital form and call the method itself for the rest of the string
		return firstLetter+ArhianRecursion(rest);
	}
	
	
	/**
	 * This method reads the file named "Assignment1DataFile.txt" in the path /Users/arhian99/Downloads and adds each word
	 * to ArhianList which is a linked list. Then it prints all the elements in the list to the console and returns the list. 
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @returns LinkedList<String> which is a linked list of all the words read from the specified file
	 */
	public LinkedList<String> ArhianCreateList() {
		/*
		 * Creates a File object instance using the absolute path in which the file is saved
		 * NOTE: this path is where the file is located in my local machine, it must be adjusted accordingly for other machines
		 */
		File file = new File("/Users/arhian99/Downloads/Assignment1DataFile.txt");
		//Declares the Scanner object instance input which will later be used to read an input stream from the file created above
		Scanner input = null;
		
		/*
		 *  this try block tries to initialize a Scanner object with the file object created previously, if the file does not exists
		 *  the program will throw a FileNotFound exception, in which case (in the catch block) we alert the user the file does not exist
		 *  by printing an error message to the console and return from this method.
		 */
		
		try {
			// initializes the Scanner object instance with the file created previously to read the file as an input stream
			input = new Scanner(file);
			
			/** The following line sets the delimiters that will be used to separate the words read from the file
			 *  The regular expression fed into the useDelimiter() method can be interpreted as follows:
			 *  (?![\\-+#]) is a negative look-ahead which excludes the characters "-", "+", and "#" from being used as delimiters.
			 *  [\\p{P}\\s\\n\\r\\t]+ this part matches punctuation ( \p{P} ), whitespace ( \s ), line feed ( \n ), carriage return ( \r ), or tab ( \t ) characters
			 *  In conclusion, the regular expression matches any characters including punctuation marks, whitespace characters, 
			 *  line feed, carriage returns, and tabs, but excludes characters "-", "+", and "#" from being used as delimiters.
			*/ 
			input.useDelimiter("(?![\\-+#])[\\p{P}\\s\\n\\r\\t]+");
		} catch (FileNotFoundException e) {
			// if the file does not exist, print this line to the console and return from the method
			System.out.println(e.getMessage());
			System.out.println(file+" does not exists.");
			return null;
		}
		
		/*
		 * Declares and initializes a LinkedList which will be used to store all the words read from the specified file
		 * Chose LinkedList because we don't know  how many words are in the file (so we don't know the size of the list before hand),
		 * Linked lists can resize dynamically without performance cost, while ArrayLists have a performance cost each resize
		 */
		LinkedList<String> ArhianList = new LinkedList<>();
		
		// checks if the input stream from the file still has data
		while(input.hasNext()) {
			// adds each of words read from the file to the end of arhianList
			ArhianList.add(input.next());
		}
		// prints each of the elements in the list to the console
		System.out.println("LIST: ");
		ArhianList.forEach(element -> System.out.println(element));
		System.out.println();
		
		//closes the file and returns the list
		input.close();
		return ArhianList;
	}
	
	
	/**
	 * This method takes a linkedList as a parameter, it iterates through all the elements in the list and for each of the elements
	 * it calls the method ArhianRecursion() to turn the element into upper case then mutates the 
	 * original linked list replacing the current element with its upper case version. Lastly it prints the 
	 * newly mutated linked list to the console.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param LinkedList<String> a linked list of Strings
	 * 
	 */
	public void ArhianProcessList(LinkedList<String> ArhianList) {
		// Instantiates a ListIterator for ArhianList
		ListIterator<String> iterator = ArhianList.listIterator();
		
		// Declares a String variable that will be used to store the capitalized version of the word from ArhianList
		String capitalizedWord;
		
		// iterates through all the elements in ArhianList
		while(iterator.hasNext()) {
			/*
			 * calls the ArhianRecursion() method using each element from ArhianList as a parameter to 
			 * turn each element into upper case then stores it in capitalizedWord
			 */ 
			capitalizedWord= ArhianRecursion(iterator.next());
			// mutates the current element in ArhianList, replacing it with capitalizedWord 
			iterator.set(capitalizedWord);
		}
		
		//prints the newly mutated ArhianList to the console
		System.out.println("CAPITALIZED LIST: ");
		ArhianList.forEach(element -> System.out.println(element));
		System.out.println();
		// this method simply mutates ArhianList by replacing each element with their capitalized versions, it does not return anything
		return;
	}
	
	
	/**
	 * This method takes in a Linked list of strings as a parameter and returns a LinkedHashSet of strings
	 * made from the LinkedList
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param LinkedList<String> a linked list of strings
	 * @return LinkedHashSet<String> a linked hash set of strings made from the list that was passed in
	 */
	public LinkedHashSet<String> ArhianCreateSet(LinkedList<String> ArhianList) {
		/*
		 * This line declares and initializes ArhianSet which is a set created from ArhianList
		 * Chose LinkedHashSet so it maintains the insertion order (except for repeated words)
		 */
		LinkedHashSet<String> ArhianSet = new LinkedHashSet<>(ArhianList);
		
		// Prints each element in ArhianSet to the console and returns the set.
		System.out.println("SET: ");
		ArhianSet.forEach(element -> System.out.println(element));
		System.out.println();
		
		return ArhianSet;
	}
	
	
	/**
	 * This method initializes ArhianMap and populates it with all the elements from ArhianSet as the keys and 
	 * the number of times those elements appear in ArhianList as the values. Then prints said map to the console
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public LinkedHashMap<String, Integer> ArhianCreateMap(LinkedList<String> ArhianList, LinkedHashSet<String> ArhianSet) {

		/* 
		 * Declares and initializes ArhianMap using the size of ArhianSet as the capacity. ArhianMap will be used to store a map created from ArhianSet. 
		 * The map contains each word in ArhianSet as the key and the number of times that word appears in ArhianList as the value.
		 */
		LinkedHashMap<String, Integer> ArhianMap = new LinkedHashMap<>(ArhianSet.size());
		
		/* 
		 * This for loop iterates through all the elements in ArhianSet, and puts them inside ArhianMap as keys
		 * with an initial value of 0 for all of them.
		 */
		for(String setElement : ArhianSet) {
			ArhianMap.put(setElement, 0);
		}
		
		/*
		 * This method iterates through ArhianList, checks if the each element from ArhianList is a key in ArhianMap,
		 * and if so, it increments the value for that key by 1
		 */
		for(String listElement : ArhianList) {
			// checks if the current element from ArhianList is a key in ArhianMap
			if(ArhianMap.containsKey(listElement)) {
				// Queries ArhianMap to get the value corresponding to the current listElement
				int value = ArhianMap.get(listElement);
				// updates the entry in ArhianMap by incrementing the value by 1
				ArhianMap.put(listElement, ++value);
			}
		}
		
		// Print all the elements in ArhianMap to the console
		System.out.println("MAP: ");
		ArhianMap.forEach((key, value) -> System.out.println(key+" : "+value));
		System.out.println();
		
		return ArhianMap;
	}
}