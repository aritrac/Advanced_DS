package ctci.solutions.aritra;

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
 * The simple brute force way is to just iterate through all pairs, compute the difference, and compare it to the current minimum difference.
 */

public class SmallestDifference {
	public static void main(String[] args) {
		int[] arr1 = {1,3,15,11,2};
		int[] arr2 = {23,127,235,19,8};
		
		SmallestDifference sd = new SmallestDifference();
		sd.findSmallestDifference(arr1, arr2);
	}
	
	int findSmallestDifference(int[] array1, int[] array2){
		if(array1.length == 0 || array2.length == 0)
			return -1;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < array1.length; i++){
			for(int j = 0; j < array2.length; j++){
				if(Math.abs(array1[i] - array2[j]) < min){
					min = Math.abs(array1[i] - array2[j]);
				}
			}
		}
		return min;
	}
}
