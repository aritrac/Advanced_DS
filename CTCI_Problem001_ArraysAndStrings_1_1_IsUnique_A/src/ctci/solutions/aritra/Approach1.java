package ctci.solutions.aritra;
/**
 * 
 * @author ARITCHAT
 * Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structure?
 *
 */
public class Approach1 {
	public static void main(String[] args) {
		String test1 = "animal";
		String test2 = "rock";
		
		System.out.println("All characters unique in test1? " + isUniqueChars(test1));
		System.out.println("All characters unique in test2? " + isUniqueChars(test2));
	}
	
	public static boolean isUniqueChars(String str){
		if(str.length() > 128) //As there are 128 unique characters, if the string length if greater then that, then it 
							   //can't be unique
			return false;
		
		boolean[] charSet = new boolean[128];
		for(int i = 0; i < str.length(); i++){
			int val = str.charAt(i);
			if(charSet[val]){	//Already found this character in string
				return false;
			}
			charSet[val] = true;
		}
		return true;
	}
}
