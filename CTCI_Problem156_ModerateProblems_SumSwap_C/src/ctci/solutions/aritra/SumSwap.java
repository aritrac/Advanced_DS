package ctci.solutions.aritra;

/*
 * Question: Given two arrays of integers, find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.
 * Example:
 * Input: {4,1,2,1,1,2} and {3,6,3,3}
 * Output: {1,3}
 * 
 * Solution: If the arrays are sorted, we can iterate through them to find an appropriate pair. This will require less space.
 */

public class SumSwap {
	int[] findSwapValues(int[] array1, int[] array2){
		Integer target = getTarget(array1, array2);
		if(target == null)
			return null;
		return findDifference(array1, array2, target);
	}
	
	int[] findDifference(int[] array1, int[] array2, int target){
		int a = 0;
		int b = 0;
		
		while(a < array1.length && b < array2.length){
			int difference = array1[a] - array2[b];
			//Compare difference to target. If difference is too small, then make it bigger by moving a to a bigger value. If it is too big, then make it smaller by moving b to a bigger value
			if(difference == target){
				int[] values = {array1[a], array2[b]};
				return values;
			}else if(difference < target){
				a++;
			}else{
				b++;
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
		int[] arr1 = {1,1,1,2,2,4}; //Providing sorted arrays
		int[] arr2 = {3,3,3,6}; //Providing sorted arrays
		
		int[] values = new SumSwap().findSwapValues(arr1, arr2);
		System.out.println(values[0] + " " + values[1]);
	}
}
