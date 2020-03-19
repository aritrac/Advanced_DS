package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Question: A circus is designing a tower routine consisting of people standing atop one another's shoulders. For practical and aesthetic reasons,each person must be both
 * shorter and lighter than the person below him or her. Given the heights and weights of each person in the circus, write a method to compute the largest possible number
 * of people in such a tower.
 * 
 * Solution: Imagine we had the longest subsequence that terminates with each element, A[0] through A[3]. Could we use this to find the longest subsequence that terminates with A[4]?
 * 
 * Array: 13,14,10,11,12
 * Longest(ending with A[0]): 13
 * Longest(ending with A[1]): 13,14
 * Longest(ending with A[2]): 10
 * Longest(ending with A[3]): 10,11
 * Longest(ending with A[4]): 10,11,12
 * 
 * Sure, we just append A[4] onto the longest subsequence that it can be appended to.
 */

public class CircusTower {
	ArrayList<HtWt> longestIncreasingSeq(ArrayList<HtWt> array) {
		Collections.sort(array);

		ArrayList<ArrayList<HtWt>> solutions = new ArrayList<ArrayList<HtWt>>();
		ArrayList<HtWt> bestSequence = null;

		// Find the longest subsequence that terminates with each element. Track
		// the longest overall subsequence as we go
		for (int i = 0; i < array.size(); i++) {
			ArrayList<HtWt> longestAtIndex = bestSeqAtIndex(array, solutions, i);
			solutions.add(i, longestAtIndex);
			bestSequence = max(bestSequence, longestAtIndex);
		}

		return bestSequence;
	}

	// Returns longer sequence
	private static ArrayList<HtWt> max(ArrayList<HtWt> seq1,
			ArrayList<HtWt> seq2) {
		if (seq1 == null) {
			return seq2;
		} else if (seq2 == null) {
			return seq1;
		}
		return seq1.size() > seq2.size() ? seq1 : seq2;
	}
	
	//Find the longest subsequence which terminates with this element
	ArrayList<HtWt> bestSeqAtIndex(ArrayList<HtWt> array, ArrayList<ArrayList<HtWt>> solutions, int index){
		HtWt value = array.get(index);
		ArrayList<HtWt> bestSequence = new ArrayList<HtWt>();
		
		//Find the longest subsequence that we can append this element to
		for(int i = 0; i < index; i++){
			ArrayList<HtWt> solution = solutions.get(i);
			if(canAppend(solution, value)){
				bestSequence = max(solution, bestSequence);
			}
		}
		
		//Append element
		@SuppressWarnings("unchecked")
		ArrayList<HtWt> best = (ArrayList<HtWt>)bestSequence.clone();
		best.add(value);
		
		return best;
	}
	
	boolean canAppend(ArrayList<HtWt> solution, HtWt value){
		if(solution == null)
			return false;
		if(solution.size() == 0)
			return true;
		
		HtWt last = solution.get(solution.size() - 1);
		return last.isBefore(value);
	}
}

class HtWt implements Comparable<HtWt> {
	private int height;
	private int weight;

	public HtWt(int h, int w) {
		height = h;
		weight = w;
	}

	public int compareTo(HtWt second) {
		if (this.height != second.height) {
			return ((Integer) this.height).compareTo(second.height);
		} else {
			return ((Integer) this.weight).compareTo(second.weight);
		}
	}

	// Returns true if this should be lined up before other. Note that its
	// possible that
	// this.isBefore(other) and other.isBefore(this) are both false. This is
	// different from the compareTo method, where if a < b then b > a
	public boolean isBefore(HtWt other) {
		if (height < other.height && weight < other.weight) {
			return true;
		} else {
			return false;
		}
	}
}