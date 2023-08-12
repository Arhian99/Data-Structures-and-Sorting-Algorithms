package Assignment2;

import java.util.List;
import java.util.Scanner;

public class ArhianAssignment2 {

	public static void main(String[] args) {
		// instantiates scanner to read input stream from console
		Scanner input = new Scanner(System.in);
		//declares and initializes int array which is used to instantiate ArhianArray, ArhianArrayList, ArhianLinkedList, and ArhianQueue
		int[] array = new int[10];
		
		//reads 10 integers from the console and places them in array
		System.out.println("Please enter any 10 integers: ");
		for(int i=0; i<10; i++) {
			array[i]= input.nextInt();
		}
		
		input.close();
		
		/*
		 * ArhianArray 
		 */
		System.out.println("-------------------ArhianArray--------------------------------------------------------");
		//instantiates ArhianArray with array (from above)
		ArhianArray ArhianArray = new ArhianArray(array);
		System.out.println("Initial Array: ");
		//prints Array in ArhianArray
		ArhianArray.ArhianPrint();
		System.out.println();
		
		ArhianArray.ArhianInsertionSort(ArhianArray.Array);
		System.out.println("Array after ArhianInsertionSort call: ");
		ArhianArray.ArhianPrint();
		System.out.println();
		
		ArhianArray.ArhianHelperShuffle();
		System.out.println("Array after Shuffling:");
		ArhianArray.ArhianPrint();
		System.out.println();
		
		ArhianArray.ArhianBubbleSort(ArhianArray.Array);
		System.out.println("Array after ArhianBubbleSort call: ");
		ArhianArray.ArhianPrint();
		System.out.println();
		
		ArhianArray.ArhianHelperShuffle();
		System.out.println("Array after Shuffling:");
		ArhianArray.ArhianPrint();
		System.out.println();
		
		ArhianArray.ArhianMergeSort(ArhianArray.Array);
		System.out.println("Array after ArhianMergeSort call: ");
		ArhianArray.ArhianPrint();
		System.out.println();
		
		ArhianArray.ArhianHelperShuffle();
		System.out.println("Array after Shuffling:");
		ArhianArray.ArhianPrint();
		System.out.println();
		
		ArhianArray.ArhianQuickSort(ArhianArray.Array);
		System.out.println("Array after ArhianQuickSort call: ");
		ArhianArray.ArhianPrint();
		System.out.println();
		
		System.out.println("ArhianSortAlgorithmComplexity call:");
		ArhianArray.ArhianSortAlgorithmComplexity();
		
		/*
		 * ArhianArrayList
		 */
		System.out.println("-------------------ArhianArrayList--------------------------------------------------------");
		//Instantiates Integer array that will be used to instantiate ArhianArrayList 
		Integer[] arrayINT = new Integer[10];
		// casts all elements of array (int's) to Integers and places them in arrayINT
		for(int i=0; i<array.length; i++) {
			arrayINT[i] = (Integer) array[i];
		}
		// Uses ArrayINT to instatiate ArhianArrayList
		ArhianArrayList<Integer> ArhianArrayList = new ArhianArrayList<>(arrayINT);
		System.out.println("Initial ArhianArrayList: ");
		ArhianArrayList.ArhianPrint();
		System.out.println();
		
		// removes the element at index 3
		ArhianArrayList.remove(3);
		System.out.println("ArrayList after remove(3) method call: ");
		ArhianArrayList.ArhianPrint();
		System.out.println();
		
		//adds integer 14 to the back of the ArrayList
		ArhianArrayList.add(14);
		System.out.println("ArrayList after add(14) method call: ");
		ArhianArrayList.ArhianPrint();
		System.out.println();
		
		// replaces the current element at index 6 with 14
		System.out.println("ArrayList after set(6, 14) method call: ");
		ArhianArrayList.set(6, 14);
		ArhianArrayList.ArhianPrint();
		System.out.println();
		
		// checks if ArhianArrayList contains the value 14 and prints the index to the console if it does
		System.out.println("Demonstration of contains() and indexOf() and lastIndexOf() methods");
		if(ArhianArrayList.contains(14)) {
			System.out.println("Index of 14: "+ArhianArrayList.indexOf(14));
			System.out.println("Last Index of 14: "+ArhianArrayList.lastIndexOf(14)+"\n");
		}
		
		System.out.println("Demonstration of sizes() and get() methods");
		//prints last value in the array
		System.out.println("Last value in the array: "+ArhianArrayList.get(ArhianArrayList.size()-1)+"\n");

		
		System.out.println("Demonstration of containsAll() and removeAll() methods");
		List<Integer> containsTest = List.of(1, 5);
		//checks if the list containsTest is contained inside ArhianArrayList, if so, it removes all the elements in containsTest from ArhianArrayList
		if(ArhianArrayList.containsAll(containsTest)) {
			ArhianArrayList.removeAll(containsTest);
		}
		System.out.println("ArrayList after removeAll() method call: ");
		ArhianArrayList.ArhianPrint();
		System.out.println();
		
		System.out.println("Demonstration of addAll() method");
		List<Integer> test = List.of(-1, -2);
		ArhianArrayList.addAll(test);
		System.out.println("ArrayList after addAll() method call: ");
		ArhianArrayList.ArhianPrint();
		System.out.println();
		
		/*
		 * ArhianLinkedList
		 */
		// Uses ArrayINT to instatiate ArhianLinkedList
		System.out.println("-------------------ArhianLinkedList--------------------------------------------------------");
		ArhianLinkedList<Integer> ArhianLinkedList = new ArhianLinkedList<>(arrayINT);
		System.out.println("Initial ArhianLinkedList: ");
		ArhianLinkedList.ArhianPrint();
		
		System.out.println("Demonstration of getFirst(), getLast(), and get() methods");
		System.out.println("The first element in ArhianLinkedList is: "+ArhianLinkedList.getFirst());
		System.out.println("The last element in ArhianLinkedList is: "+ArhianLinkedList.getLast());
		System.out.println("The element at index 3 in ArhianLinkedList is: "+ArhianLinkedList.get(3));
		System.out.println();
		
		System.out.println("Demonstration of removeFirst() and removeLast() methods");
		ArhianLinkedList.removeFirst();
		ArhianLinkedList.removeLast();
		System.out.println("ArhianLinkedList after removeFirst() and removeLast() method calls");
		ArhianLinkedList.ArhianPrint();
		
		System.out.println("Demonstration of addFirst() and addLast() methods");
		ArhianLinkedList.addFirst(25);
		ArhianLinkedList.addLast(50);
		System.out.println("ArhianLinkedList after addFirst(25) and addLast(50) method calls");
		ArhianLinkedList.ArhianPrint();
		
		System.out.println("Demonstration of add() and remove() methods");
		int elementSix = ArhianLinkedList.remove(6);
		System.out.println("ArhianLinkedList after remove(6) method call");
		ArhianLinkedList.ArhianPrint();
		
		ArhianLinkedList.add(6, elementSix);
		System.out.println("ArhianLinkedList after add(6, elementSix) method call");
		ArhianLinkedList.ArhianPrint();

		System.out.println("Demonstration of contains(), indexOf(), and set() methods");
		if(ArhianLinkedList.contains(50)) {
			int index50 = ArhianLinkedList.indexOf(50);
			ArhianLinkedList.set(index50-2, 50);
		}
		System.out.println("ArhianLinkedList after set() method call");
		ArhianLinkedList.ArhianPrint();
		
		System.out.println("Demonstration of lastIndexOf() methods");
		System.out.println("Last index of the value 50 is: "+ ArhianLinkedList.lastIndexOf(50));
		System.out.println();
		
		/*
		 * ArhianQueue
		 */
		// Uses ArrayINT to instantiate ArhianLinkedList
		System.out.println("-------------------ArhianQueue--------------------------------------------------------");
		ArhianQueue<Integer> ArhianQueue = new ArhianQueue<>(arrayINT);
		System.out.println("Initial ArhianQueue");
		ArhianQueue.ArhianPrint();
		
		System.out.println("Demonstration of dequeue() method");
		System.out.println("First dequeue() call: "+ArhianQueue.dequeue());
		System.out.println("Second dequeue() call: "+ArhianQueue.dequeue());
		System.out.println();
		System.out.println("ArhianQueue after 2 dequeue() method calls");
		ArhianQueue.ArhianPrint();
		
		System.out.println("Demonstration of enqueue() method");
		ArhianQueue.enqueue(-1);
		ArhianQueue.enqueue(-2);
		System.out.println("ArhianQueue after enque(-1) and enque(-2) method calls");
		ArhianQueue.ArhianPrint();
	}

}
