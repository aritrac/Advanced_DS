package ctci.solutions.aritra;

/*
 * Question: A majority element is an element that makes up more than half of the items in an array. Given a positive integers array, find the majority element.
 * If there is no majority element, return -1. Do this in O(N) time and O(1) space.
 * 
 * Input: 	1 2 5 9 5 9 5 5 5
 * Output: 	5
 * 
 * Solution: One simple bruteforce approach is to just iterate through the array and check each element for whether it's the majority element. This takes O(N^2) time
 * and O(1) space.
 */

public class MajorityElement {
	int findMajorityElement(int[] array){
		for(int x: array){
			if(validate(array, x)){
				return x;
			}
		}
		return -1;
	}
	
	boolean validate(int[] array, int majority){
		int count = 0;
		for(int n : array){
			if(n == majority){
				count++;
			}
		}
		return count > array.length / 2;
	}
}
