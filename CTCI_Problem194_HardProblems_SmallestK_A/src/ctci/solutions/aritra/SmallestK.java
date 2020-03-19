package ctci.solutions.aritra;

import java.util.Arrays;

/*
 * Question: Design an algorithm to find the smallest K numbers in an array
 * 
 * Solution: There are a number of ways to approach this problem. We will go through three of them: sorting, max heap and selection rank.
 * We can sort the elements in ascending order and then take the first K numbers from that.
 */

public class SmallestK {
	int[] smallestK(int[] array, int k){
		if(k <= 0 || k > array.length){
			throw new IllegalArgumentException();
		}
		
		//Sort array
		Arrays.sort(array);
		
		//Copy first k elements
		int[] smallest = new int[k];
		for(int i = 0; i < k; i++){
			smallest[i] = array[i];
		}
		return smallest;
	}
}
