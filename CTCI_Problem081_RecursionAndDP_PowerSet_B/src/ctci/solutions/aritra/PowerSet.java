package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Question: Write a method to return all subsets of a set
 * 
 * Solution: When we are generating a set, we have two choices for each element: (1) the element is in the set (the "yes" state) or (2)the element is not in the set
 * (the "no" state). This means that each subset is a sequence of yeses or nos - eg. This gives us 2^n possible subsets. How can we iterate through all
 * possible sequences of "yes"/"no" states for all elements? If each "yes" can be treated as a 1 and each "no" can be treated as a "0", then
 * each subset can be treated as a binary string. Generating all subsets, the, really just comes down to generating all binary numbers (that is, all integers).
 * We iterate through all numbers from 0 to 2^n (exclusive) and translate the binary representation of the numbers into a set.
 */

public class PowerSet {
	ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set){
		ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();
		int max = 1 << set.size(); //Compute 2^n
		for(int k = 0; k < max; k++){
			ArrayList<Integer> subset = convertIntToSet(k,set);
			allSubsets.add(subset);
		}
		return allSubsets;
	}
	
	ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set){
		ArrayList<Integer> subset = new ArrayList<Integer>();
		int index = 0;
		for(int k = x; k > 0; k >>=1){
			if((k & 1) == 1){
				subset.add(set.get(index));
			}
			index++;
		}
		return subset;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		
		ArrayList<ArrayList<Integer>> subsets = new PowerSet().getSubsets(set);
		
		Iterator<ArrayList<Integer>> iter = subsets.iterator();
		
		while(iter.hasNext()){
			ArrayList<Integer> list = iter.next();
			System.out.print("{");
			Iterator<Integer> iter2 = list.iterator();
			while(iter2.hasNext()){
				System.out.print(" " + iter2.next() + " ");
			}
			System.out.println("}");
		}
	}
}
