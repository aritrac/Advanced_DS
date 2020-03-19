package ctci.solutions.aritra;

/*
 * Question: Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), write code to calculate the number of ways
 * of representing n cents
 * 
 * Solution: This is a recursive algorithm, so let's figure out how to compute makeChange(n) using prior solutions (i.e. subproblems)
 * Lets say n = 100. We want to compute the number of ways of making change for 100 cents. What is the relationship for this problem and its subproblems?
 * 
 * We know that making change for 100 cents will involve either 0,1,2,3 or 4 quarters. So:
 * makeChange(100) = makeChange(100 using 0 quarters) + makeChange(100 using 1 quarter) + makeChange(100 using 2 quarters) + makeChange(100 using 3 quarters) + makeChange(100 using 4 quarters)
 * 
 * Inspecting further, we can see that some of these problems reduce. For example makeChange(100 using 1 quarter) will equal makeChange(75 using 0 quarters). This is because,
 * if we must use exactly one quarter to make change for 100 cents, then our only remaining choices involve making change for the remaining 75 cents
 * 
 * We have thus reduced the above statement to the following
 * makeChange(100) = makeChange(100 using 0 quarters) + makeChange(75 using 0 quarters) + makeChange(50 using 0 quarters) + makeChange(25 using 0 quarters) + 1
 * 
 * Now that we have used up all our quarters, now we can start applying our next biggest denomination : dimes
 * 
 * Our approach for quarters applies to dimes as well, but we apply this for each of the four or five parts of the above statement. So for the first part , we get the following statements
 * 
 * makeChange(100 using 0 quarters) = makeChange(100 using 0 quarters, 0 dimes) +
 * 									  makeChange(100 using 0 quarters, 1 dime) +
 * 									  makeChange(100 using 0 quarters, 2 dimes) +
 * 									  ...
 * 									  makeChange(100 using 0 quarters, 10 dimes) +
 * 
 * makeChange(75 using 0 quarters)  = makeChange(75 using 0 quarters, 0 dimes) +
 * 									  makeChange(75 using 0 quarters, 1 dime) +
 * 									  makeChange(75 using 0 quarters, 2 dimes) +
 * 									  ...
 * 									  makeChange(75 using 0 quarters, 7 dimes)
 * 
 * makeChange(50 using 0 quarters)  = makeChange(50 using 0 quarters, 0 dimes) +
 * 									  makeChange(50 using 0 quarters, 1 dime) +
 * 									  makeChange(50 using 0 quarters, 2 dimes) +
 * 									  ...
 * 									  makeChange(50 using 0 quarters, 5 dimes)
 * 
 * makeChange(25 using 0 quarters)  = makeChange(50 using 0 quarters, 0 dimes) +
 * 									  makeChange(50 using 0 quarters, 1 dime) +
 * 									  makeChange(50 using 0 quarters, 2 dimes)
 * 
 * Each one of these, in turn, expands out once we start applying nickels. We end up with a tree-like recursive structure where each call expands out to four or more calls
 * The base case of our recursion is the fully reduced statement. For example, makeChange(50 using 0 quarters, 5 dimes) is fully reduced to 1, since 5 dimes equals 50 cents
 * 
 * This leads to a recursive algorithm that looks like this
 * 
 */

public class WaysOfCoinChange {
	public static void main(String[] args) {
		WaysOfCoinChange wcc = new WaysOfCoinChange();
		System.out.println("The number of ways 100 cents change can be given = " + wcc.makeChange(10));
	}
	
	int makeChange(int amount, int[] denoms, int index){
		if(index >= denoms.length - 1) //last denom
			return 1;
		int denomAmount = denoms[index];
		int ways = 0;
		for(int i = 0; i * denomAmount <= amount; i++){
			int amountRemaining = amount - i * denomAmount;
			ways += makeChange(amountRemaining, denoms, index + 1);
		}
		return ways;
	}
	
	int makeChange(int n){
		int[] denoms = {25,10,5,1};
		return makeChange(n,denoms, 0);
	}
}
