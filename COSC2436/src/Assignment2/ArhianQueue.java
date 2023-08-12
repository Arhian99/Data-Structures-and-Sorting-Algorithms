package Assignment2;

public class ArhianQueue<E> {
	private ArhianLinkedList<E> list=new ArhianLinkedList<>();;
	
	//no-arg constructor 
	public ArhianQueue() {
	}
	
	public ArhianQueue(E[] objects) {
		for(E element : objects) {
			enqueue(element);
		}
	}
	//adds an element to the queue (to the end)
	public void enqueue(E e) {
		list.addLast(e);
	}
	
	//removes an element from the queue (from the beginning)
	public E dequeue() {
		return list.removeFirst();
	}
	
	//returns size of list
	public int getSize() {
		return list.size();
	}
	
	public void ArhianPrint() {
		System.out.print("QUEUE = [");
		list.ArhianPrintQueueHelper();
		System.out.print("] \n");
		System.out.println();
	}

}
