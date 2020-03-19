package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
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
 * Solution: To travel to only valid words, we clearly need a way of going from each word to a list of all the valid related words.
 * 
 * What makes two words 'related' (one edit away?) They are one edit away if all but one character is the same. For example, ball and bill are one edit away,because
 * they are both in the form b_ll. Thereform, one approach is to group all words that look like b_ll together.
 * 
 * We can do this for the whole dictionary by creating a mapping from a 'wildcard word' (like b_ll) to a list of all words in this form.
 * For example, for a very small dictionary like {all, ill, ail, ape, ale} the mapping might look like this.
 * 
 * _il -> ail
 * _le -> ale
 * _ll -> all, ill
 * _pe -> ape
 * 
 * a_e -> ape, ale
 * a_l -> all, ail
 * i_l -> ill
 * 
 * ai_ -> ail
 * al_ -> all, ale
 * il_ -> ill
 * 
 * Now when we want to know the words that are one edit away from a word like ale, we look up _le, a_e and al_ in the hash table.
 * The algorithm otherwise is essentially same.
 */

public class WordTransformer {
	LinkedList<String> transform(String start, String stop, String[] words){
		HashMapList<String, String> wildcardToWordList = createWildcardToWordMap(words);
		HashSet<String> visited = new HashSet<String>();
		return transform(visited, start, stop, wildcardToWordList);
	}
	
	//Do a depth-first search from startWord to stopWord, travelling through each word that is one edit away
	LinkedList<String> transform(HashSet<String> visited, String start, String stop, HashMapList<String, String> wildcardToWordList){
		if(start.equals(stop)){
			LinkedList<String> path = new LinkedList<String>();
			path.add(start);
			return path;
		}else if(visited.contains(start)){
			return null;
		}
		visited.add(start);
		ArrayList<String> words = getValidLinkedWords(start, wildcardToWordList);
		
		for(String word : words){
			LinkedList<String> path = transform(visited, word, stop, wildcardToWordList);
			if(path != null){
				path.addFirst(start);
				return path;
			}
		}
		return null;
	}
	
	//Insert words in dictionary intp mapping from wildcard form -> word.
	HashMapList<String, String> createWildcardToWordMap(String[] words){
		HashMapList<String,String> wildcardToWords = new HashMapList<String,String>();
		for(String word: words){
			ArrayList<String> linked = getWildcardRoots(word);
			for(String linkedWord : linked){
				wildcardToWords.put(linkedWord, word);
			}
		}
		return wildcardToWords;
	}
	
	//Get list of wildcards associated with word
	ArrayList<String> getWildcardRoots(String w){
		ArrayList<String> words = new ArrayList<String>();
		for(int i = 0; i < w.length(); i++){
			String word = w.substring(0, i) + "_" + w.substring(i + 1);
			words.add(word);
		}
		return words;
	}
	
	//Return words that are one edit away
	ArrayList<String> getValidLinkedWords(String word, HashMapList<String,String> wildcardToWords){
		ArrayList<String> wildcards = getWildcardRoots(word);
		ArrayList<String> linkedWords = new ArrayList<String>();
		for(String wildcard : wildcards){
			ArrayList<String> words = wildcardToWords.get(wildcard);
			for(String linkedWord : words){
				if(!linkedWord.equals(word)){
					linkedWords.add(linkedWord);
				}
			}
		}
		return linkedWords;
	}
}

class HashMapList<T,E>{
	private HashMap<T, ArrayList<E>> map = new HashMap<T, ArrayList<E>>();
	
	//Insert item into list at key.
	public void put(T key, E item){
		if(!map.containsKey(key)){
			map.put(key, new ArrayList<E>());
		}
		map.get(key).add(item);
	}
	
	//Insert list of items at key
	public void put(T key, ArrayList<E> items){
		map.put(key, items);
	}
	
	//Get list of items at key
	public ArrayList<E> get(T key){
		return map.get(key);
	}
	
	//Check if hashmaplist contains key
	public boolean containsKey(T key){
		return map.containsKey(key);
	}
	
	//Check if list at key contains value
	public boolean containsKeyValue(T key, E value){
		ArrayList<E> list = get(key);
		if(list == null)
			return false;
		return list.contains(value);
	}
	
	//Get the list of keys
	public Set<T> keySet(){
		return map.keySet();
	}
	
	@Override
	public String toString(){
		return map.toString();
	}
}
