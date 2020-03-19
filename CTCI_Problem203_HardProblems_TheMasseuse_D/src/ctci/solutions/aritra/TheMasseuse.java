package ctci.solutions.aritra;

/*
 * Question: A popular masseuse receives a sequence of back-to-back appointment requests and is debating which ones to accept. She needs a 15-minute break between appointments and therefore
 * she cannot accept any adjacent requests. Given a sequence of back-to-back appointment requests (all multiples of 15 minutes, none overlap, and none can be moved),
 * find the optimal (highest total booked minutes) set the masseuse can honor. Return the number of minutes
 * 
 * Example:
 * Input: {30,15,60,75,45,15,15,45}
 * Output: 180 minutes ({30,60,45,45})
 * 
 * Solution: In reviewing the last solution, we can recognize that we only use the values in the memo table for a short amount of time, once we are several elements
 * past an index, we never use that elements index again.
 * In fact, at any given index i, we only need to know the best value from i + 1 and i + 2. Therefore, we can get rid of the memo table and just use two integers.
 */

public class TheMasseuse {
	int maxMinutes(int[] messages){
		int oneAway = 0; //replaces memo[i + 1] in previous algo
		int twoAway = 0; //replaces memo[i + 2] in previous algo
		for(int i = messages.length - 1; i >= 0; i--){
			int bestWith = messages[i] + twoAway;
			int bestWithout = oneAway;
			int current = Math.max(bestWith, bestWithout);
			twoAway = oneAway; //current oneAway becomes an index further for the next current_index - 1 calculation, hence we assign it to twoAway
			oneAway = current; //current index best becomes the oneAway neighbor of the current_index - 1 index, hence assigning the current soln to oneAway
		}
		return oneAway;
	}
}
