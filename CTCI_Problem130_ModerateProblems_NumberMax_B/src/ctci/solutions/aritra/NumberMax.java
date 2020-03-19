package ctci.solutions.aritra;

/*
 * Question: Write a method that finds the maximum of two numbers. You should not use if-else or any other comparison operator.
 * 
 * Solution: The previous code almost works. It fails, unfortunately, when a - b overflows. Suppose, for example, that a is INT_MAX - 2 and b is -15.
 * In this case, a - b will be greater than INT_MAX and will overflow, resulting in a negative value.
 * 
 * We can implement a solution to this problem by using the same approach. Our goal is to maintain the condition where k is 1 where a > b. We will need to use more complex
 * logic to accomplish this.
 * 
 * When does a - b overflow? It will overflow only when a is positive and b is negative, or the other way around. If may be difficult to specially detect the overflow condition,
 * but we can detect when a and b have different signs. Note that if a and b have different signs, then we want k to equal sign(a)
 * 
 * The logic looks like
 * If a and b have different signs:
 * 		if a > 0, then b < 0, and k = 1
 * 		if a < 0, then b > 0, and k = 0
 * so either way, k = sign(a)
 * let k = sign(a) -> overflow is possible as a and b are of different signs in this case
 * else
 * let k = sign(a-b) -> overflow is impossible as a and b are both positive numbers in this case
 * 
 * The code below implements this, using multiplication instead of if-statements
 */

public class NumberMax {
	public static void main(String[] args) {
		NumberMax nm = new NumberMax();
		System.out.println("The max of NUM_MAX and -15 = "
				+ nm.getMax(Integer.MAX_VALUE, -15));
		System.out.println("The max of 13 and 17 = " + nm.getMax(13, 17));
		System.out.println("The max of 13 and -17 = " + nm.getMax(13, -17));
		System.out.println("The max of -13 and -17 = " + nm.getMax(-13, -17));
		System.out.println("The max of -13 and 17 = " + nm.getMax(-13, 17));
	}

	// Flips a 1 to a 0 and a 0 to a 1
	int flip(int bit) {
		return 1 ^ bit;
	}

	// Returns 1 if a is positive, and 0 if a is negative.
	int sign(int a) {
		return flip((a >> 31) & 0x1);
	}

	int getMax(int a, int b) {
		int c = a - b;
		int sa = sign(a); // if a >= 0, then 1 else 0
		int sb = sign(b); // if b >= 0, then 1 else 0
		int sc = sign(c); // depends on whether or not a - b overflows

		// Goal: define a value k which is 1 if a > b and 0 if a < b.
		// (if a == b, it doesn't matter what value k is)

		// If a and b have different signs, then k = sign(a)
		int use_sign_of_a = sa ^ sb;

		// If a and b have the same sign, then k = sign(a - b)
		int use_sign_of_c = flip(sa ^ sb);

		int k = use_sign_of_a * sa + use_sign_of_c * sc;
		int q = flip(k); // opposite of k

		return a * k + b * q;
	}
}
