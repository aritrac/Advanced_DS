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
 * Solution: We can precompute the location of each of the elements in the shorter array from each index in the larger array. We can do this in just a single backwards sweep.
 * Iterate through the array backwards, tracking the last most recent occurrence of 5.
 * 
 * Value: 	7	5	9	0	2	1	3	5	7	9	1	1	5	8	8	9	7
 * Index:	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16
 * next 1:  5	5	5	5	5	5	10	10	10	10	10	11	x	x	x	x	x
 * next 5:  1	1	7	7	7	7	7	7	12	12	12	12	12	x	x	x	x
 * next 9:  2	2	2	9	9	9	9	9	9	9	15	15	15	15	15	15	x
 * 
 * The findNextInstance function can now just use this table to find the next occurrence, rather than doing a search.
 * 
 * But, actually, we can make it a bit simpler. Using the table above, we can quickly compute the closure of each index. It's just the max of the column. If a column has an x in it,
 * then there is no closure, and this indicates that there's no next occurrence of that character.
 * The difference between the index and the closure is the smallest subarray starting at that index
 * 
 * Value: 	7	5	9	0	2	1	3	5	7	9	1	1	5	8	8	9	7
 * Index:	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16
 * next 1:  5	5	5	5	5	5	10	10	10	10	10	11	x	x	x	x	x
 * next 5:  1	1	7	7	7	7	7	7	12	12	12	12	12	x	x	x	x
 * next 9:  2	2	2	9	9	9	9	9	9	9	15	15	15	15	15	15	x
 * closure: 5	5	7	9	9	9	10	10	12	12	15	15	x	x	x	x	x
 * diff:	5	4	5	6	5	4	4	3	4	3	5	4	x	x	x	x	x
 * 
 * Now, all we have to do is to find the minimum distance in this table.
 */

public class ShortestSuperSequence {
	Range shortestSuperSequence(int[] big, int[] small){
		int[][] nextElements = getNextElementsMulti(big, small);
		int[] closures = getClosures(nextElements);
		return getShortestClosure(closures);
	}
	
	//Create table of next occurrences
	int[][] getNextElementsMulti(int[] big, int[] small){
		int[][] nextElements = new int[small.length][big.length];
		for(int i = 0; i < small.length; i++){
			nextElements[i] = getNextElement(big, small[i]);
		}
		return nextElements;
	}
	
	//Do backwards sweep to get a list of the next occurrence of value from each index
	int[] getNextElement(int[] bigArray, int value){
		int next = -1;
		int[] nexts = new int[bigArray.length];
		for (int i = bigArray.length - 1; i >= 0; i--){
			if(bigArray[i] == value){
				next = i;
			}
			nexts[i] = next;
		}
		return nexts;
	}
	
	//Get closure for each index
	int[] getClosures(int[][] nextElements){
		int[] maxNextElement = new int[nextElements[0].length];
		for(int i = 0; i < nextElements[0].length; i++){
			maxNextElement[i] = getClosureForIndex(nextElements, i);
		}
		return maxNextElement;
	}
	
	//Given an index and the table of next elements, find the closure for this index (which will be the min of this column)
	int getClosureForIndex(int[][] nextElements, int index){
		int max = -1;
		for(int i = 0; i < nextElements.length; i++){
			if(nextElements[i][index] == -1){
				return -1;
			}
			max = Math.max(max, nextElements[i][index]);
		}
		return max;
	}
	
	//Get shortest closure
	Range getShortestClosure(int[] closures){
		int bestStart = -1;
		int bestEnd = -1;
		for(int i = 0; i < closures.length; i++){
			if(closures[i] == -1){
				break;
			}
			int current = closures[i] - i;
			if(bestStart == -1 || current < bestEnd - bestStart){
				bestStart = i;
				bestEnd = closures[i];
			}
		}
		return new Range(bestStart, bestEnd);
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