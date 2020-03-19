package ctci.solutions.aritra;

import java.util.Arrays;

/*
 * Question: Design an algorithm to find all pairs of integers within an array which sum to a specified value
 * 
 * Solution: We can sort the array and then find the pairs in a single pass. Consider this array:
 * {-2,-1,0,3,5,6,7,9,13,14}
 * Let first point to the head of the array and last point to the end of the array. To find the complement of first, we just move last backwards until we find it.
 * If first + last < sum, then there is no complement for first. We can therefore move first forward. We stop when first is greater than last.
 * Why must this find all complements for first? Because the array is sorted and we're trying progressively smaller numbers. When the sum of first and last is less
 * than the sum, we know that trying even smaller numbers (as last) won't help us find a complement.
 * Why must this find all complements for last? Because all pairs must be made up of a first and a last. We've found all complements for first, therefore we've found
 * all complements of last.
 */

public class PairsWithSum {
	public static void main(String[] args) {
		int[] array = { 3, 4, 1, 6, 12, -5, 18, 2 , 4, 4};
		int sum = 7;
		new PairsWithSum().printPairSums(array, sum);
	}
	
	void printPairSums(int[] array, int sum){
		Arrays.sort(array);
		int first = 0;
		int last = array.length - 1;
		while(first < last){
			int s = array[first] + array[last];
			if(s == sum){
				System.out.println(array[first] + " " + array[last]);
				first++;
				last--;
			}else{
				if(s < sum)
					first++;
				else
					last--;
			}
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
