package ctci.solutions.aritra;

import java.util.HashSet;
import java.util.Iterator;

/*
 * Question: You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of planks, one of length shorter
 * and one of length longer.You must use exactly K planks of wood. Write a method to generate all possible lengths
 * for the diving board.
 * 
 * Solution: As in many recursive algorithms(especially those with exponential runtimes), we can optimize this through memoization(a form of dynamic programming)
 * Observe that some of the recursive calls will be essentially equivalent. For example, picking plank 1 and then plank 2 is equivalent to picking
 * plank 2 and then plank 1.
 * Therefore, if we've seen this (total, plank count) pair before then we stop this recursive path. We can do this using HashSet with a key of (total, plank count)
 * 
 * Many people make the mistake which is rather than stopping only when they've seen (total, plank count), they'll stop whenever they've seen just total before. This is incorrect. Seeing
 * two planks of length 1 is not the same thing as one plank of length 2, because there are different numbers of planks remaining. In memoization probables, be very careful
 * about what you choose for your key.
 */

public class DivingBoard {
	HashSet<Integer> allLengths(int k, int shorter, int longer){
		HashSet<Integer> lengths = new HashSet<Integer>();
		HashSet<String> visited = new HashSet<String>();
		getAllLengths(k,0,shorter,longer,lengths,visited);
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
	
	void getAllLengths(int k, int total, int shorter, int longer, HashSet<Integer> lengths, HashSet<String> visited){
		if(k == 0){
			lengths.add(total);
			return;
		}
		String key = k + " " + total;
		if(visited.contains(key)){
			return;
		}
		getAllLengths(k-1, total + shorter, shorter, longer, lengths, visited);
		getAllLengths(k-1, total + longer, shorter, longer, lengths, visited);
		visited.add(key);
	}
}
