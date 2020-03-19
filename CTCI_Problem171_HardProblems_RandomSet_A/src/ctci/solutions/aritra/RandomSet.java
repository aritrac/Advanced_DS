package ctci.solutions.aritra;

/*
 * Question: Write a method to randomly generate a set of m integers from an array of size n. Each element must have equal probability of being chosen.
 * 
 * Solution: Like the prior problem, we can look at this problem recursively using the Base Case and Build Approach
 * Suppose we have an algorithm that can pull a random set from m elements from an array of size n - 1. How can we use this algorithm to pull a random set of m elements
 * from an array of size n? We can first pull a random set of size m from the first n - 1 elements. Then, we just need to decide if array[n] should be inserted
 * into our subset (which would require pulling out a random element from it). An easy way to do this is to pick a random number k from 0 through n. If k < m, then insert
 * array[n] into subset[k]. This will both "fairly" (i.e. with proportional probability) insert array[n] into the subset and 'fairly' remove a random element
 * from the subset.
 * 
 * The pseudocode for this recursive algorithm would look like this.
 */

public class RandomSet {
	
	/* Random number between lower and higher, inclusive */
	public static int rand(int lower, int higher) { 
		return lower + (int)(Math.random() * (higher - lower + 1));
	}
	
	/* pick M elements from original array, using only elements 0 through i (inclusive).*/
	public static int[] pickMRecursively(int[] original, int m, int i) {
		if (i + 1 < m) { // Not enough elements
			return null; 
		} else if (i + 1 == m) { // Base case -- copy first m elements into array
			int[] set = new int[m];
			for (int k = 0; k < m; k++) {
				set[k] = original[k];
			}
			return set;
		} else {
			int[] set = pickMRecursively(original, m, i - 1);
			int k = rand(0, i);
			if (k < m) {
				set[k] = original[i];
			}
			return set;
		}
	}	
	
	public static void main(String[] args) {
		int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println(arrayToString(cards));
		int[] set = pickMRecursively(cards, 4,9);
		System.out.println(arrayToString(set));
	}
	
	public static String arrayToString(int[] array) {
		StringBuilder sb = new StringBuilder();
		for (int v : array) {
			sb.append(v + ", ");
		}
		return sb.toString();
	}
}
