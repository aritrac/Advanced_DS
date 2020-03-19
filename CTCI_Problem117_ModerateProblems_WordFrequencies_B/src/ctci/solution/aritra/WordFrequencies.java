package ctci.solution.aritra;

import java.util.HashMap;

import ctci.solution.aritra.WordFrequencies;

/*
 * Question: Design a method to find the frequency of occurrences of any given word in a book. What if we were running this algorithm multiple times?
 * 
 * Solution: If we're doing the operation repeatedly, then we can probably afford to take some time and extra memory to do pre-processing on the book. We can create a hash table which
 * maps from a word to its frequency. The frequency of any word can be easily looked up in O(1) time. The code for this is below.
 */

public class WordFrequencies {
	int getFrequency(HashMap<String, Integer> table, String word){
		if(table == null || word == null)
			return -1;
		word = word.toLowerCase();
		if(table.containsKey(word)){
			return table.get(word);
		}
		return 0;
	}
	
	HashMap<String, Integer> setupDictionary(String[] book){
		HashMap<String, Integer> table = new HashMap<String, Integer>();
		for(String word : book){
			word = word.toLowerCase();
			if(word.trim() != ""){
				if(!table.containsKey(word)){
					table.put(word, 0);
				}
				table.put(word, table.get(word) + 1);
			}
		}
		return table;
	}
	
	public static void main(String[] args) {
		String[] book = "I was alone in the jungle when it happened and then again we forgot most of it but those eyes I still remember those eyes".split(" ");
		WordFrequencies wf = new WordFrequencies();
		System.out.println("The number of times 'it' word occurs = " + wf.getFrequency(wf.setupDictionary(book), "it"));
	}
}
