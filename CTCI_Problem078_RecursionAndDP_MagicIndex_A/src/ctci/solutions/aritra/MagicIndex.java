package ctci.solutions.aritra;

/*
 * Question: A magic index in an array A[1...n-1] is defined to be an index such that A[i] = i. Given a sorted array of distinct integers,
 * write a method to find a magic index, if one exists, in array A.
 * 
 * Solution: 				-40	-20	-1	1	2	3	5	7	9	12	13 <- Values
 * 							  0	  1  2  3   4   5   6   7   8    9  10 <- Indices
 * 
 * When we look at the middle element A[5] = 3, we know that the magic index must be on the right side, since A[mid] < mid.
 * Why couldn't the magic index be on the left side? Observe that when we move from i to i - 1, the value at this index must decrease by atleast 1, if not more
 * (since the array is sorted and all elements are distinct). So, if the middle element is already too small to be a magic index, then when we move to the left,
 * subtracting k indexes and (at least) k values, all subsequent elements will also be too small. 
 * We continue to apply this recursive algorithm, developing code that looks very much like binary search.
 */

public class MagicIndex {
	int magicFast(int[] array){
		return magicFast(array,0,array.length - 1);
	}
	
	int magicFast(int[] array, int start, int end){
		if(end < start){
			return -1;
		}
		
		int mid = (start + end)/2;
		if(array[mid] == mid){
			return mid;
		}else if(array[mid] > mid){
			return magicFast(array, start, mid - 1);
		}else{
			return magicFast(array, mid + 1, end);
		}
	}
	
	public static void main(String[] args) {
		int[] array = {-40,-20,-1,1,2,3,5,7,9,12,13};
		
		System.out.println("The magic index is at " + new MagicIndex().magicFast(array));
	}
}
