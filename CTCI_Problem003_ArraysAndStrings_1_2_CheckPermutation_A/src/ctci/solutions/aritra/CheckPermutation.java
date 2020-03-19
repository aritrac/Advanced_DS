package ctci.solutions.aritra;

import java.util.Arrays;

/**
 * 
 * @author ARITCHAT
 * Given two strings, write a method to decide if one if a permutation of the other
 * 
 * We will sort the strings to find out they are same or not, if yes then they are permutations
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
	
	public static String sort(String s){
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	
	public static boolean isPermutations(String s, String t){
		if(s.length() != t.length()){
			return false;
		}
		return sort(s).equals(sort(t));
	}
}
