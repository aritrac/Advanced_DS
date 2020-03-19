package ctci.solutions.aritra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Question: Numbers are randomly generated and passed to a method. Write a program to find and maintain the median value as new values are generated.
 * 
 * Solution: One solution is to use two priority heaps: a max heap for the values below the median, and a min heap for the values above the median. This will divide the elements
 * roughly in half, with the middle two elements as the top of the two heaps. This makes it trivial to find the median.
 * What do we mean by 'roughly in half' though? 'Roughly' means that, if we have an odd number of values, one heap will have an extra value. Observe that the following is true:
 * >>If maxHeap.size() > minHeap.size(), then the average of maxHeap.top() and the minHeap.top() will be the median.
 * 
 * By the way, in which we rebalance the heaps, we will ensure that it is always maxHeap with extra element.
 * 
 * The algorithm works as follows. When a new value arrives, it is placed in the maxHeap if the value is less than or equal to the median, otherwise it is placed into the minHeap. The heap
 * sizes can be equal, or the maxHeap may have one extra element. This constraint can easily be restored by shifting an element from one heap to the other. The median is available in
 * constant time, by looking at the top elements.
 */

public class ContinuousMedian {
	private static Comparator<Integer> maxHeapComparator;
	private static Comparator<Integer> minHeapComparator;
	private static PriorityQueue<Integer> maxHeap;
	private static PriorityQueue<Integer> minHeap;

	public static void addNewNumber(int randomNumber) {
		/* Note: addNewNumber maintains a condition that maxHeap.size() >= minHeap.size() */
		if (maxHeap.size() == minHeap.size()) {
			if ((minHeap.peek() != null) && 
					randomNumber > minHeap.peek()) {
				maxHeap.offer(minHeap.poll()); //remove the top element from min heap and add it to max heap
				minHeap.offer(randomNumber); //add the random number to min heap now
			} else {
				maxHeap.offer(randomNumber);
			}
		}
		else {
			if(randomNumber < maxHeap.peek()){
				minHeap.offer(maxHeap.poll()); //remove the top element from max heap and add it to min heap
				maxHeap.offer(randomNumber); //add the random number to max heap
			}
			else {
				minHeap.offer(randomNumber);
			}
		}
	}

	public static double getMedian() {
		/* maxHeap is always at least as big as minHeap. So if maxHeap is empty, then minHeap is also. */		
		if (maxHeap.isEmpty()) {
			return 0;
		} 
		if (maxHeap.size() == minHeap.size()) {
			return ((double)minHeap.peek() + (double) maxHeap.peek()) / 2;
		} else {
			/* If maxHeap and minHeap are of different sizes, then maxHeap must have one extra element. Return maxHeap’s top element.*/			
			return maxHeap.peek();
		} 
	}

	public static void addNewNumberAndPrintMedian(int randomNumber) {
		addNewNumber(randomNumber);
		System.out.println("Random Number = " + randomNumber);
		printMinHeapAndMaxHeap();
		System.out.println("\nMedian = " + getMedian() + "\n");
	}

	public static void printMinHeapAndMaxHeap(){
		Integer[] minHeapArray = minHeap.toArray(
				new Integer[minHeap.size()]);
		Integer[] maxHeapArray = maxHeap.toArray(
				new Integer[maxHeap.size()]);

		Arrays.sort(minHeapArray, minHeapComparator);
		Arrays.sort(maxHeapArray, maxHeapComparator);
		System.out.print("MinHeap =");
		for (int i = minHeapArray.length - 1; i >= 0 ; i--){
			System.out.print(" " + minHeapArray[i]);
		}
		System.out.print("\nMaxHeap =");
		for (int i = 0; i < maxHeapArray.length; i++){
			System.out.print(" " + maxHeapArray[i]);
		}
	}

	public static void main(String[] args) {
		int arraySize = 10;
		int range = 7;

		maxHeapComparator = new MaxHeapComparator();
		minHeapComparator = new MinHeapComparator();
		maxHeap = new PriorityQueue<Integer>(arraySize - arraySize/2, maxHeapComparator);
		minHeap = new PriorityQueue<Integer>(arraySize/2, minHeapComparator);
		
		for(int i = 0; i < arraySize; i++) {
			int randomNumber = (int) (Math.random( ) * (range+1));
			addNewNumberAndPrintMedian(randomNumber);
		}
	}
}

class MaxHeapComparator implements Comparator<Integer>{
	// Comparator that sorts integers from highest to lowest
	@Override
	public int compare(Integer o1, Integer o2) {
		// TODO Auto-generated method stub
		if (o1 < o2) return 1;
		else if (o1 == o2) return 0;
		else return -1;
	}
}

class MinHeapComparator implements Comparator<Integer>{
	// Comparator that sorts integers from lowest to highest
	@Override
	public int compare(Integer o1, Integer o2) {
		if (o1 > o2) return 1;
		else if (o1 == o2)	return 0;
		else return -1;
	}
}
