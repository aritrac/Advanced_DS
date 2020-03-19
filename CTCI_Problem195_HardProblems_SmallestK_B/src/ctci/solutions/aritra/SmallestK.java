package ctci.solutions.aritra;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * Question: Design an algorithm to find the smallest K numbers in an array
 * 
 * Solution: We can use a max heap to solve this problem. We first create a max heap (largest element at the top) for the first million numbers.
 * Then we traverse through the list. On each element, if it's smaller than the root, we insert it into the heap and delete the largest element (which will be the root)
 * At the end of the traversal, we will have a heap containing the smallest one million numbers. 
 * 
 * Java uses the PriorityQueue class to offer heap-like functionality. By default, it operates as a min heap, with the smallest element on top. To switch
 * it to the biggest element on the top, we can pass in a different comparator.
 */

public class SmallestK {
	int[] smallestK(int[] array, int k){
		if(k <= 0 || k > array.length){
			throw new IllegalArgumentException();
		}
		
		PriorityQueue<Integer> heap = getKMaxHeap(array,k);
		return heapToIntArray(heap);
	}
	
	//Create max heap of smallest k elements
	PriorityQueue<Integer> getKMaxHeap(int[] array, int k){
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new MaxHeapComparator());
		for(int a: array){
			if(heap.size() < k){//If space remaining
				heap.add(a);
			}else if(a < heap.peek()){	//If full and top is greater than the incoming element
				heap.poll(); //remove highest
				heap.add(a); //Insert new incoming element
			}
		}
		return heap;
	}
	
	//Convert heap to int array
	int[] heapToIntArray(PriorityQueue<Integer> heap){
		int[] array = new int[heap.size()];
		while(!heap.isEmpty()){
			array[heap.size() - 1] = heap.poll();
		}
		return array;
	}
}

class MaxHeapComparator implements Comparator<Integer>{
	public int compare(Integer x, Integer y){
		return y - x;
	}
}
