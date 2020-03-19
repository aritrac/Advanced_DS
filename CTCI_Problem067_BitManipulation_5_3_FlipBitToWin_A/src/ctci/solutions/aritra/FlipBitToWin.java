package ctci.solutions.aritra;

import java.util.ArrayList;

/*
 * Question: You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the longest sequence of 1s you could create.
 * EXAMPLE
 * Input: 1775 or 11011101111
 * Output: 8
 * 
 * Solution:
 * It's a strictly alternating sequence, always starting with the 0s sequence. Once we have this, we just walk through the array. At each 0s sequence
 * then we consider merging the adjacent 1s sequence if the 0s sequence has length 1
 */

public class FlipBitToWin {
	
	public static void main(String[] args) {
		System.out.println("Longest sequence in 11011101111 = " + new FlipBitToWin().longestSequence(1775));
	}
	
	int longestSequence(int n){
		if(n == -1)
			return 32;
		ArrayList<Integer> sequences = getAlternatingSequences(n);
		return findLongestSequence(sequences);
	}
	
	//Return a list of the sizes of the sequences. The sequence starts off with the number of 0s (which might be 0) and then alternates
	//with the counts of each value
	
	ArrayList<Integer> getAlternatingSequences(int n){
		ArrayList<Integer> sequences = new ArrayList<Integer>();
		
		int searchingFor = 0;
		int counter = 0;
		
		for(int i = 0; i < 32; i++){
			if((n & 1) != searchingFor){
				sequences.add(counter);
				searchingFor = n & 1; //Flips 1 to 0 or 0 to 1
				counter = 0;
			}
			counter++;
			n >>>= 1;
		}
		sequences.add(counter);
		return sequences;
	}
	
	//Given the lengths of alternating sequences of 0s and 1s, find the longest one we can build
	int findLongestSequence(ArrayList<Integer> seq){
		int maxSeq = 1;
		
		for(int i = 0; i < seq.size(); i += 2){
			int zerosSeq = seq.get(i);
			int onesSeqRight = i - 1 >= 0? seq.get(i - 1) : 0;
			int onesSeqLeft = i + 1 < seq.size() ? seq.get(i + 1) : 0;
			
			int thisSeq = 0;
			if(zerosSeq == 1){//Can merge
				thisSeq = onesSeqLeft + 1 + onesSeqRight;
			}
			if(zerosSeq > 1){//Just add a zero to either side
				thisSeq = 1 + Math.max(onesSeqRight,  onesSeqLeft);
			}else if(zerosSeq == 0){//No zero, but take either side
				thisSeq = Math.max(thisSeq, maxSeq);
			}
			maxSeq = Math.max(thisSeq, maxSeq);
		}
		
		return maxSeq;
	}
}
