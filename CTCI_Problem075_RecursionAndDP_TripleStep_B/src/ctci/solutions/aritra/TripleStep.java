package ctci.solutions.aritra;

import java.util.Arrays;

/*
 * Question: A child is running up a staircase and can hop either 1 step, 2 steps or 3 steps at a time. Implement a method to count how many possible
 * ways the child can run up the stairs.
 * 
 * Solution: The previous solution for countWays is called many times for the same values, which is unnecessary. We can fix this through memoization.
 * Essentially, if we've seen this value of n before, return the cached value. Each time we compute a fresh value, add it to the cache.
 * Typically, we use a HashMap<Integer, Integer> for a cache. In this case, the keys will be exactly 1 through n. It's more compact to use
 * an integer array
 */

public class TripleStep {

	int countWays(int n){
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		return countWays(n, memo);
	}
	
	int countWays(int n, int[] memo){
		if(n < 0){
			return 0;
		}else if(n == 0){
			return 1;
		}else if(memo[n] > -1){
			return memo[n];
		}else{
			memo[n] = countWays(n-1, memo) + countWays(n-2, memo) + countWays(n-3, memo);
			return memo[n];
		}
	}
	
	public static void main(String[] args) {
		TripleStep ts = new TripleStep();
		System.out.println("Number of ways to hop 12 stairs = " + ts.countWays(12));
	}
}
