package ctci.solutions.aritra;

/*
 * Question: A child is running up a staircase and can hop either 1 step, 2 steps or 3 steps at a time. Implement a method to count how many possible
 * ways the child can run up the stairs.
 * 
 * Solution: This is a fairly straight-forward algorithm to implement recursively. We just need to follow the logic like this:
 * countWays(n-1) + countWays(n-2) + countWays(n-3)
 * The one tricky bit is defining the base case. If we have 0 steps to go (we're currently standing on the step) are there 0 paths to that step or one path?
 * That is what is countWays(0)? Is it 1 or 0?
 * You could define it either way. There is no right answer here.
 * 
 * However its a lot easier to define it as 1. If you defined it as 0, then you would need some additional base cases (or lese you would just wind up
 * with a series of 0s getting added)
 * 
 * A simple implementation of the code is provided below
 */

public class TripleStep {
	public static void main(String[] args) {
		TripleStep ts = new TripleStep();
		System.out.println("Number of ways to hop 12 stairs = " + ts.countWays(12));
	}
	
	int countWays(int n){
		if(n < 0){
			return 0;
		}else if(n == 0){
			return 1;
		}else{
			return countWays(n-1) + countWays(n-2) + countWays(n-3);
		}
	}
}
