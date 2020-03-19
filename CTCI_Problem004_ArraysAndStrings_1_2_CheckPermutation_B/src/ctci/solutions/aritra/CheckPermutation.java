package ctci.solutions.aritra;

/**
 * 
 * @author ARITCHAT
 * This solution checks for the character count in each of the strings and compares them. If they are same then the strings 
 * are permutations
 *
 */
public class CheckPermutation {
	public static void main(String[] args) {
		String test1 = "forgive";
		String test2 = "orgvfie";
		String test3 = "forgave";
		
		System.out.println("Is test1 and test2 permutations? " + isPermutations(test1, test2));
		System.out.println("Is test2 and test3 permutations? " + isPermutations(test2, test3));
	}
	
	public static boolean isPermutations(String s, String t){
		if(s.length() != t.length()){
			return false;
		}
		
		int[] letters = new int[128]; //Assumption
		
		char[] s_array = s.toCharArray();
		for(char c : s_array){ //count number of each character in s.
			letters[c]++;
		}
		
		for(int i = 0; i < t.length(); i++){
			int c = (int)t.charAt(i);
			letters[c]--;
			if(letters[c] < 0){
				return false;
			}
		}
		return true;
	}
}
