package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Question: You are given two arrays, one shorter (with all distinct elements) and one longer. Find the shortest subarray in the longer array that contains all the elements in the
 * shorter array. The items can appear in any order.
 * 
 * Example:
 * Input:
 * {1, 5, 9}
 * {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 5, 8, 8, 9, 7}
 * Output: [7, 10] (the underlined portion above)
 * 
 * Solution: Lets approach it in a different way than the previous approach. Let's suppose we had a list of the occurrence of each element in smallArray
 * 
 * value : 7	5	9	9	2	1	3	5	7	9	1	1	5	8	8	9	7
 * index : 0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16
 * 
 * 1 -> {5,10,11}
 * 5 -> {1,7,12}
 * 9 -> {2,3,9,15}
 * We can just look at the heads of each list to tell us this. The minimum of the heads is the start of the range and the max of the heads is the end of the range. In this case, the first
 * range is [1,5]. This is currently our 'best' subsequence.
 * 
 * How can we find the next one? Well, the next one will not include index 1, so let's remove that from the list.
 * 1 -> {5, 10, 11}
 * 5 -> {7,12}
 * 9 -> {2,3,9,15}
 * The next subsequence is [2,7]. This is worse than the earlier best, so we can toss it.
 * Now, what's the next subsequence? We can remove the min from earlier (2) and find out
 * 
 * 1 -> {5,10,11}
 * 5 -> {7,12}
 * 9 -> {3,9,15}
 * 
 * The next subsequence is [3,7], which is no better or worse than our current best.
 * We can continue down this path each time, repeating this process. We will end up iterating through all 'minimal' subsequences that start from a given point.
 * 
 * 1. Current subsequence is [min of heads, max of heads]. Compare to best subsequences and update if necessary
 * 2. Remove the minimum head
 * 3. Repeat.
 * 
 * We can make this a bit faster by using a min-heap. First, put each of the heads in a min-heap. Remove the minimum. Look up the list that this minimum came from and add back the new head. Repeat
 * To get the list that the minimum element came from, we'll need to use a HeapNode class that stores both the locationWithinList (the index) and the listId. This way,when we remove the minimum,
 * we can jump back to the correct list and add its new head to the heap.
 */

public class ShortestSuperSequence {
	Range shortestSuperSequence(int[] array, int[] elements){
		ArrayList<Queue<Integer>> locations = getLocationsForElements(array, elements);
		if(locations == null)
			return null;
		return getShortestClosure(locations);
	}
	
	//Get list of queues (linked lists) storing the indices at which each element in smallArray appears in bigArray
	ArrayList<Queue<Integer>> getLocationsForElements(int[] big, int[] small){
		//Initialize hash map from item value to locations
		HashMap<Integer, Queue<Integer>> itemLocations = new HashMap<Integer, Queue<Integer>>();
		for(int s : small){
			Queue<Integer> queue = new LinkedList<Integer>();
			itemLocations.put(s, queue);
		}
		
		//Walk through big array, adding the item locations to hash map
		for(int i = 0; i < big.length; i++){
			Queue<Integer> queue = itemLocations.get(big[i]);
			if(queue != null){
				queue.add(i);
			}
		}
		
		ArrayList<Queue<Integer>> allLocations = new ArrayList<Queue<Integer>>();
		allLocations.addAll(itemLocations.values());
		return allLocations;
	}
	
	//Get queue (linked list) of the indices at which this element appears in the big array
	Queue<Integer> getLocations(int[] big, int small){
		Queue<Integer> locations = new LinkedList<Integer>();
		for(int i = 0; i < big.length; i++){
			if(big[i] == small){
				locations.add(i);
			}
		}
		return locations;
	}
	
	Range getShortestClosure(ArrayList<Queue<Integer>> lists){
		PriorityQueue<HeapNode> minHeap = new PriorityQueue<HeapNode>();
		int max = Integer.MIN_VALUE;
		
		//Insert min element from each list
		for(int i = 0; i < lists.size(); i++){
			int head = lists.get(i).remove();
			minHeap.add(new HeapNode(head, i));
			max = Math.max(max, head);
		}
		
		int min = minHeap.peek().locationWithinList;
		int bestRangeMin = min;
		int bestRangeMax = max;
		
		while(true){
			//Remove min node
			HeapNode n = minHeap.poll();
			Queue<Integer> list = lists.get(n.listId);
			
			//Compare range to best range
			min = n.locationWithinList;
			if(max - min < bestRangeMax - bestRangeMin){
				bestRangeMax = max;
				bestRangeMin = min;
			}
			
			//If there are no more elements, then there's no more subsequences and we can break
			if(list.size() == 0){
				break;
			}
			
			//Add new head of list to heap
			n.locationWithinList = list.remove();
			minHeap.add(n);
			max = Math.max(max, n.locationWithinList);
		}
		return new Range(bestRangeMin, bestRangeMax);
	}
}

class Range{
	private int start;
	private int end;
	public Range(int s, int e){
		start = s;
		end = e;
	}
	
	public int length(){
		return end - start + 1;
	}
	
	public int getStart(){
		return start;
	}
	
	public int getEnd(){
		return end;
	}
	
	public boolean shorterThan(Range other){
		return length() < other.length();
	}
}

class HeapNode implements Comparable<HeapNode> {
	public int locationWithinList;
	public int listId;
	
	public HeapNode(int location, int list) {
		locationWithinList = location;
		listId = list;
	}

	@Override
	public int compareTo(HeapNode n) {
		return locationWithinList - n.locationWithinList;
	}	
}
