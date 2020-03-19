package ctci.solutions.aritra;

/*
 * Question: You are given an array of integers (both positive and negative). Find the contiguous sequence with the largest sum. Return the sum.
 * 
 * Example:
 * Input: 2, 3, -8, -1, 2, 4, -2, 3
 * Output: 7 (i.e., {6, -2, 3})
 * 
 * Solution: For the purpose of coming up with our algorithm, we can think about our array as being a sequence of alternating negative and positive numbers. Each number
 * corresponds to the sum of a subsequence of positive numbers of a subsequence of negative numbers. For the array above, our new array would be:
 * 5 -9 6 -2 3
 * 
 * This doesn't give away a great algorithm immediately, but it does help us to better understand what we're working with.
 * Consider the array above. Would it ever make sense to have {5, -9} in a subsequence? No, these numbers sum to -4, so we're better off not including either number,
 * or possibly just having the sequence be just {5}.
 * When would we want negative numbers included in a subsequence? Only if it allows us to join two positive subsequences, each of which have a sum greater than the negative value.
 * 
 * We can approach this in a step-wise manner, starting with the first element in the array.
 * 
 * When we look at 5, this is the biggest sum we've seen so far. We set maxSum to 5, and sum to 5. Then, we consider -9. If we added it to sum, we'd get a negative value.
 * There's no sense in extending the subsequence from 5 to -9(which reduces to a sequence of just -4), so we just reset the value of sum
 * 
 * Now we consider 6. This subsequence is greater than 5, so we update both maxSum and sum.
 * 
 * Next, we look at -2. Adding this to 6 will set sum to 4. Since this is still a "value add" (when adjoined to another, bigger sequence) we might want {6,-2} in our subsequence.
 * We'll update sum but not maxSum.
 * 
 * Finally, we look at 3. Adding 3 to sum(4) gives us 7, so we update maxSum. The max subsequence is therefore the sequence {6,-2,3}.
 * 
 * If the array is all negative numbers, then the maxSum is either
 * 1.Greatest negative number if we assume the subsequence to be non-empty
 * 2.0 (the subsequence has 0 length)
 * 3.MINIMUM_INT (essentially, the error case)
 * In our code below, we have used #2 which is maxSum = 0, but can be either of the other two also.
 */

public class ContiguousSequence {
	public static void main(String[] args) {
		int[] arr = {2,3,-8,-1,2,4,-2,3};
		int maxSum = new ContiguousSequence().getMaxSum(arr);
		System.out.println(maxSum);
	}
	
	int getMaxSum(int[] a){
		int maxSum = 0;
		int sum = 0;
		for(int i = 0; i < a.length; i++){
			sum += a[i];
			if(maxSum < sum){
				maxSum = sum;
			}else if(sum < 0){
				sum = 0;
			}
		}
		return maxSum;
	}
}
