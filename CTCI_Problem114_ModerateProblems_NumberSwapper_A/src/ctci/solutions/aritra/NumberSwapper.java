package ctci.solutions.aritra;

/*
 * Question: Write a function to swap a number in place (that is, without temporary variables)
 * 
 * Solution: We will walk through this using a0 to indicate original value of a and b0 to indicate original value of b.
 * We'll also use diff to indicate the value of a0 - b0
 * 
 * Lets picture these on a number line for the case where a > b
 * 
 * |--------|--diff------|
 * 0       a0           b0
 * 
 * First, we briefly set a to diff, which is the right side of the above number line. Then, when we add b and diff (and store that value in b), we get a0. 
 * We now have b = a0 and a = diff. All that's left to do is to set a equal to a0 - diff, which is just b - a
 */

public class NumberSwapper {
	public static void main(String[] args) {
		System.out.println("Swapping 63 and 29");
		int a = 63;
		int b = 29;
		System.out.println("Before");
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		a = a - b;
		b = a + b; //diff + b
		a = b - a; //b - diff
		System.out.println("After");
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}
}
