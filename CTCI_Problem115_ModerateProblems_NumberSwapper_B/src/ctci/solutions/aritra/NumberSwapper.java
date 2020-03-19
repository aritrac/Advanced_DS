package ctci.solutions.aritra;

/*
 * Question: Write a function to swap a number in place (that is, without temporary variables)
 * 
 * Solution: We can use XOR to also do the inplace swapping
 * For example let a = 101(which is 5)
 * 			   let b = 110(which is 6)
 * We now want to swap these using bitwise XOR. We find if we XOR any bit with 1, it gets toggled and if we XOR any bit with 0 it stays the same.
 * We will first XOR both the numbers to find out which bits differ and which bits are same
 * So a ^ b = 011 which basically means than bit 0 and bit 1 is different in both the numbers, but 2 is same. 
 * So now if want to swap, all we need to do is to toggle the bits which are different in both the numbers (indicate by 1s in a ^ b)
 * 
 * so a = a ^ b // a = 101 ^ 110 = 011
 *    b = a ^ b // b = 011 ^ 110 = 101 (which is a's value assigned to b now as we toggled the different bits in b, we are bound to get a)
 *    a = a ^ b // a = 011 ^ 101 = 110 (which is b's value assigned to a now as we toggled the different bits in b, we are bound to get a back, and now assign that to a again) 
 */

public class NumberSwapper {
	public static void main(String[] args) {
		System.out.println("Swapping 63 and 29");
		int a = 63;
		int b = 29;
		System.out.println("Before");
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		a = a ^ b;
		b = a ^ b; 
		a = a ^ b; 
		System.out.println("After");
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	}
}
