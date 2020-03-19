package ctci.solutions.aritra;

/*
 * Question: Write a method that adds two number. You should not use + or any arithmetic operators.
 * 
 * Solution: Let us take an example of how to add 2 base 10 numbers: 759 and 674.
 * 1)Add 759 and 674, but forget to carry. I then get 323
 * 2)Add 759 + 674 but only do carrying,rather than the addition of each digit. I then get 1110.
 * 3)Add the result of the first two operations (recursively, using the same process described in step 1 and 2): 1110 + 323 = 1433
 * 
 *  Now how do we do this in binary?
 *  1) If I add two binary numbers together, but forget to carry, the ith bit in the sum will be 0 only if a and b have the same ith bit (both 0 or both 1). This is essentially an XOR.
 *  2)If I add two numbers together but only carry, I will have a 1 in the ith bit of the sum only if bits i - 1 of a and b are both 1s. This is an AND shifted.
 *  3)Now, recurse until there is nothing to carry.
 *  The following code implements this algorithm in a recursive fashion.
 */

public class AddWithoutPlus {
	public static void main(String[] args) {
		System.out.println("759 + 674 = " + new AddWithoutPlus().add(759, 674));
	}

	int add(int a, int b){
		if(b == 0)
			return a;
		int sum = a ^ b; //Add without carrying
		int carry = (a & b) << 1; //carry but don't add
		return add(sum, carry); //recurse with sum, carry
	}
}
