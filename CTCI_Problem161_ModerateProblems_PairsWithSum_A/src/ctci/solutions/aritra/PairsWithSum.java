package ctci.solutions.aritra;

import java.util.ArrayList;

/*
 * Question: Design an algorithm to find all pairs of integers within an array which sum to a specified value
 * 
 * Solution: If we're trying to find a pair of numbers that sums to z, the complement of x will be z - x (that is, the number that can be added to x to make z).
 * For example, if we're trying to find a pair of numbers that sums to 12, the complement of -5 would be 17
 * A brute force solution is to just iterate through all pairs and print the pair if its sum matches the target sum. 
 * If there are duplicates in the array, it might print the same sum twice.
 */

public class PairsWithSum {
	ArrayList<Pair> printPairSums(int[] array, int sum){
		ArrayList<Pair> result = new ArrayList<Pair>();
		for(int i = 0; i < array.length; i++){
			for(int j = i + 1; j < array.length; j++){
				if(array[i] + array[j] == sum){
					result.add(new Pair(array[i], array[j]));
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] array = {3,4,1,6,12,-5,18,2};
		int sum = 7;
		
		ArrayList<Pair> result = new PairsWithSum().printPairSums(array, sum);
		
		for(Pair p : result){
			System.out.println(p.x + " " + p.y);
		}
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
