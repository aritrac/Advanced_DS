package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashSet;

/*
 * Question: On old cell phones, users typed on a numeric keypad and the phone would provide a list of words that matched these numbers. Each digit mapped to a set of 0-4 letters.
 * Implement an algorithm to return a list of matching words, given a sequence of digits. You are provided a list of valid words(provided in whatever data structure you'd like).
 * The mapping is shown in the diagram below.
 * 
 *  1		2(abc)		3(def)
 *  4(ghi)	5(jkl)		6(mno)
 *  7(pqrs) 8(tuv)		9(wxyz)
 *  		0
 *  
 *  Example:
 *  Input: 8733
 *  Output: tree, used
 *  
 *  Solution: Imagine how you would solve the problem if you had to do it by hand. You'd probably try every possible value for each digit with all other possible values.
 *  This is exactly what we do algorithmically. We take the first digit and run through all the characters that map to that digit. For each character, we add it to a prefix variable and recurse,
 *  passing the prefix downward. Once we run out of characters, we print prefix (which now contains the full word) if the string is a valid word.
 *  
 *  We will assume the list of words is passed in as a HashSet.A HashSet operates similarly to a hash table, but rather than offering key -> value lookups, it can tell us
 *  if a word is contained in the set in O(1) time.
 */

public class T9Dictionary {
	ArrayList<String> getValidT9Words(String number, HashSet<String> wordList){
		ArrayList<String> results = new ArrayList<String>();
		getValidWords(number, 0, "", wordList, results);
		return results;
	}
	
	void getValidWords(String number, int index, String prefix, HashSet<String> wordSet, ArrayList<String> results){
		//If its a complete word, print it
		if(index == number.length() && wordSet.contains(prefix)){
			results.add(prefix);
			return;
		}
		
		//Get characters that match this digit
		char digit = number.charAt(index);
		char[] letters = getT9Chars(digit);
		
		//Go through all remaining options
		if(letters != null){
			for(char letter : letters){
				getValidWords(number, index + 1, prefix + letter, wordSet, results);
			}
		}
	}
	
	//Return array of characters that map to this digit
	char[] getT9Chars(char digit){
		if(!Character.isDigit(digit)){
			return null;
		}
		int dig = Character.getNumericValue(digit) - Character.getNumericValue('0');
		return t9Letters[dig];
	}
	
	//Mapping of digits to letters
	char[][] t9Letters = {null, null, {'a','b','c'},
						  {'d','e','f'},
						  {'g','h','i'},
						  {'j','k','l'},
						  {'m','n','o'},
						  {'p','q','r','s'},
						  {'t','u','v'},
						  {'w','x','y','z'}};
}
