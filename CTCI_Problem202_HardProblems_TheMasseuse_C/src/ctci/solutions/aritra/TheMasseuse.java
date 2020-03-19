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
 * Solution: Can we do better than the previous approach? We certainly can't beat the time complexity since we have to look at each appointment. However, we might be able to 
 * beat the space complexity. This would mean not solving the problem recursively.
 * 
 * Let's look at our first example again.
 * 
 * r0 = 30 r1 = 15 r2 = 60 r3 = 75 r4 = 45 r5 = 15 r6 = 15 r7 = 45
 * 
 * As we noted in the problem statement, we cannot take adjacent appointments.
 * There is another observation, though, that we can make. We should never skip three consecutive appointments. That is, we might skip r1 and r2 if we wanted to take r0 and r3. But
 * we would never skip r1,r2 and r3. This would be suboptimal since we could always improve our set by grabbing that middle element.
 * This means that if we take r0, we know we'll definitely skip r1 and definitely take either r2 or r3. this substantially limits the options we need to evaluate and opens the door
 * to an iterative solution.
 * Let's think about our recursive + memoization solution and try to reverse the logic. that is, lets try to approach it iteratively.
 * A useful way to do this is to approach it from the back and move toward the start of the array. At each point, we find the solution for the subarray.
 * 
 * best(7): what's the best option for {r7 = 45}? We can get 45 min. If we take r7, so best(7) = 45
 * 
 * best(6): What's the best option for {r6 = 15, ...}? Still 45 min.,so best(6) = 45.
 * 
 * best(5): What's the best option for {r5 = 15, ...}? We can either:
 * 		>>take r5 = 15 and merge it with best(7) = 45, or
 * 		>>take best(6) = 45
 * The first gives us 60 minutes, best(5) = 60
 * 
 * best(4): What's the best option for {r4 = 45, ...}? We can either:
 * 		>>take r4 = 45 and merge it with the best(6) = 45, or
 * 		>>take best(5) = 50.
 * The first gives us 90 minutes, best(4) = 90
 * 
 * best(3): What's the best option for {r3 = 75, ...}? We can either:
 * 		>>take r3 = 75 and merge it with best(5) = 60, or
 * 		>>take best(4) = 90
 * The first gives us 135 minutes, best(3) = 135
 * 
 * best(2): What's the best option for {r2 = 60, ...}? We can either:
 * 		>>take r2 = 60 and merge it with best(3) = 135, or
 * 		>>take best(3) = 135
 * The first gives us 150 minutes, best(2) = 150
 * 
 * best(1): What's the best option for {r1 = 15,...? We can either:
 * 		>>take r1 = 15 and merge it with best(3) = 135, or:
 * 		>>take best(2) = 150
 * Either way, best(1) = 150
 * 
 * best(0):What's the best option for {r0 = 30, ...}? We can either:
 * 		>>take r0 = 30 and merge it with best(2) = 150 or,
 * 		>>take best(1) = 150
 * The first gives us 180 minutes, best(0) = 180.
 * Therefore, we return 180 minutes
 */

public class TheMasseuse {
	int maxMinutes(int[] messages){
		//Allocate 2 extra slots in the array so we don't have to do bounds checking on lines 7 and 8
		int[] memo = new int[messages.length + 2];
		memo[messages.length] = 0; 		//avoids bound check while calculating memo[message.length - 1]
		memo[messages.length + 1] = 0;  //avoids bound check while calculating memo[message.length - 1]
		for(int i = messages.length - 1; i >= 0; i--){ //sweep from the back
			int bestWith = messages[i] + memo[i + 2];
			int bestWithout = memo[i + 1];
			memo[i] = Math.max(bestWith, bestWithout); //Rest is the same logic that you have to mandatorily pick one from index, index + 1 and index + 2
		}
		return memo[0];
	}
}
