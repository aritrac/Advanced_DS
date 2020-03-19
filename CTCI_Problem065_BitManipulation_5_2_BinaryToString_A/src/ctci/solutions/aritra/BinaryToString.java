package ctci.solutions.aritra;

/*
 * Given a real number between 0 and 1 (e.g. 0.72) that is passed in as a double, print the binary representation. If the number cannot be represented accurately
 * in binary with at most 32 characters, print "ERROR".
 * 
 * Solution:We'll use the subscripts x2 and x10 to indicate whether x is in base 2 or base 10.
 * First, let's start off by asking ourselves what a non-integer number in binary looks like. By analogy to a decimal number, the binary
 * number 0.101(2) would look like
 * 
 * 0.101(2) = 1 * (1/2^1) + 0 * (1/2^2) + 1 * (1/2^3)
 * 
 * To print the decimal part, we can multiply by 2 abd check if 2n is greater than or equal to 1. This is essentially "shifting" the fractional sum. That is:
 * 
 * r = 2(10) * n
 * 	 = 2(10) * 0.101
 *   = 1 * (1/2^0) + 0 * (1/2^1) + 1 * (1/2^2)
 *   = 1.01(2)
 * If r >= 1 then we know that n had a 1 right after the decimal point. By doing this continuously, we can check every digit.	
 */

public class BinaryToString {
	String printBinary(double num){
		if(num >= 1 || num <= 0){
			return "ERROR";
		}
		
		StringBuilder binary = new StringBuilder();
		binary.append(".");
		while(num > 0){
			//Setting a limit on length: 32 characters
			if(binary.length() >= 32){
				break;
			}
			
			double r = num * 2;
			if(r >= 1){
				binary.append(1);
				num = r - 1;
			}else{
				binary.append(0);
				num = r;
			}
		}
		return binary.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new BinaryToString().printBinary(0.5));
		System.out.println(new BinaryToString().printBinary(0.72));
	}
}
