package ctci.solutions.aritra;

import java.util.HashMap;

/*
 * Question: Given an array filled with letters and numbers, find the longest subarray with equal number of letters and numbers
 * 
 * Solution: Let us take an example array
 * a a a a 1 1 a 1 1 a a 1 a a 1 a a a a a
 * 
 * Now we will create a table with count of a's and 1's
 * 		
 * 		a 	a 	a 	a 	1 	1 	a 	1 	1 	a 	a 	1 	a 	a 	1 	a 	a 	a 	a 	a
 * #a   1   2   3   4   4   4   5   5   5   6   7   7   8   9   9   10  11  12  13  14
 * #1   0   0   0   0   1   2   2   3   4   4   4   5   5   5   6   6   6   6   6   6
 * -    1   2   3   4   3   2   3   2   1   2   3   2   3   4   3   4   5   6   7   8
 * 
 * If we study the difference of a's and 1's at say (9,5) and (10,6). This makes sense. Since they've added the same number of letters and numbers, they should maintain the same difference.
 * So whenever we return the same difference , then we know we have found an equal subarray. To find the biggest subarray, we just have to find the two indices farthest apart with the same value.
 * 
 * To do so, we use a hashtable to store the first time we see a particular difference. Then, each time we see the same difference, we see if this subarray (from first occurrence of this index to curr
 * ent index) is bigger than the current max. If so we update max.
 */

public class LettersAndNumbers {
	
	public static void main(String[] args) {
		char[] array = {'a','a','a','a','1','1','a','1','1','a','a','1','a','a','1','a','a','a','a','a'};
		
		LettersAndNumbers ln = new LettersAndNumbers();
		char[] longestSubarray = ln.findLongestSubarray(array);
		
		System.out.println(longestSubarray);
	}
	
	
	char[] findLongestSubarray(char[] array) {
		// Compute deltas between count of numbers and count of letters.
		int[] deltas = computeDeltaArray(array);

		// Find pair in deltas with matching values and largest span.
		int[] match = findLongestMatch(deltas);

		// return the subarray. Note that it starts one after the initial
		// occurrence of this delta
		return extract(array, match[0] + 1, match[1]);
	}

	// Compute the difference between the number of letters and numbers between
	// the beginning of the array and each index
	int[] computeDeltaArray(char[] array) {
		int[] deltas = new int[array.length];
		int delta = 0;
		for (int i = 0; i < array.length; i++) {
			if (Character.isLetter(array[i])) {
				delta++;
			} else if (Character.isDigit(array[i])) {
				delta--;
			}
			deltas[i] = delta;
		}
		return deltas;
	}

	// Find the matching pair of values in the deltas array with the largest
	// difference in indices
	int[] findLongestMatch(int[] deltas) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1);
		int[] max = new int[2];
		for (int i = 0; i < deltas.length; i++) {
			if (!map.containsKey(deltas[i])) {
				map.put(deltas[i], i);
			} else {
				int match = map.get(deltas[i]);
				int distance = i - match;
				int longest = max[1] - max[0];
				if (distance > longest) {
					max[1] = i;
					max[0] = match;
				}
			}
		}
		return max;
	}

	// Return subarray of array between start and end (inclusive)
	char[] extract(char[] array, int start, int end) {
		char[] subarray = new char[end - start + 1];
		for (int i = start; i <= end; i++) {
			subarray[i - start] = array[i];
		}
		return subarray;
	}
}
