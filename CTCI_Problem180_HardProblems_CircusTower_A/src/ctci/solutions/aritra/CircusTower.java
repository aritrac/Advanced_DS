package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Question: A circus is designing a tower routine consisting of people standing atop one another's shoulders. For practical and aesthetic reasons,each person must be both
 * shorter and lighter than the person below him or her. Given the heights and weights of each person in the circus, write a method to compute the largest possible number
 * of people in such a tower.
 * 
 * Solution: We have a list of pairs of items. Find the longest sequence such that both the first and second items are in non-decreasing order.
 * One thing we might first try is sorting the items on an attribute. This is useful actually, but it won't get us all the way there.
 * By sorting the items by height, we have a relative order the items must appear in. We still need to find the longest increasing subsequence of weight though.
 * In the recursive approach, we need to essentially try all the possibilities. After sorting by height, we iterate through the array. At each element, we branch into
 * two choices: add this element to the subsequence (if it's valid) or do not.
 */

public class CircusTower {
	ArrayList<HtWt> longestIncreasingSeq(ArrayList<HtWt> items){
		Collections.sort(items);
		return bestSeqAtIndex(items,new ArrayList<HtWt>(), 0);
	}
	
	ArrayList<HtWt> bestSeqAtIndex(ArrayList<HtWt> array, ArrayList<HtWt> sequence, int index){
		if(index >= array.size())
			return sequence;
		HtWt value = array.get(index);
		
		ArrayList<HtWt> bestWith = null;
		if(canAppend(sequence, value)){
			@SuppressWarnings("unchecked")
			ArrayList<HtWt> sequenceWith = (ArrayList<HtWt>)sequence.clone();
			sequenceWith.add(value);
			bestWith = bestSeqAtIndex(array, sequenceWith, index + 1);
		}
		
		ArrayList<HtWt> bestWithout = bestSeqAtIndex(array, sequence, index + 1);
		
		if(bestWith == null || bestWithout.size() > bestWith.size()){
			return bestWithout;
		}else{
			return bestWith;
		}
	}
	
	boolean canAppend(ArrayList<HtWt> solution, HtWt value){
		if(solution == null)
			return false;
		if(solution.size() == 0)
			return true;
		
		HtWt last = solution.get(solution.size() - 1);
		return last.isBefore(value);
	}
	
	ArrayList<HtWt> max(ArrayList<HtWt> seq1, ArrayList<HtWt> seq2){
		if(seq1 == null){
			return seq2;
		}else if(seq2 == null){
			return seq1;
		}
		return seq1.size() > seq2.size() ? seq1 : seq2;
	}
}

class HtWt implements Comparable<HtWt>{
	private int height;
	private int weight;
	public HtWt(int h, int w){
		height = h;
		weight = w;
	}
	
	public int compareTo(HtWt second){
		if(this.height != second.height){
			return ((Integer) this.height).compareTo(second.height);
		}else{
			return ((Integer)this.weight).compareTo(second.weight);
		}
	}
	
	//Returns true if this should be lined up before other. Note that its possible that
	//this.isBefore(other) and other.isBefore(this) are both false. This is different from the compareTo method, where if a < b then b > a
	public boolean isBefore(HtWt other){
		if(height < other.height && weight < other.weight){
			return true;
		}else{
			return false;
		}
	}
}
