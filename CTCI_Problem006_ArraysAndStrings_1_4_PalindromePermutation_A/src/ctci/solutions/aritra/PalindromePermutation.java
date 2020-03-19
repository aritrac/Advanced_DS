package ctci.solutions.aritra;

/**
 * 
 * @author ARITCHAT
 * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase that is same forwards and backwards
 * .A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.
 * 
 * Example:
 * Input: Tact Coa
 * Output: true (permutations: "taco cat","atco cta", etc).
 * 
 * To be more precise, Strings with even length (after removing all non-letter characters) must have all even counts of characters. Strings of an odd length
 * must have exactly one character with an odd count. Of course, an 'even' string can't have an odd number of exactly one character, otherwise it
 * wouldn't be an even-length string(an odd number + many even numbers = an odd number). Likewise, a string with odd length can't have all
 * characters with even counts(sum of evens is even). Its therefore sufficient to say that to be a permutation of a palindrome, a string can have no more
 * than one character that is odd. This will cover both the odd and the even ones.
 * 
 * Implementing this algorithm is fairly straightforward. We use a hash table to count how many times each character appears. Then we iterate through the 
 * hash table and ensure that no more than one character has an odd count.
 */

public class PalindromePermutation {
	public static void main(String[] args) {
		String p1 = "taco cat";
		String p2 = "atco cta";
		String p3 = "atco ctr";
		
		System.out.println("Is p1 a palindromic permutation of t1? " + isPermutationOfPalindrome(p1));
		System.out.println("Is p2 a palindromic permutation of t1? " + isPermutationOfPalindrome(p2));
		System.out.println("Is p3 a palindromic permutation of t1? " + isPermutationOfPalindrome(p3));
	}
	
	public static boolean isPermutationOfPalindrome(String phrase){
		int[] table = buildCharFrequencyTable(phrase);
		return checkMaxOneOdd(table);
	}
	
	//Check that no more than one character has an odd count
	public static boolean checkMaxOneOdd(int[] table){
		boolean foundOdd = false;
		for(int count : table){
			if(count % 2 == 1){
				if(foundOdd){
					return false;
				}
				foundOdd = true;
			}
		}
		return true;
	}
	
	//Map each character to a number. a -> 0, b -> 1, c -> 2, etc. This is case insensitive. Non-letter characters map to -1
	public static int getCharNumber(Character c){
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		if(a <= val && val <= z){
			return val - a;
		}
		return -1;
	}
	
	//Count how many times each character appears.
	
	public static int[] buildCharFrequencyTable(String phrase){
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for(char c : phrase.toCharArray()){
			int x = getCharNumber(c);
			if(x != -1){
				table[x] ++;
			}
		}
		return table;
	}
}
