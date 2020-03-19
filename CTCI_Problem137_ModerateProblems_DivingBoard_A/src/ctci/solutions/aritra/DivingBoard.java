package ctci.solutions.aritra;

import java.util.HashSet;
import java.util.Iterator;

/*
 * Question: You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of planks, one of length shorter
 * and one of length longer.You must use exactly K planks of wood. Write a method to generate all possible lengths
 * for the diving board.
 * 
 * Solution: One way to approach this is to think about the choices we make as we're building. This leads us to a recursive algorithm. For a recursive solution
 * ,we can imagine ourselves building a diving board. We make K decisions, each time choosing which plank we will put on next. Once we've put on K planks
 * we have a complete diving board and we can add this to the list(assuming we haven't seen this length before)
 * 
 * We can follow this logic to write recursive code. Note that we don't need to track the sequence of planks. All we need to know is the current length
 * and the number of planks remaining.
 */

public class DivingBoard {
	HashSet<Integer> allLengths(int k, int shorter, int longer){
		HashSet<Integer> lengths = new HashSet<Integer>();
		getAllLengths(k,0,shorter,longer,lengths);
		return lengths;
	}
	
	public static void main(String[] args) {
		int num_of_planks = 10;
		int short_plank_length = 15;
		int long_plank_length = 23;
		HashSet<Integer> allLengths = new DivingBoard().allLengths(num_of_planks, short_plank_length, long_plank_length);
		
		Iterator<Integer> iter = allLengths.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	void getAllLengths(int k, int total, int shorter, int longer, HashSet<Integer> lengths){
		if(k == 0){
			lengths.add(total);
			return;
		}
		getAllLengths(k - 1, total + shorter, shorter, longer, lengths);
		getAllLengths(k - 1, total + longer, shorter, longer, lengths);
	}
}
