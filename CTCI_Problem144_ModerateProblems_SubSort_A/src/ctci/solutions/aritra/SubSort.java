package ctci.solutions.aritra;

/*
 * Question: Given an array of integers, write a method to find indices m and n such that if you sorted elements m through n, the entire array would be sorted.
 * Minimize n - m (that is, find the smallest such sequence)
 * 
 * EXAMPLE:
 * Input: 1, 2, 4, 10, 11, 7, 12, 6, 7, 16, 18, 19
 * Output: (3, 9)
 * 
 * Solution: In order to solve our problem, though, we would need to be able to sort the middle part of the array and by doing just that, get all the elements in the array in order.
 * Specifically, the following would have to be true.
 * 
 * All items on left are smaller than all items in middle
 * min(middle) > end(left)
 * 
 * All items in middle are smaller than all items in right
 * max(middle) < start(right)
 * 
 * Or, in other words, for all elements:
 * left < middle < right
 * 
 * In fact, this condition will never be met. The middle section is, by definition, the elements that were out of order. That is, it is always the case that left.end > middle.start and
 * middle.end > right.start. Thus, you cannot sort the middle to make the entire array sorted.
 * 
 * But, what we can do is shrink the left and right subsequences until the earlier conditions are met. We need the left part to be smaller than all elements in the middle and right side,
 * and the right part to be bigger than all the elements on the left and middle.
 * 
 * Let min equal min(middle and right side) and max equal max(middle and left side). Observe that since the right and left sides are already in sorted order, we only actually
 * need to check their start or end point.
 * 
 * On the left side, we start with the end of the subsequence (value 11, at element 5) and move to the left. The value min equals 5. Once we find an element i such that array[i] < min,
 * we know that we could sort the middle and have that part of the array appear in order.
 * 
 * Then we do a similar thing on the right side. The value max equals 12. So, we being with the start of the right subsequence (value 6) and move to the right. We compare the max of 12 to 6,
 * then 7, then 16. When we reach 16, we know that no element smaller than 12 could be after it (since it's an increasing subsequence). Thus, the middle of the array could now be sorted
 * to make the entire array sorted.
 * 
 * The following code implements this algorithm.
 */

public class SubSort {
	public static void main(String[] args) {
		int[] array = {1,2,4,7,10,11,8,12,5,6,16,18,19};
		new SubSort().findUnsortedSequence(array);
	}
	
	void findUnsortedSequence(int[] array){
		//find left subsequence
		int end_left = findEndOfLeftSubsequence(array);
		if(end_left >= array.length - 1) //Already sorted
			return;
		
		//Find right subsequence
		int start_right = findStartOfRightSubsequence(array);
		
		//get min and max
		int max_index = end_left;	//max of left side
		int min_index = start_right; //min of right side
		for(int i = end_left + 1; i < start_right; i++){
			if(array[i] < array[min_index])
				min_index = i;
			if(array[i] > array[max_index])
				max_index = i;
		}
		
		//slide left until less than array[min_index]
		int left_index = shrinkLeft(array, min_index, end_left);
		
		//slide right until greater than array[max_index]
		int right_index = shrinkRight(array, max_index, start_right);
		
		System.out.println(left_index + " " + right_index);
		
	}
	
	int findEndOfLeftSubsequence(int[] array){
		for(int i = 1; i < array.length; i++){
			if(array[i] < array[i - 1])
				return i - 1;
		}
		return array.length - 1;
	}
	
	int findStartOfRightSubsequence(int[] array){
		for(int i = array.length - 2; i >= 0; i--){
			if(array[i] > array[i + 1])
				return i + 1;
		}
		return 0;
	}
	
	int shrinkLeft(int[] array, int min_index, int start){
		int comp = array[min_index];
		for(int i = start - 1; i >= 0; i--){
			if(array[i] <= comp)
				return i + 1;
		}
		return 0;
	}
	
	int shrinkRight(int[] array, int max_index, int start){
		int comp = array[max_index];
		for(int i = start; i < array.length; i++){
			if(array[i] >= comp)
				return i - 1;
		}
		return array.length - 1;
	}
}
