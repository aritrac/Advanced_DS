package ctci.solutions.aritra;

import java.util.HashSet;

/*
 * Question: Given two arrays of integers, find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.
 * Example:
 * Input: {4,1,2,1,1,2} and {3,6,3,3}
 * Output: {1,3}
 * 
 * Solution: This problem reduces to finding a pair of values that have a particular difference. With that in mind, let's revisit what the brute force does.
 * 
 * In the brute force, we're looping through A and then for each element, looking for an element in B which gives us the "right" difference. If the value in A is 5 and the target is 3,
 * then we must be looking for the value 2. That's the only value that could fulfill the goal.
 * 
 * That is, rather than writing one - two == target, we could have written two == one - target. How can we more quickly find an element in B that equals one - target?
 * 
 * We can do this very quickly with a hash table. We just throw all the elements in B into a hashtable. Then, iterate through A and look for the appropriate element in B.
 */

public class SumSwap {
	int[] findSwapValues(int[] array1, int[] array2){
		Integer target = getTarget(array1, array2);
		if(target == null)
			return null;
		return findDifference(array1, array2, target);
	}
	
	//find a pair of values with a specific difference
	int[] findDifference(int[] array1, int[] array2, int target){
		HashSet<Integer> contents2 = getContents(array2);
		for(int one : array1){
			int two = one - target;
			if(contents2.contains(two)){
				int[] values = {one, two};
				return values;
			}
		}
		return null;
	}
	
	//Put contents of array into hash set
	HashSet<Integer> getContents(int[] array){
		HashSet<Integer> set = new HashSet<Integer>();
		for(int a : array){
			set.add(a);
		}
		return set;
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
