package ctci.solutions.aritra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/*
 * Question: Given a list of words, write a program to find the longest word made of other words in the list.
 * 
 * Solution: The previous algorithm works great for when we just want to know composites of two words. But what if a word could be formed by any number of other words.
 * In this case, we could apply a very similar approach, with one modification: rather than simply looking up if the right side is in the array, we could recursively
 * see if we can build the right side from the other elements in the array. the code below implements the following algorithm.
 */

public class LongestWord {
	String printLongest(String arr[]) {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		for (String str : arr) {
			map.put(str, true);
		}
		Arrays.sort(arr, new LengthComparator()); // Sort by length
		for(String s : arr){
			if(canBuildWord(s,true, map)){
				System.out.println(s);
				return s;
			}
		}
		return "";
	}
	
	boolean canBuildWord(String str, boolean isOriginalWord, HashMap<String, Boolean> map){
		if(map.containsKey(str) && !isOriginalWord){
			return map.get(str);
		}
		for(int i = 1; i < str.length(); i++){
			String left = str.substring(0,i);
			String right = str.substring(i);
			if(map.containsKey(left) &&  map.get(left) == true && canBuildWord(right, false, map)){
				return true;
			}
		}
		map.put(str, false); //storing computations here so in future no need to again check it
		return false;
	}
}

class LengthComparator implements Comparator<String> {
	public int compare(String o1, String o2) {
		if (o1.length() < o2.length())
			return 1; 
		if (o1.length() > o2.length())
			return -1; 
		return 0;
	}
}