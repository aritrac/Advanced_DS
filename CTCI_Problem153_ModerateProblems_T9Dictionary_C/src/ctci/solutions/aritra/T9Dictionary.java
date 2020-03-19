package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
 *  Solution: We can actually make it run even faster. We just need to do a little bit of preprocessing. That's not a big deal though. We were doing that to build the trie anyway.
 *  This problem is asking us to list all the words represented by a particular number in T9. Instead of trying to do this on the fly and going through a lot of possibilities,
 *  many of which won't actually work, we can just do this in advance.
 *  
 *  Pre-computation:
 *  1)Create a hash table that maps from a sequence of digits to a list of strings
 *  2)Go though each word in the dictionary and convert it to its T9 representation (e.g. APPLE -> 27753). Store each of these in the above hash table.
 *  For example, 8733 would map to {used, tree}.
 *  
 *  Word Lookup:
 *  1)Just look up the entry in the hash table and return the list.
 *  
 *  That's it !!
 */

public class T9Dictionary {
	//WORD LOOKUP
	ArrayList<String> getValidT9Words(String numbers, HashMapList<String, String> dictionary){
		return dictionary.get(numbers);
	}
	
	//PRECOMPUTATION
	
	//Create a hash table that maps from a number to all words that have this numerical representation.
	
	HashMapList<String, String> initializeDictionary(String[] words){
		//Create a hash table that maps from a letter to the digit
		HashMap<Character, Character> letterToNumberMap = createLetterToNumberMap();
		
		//Create word -> number map
		HashMapList<String, String> wordsToNumbers = new HashMapList<String, String>();
		for(String word : words){
			String numbers = convertToT9(word, letterToNumberMap);
			wordsToNumbers.put(numbers, word);
		}
		return wordsToNumbers;
	}
	
	//Creates a map of letters to its number representation
	HashMap<Character, Character> createLetterToNumberMap(){
		HashMap<Character, Character> letterToNumberMap = new HashMap<Character, Character>();
		for(int i = 0; i < t9Letters.length; i++){
			char[] letters = t9Letters[i];
			if(letters != null){
				for(char letter : letters){
					char c = Character.forDigit(i, 10); //Getting the number of the character
					letterToNumberMap.put(letter, c);
				}
			}
		}
		return letterToNumberMap;
	}
	
	//Convert from a string to its T9 representation
	String convertToT9(String word, HashMap<Character, Character> letterToNumberMap){
		StringBuilder sb = new StringBuilder();
		for(char c : word.toCharArray()){
			if(letterToNumberMap.containsKey(c)){
				char digit = letterToNumberMap.get(c);
				sb.append(digit);
			}
		}
		return sb.toString();
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

class HashMapList<T, E> {
	private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();
	
	/* Insert item into list at key. */
	public void put(T key, E item) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(item);
	}
	
	/* Insert list of items at key. */
	public void put(T key, ArrayList<E> items) {
		map.put(key, items);
	}
	
	/* Get list of items at key. */
	public ArrayList<E> get(T key) {
		return map.get(key);
	}
	
	/* Check if hashmaplist contains key. */
	public boolean containsKey(T key) {
		return map.containsKey(key);
	}
	
	/* Check if list at key contains value. */
	public boolean containsKeyValue(T key, E value) {
		ArrayList<E> list = get(key);
		if (list == null) return false;
		return list.contains(value);
	}
	
	/* Get the list of keys. */
	public Set<T> keySet() {
		return map.keySet();
	}
	
	@Override
	public String toString() {
		return map.toString();
	}
}