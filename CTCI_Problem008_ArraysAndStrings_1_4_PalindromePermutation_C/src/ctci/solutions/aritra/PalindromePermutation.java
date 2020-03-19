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
 * We can use a single integer (as a bit vector). When we see a letter, we map it to an integer betwen 0 and 26 (assuming an English alphabet). Then we
 * toggle the bit at that value. At the end of the iteration, we check that atmost one bit in the integer is set to 1.
 * 
 * Picture an integer like 00010000. We could ofcourse shift the integer repeatedly to check that there's only a single 1. Alternatively
 * , if we subtract 1 from the number, we'll get 00001111. What's notable about this is that there is no overlap between the numbers
 * (as opposed to say 00101000, which, when we subtract 1 from, we get 00100111). So, we can check to see that a number has exactly one 1 because
 * if we subtract 1 from it and then AND it with the new number, we should get 0.
 * 
 * 00010000 - 1 = 00001111
 * 00010000 & 00001111 = 0
 * 
 * This leads us to our final implementation
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
		int bitVector = createBitVector(phrase);
		return bitVector == 0 || checkExactlyOneBitSet(bitVector); //Checking if only one char is appearing 1 or all characters are having even counts
	}
	
	//Create a bit vector for the string. For each letter with value i, toggle the ith bit.
	public static int createBitVector(String phrase){
		int bitVector = 0;
		for(char c : phrase.toCharArray()){
			int x = getCharNumber(c);
			bitVector = toggle(bitVector,x);
		}
		return bitVector;
	}
	
	//Toggle the ith bit in the integer
	public static int toggle(int bitVector, int index){
		if(index < 0)
			return bitVector;
		
		int mask = 1 << index;
		if((bitVector & mask) == 0){ //If the ith location is 0 then put a 1
			bitVector |= mask;
		}else{                       //If the ith location is 1, then inverse the mask and AND it with the bit vector to set the ith bit to 0
			bitVector &= ~mask;
		}
		
		return bitVector;
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
	
	//Check that exactly one bit is set by subtracting one from the integer and ANDing it with the original integer.
	public static boolean checkExactlyOneBitSet(int bitVector){
		return (bitVector & (bitVector - 1)) == 0;
	}
}
