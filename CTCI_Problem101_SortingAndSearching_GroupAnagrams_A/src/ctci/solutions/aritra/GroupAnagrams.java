package ctci.solutions.aritra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

/*
 * Question: Write a method to sort an array of strings so that all the anagrams are next to each other.
 * 
 * Solution: The problem asks us to group the strings in an array such that the anagrams appear next to each other. Note that no specific ordering of the
 * words is required, other than this.
 * 
 * We need a quick and easy way of determining if two strings are anagrams of each other. What defines if two words are anagrams of each other?
 * Well, anagrams are words that have the same characters but in different orders. It follows then that if we can put the characters in the same order, we can
 * easily check if the new words are identical. 
 * 
 * One way to do this is to jsut apply any standard sorting algorithm, like merge sort or quick sort, and modify the comparator. This comparator will be used
 * to indicate that two strings which are anagrams of each other are equivalent.
 * 
 * What's the easiest way of checking if two words are anagrams? We could count the occurrences of the distinct characters in each string and return true if they match.
 * Or, we could just sort the string. After all, two words which are anagrams will look the same once they're sorted.
 * 
 * This may be the best we can do for a general sorting algorithm, but we don't actually need to fully sort the array. We only need to group the strings in the array by anagram.
 * We can do this by using a hash table which maps from a sorted version of a word to a list of its anagrams. So, for example, acre will map to the list
 * {acre, race, care}. Once we've grouped all the words into these lists by anagram, we can then put them back into the array.
 * 
 * This algorithm is a modification of bucket sort.
 */

public class GroupAnagrams {
	public static void main(String[] args) {
		
	}
	
	void sort(String[] array){
		HashMapList<String, String> mapList = new HashMapList<String, String>();
		
		//Group words by anagram
		for(String s : array){
			String key = sortChars(s);
			mapList.put(key, s);
		}
		
		//Convert hash table to array
		int index = 0;
		
		for(String key : mapList.keySet()){
			ArrayList<String> list = mapList.get(key);
			for(String t : list){
				array[index] = t;
				index++;
			}
		}
	}
	
	String sortChars(String s){
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
}

class AnagramComparator implements Comparator<String>{
	public String sortChars(String s){
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	
	public int compare(String s1, String s2){
		return sortChars(s1).compareTo(sortChars(s2));
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