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
 * Solution: Let's start with an example. We'll draw it visually to get a better feel for the problem. Each number indicates the number of minutes in the appointment.
 * 
 * r0 = 75 r1 = 105 r2 = 120 r3 = 75 r4 = 90 r5 = 135
 * Alternatively, we could have also divided all the values (including the break) by 15 mins, to give us the array {5,7,8,5,6,9}. this would be equivalent, but now we would want a 1-minute break.
 * The best set of appointments for this problem has 330 minutes total, formed with {r0 = 75, r2 = 120, r5 = 135}. Note that we've intentionally chosen an example in which
 * the best sequence of appointments was not formed through a strictly alternating sequence.
 * We should also recognize that choosing the longest appointment first (the 'greedy' strategy) would not necessarily be optimal.
 * For example, a sequence like {45, 60, 45, 15} would not have 60 in the optimal set.
 * The first thing that may come to mind is a recursive solution. We have essentially a sequence of choices as we walk down the list of appointments.
 * Do we use this appointment or do we not? If we use appointment i, we must skip appointment i + 1 as we can't take back-to-back appointments. Appointment i + 2 is a possibility
 * but not necessarily the best choice.
 */

public class TheMasseuse {
	int maxMinutes(int[] messages){
		return maxMinutes(messages,0);
	}
	
	int maxMinutes(int[] messages, int index){
		if(index >= messages.length){	//Out of bounds
			return 0;
		}
		
		//Best with this reservation
		int bestWith = messages[index] + maxMinutes(messages, index + 2);
		
		//Best without this reservation
		int bestWithout = maxMinutes(messages, index + 1);
		
		//Return best of this subarray, starting from index
		return Math.max(bestWith, bestWithout);
	}
}
