package ctci.solutions.aritra;

import java.util.Arrays;

/*
 * Question: In an array of integers, a 'peak' is an element which is greater than or equal to the adjacent integers and a 'valley' is an element which is less than or equal to the
 * adjacent integers. For example, in the array {5,8,6,2,3,4,6} {8,6} are valleys and {5,2} are valleys. Given an array of integers, sort the array into an alternating 
 * sequence of peaks and valleys.
 * Example:
 * {5,3,1,2,3}
 * {5,1,3,2,3}
 * 
 * Solution:
 * Since this problem asks us to sort the array in a particular way, one thing we can try is doing a normal sort and then 'fixing' the array into an alternating sequence
 * of peaks and valleys.
 * Imagine we wre given an unsorted array and then sort it to become the following:
 * 0 1 4 7 8 9
 * We now have an ascending list of integers
 * How can we rearrange this into a proper alternating sequence of peaks and valleys? Let's walk through it and try to do that
 * The 0 is okay.
 * The 1 is in the wrong place. We can swap it with either the 0 or 4. Let's swap it with the 0.
 * 1 0 4 7 8 9
 * The 4 is okay
 * the 7 is in the wrong place. We can swap it with either the 4 or the 8. Let's swap it with the 4.
 * 1 0 7 4 8 9
 * The 9 is in the wrong place. Let's swap it with the 8.
 * 1 0 7 4 9 8
 * 
 * Observe htat there's nothing special about the array having these values. The relative order of the elements matter, but all sorted arrays will have the same relative order.
 * Therefore we can take this same approach on any sorted array.
 * 
 * Before coding, we should clarify the exact algorithm, though.
 * 1)Sort the array in ascending order.
 * 2)Iterate through the elements, starting from index 1 (not 0) and jumping two elemens at a time
 * 3)At each element, swap it with the previous element. Since every three elements appear in the order small <= medium <= large, swapping these elements will always put medium as a peak:
 * medium <= small <= large.
 * This approach will ensure that the peaks are in the right place. Indexes 1,3,5 and so on. As long as the odd-numbered elements (the peaks) are bigger than the
 * adjacent elements, then the even numbered elements (the valleys) must be smaller than the adjacent elements.
 * 
 * The code to implement this is below:
 */ 

public class PeaksAndValleys {
	
	public static void main(String[] args) {
		int[] arr = {0,1,4,7,8,9};
		PeaksAndValleys pav = new PeaksAndValleys();
		System.out.println("Before sorting");
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(" " + arr[i]);
		}
		
		pav.sortValleyPeak(arr);
		
		System.out.println("\nAfter sorting");
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(" " + arr[i]);
		}
	}
	
	void sortValleyPeak(int[] array){
		Arrays.sort(array);
		for(int i = 1; i < array.length; i += 2){
			swap(array, i-1,i);
		}
	}
	
	void swap(int[] array, int left, int right){
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
