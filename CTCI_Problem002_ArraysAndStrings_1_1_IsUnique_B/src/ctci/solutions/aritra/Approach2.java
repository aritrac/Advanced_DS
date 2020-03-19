package ctci.solutions.aritra;

/**
 * 
 * @author ARITCHAT
 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structure?
 * 
 * This solution uses a bit vector to reduce the space complexity by a factor of 8
 *
 */
public class Approach2 {
	public static void main(String[] args) {
		String test1 = "animal";
		String test2 = "rock";
		
		System.out.println("All characters unique in test1? " + isUniqueChars(test1));
		System.out.println("All characters unique in test2? " + isUniqueChars(test2));
	}
	
	public static boolean isUniqueChars(String str){
		int checker = 0;
		for(int i = 0; i < str.length(); i++){
			int val = str.charAt(i) - 'a';
			System.out.println(val + " " + checker);
			if((checker & (1 << val)) > 0){
				return false;
			}
			checker |= (1 << val);
		}
		return true;
	}
}
