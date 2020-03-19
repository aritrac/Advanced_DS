package ctci.solutions.aritra;

/*
 * Question: In an array of integers, a 'peak' is an element which is greater than or equal to the adjacent integers and a 'valley' is an element which is less than or equal to the
 * adjacent integers. For example, in the array {5,8,6,2,3,4,6} {8,6} are valleys and {5,2} are valleys. Given an array of integers, sort the array into an alternating 
 * sequence of peaks and valleys.
 * Example:
 * {5,3,1,2,3}
 * {5,1,3,2,3}
 * 
 * Solution:
 * To optimize past the prior solution, we need to cut out the sorting step. The algorithm must operate on an unsorted array. Let's revisit an example.
 * 9 1 0 4 8 7
 * For each element, we'll look at the adjacent elements. Let's imagine some sequences. We'll just use the numbers 0,1, and 2. The specific values don't matter.
 * 0 1 2
 * 0 2 1 //Peak
 * 1 0 2
 * 1 2 0 //Peak
 * 2 1 0
 * 2 0 1
 * 
 * If the center element needs to be a peak, then two of those sequences work. Can we fix the other ones to make the center element a peak?
 * Yes, We can fix this sequence by swapping the center element with the largest adjacent element
 * 
 * 0 1 2 -> 0 2 1
 * 0 2 1 // peak
 * 1 0 2 -> 1 2 0
 * 1 2 0 // peak
 * 2 1 0 -> 1 2 0
 * 2 0 1 -> 0 2 1
 * 
 * As we noted before, if we make sure the peaks are in the right place then we know the valleys are in the right place.
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
		for(int i = 1; i < array.length; i += 2){
			int biggestIndex = maxIndex(array, i - 1, i , i + 1);
			if(i != biggestIndex){
				swap(array,i,biggestIndex);
			}
		}
	}
	
	void swap(int[] array, int left, int right){
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	int maxIndex(int[] array, int a, int b, int c){
		int len = array.length;
		int aValue = a >= 0 && a < len ? array[a] : Integer.MIN_VALUE;
		int bValue = b >= 0 && b < len ? array[b] : Integer.MIN_VALUE;
		int cValue = c >= 0 && c < len ? array[c] : Integer.MIN_VALUE;
		
		int max = Math.max(aValue, Math.max(bValue, cValue));
		if(aValue == max)
			return a;
		else if(bValue == max)
			return b;
		else
			return c;
	}
}
