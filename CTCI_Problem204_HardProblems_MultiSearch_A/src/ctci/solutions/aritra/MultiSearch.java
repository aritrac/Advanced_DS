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
 * Solution: The naive solution is reasonably straightforward. Just search through the bigger string for each instance of the smaller string.
 */

public class MultiSearch {
	HashMapList<String, Integer> searchAll(String big, String[] smalls){
		HashMapList<String, Integer> lookup = new HashMapList<String,Integer>();
		for(String small : smalls){
			ArrayList<Integer> locations = search(big, small);
			lookup.put(small, locations);
		}
		return lookup;
	}
	
	//Find all locations of the smaller string within the bigger string
	ArrayList<Integer> search(String big, String small){
		ArrayList<Integer> locations = new ArrayList<Integer>();
		for(int i = 0; i < big.length() - small.length() + 1; i++){
			if(isSubstringAtLocation(big,small,i)){
				locations.add(i);
			}
		}
		return locations;
	}
	
	//Check if small appears at index offset within big
	boolean isSubstringAtLocation(String big, String small, int offset){
		for(int i = 0; i < small.length(); i++){
			if(big.charAt(offset + i) != small.charAt(i)){
				return false;
			}
		}
		return true;
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
