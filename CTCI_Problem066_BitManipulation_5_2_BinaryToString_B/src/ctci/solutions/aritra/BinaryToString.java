package ctci.solutions.aritra;

/*
 * Given a real number between 0 and 1 (e.g. 0.72) that is passed in as a double, print the binary representation. If the number cannot be represented accurately
 * in binary with at most 32 characters, print "ERROR".
 * 
 * Solution:Alternatively, rathen than multiplying the number by two and comparing it to 1, we can compare the number to .5, then .25 and so on. The code below demonstrates this approach	
 */

public class BinaryToString {
	String printBinary(double num){
		if(num >= 1 || num <= 0){
			return "ERROR";
		}
		
		StringBuilder binary = new StringBuilder();
		double frac = 0.5;
		binary.append(".");
		while(num > 0){
			//Setting a limit on length: 32 characters
			if(binary.length() > 32){
				return "ERROR";
			}
			if(num >= frac){
				binary.append(1);
				num -= frac;
			}else{
				binary.append(0);
			}
			frac /= 2;
		}
		return binary.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new BinaryToString().printBinary(0.5));
		System.out.println(new BinaryToString().printBinary(0.72));
	}
}
