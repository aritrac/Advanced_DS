package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/*
 * Question: Given two words of equal length that are in a dictionary, write a method to transform one word into another word by changing only one letter at a time. The new word
 * you get in each step must be in the dictionary
 * 
 * Example:
 * INPUT: 	DAMP,LIKE
 * OUTPUT:	DAMP -> LAMP -> LIMP -> LIME -> LIKE
 * 
 * Solution: One way of solving this problem is to just transform the words in every possible way (of course checking at each step to ensure each is a valid word), and then see if we can reach
 * the final word
 * 
 * So, for example, the word bold would be transformed into:
 * [a]old, [b]old,...[z]old
 * b[a]ld, b[b]ld,...b[z]ld
 * bo[a]d, bo[b]d,...,bo[z]d
 * bol[a], bol[b],...,bol[z]
 * 
 * We will terminate(not persue the path) if the string is not a valid word or if we've already visited this word.
 * This is essentially a depth-first search where there is an 'edge' between two words if they are only one edit apart. This means that this algorithm will not find the shortest path. It will
 * only find a path. If we wanted to find the shortest path, we would want to use breadth-first-search
 */

public class WordTransformer {
	LinkedList<String> transform(String start, String stop, String[] words){
		HashSet<String> dict = setupDictionary(words);
		HashSet<String> visited = new HashSet<String>();
		return transform(visited, start, stop, dict);
	}
	
	HashSet<String> setupDictionary(String[] words){
		HashSet<String> hash = new HashSet<String>();
		for(String word : words){
			hash.add(word.toLowerCase());
		}
		return hash;
	}
	
	LinkedList<String> transform(HashSet<String> visited, String startWord, String stopWord, Set<String> dictionary){
		if(startWord.equals(stopWord)){
			LinkedList<String> path = new LinkedList<String>();
			path.add(startWord);
			return path;
		}else if(visited.contains(startWord) || !dictionary.contains(startWord)){ //If we came across that word before or the dictionary does not contain that word, then return
			return null;
		}
		
		visited.add(startWord);
		
		//Generating all the words which are 1 edit away from the current word which may or may not be in dictionary
		ArrayList<String> words = wordsOneAway(startWord);
		
		for(String word : words){
			LinkedList<String> path = transform(visited,word, stopWord, dictionary);
			if(path != null){
				path.addFirst(startWord);
				return path;
			}
		}
		return null;
	}
	
	//Generating exhaustive list of words by replacing each character of the word by all alphabets and adding them to a list which is returned
	ArrayList<String> wordsOneAway(String word){
		ArrayList<String> words = new ArrayList<String>();
		for(int i = 0; i < word.length(); i++){
			for(char c = 'a'; c <= 'z'; c++){
				String w = word.substring(0,i) + c + word.substring(i + 1);
				words.add(w);
			}
		}
		return words;
	}
}
