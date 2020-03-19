package ctci.solutions.aritra;

/*
 * Question: Given an array filled with letters and numbers, find the longest subarray with equal number of letters and numbers
 * 
 * Solution: Let's start with the obvious solution. Just go through all subarrays, count the number of As and Bs (or letters and numbers), and find the longest one that is equal.
 * We can make one small optimization to this. We can start with the longest subarray and as soon as we find one which fits this equality condition, return it.
 * 
 * Despite one optimization, this algorithm is still O(N^2), where N is the length of the array.
 */

public class LettersAndnumbers {
	//Return the largest subarray with equal number of 0s and 1s. Look at each subarray, starting from the longest. As soon as we find one that's equal, we return.
	char[] findLongestSubarray(char[] array){
		for(int len = array.length; len > 1; len--){
			for(int i = 0; i <= array.length - len; i++){
				if(hasEqualLettersNumbers(array,i,i + len - 1)){
					return extractSubarray(array, i, i + len - 1);
				}
			}
		}
		return null;
	}
	
	//Check if subarray has equal number of letters and numbers.
	boolean hasEqualLettersNumbers(char[] array, int start, int end){
		int counter = 0;
		for(int i = start; i <= end; i++){
			if(Character.isLetter(array[i])){
				counter++;
			}else if(Character.isDigit(array[i])){
				counter--;
			}
		}
		return counter == 0;
	}
	
	//Return subarray of array between start and end (inclusive)
	char[] extractSubarray(char[] array, int start, int end){
		char[] subarray = new char[end - start + 1];
		for(int i = start; i <= end; i++){
			subarray[i - start] = array[i];
		}
		return subarray;
	}
}
