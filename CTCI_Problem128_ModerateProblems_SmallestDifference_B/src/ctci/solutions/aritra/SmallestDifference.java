package ctci.solutions.aritra;

import java.util.Arrays;

/*
 * Question: Given two array of integers, compute the pair of values (one value in each array) with the smallest (non-negative) difference. Return the difference.
 * 
 * EXAMPLE:
 * 
 * Input: {1,3,15,11,2}, {23,127,235,19,8}
 * 
 * Output: 3. That is, the pair (11,8)
 * 
 * Solution: 
 * One minor optimization that we could have done from here is to return immediately if we find a difference of 0, since this is the smallest difference possible.
 * However, depending on the input, this might actually be slower.
 * This will only be faster if there's a pair with difference zero early in the list of pairs. But to add to this optimization, we need to execute an additional line of code
 * each time. There's a tradeoff here; it's faster for some inputs and slower for others. Given that it adds complexity in reading the code, it may be best to leave it out.
 * 
 * But in the approach that we are going to use, we are going to resort to sorting.
 * Consider the following two arrays:
 * A: {1,2,11,15}
 * B: {4,12,19,23,127,235}
 * Try the following approach:
 * 1)Suppose a pointer a points to the beginning of A and a pointer b points to the beginning of B. The current difference between a and b is 3. Store this as the min.
 * 2)How can we (potentially) make this difference smaller? Well, the value at b is bigger than the value at a, so moving b will only make the difference larger.
 * Therefore, we want to move a
 * 3)Now a points to 2 and b (still) points to 4. This difference is 2, so we should update min. Move a, since it is smaller.
 * 4)Now a points to 11 and b points to 4. Move b.
 * 5)Now a points to 11 and b points to 12. Update min to 1. Move b
 * And so on.
 */

public class SmallestDifference {
	public static void main(String[] args) {
		int[] arr1 = {1,3,15,11,2};
		int[] arr2 = {23,127,235,19,8};
		
		SmallestDifference sd = new SmallestDifference();
		sd.findSmallestDifference(arr1, arr2);
	}
	
	int findSmallestDifference(int[] array1, int[] array2){
		Arrays.sort(array1);
		Arrays.sort(array2);
		int a = 0;
		int b = 0;
		int difference = Integer.MAX_VALUE;
		while(a < array1.length && b < array2.length){
			if(Math.abs(array1[a] - array2[b]) < difference){
				difference = Math.abs(array1[a] - array2[b]);
			}
			
			//Move smaller value
			if(array1[a] < array2[b]){
				a++;
			}else{
				b++;
			}
		}
		return difference;
	}
}
