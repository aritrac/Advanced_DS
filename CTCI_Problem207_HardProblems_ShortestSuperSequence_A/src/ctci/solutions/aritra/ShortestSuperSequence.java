package ctci.solutions.aritra;

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
 * Solution: The slow and easy way to do this is to iterate bigArray and do repeated small passes through it.
 * At each index in bigArray, scan forward to find the next occurrence of each element in smallArray. The largest of these next occurrences will tell us the shortest subarray that starts at that
 * index. (We'll call this concept 'closure'. That is, the closure is the element that 'closes' a complete subarray starting at that index. For example, the closure of index 3 - which has value 0
 * --in the example is index 9).
 */

public class ShortestSuperSequence {
	Range shortestSuperSequence(int[] bigArray, int[] smallArray){
		int bestStart = -1;
		int bestEnd = -1;
		for(int i = 0; i < bigArray.length; i++){
			int end = findClosure(bigArray, smallArray, i);
			if(end == -1)
				break;
			if(bestStart == -1 || end - i < bestEnd - bestStart){ //If no closure was found or if the new window of the current closure is less than the best known so far
				bestStart = i;
				bestEnd = end;
			}
		}
		return new Range(bestStart, bestEnd);
	}
	
	//Given an index, find the closure (the element which terminates a complete subarray containing all smallArray's elements). This will be the max of the next locations of each element.
	int findClosure(int[] bigArray, int[] smallArray, int index){
		int max = -1;
		for(int i = 0; i < smallArray.length; i++){
			int next = findNextInstance(bigArray, smallArray[i], index);
			if(next == -1){
				return -1;
			}
			max = Math.max(next, max);
		}
		return max;
	}
	
	//Find next instance of element starting from index.
	int findNextInstance(int[] array, int element, int index){
		for(int i = index; i < array.length; i++){
			if(array[i] == element){
				return i;
			}
		}
		return -1;
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
