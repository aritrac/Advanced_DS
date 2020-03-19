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
 * Solution: As in the previous approach, we can reduce the space usage. Actually all we need is the closure rows. We don't need to store all the other next occurrence information
 * the entire time.
 * 
 * Instead as we do each sweep, we just update the closure row with the minimums. The rest of the algorithm works essentially the same way.
 */

public class ShortestSuperSequence {
	Range shortestSuperSequence(int[] big, int[] small){
		int[] closures = getClosures(big, small);
		return getShortestClosure(closures);
	}
	
	//Get closures for each index
	int[] getClosures(int[] big, int[] small){
		int[] closure = new int[big.length];
		for(int i = 0; i < small.length; i++){
			sweepForClosure(big, closure, small[i]);
		}
		return closure;
	}
	
	//Do backwards sweep and update the closures list with the next occurrence of value, if it's later than the current closure
	void sweepForClosure(int[] big, int[] closures, int value){
		int next = -1;
		for(int i = big.length - 1; i >= 0; i--){
			if(big[i] == value){
				next = i;
			}
			if((next == -1 || closures[i] < next) && (closures[i] != -1)){ //This is where we eliminate the need of 2D sweep array from the previous approach
				closures[i] = next;
			}
		}
	}
	
	//Get shortest closure
	Range getShortestClosure(int[] closures){
		Range shortest = new Range(0, closures[0]);
		for(int i = 1; i < closures.length; i++){
			if(closures[i] == -1){
				break;
			}
			Range range = new Range(i, closures[i]);
			if(!shortest.shorterThan(range)){
				shortest = range;
			}
		}
		return shortest;
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
