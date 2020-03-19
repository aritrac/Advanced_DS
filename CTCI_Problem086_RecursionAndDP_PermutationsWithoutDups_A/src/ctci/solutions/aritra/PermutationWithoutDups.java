package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Question: Write a method to compute all permutations of a string of unique characters
 * 
 * Solution: 
 * Base Case: permutations of first character substrings
 * The only permutation of a1 is the string a1. So
 * P(a1) = a1
 * 
 * Case: permutations of a1a2
 * P(a1a2) = a1a2 or a2a1
 * 
 * Case: permutations of a1a2a3
 * P(a1a2a3) = a1a2a3, a3a1a2, a1a3a2, a3a2a1, a2a1a3, a2a3a1
 * 
 * Case: permutations of a1a2a3a4
 * This is the first interesting case. How can we generate permutations of a1a2a3a4 from a1a2a3?
 * Each permutation of a1a2a3a4 represents an ordering of a1a2a3. For example a2a4a1a3 represents the order a2a1a3
 * Therefore, if we took all the permutations of a1a2a3 and added a4 into all possible locations, we would get all permutations of a1a2a3a4
 * 
 * a1a2a3 -> a4a1a2a3, a1a4a2a3, a1a2a4a3, a1a2a3a4
 * a1a3a2 -> a4a1a3a2, a1a4a3a2, a1a3a4a2, a1a3a2a4
 * a3a1a2 -> a4a3a1a2, a3a4a1a2, a3a1a4a2, a3a1a2a4
 * a2a1a3 -> a4a2a1a3, a2a4a1a3, a2a1a4a3, a2a1a3a4
 * a2a3a1 -> a4a2a3a1, a2a4a3a1, a2a3a4a1, a2a3a1a4
 * a3a2a1 -> a4a3a2a1, a3a4a2a1, a3a2a4a1, a3a2a1a4
 * 
 * We can now implement this recursively
 */

public class PermutationWithoutDups {
	public static void main(String[] args) {
		PermutationWithoutDups pwd = new PermutationWithoutDups();
		
		List<String> allPerms = pwd.getPerms("arit");
		
		Iterator<String> permIter = allPerms.iterator();
		while(permIter.hasNext()){
			System.out.println(permIter.next());
		}
	}
	
	ArrayList<String> getPerms(String str){
		if(str == null)
			return null;
		
		ArrayList<String> permutations = new ArrayList<String>();
		if(str.length() == 0){ //base case
			permutations.add("");
			return permutations;
		}
		
		char first = str.charAt(0); //get first character
		String remainder = str.substring(1); //remove the first character
		ArrayList<String> words = getPerms(remainder);
		for(String word : words){
			for(int j = 0; j <= word.length(); j++){
				String s = insertCharAt(word, first, j);
				permutations.add(s);
			}
		}
		return permutations;
	}
	
	//Insert char c at index i in word
	String insertCharAt(String word, char c, int i){
		String start = word.substring(0,i);
		String end = word.substring(i);
		return start + c + end;
	}
}
