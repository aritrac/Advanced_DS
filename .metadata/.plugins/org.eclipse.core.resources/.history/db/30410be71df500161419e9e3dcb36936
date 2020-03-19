package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Question: Write a method to return all subsets of a set
 * 
 * Solution: This problem is a good candidate for the Base Case and Build approach. Image that we are trying to find all the subsets of a set like
 * S = {a1,a2,...,an}.We can start with the base case
 * 
 * Base Case: n = 0
 * There is just one subset of the empty set: {}
 * 
 * Case: n = 1
 * There are two subsets of the set {a1}: {},{a1}
 * 
 * Case: n = 2
 * There are four subsets of the set {a1,a2}: {}, {a1}, {a2}, {a1,a2}
 * 
 * Case: n = 3
 * Now here's where things get interesting. We want to find a way of generating the solution for n = 3 based on the prior solutions.
 * What is the difference between the solution for n = 3 and the solution for n = 2? Let's look at this more deeply
 * 
 * P(2) = {},{a1},{a2},{a1,a2}
 * P(3) = {},{a1},{a2},{a3},{a1,a2},{a1,a3},{a2,a3},{a1,a2,a3}
 * 
 * The difference between these solutions is that P(2) is missing all the subsets containing a3
 * P3 - P2 = {a3},{a1,a3},{a2,a3},{a1,a2,a3}
 * 
 * How can we use P(2) to create P(3)? We can simply clone the subsets in P(2) and add a3 to them
 * P(2) = {}, {a1}, {a2}, {a1,a2}
 * P(2) + a3 = {a3}, {a1,a3}, {a2,a3}, {a1,a2,a3}
 * 
 * When merged together, the lines above make P(3)
 * 
 * Case: n > 0
 * Generating P(n) for the general case is just a simple generalization of the above steps. We compute P(n-1), clone the results, and then add an to each
 * of these cloned sets.
 * 
 * The following code implements this algorithm
 */

public class PowerSet {
	ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index){
		ArrayList<ArrayList<Integer>> allSubsets;
		if(set.size() == index){ //Base case - add empty set
			allSubsets = new ArrayList<ArrayList<Integer>>();
			allSubsets.add(new ArrayList<Integer>()); //Empty Set
		}else{
			allSubsets = getSubsets(set,index + 1);
			int item = set.get(index);
			ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();
			for(ArrayList<Integer> subset : allSubsets){
				ArrayList<Integer> newSubset = new ArrayList<Integer>();
				newSubset.addAll(subset);
				newSubset.add(item);
				moreSubsets.add(newSubset);
			}
			allSubsets.addAll(moreSubsets);
		}
		return allSubsets;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		
		ArrayList<ArrayList<Integer>> subsets = new PowerSet().getSubsets(set, 0);
		
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
