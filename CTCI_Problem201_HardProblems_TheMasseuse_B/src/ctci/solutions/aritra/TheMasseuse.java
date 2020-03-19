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
 * Solution: As with the previous approach, we will repeatedly call maxMinutes on the same inputs. For example, we'll call it on index 2 when we're deciding whether to take appointment
 * 0. We'll also call it on index 2 when we're deciding whether to take appointment 1. We should memoize or store the intermediate results.
 * Our memo table is just a mapping from index to the max minutes. Therefore, a simple array will suffice.
 */

public class TheMasseuse {
	int maxMinutes(int[] messages){
		int[] memo = new int[messages.length]; //array which stores intermediate function call values
		return maxMinutes(messages,0,memo);
	}
	
	int maxMinutes(int[] messages, int index, int[] memo){
		if(index >= messages.length){
			return 0;
		}
		if(memo[index] == 0){ //this condition avoids recursive calls if we have previously calculated this particular index value to save computations
			int bestWith = messages[index] + maxMinutes(messages, index + 2, memo);
			int bestWithout = maxMinutes(messages, index + 1, memo);
			memo[index] = Math.max(bestWith,bestWithout); //same as previous but here we are storing the max value for a particular index in its corresponding array location
		}
		return memo[index];
	}
}
