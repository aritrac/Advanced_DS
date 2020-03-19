package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/*
 * Question: Given a string b and an array of smaller strings T, design a method to search b for each small string in T.
 * 
 * Solution: Let's start with an example:
 * T = {"is","ppi","hi","sis","i","ssippi"}
 * b = {"mississippi"}
 * 
 * Solution: To optimize this, we should think about how we can tackle all the elements in T at once, or somehow re-use work.
 * One way is to create a trie-like data structure using each suffix in the bigger string. For the string bibs, the suffix would be:
 * bibs, ibs, bs, s
 * 
 * The tree for this is below
 * 						*
 * 			B			I			S
 * 		I		S			B
 * 	  B						  S
 *   S
 * Then all you need to do is search in the suffix tree for each string in T. Note that if 'B' were a word, you would come up with two locations
 */

public class MultiSearch {
	HashMapList<String, Integer> searchAll(String big, String[] smalls){
		HashMapList<String, Integer> lookup = new HashMapList<String, Integer>();
		Trie tree = createTrieFromString(big);
		for(String s : smalls){
			//Get terminating location of each occurrence
			ArrayList<Integer> locations = tree.search(s);
			
			//Adjust to starting location.
			subtractValue(locations, s.length());
			
			//Insert
			lookup.put(s, locations);
		}
		return lookup;
	}
	
	Trie createTrieFromString(String s){
		Trie trie = new Trie();
		for(int i = 0; i < s.length(); i++){
			String suffix = s.substring(i);
			trie.insertString(suffix, i);
		}
		return trie;
	}
	
	void subtractValue(ArrayList<Integer> locations, int delta){
		if(locations == null)
			return;
		for(int i = 0; i < locations.size(); i++){
			locations.set(i, locations.get(i) - delta);
		}
	}
}

class Trie{
	private TrieNode root = new TrieNode();
	
	public Trie(String s){
		insertString(s, 0);
	}
	
	public Trie(){
		
	}
	
	public ArrayList<Integer> search(String s){
		return root.search(s);
	}
	
	public void insertString(String str, int location){
		root.insertString(str, location);
	}
	
	public TrieNode getRoot(){
		return root;
	}
}

class TrieNode{
	private HashMap<Character, TrieNode> children;
	private ArrayList<Integer> indexes;
	private char value;
	
	public TrieNode(){
		children = new HashMap<Character, TrieNode>();
		indexes = new ArrayList<Integer>();
	}
	
	//Suppose ur suffix trie for a string banana will have a single path for each of the following string
	//banana ->here index while inserting through root will be 0
	//anana  ->here index while inserting through root will be 1
	//nana   ->here index while inserting through root will be 2
	//ana    ->here index while inserting through root will be 3
	//na     ->here index while inserting through root will be 4
	//a      ->here index while inserting through root will be 5
	
	//Now suppose we search for string an starting from root, it will directly start the search from the index 3 with string ana
	//and the search will terminate at index 4 as the next child completes the search, so we return to the main code
	//indexes 3 and 4 which gives the location of string an in the main string banana
	
	public void insertString(String s, int index){ //index indicates the index at which the char occurs in the main string, from there on it is incremented by 1 for each following character
		indexes.add(index);
		if(s != null && s.length() > 0){
			value = s.charAt(0);
			TrieNode child = null;
			if(children.containsKey(value)){
				child = children.get(value);
			}else{
				child = new TrieNode();
				children.put(value, child);
			}
			String remainder = s.substring(1);
			child.insertString(remainder, index + 1); //This is where the Trie gets built recursively for a single path from root till the terminating null char
		}else{
			children.put('\0', null); //Terminating Character
		}
	}
	
	public ArrayList<Integer> search(String s){
		if(s == null || s.length() == 0){
			return indexes; //When the string is finally found this is what is returned
		}else{
			char first = s.charAt(0);
			if(children.containsKey(first)){
				String remainder = s.substring(1);
				return children.get(first).search(remainder); //recursive call while searching the Trie
			}
		}
		return null;
	}
	
	public boolean terminates(){
		return children.containsKey('\0');
	}
	
	public TrieNode getChild(char c){
		return children.get(c);
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
