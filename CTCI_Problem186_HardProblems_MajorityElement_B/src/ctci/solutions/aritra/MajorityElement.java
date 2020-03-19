package ctci.solutions.aritra;

/*
 * Question: A majority element is an element that makes up more than half of the items in an array. Given a positive integers array, find the majority element.
 * If there is no majority element, return -1. Do this in O(N) time and O(1) space.
 * 
 * Solution: Lets take the following example:
 * 3	1	7	1	1	7	7	3	7	7	7
 * 0	1	2	3	4	5	6	7	8	9	10
 * 
 * 1. We start off with [3] and we expand the subarray until 3 is no longer the majority element. We fail at [3, 1]. At the moment
 * we fail, the subarray can have no majority element
 * 2. Then we go to [7] and expand until [7,1]. Again, we terminate and nothing could be the majority element in that subarray.
 * 3. We move to [1] and expand to [1,7]. We terminate. Nothing there could be the majority element.
 * 4. We go to [7] and expand to [7,3]. We terminate. Nothing there could be the majority element.
 * 5. We go to [7] and expand until the end of the array: [7, 7, 7]. We have found the majority element(and now we must validate that)
 * 
 * Each time we terminate the validate step, the subarray has no majority element. This means that there are atleast as many non-7s as there are 7s.
 * Although, we're essentially removing this subarray from the original array, the majority element will still be found in the rest of the array-
 * and will still have majority status. Therefore, at some point, we will discover the majority element.
 */

public class MajorityElement {
	int findMajortiyElement(int[] array){
		int candidate = getCandidate(array);
		return validate(array, candidate)? candidate : -1;
	}
	
	int getCandidate(int[] array){
		int majority = 0;
		int count = 0;
		for(int n : array){
			if(count == 0){//No majority element in previous set
				majority = n;
			}
			if(n == majority){
				count++;
			}else{
				count--;
			}
		}
		return majority;
	}
	
	boolean validate(int[] array, int majority){
		int count = 0;
		for(int n : array){
			if(n == majority){
				count++;
			}
		}
		return count > array.length /2;
	}
}
