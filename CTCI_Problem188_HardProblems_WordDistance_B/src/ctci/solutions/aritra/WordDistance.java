package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/*
 * Question: You have a large text file containing words. Given any two words, find the shortest distance (in terms of number of words) between them in the file.
 * If the operation will be repeated many times for the same file (but different pairs of words), can you optimize your solution?
 * 
 * Solution: If we need to repeat the operation for other pairs of words, we can create a hash table that maps from each word to the locations where it occurs.
 * We'll only need to read through the list of words once. After that point, we can do a very similar algorithm but just iterate through the locations directly.
 * 
 * Consider the following lists of locations.
 * listA: {1,2,9,15,25}
 * listB: {4,10,19}
 * 
 * Picture pointers pA and pB that point to the beginning of each list. Or goal is to make pA and pB point to values as close together as possible.
 * The first potential pair is (1,4)
 * 
 * If we moved pB, then the distance would definitely get larger. If we move pA, though, we might get a better pair. Let's do that
 * The second potential pair is (2,4). This is better than the previous pair, so lets record this as the best pair
 * 
 * We move pA again and get (9,4). This is worse than we had before.
 * 
 * Now since the value at pA is bigger than the one at pB, we move pB. We get (9,10)
 * 
 * Next we get (15,10), then (15,19) , then (25,19)
 * 
 * We can implement the algorithm as shown below
 */

public class WordDistance {
	LocationPair findClosest(String word1, String word2, HashMapList<String,Integer> locations){
		ArrayList<Integer> locations1 = locations.get(word1);
		ArrayList<Integer> locations2 = locations.get(word2);
		return findMinDistancePair(locations1, locations2);
	}
	
	LocationPair findMinDistancePair(ArrayList<Integer> array1, ArrayList<Integer> array2){
		if(array1 == null || array2 == null || array1.size() == 0 || array2.size() == 0){
			return null;
		}
		int index1 = 0;
		int index2 = 0;
		LocationPair best = new LocationPair(array1.get(0), array2.get(0));
		LocationPair current = new LocationPair(array1.get(0), array2.get(0));
		
		while(index1 < array1.size() && index2 < array2.size()){
			current.setLocations(array1.get(index1), array2.get(index2));
			best.updateWithMin(current); //If shorter, update values
			if(current.location1 < current.location2){
				index1++;
			}else{
				index2++;
			}
		}
		return best;
	}
	
	//Precomputation
	HashMapList<String, Integer> getWordLocations(String[] words){
		HashMapList<String,Integer> locations = new HashMapList<String, Integer>();
		for(int i = 0; i < words.length; i++){
			locations.put(words[i], i);
		}
		return locations;
	}
}

class LocationPair{
	public int location1, location2;
	
	public LocationPair(int first, int second){
		setLocations(first, second);
	}
	
	public void setLocations(int first, int second){
		this.location1 = first;
		this.location2 = second;
	}
	
	public void setLocations(LocationPair loc){
		setLocations(loc.location1, loc.location2);
	}
	
	public int distance(){
		return Math.abs(location1 - location2);
	}
	
	public boolean isValid(){
		return location1 >= 0 && location2 >= 0;
	}
	
	public void updateWithMin(LocationPair loc){
		if(!isValid()||loc.distance() < distance()){
			setLocations(loc);
		}
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
