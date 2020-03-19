package ctci.solutions.aritra;

import java.util.HashMap;

/*
 * Question: Given a list of words, write a program to find the longest word made of other words in the list.
 * 
 * Solution: This problem seems complex, so let's simplify it. What if we wanted to know the longest word made of two other words in the list?
 * We could solve this by iterating through the list, from the longest word to the shortest word. For each word, we would split it into all possible pairs
 * and check if both the left and right side are contained in the list. The code for this is below
 */

public class LongestWord {
	String getLongestWord(String[] list){
		String[] array = sortByLength(list);//sorting the list by length in ascending order
		//Create map for easy lookup
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		
		for(String str : array){
			map.put(str, true);
		}
		
		for(String s : array){
			//Divide into every possible pair
			for(int i = 1; i < s.length(); i++){
				String left = s.substring(0,i);
				String right = s.substring(i);
				//Check if both sides are in the array
				if(map.containsKey(left) && map.containsKey(right)){
					return s;
				}
			}
		}
		return null;
	}
	
	String[] sortByLength(String[] list){
		return null;
	}
}
