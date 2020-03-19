package ctci.solutions.aritra;

/*
 * Question: Given two arrays of integers, find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.
 * Example:
 * Input: {4,1,2,1,1,2} and {3,6,3,3}
 * Output: {1,3}
 * 
 * Solution: We should start by trying to understand what exactly we're looking for.
 * We have two arrays and their sums. Although we likely aren't given their sums upfront, we can just act like we are for now. After all,
 * computing the sum is an O(N) operation and we know we can't beat O(N) anyway. computing the sum, therefore, won't impact
 * the runtime.
 * When we move a (positive) value a from array A to array B, then the sum of A drops by a and the sum of B increases by a.
 * We are looking for two values, a and b, such that
 * sumA - a + b = sumB - b + a
 * 
 * Doing some quick math
 * 2a - 2b = sumA - sumB
 * a - b = (sumA - sumB) / 2
 * 
 * therefore, we're looking for two values that have a specific target difference: (sumA - subB) / 2
 * Observe that because that the target must be an integer (after all, you can't swap two integers to get a non-integer difference), we
 * can conclude that the difference between the sums must be even to have a valid pair.
 */

public class SumSwap {
	int[] findSwapValues(int[] array1, int[] array2){
		Integer target = getTarget(array1, array2);
		if(target == null) return null;
		
		for(int one : array1){
			for(int two : array2){
				if(one - two == target){
					int[] values = {one,two};
					return values;
				}
			}
		}
		return null;
	}
	
	Integer getTarget(int[] array1, int[] array2){
		int sum1 = sum(array1);
		int sum2 = sum(array2);
		
		if((sum1 - sum2) % 2 != 0)
			return null;
		return (sum1 - sum2) / 2;
	}
	
	int sum(int[] arr){
		int sum1 = 0;
		for(int a : arr){
			sum1 += a;
		}
		return sum1;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {4,1,2,1,1,2};
		int[] arr2 = {3,6,3,3};
		
		int[] values = new SumSwap().findSwapValues(arr1, arr2);
		System.out.println(values[0] + " " + values[1]);
	}
}
