package ctci.solutions.aritra;

import java.util.HashSet;
import java.util.Iterator;

/*
 * Question: You are building a diving board by placing a bunch of planks of wood end-to-end. There are two types of planks, one of length shorter
 * and one of length longer.You must use exactly K planks of wood. Write a method to generate all possible lengths
 * for the diving board.
 * 
 * Solution: This can be done in an iterative approach also. There are K distinct sums we can get. Isn't that the whole point of the problem - to find all possible sums?
 * We don't actually need to go through all arrangements of planks. We just need to go through all unique sets of k planks (sets, not orders!). There are only K ways of picking K planks
 * if we only have two possible types: {0 of type A, K of type B},{1 of type A, K-1 of type B},{2 of type A, K-2 of type B},...
 * 
 * This can be done in just a simple loop. At each sequence we just compute the sum
 */

public class DivingBoard {
	HashSet<Integer> allLengths(int k, int shorter, int longer){
		HashSet<Integer> lengths = new HashSet<Integer>();
		for(int nShorter = 0; nShorter <= k; nShorter++){
			int nLonger = k - nShorter;
			int length = nShorter * shorter + nLonger * longer;
			lengths.add(length);
		}
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
}
