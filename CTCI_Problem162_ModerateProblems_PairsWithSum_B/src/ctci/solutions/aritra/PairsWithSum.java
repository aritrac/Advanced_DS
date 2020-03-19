package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Question: Design an algorithm to find all pairs of integers within an array which sum to a specified value
 * 
 * Solution: We can optimize this with a hashmap, where the value in the hashmap reflects the number of "unpaired" instances of a key. We walk through the array. At each element x,
 * check how many unpaired instances of x's complement preceded it in the array. If the count is atleast one, then there is an unpaired instance of x's complement. We add this pair and decrement
 * x's complement to signify that this element has been paired. If the count is zero, then increment the value of x in the hashtable to signify that x is unpaired.
 */

public class PairsWithSum {

	public static void main(String[] args) {
		int[] array = { 3, 4, 1, 6, 12, -5, 18, 2 , 4, 4};
		int sum = 7;

		ArrayList<Pair> result = new PairsWithSum().printPairSums(array, sum);

		for (Pair p : result) {
			System.out.println(p.x + " " + p.y);
		}
	}
	
	ArrayList<Pair> printPairSums(int[] array, int sum){
		ArrayList<Pair> result = new ArrayList<Pair>();
		HashMap<Integer, Integer> unpairedCount = new HashMap<Integer, Integer>();
		for(int x : array){
			int complement = sum - x;
			if(unpairedCount.getOrDefault(complement, 0) > 0){
				result.add(new Pair(x,complement));
				adjustCounterBy(unpairedCount, complement, -1); //decrement complement
			}else{
				adjustCounterBy(unpairedCount, x, 1); //increment count
			}
		}
		return result;
	}
	
	void adjustCounterBy(HashMap<Integer, Integer> counter, int key, int delta){
		counter.put(key, counter.getOrDefault(key, 0) + delta);
	}

}

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
