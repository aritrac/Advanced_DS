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
 * Solution: Instead of adding the bigger word, we can add each smaller words into a trie. For example, the strings {i, is, pp, ms} would look like the trie below. The
 * asterisk (*) hanging from a node indicates that this node completes a word.
 * 
 * 																						Root
 * 																		I				  P					M
 * 																S				*				P					S	
 * 															*										*					*
 * Now, when we want to find all words in mississippi, we search through this trie starting with each word.
 * >>m: We would first look up in the trie starting with m, the first letter mississippi. As soon as we go to mi, we terminate
 * >>i: Then, we go to i, the second character in mississippi. We see that i is a complete word, so we add that to the list. We also keep going with o over to is. The string
 * is 'is' also a complete word, so we add that to the list. This node has no more children, so we move onto the next character in mississippi.
 * >>s:We now go to s. There is no upper-level node for s, so we go onto the next character.
 * >>s:Another s. Go on to the next character
 * >>i:We see another i. We go to the i node in the trie. We see that i is a complete word, so we add it to the list. We also keep going with i over to is. The string is is also a complete
 * word, so we add that to the list. The node has no more children, so we move onto the next character in mississippi.
 * >>s:We go to s. There is no upper level node for s.
 * >>s:Another s. Go on to the next character.
 * >>i:We go to the i node. We see that i is a complete word, so we add it to the trie. The next character in mississippi is a p. There is no node p, so we break here.
 * >>p:We see a p. There is no node p.
 * >>p:Another p.
 * >>i:We go to the i node. We see that i is a complete word, so we add it to the trie. There are no more characters left in mississippi, so we are done.
 * 
 * Each time we find a complete small word, we add it to a list along with the location in the bigger word where we found the smaller word	
 */

public class MultiSearch {
	HashMapList<String, Integer> searchAll(String big, String[] smalls){
		HashMapList<String, Integer> lookup = new HashMapList<String,Integer>();
		int maxLen = big.length();
		TrieNode root = createTreeFromStrings(smalls, maxLen).getRoot();
		
		for(int i = 0; i < big.length(); i++){
			ArrayList<String> strings = findStringsAtLoc(root, big, i);//start searching the trie at the given index
			insertIntoHashMap(strings, lookup, i);
		}
		
		return lookup;
	}
	
	//Insert each string into trie (provided string is not longer than maxLen)
	Trie createTreeFromStrings(String[] smalls, int maxLen){
		Trie tree = new Trie("");
		for(String s : smalls){
			if(s.length() <= maxLen){
				tree.insertString(s, 0);
			}
		}
		return tree;
	}
	
	//Find strings in trie that start at index "start" within big
	ArrayList<String> findStringsAtLoc(TrieNode root, String big, int start){
		ArrayList<String> strings = new ArrayList<String>();
		int index = start;
		while(index < big.length()){
			root = root.getChild(big.charAt(index));
			if(root == null)
				break;
			if(root.terminates()){//Is complete string, add to list
				strings.add(big.substring(start, index + 1)); //add from starting index passed for search to the index at which it terminates or encounters null
			}
			index++;
		}
		return strings;
	}
	
	public static void insertIntoHashMap(ArrayList<String> strings, HashMapList<String, Integer> map, int index) {
		for (String s : strings) {
			map.put(s, index);
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
	
	public void insertString(String s, int index){
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
			child.insertString(remainder, index + 1);
		}else{
			children.put('\0', null); //Terminating Character
		}
	}
	
	public ArrayList<Integer> search(String s){
		if(s == null || s.length() == 0){
			return indexes;
		}else{
			char first = s.charAt(0);
			if(children.containsKey(first)){
				String remainder = s.substring(1);
				return children.get(first).search(remainder);
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

