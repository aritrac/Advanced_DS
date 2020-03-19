 package ctci.solutions.aritra;

/*
 * Question: Write a method that finds the maximum of two numbers. You should not use if-else or any other comparison operator.
 * 
 * Solution: A common way of implementing a max function is to look at the sign of a - b. In this case, we can't use a comparison operator on this sign, but we can use multiplication.
 * Let k equal the sign of a - b such that if a - b >= 0, then k is 1. Else, k = 0. Let q be the inverse of k.
 * 
 * Based on this logic, we can write the code as follows
 *
 */

public class NumberMax {
	
	public static void main(String[] args) {
		int a = 13;
		int b = 17;
		NumberMax nm = new NumberMax();
		System.out.println("The max of 13 and 17 = " + nm.getMaxNaive(a, b));
	}
	//Flips a 1 to a 0 and a 0 to a 1
	int flip(int bit){
		return 1^bit;
	}
	
	//Returns 1 if a is positive, and 0 if a is negative.
	int sign(int a){
		return flip((a >> 31) & 0x1); //extracting the sign bit, for positive numbers 0 is passed to flip which returns a 1, hence for positive numbers a 1 is returned.
	}
	
	int getMaxNaive(int a, int b){
		int k = sign(a-b);
		int q = flip(k);
		return a * k + b * q;
	}
}
