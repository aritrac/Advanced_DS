package ctci.solutions.aritra;

import java.util.ArrayList;

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
 *  Solution: Lets return to thinking about how you would do this, if you were doing this by hand. Imagine the example of 33835676368 (which corresponds to development).
 *  If you were doing this by hand, I bet you'd skip over solutions that start with fftf [3383], as no valid words start with those characters.
 *  Ideally, we'd like our program to make the same sort of optimization: stop recursing down paths which will obviously fail. Specifically, if there are no words
 *  in the dictionary that start with prefix, stop recursing.
 *  
 *  The Trie data structure can do this for us. Whenever we reach a string which is not a valid prefix, we exit.
 */

public class T9Dictionary {
	ArrayList<String> getValidT9Words(String number, Trie trie){
		ArrayList<String> results = new ArrayList<String>();
		getValidWords(number, 0, "", trie.getRoot(), results);
		return results;
	}
	
	void getValidWords(String number, int index, String prefix, TrieNode trieNode, ArrayList<String> results){
		//If it's a complete word, print it
		if(index == number.length()){
			if(trieNode.terminates()){ //Is complete word
				results.add(prefix);
			}
			return;
		}
		
		//Get characters that match this digit
		char digit = number.charAt(index);
		char[] letters = getT9Chars(digit);
		
		//go through all remaining options
		if(letters != null){
			for(char letter : letters){
				TrieNode child = trieNode.getChild(letter);
				//If there are words that start with prefix + letter, then continue recursing
				if(child != null){
					getValidWords(number, index + 1, prefix + letter, child, results);
				}
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
