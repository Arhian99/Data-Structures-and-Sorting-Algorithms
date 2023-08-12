package Assignment2;

import java.util.Arrays;
import java.util.Random;

/**
 * This class has a constructor that takes an array as a parameter and creates an array named Array 
 * from the array that was passed into the constructor. Alternatively it also has a no-arg constructor 
 * that creates an empty array of 10 integer named Array. It also has methods ArhianSort(), ArhianBubbleSort(), 
 * ArhianMergeSort(), and ArhianQuickSort() which all sort Array using insertion, bubble, merge, and quick sort,
 * respectively. It also contains ArhianPrint() which prints Array to the console. And ArhianSortAlgorithmComplexity()
 * which outputs the time complexity and and explanation of each of the sorting methods mentioned above.
 * @author Arhian Albis Ramos
 * @version 0.0.1
 */
public class ArhianArray {
	// Declares integer array
	int[] Array;
	
	// Constructor that takes an array as a parameter and creates Array from the array that was passsed in.
	public ArhianArray(int[] array) {
		this.Array = Arrays.copyOf(array, array.length);
	}
	
	// No-Arg constructor that declares an empty array of 10 integers and assigns it to Array
	public ArhianArray() {
		Array = new int[10];
	}
	
	/**
	 * This method takes an unsorted array of integers an sorts the array in increasing order. It mutates the 
	 * array that was passed in and returns the sorted array. Implementation of InsertionSort algorithm.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param array: an unsorted array of integers 
	 * @return array: a sorted array of integers
	 */
	public int[] ArhianInsertionSort(int[] array) {
		//the outer loop 'defines' a sorted sublist in array, which initially only holds the first element in the list
		for(int i=1; i<array.length; i++) {
			// the next element that is to be sorted (and added to the sorted sublist) is stored in currentElement
			int currentElement = array[i];
			int k;
			/*
			 *  the control variable k represents the element being iterated over currently IN the sorted sublist.
			 *  Then it checks if the currentElement (element to be sorted) is LESS THAN the current element IN the sortedList,
			 *  and if so, it keeps moving the elements in the sorted sublist to the right one index (array[k+1] = array[k];) to make space for currentElement.
			 *  it performs this loop until this condition is no longer true or until there are no more elements in the sorted sublist
			 *  to move to the right (k<0)
			 */
			for(k=i-1; k>=0 && array[k]>currentElement; k--) {
				array[k+1] = array[k];
			}
			// Places currentElement in the appropriate index in the sorted sublist
			array[k+1]= currentElement;
		}
		//returns sorted array
		return array;
	}
	
	/**
	 * This method takes an unsorted array of integers an sorts the array in increasing order. It mutates the 
	 * array that was passed in and returns the sorted array. Implementation of BubbleSort algorithm
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param array: an unsorted array of integers 
	 * @return array: a sorted array of integers
	 */
	public int[] ArhianBubbleSort(int[] array) {
		/*
		 * This algorithm passes through the array several times, and compares neighboring elements.
		 * If elements are in decreasing order they are swapped. This is repeated until the array is sorted.
		 * If no swap take place in a pass, the elements of the array are already sorted therefore no need to 
		 * perform a successive pass. This variable stores this last property as a boolean value 
		 */
		boolean needNextPass = true;
		/*
		 * The outer loop 'keeps track of' the elements that are already sorted (the number of passes that have been
		 * Performed) with the control variable k. After the first pass, the largest element will be the last element
		 * in the array so in the next pass there is no need to go all the way to the end. This is why in the inner loop
		 * only iterates from i=0 to i= array.length-k 
		 */
		for(int k=1; k<array.length && needNextPass; k++) {
			needNextPass = false;
			// iterates from i=0 to the last non-sorted element in the array
			for(int i=0; i<array.length-k; i++) {
				// Checks if two successive elements are in decreasing order
				if(array[i]>array[i+1]) {
					// the next three lines swap two successive elements in array
					int temp=array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					
					// since a swap has been performed, the array is not yet sorted therefore another pass is needed
					needNextPass = true;
				}
			}
		}
		// returns sorted array
		return array;
	}
	
	
	
	
	/**
	 * This method takes an unsorted array of integers and sorts the array in increasing order. It mutates the 
	 * array that was passed in and returns the sorted array. Implementation of MergeSort algorithm
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param array: an unsorted array of integers 
	 * @return array: a sorted array of integers
	 */
	public int[] ArhianMergeSort(int[] array) {
		/*
		 *  if the array passed in has more than 1 element, then break it in half, call this same method recursively for each half
		 *  then call the mergeSortHelperMerge() method to merge the two halves
		 */
		if(array.length > 1) {
			// Declare and initialize an integer array which will store the first half of the array that was passed in
			int[] firstHalf = new int[array.length / 2];
			// copy the first half of the array that was passed in into firstHalf (declared above)
			System.arraycopy(array, 0, firstHalf, 0, array.length/2);
			//call this method recursively on the first half
			ArhianMergeSort(firstHalf);
			
			// compute the length of the second half of the array that was passed in
			int secondHalfLength = array.length - array.length/2;
			// Declare and initialize an integer array which will store the second half of the array that was passed in
			int[] secondHalf = new int[secondHalfLength];
			// copy the second half of the array that was passed in into secondHalf (declared above)
			System.arraycopy(array, array.length/2, secondHalf, 0, secondHalfLength);
			//call this method recursively on the second half
			ArhianMergeSort(secondHalf);
			
			//calls the mergeSortHelperMerge method to merge firstHalf and secondHalf into array
			mergeSortHelperMerge(firstHalf, secondHalf, array);
		}
		
		//returns sorted array
		return array;
	}
	
	
	/**
	 * This is a helper method for the ArhianMergeSort() method. It takes 3 arrays as parameters, the first two (array1 and array2)
	 * are the two arrays you would like to merge. The third array is the array which stores the merged array1 and array2 
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param array1: an array to be merged
	 * @param array2: an array to be merged
	 * @param temp: an array which holds the sorted merged array1 and array2
	 */
	public void mergeSortHelperMerge(int[] array1, int[] array2, int[] temp) {
		// These three variables keep the current index being considered for array1, array2, and temp respectively.
		int current1=0;
		int current2=0;
		int current3=0;
		
		// Checks if there are still elements to consider inside BOTH of the arrays to be merged
		while(current1< array1.length && current2<array2.length) {
			
			/*
			 * if the current element from array1 is less than the current element from array2, then place the current element of array1
			 * into the next open index at temp AND increment the the index control variables so it points to the next available index
			 * in both temp and array1.
			 */
			if(array1[current1] < array2[current2]) {
				temp[current3++] = array1[current1++];
			} 
			/*
			 * otherwise place the current array2 element into the next available index at temp first AND increment both index control variables
			 * accordingly.
			 */
			else {
				temp[current3++] = array2[current2++];
			}
		}
		
		//After comparing all elements in either array1 or array2 place the remainder of the elements in either of the arrays into the temp array
		while(current1<array1.length) {
			temp[current3++] = array1[current1++];
		}
		
		//After comparing all elements in either array1 or array2 place the remainder of the elements in either of the arrays into the temp array
		while(current2<array2.length) {
			temp[current3++] = array2[current2++];
		}
	}
	
	
	
	/**
	 * This method takes an unsorted array of integers and sorts a sub-array of that array. The sub-array to be sorted is indicated by the firstIndex and 
	 * lastIndex parameters that are passed in. Implementation of QuickSort algorithm
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param array: an unsorted array of integers
	 * @param: firstIndex: integer that indicates index of first element of the array that is to be sorted 
	 * @param: lastIndex: integer that indicates index of last element of the array that is to be sorted 
	 */
	
	public void ArhianQuickSort(int[] array, int firstIndex, int lastIndex) {
		// checks if the array to be sorted has more than one element (if lastIndex=firstIndex, the array to be sorted has only one element, in which case its already sorted)
		if(lastIndex>firstIndex) {
			// calls the quickSortHelperPartition() method which picks a pivot, places it in the right (sorted) spot in array and returns the index of that pivot, which is stored in the variable below
			int pivotIndex = quickSortHelperPartition(array, firstIndex, lastIndex);
			// the method recursively calls itself for the sub-array that is to the left of the pivot
			ArhianQuickSort(array, firstIndex, pivotIndex-1);
			// the method recursively calls itself for the sub-array that is to the right of the pivot
			ArhianQuickSort(array, pivotIndex+1, lastIndex);
		}
	}
	
	/**
	 * This is a helper method for the ArhianQuickSort() method. It partitions the array that was passed in into two sub-arrays by picking a pivot and placing all elements that are less than the pivot to the
	 * left of the pivot and all the elements that are greater than the pivot to the right of the pivot. Therefore, the pivot is placed in its final place in the sorted array. The index of the pivot is returned
	 * @version 0.0.1
	 * 
	 * @param 
	 */
	public int quickSortHelperPartition(int[] array, int firstIndex, int lastIndex) {
		// picks the first element in the sub-array as the pivot
		int pivot = array[firstIndex];
		// initializes the second element index in the sub-array as leftPtr
		int leftPtr = firstIndex+1;
		// initializes the last element index in the sub-array as the rightPtr
		int rightPtr = lastIndex;
		
		/*
		 *  Inside this while loop we will scan the sub-array from left to right (using the variable leftPtr) AND 
		 *  from right to left (using the variable rightPtr). In the nested while loops, we look for elements on the right
		 *  side that are lower than the pivot and look for elements on the left side that are greater than the pivot 
		 *  and swap these two elements with each other
		 *  
		 *  When these two pointers pass each other (rightPtr<=leftPtr)
		 *  then we know we would have scanned the entire sub-array, in which case we break out of the loop. 
		 */
		while(rightPtr>leftPtr) {
			/*
			 *  this while loop scans the sub-array from right to left until it finds an element that is LESS THAN the pivot
			 *  OR until the rightPtr passes the leftPtr as described above
			 *  
			 *  When this while loop stops, it will either be pointing at an element in the sub-array that is LESS THAN the pivot 
			 *  or the rightPtr would have passed the leftPtr
			 */
			while(leftPtr<=rightPtr && array[rightPtr]>pivot) {
				rightPtr--;
			}
			
			/*
			 *  this while loop scans the sub-array from left to right until it finds an element that is GREATER THAN the pivot
			 *  OR until the leftPtr passes the rightPtr as described above
			 *  
			 *  When this while loop stops, it will either be pointing at an element in the sub-array that is GREATER THAN the pivot 
			 *  or the leftPtr would have passed the rightPtr
			 */
			while(leftPtr<=rightPtr && array[leftPtr]<pivot) {
				leftPtr++;
			}
			
			/*
			 * if the pointers have not passed each other then the rightPtr is pointing at an element that is LESS THAN the pivot and the
			 * leftPtr is pointing at an element that is GREATER THAN the pivot.
			 */
			if(rightPtr>leftPtr) {
				// the following three lines swap the two elements with each other
				int temp = array[rightPtr];
				array[rightPtr] = array[leftPtr];
				array[leftPtr] = temp;
			}
		}
		
		/*
		 *  this while loop continues walking the rightPtr down the sub-array until either it is pointing at the first element in the subArray 
		 *  (the pivot) in which case rightPtr=firstIndex. OR until rightPtr is pointing to an element that is LESS THAN the pivot
		 */
		while(rightPtr>firstIndex && array[rightPtr]>=pivot) {
			rightPtr--;
		}
		
		/*
		 * if the rightPtr is pointing to an element that is LESS THAN the pivot, then swap the pivot (currently the first element in the sub-array) with the
		 * element that the rightPtr is currently pointing to. After the swap is done, the rightPtr will be pointing to the pivot element
		 * Then, return the rightPtr (which would be pointing now to the pivot)
		 */
		if(pivot>array[rightPtr]) {
			array[firstIndex] = array[rightPtr];
			array[rightPtr] = pivot;
			return rightPtr;
		} 
		// otherwise; the rightPtr is pointing at the firstElement in the subArray (the pivot), return it.
		else {
			return firstIndex;
		}
	}
	
	/**
	 * This is an overloaded version of the method ArhianQuickSort(), it takes an array as a parameter and calls the other version of ArhianQuickSort()
	 * using 0 as the firstIndex parameter and the last index of the array it was passed in as the lastIndex parameter.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 * 
	 * @param array: an unsorted array of integers 
	 * @return: array: a sorted array of integers
	 */
	public int[] ArhianQuickSort(int[] array) {
		ArhianQuickSort(array, 0, array.length-1);
		return array;
	}


	/**
	 * This method prints all the elements in the array
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void ArhianPrint() {
		System.out.print("ARRAY = [");
		// iterates through entire array and prints each element
		for(int i=0; i<Array.length;i++) {
			// if its the last element it does NOT append a comma to the end
			if(i==Array.length-1) {
				System.out.print(Array[i]);
			} else {
				System.out.print(Array[i]+", ");
			}
		}
		System.out.print("]");
		System.out.println();
	}
	
	/**
	 * This is a helper method which shuffles the elemnts in Array randomly.
	 * @author Arhian Albis Ramos
	 * @version 0.0.1
	 */
	public void ArhianHelperShuffle() {
		Random random = new Random();
		// 
		for(int i=0; i<Array.length;i++) {
			//chooses a random index using nextInt() method from random object
			int randomIndex = random.nextInt(Array.length);
			//swaps the current element being iterated over in Array with the element at the random index. 
			int temp = Array[randomIndex];
			Array[randomIndex] = Array[i];
			Array[i] = temp;
		}
		
	}
	
	public void ArhianSortAlgorithmComplexity() {
		System.out.println("InsertionSort: O(n^2) - Quadratic Time Complexity");
		System.out.println("BubbleSort: O(n^2) - Quadratic Time Complexity");
		System.out.println("MergeSort: O(n*log(n)) - Linear Logarithmic Time Complexity");
		System.out.println("QuickSort: O(n*log(n)) - Linear Logarithmic Time Complexity");
		System.out.println();
		System.out.println("The best algorithm is Merge Sort because its average and its worst case time complexity \n"
				+ "linear-log. The only other algorithm that approximates this is QuickSort which has a time complexity \n"
				+ "of linear-log as well for its average case scenario. However, the complexity of QuickSort is quadratic \n"
				+ "in its worst case scenerio, making MergeSort the more efficient algorithm from the 4.");
		System.out.println();
	}
}






