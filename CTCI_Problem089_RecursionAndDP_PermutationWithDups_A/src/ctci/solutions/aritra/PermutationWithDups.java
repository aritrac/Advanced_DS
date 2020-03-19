package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/*
 * Question: Write a method to compute all permutations of a string whose characters are not necessarily unique. The list of permutations should not have duplicates
 * 
 * Answer:
 * 
 * Ideally, we would like to create the unique permutations, rather than creating every permutation and then ruling out the duplicates. We can start with computing the count
 * of each letter (easy enough to get this - just use a hash table). For a string such as aabbbbc, this would be
 * a->2 | b -> 4 | c -> 1
 * 
 * Let's imagine generating a permutation of this string (now represented as a hash table). The first choice we make is whether to
 * use an a,b, or c as the first character. After that, we have a subproblem to solve: find all permutations of the remaining characters, and append those to the already picked "prefix"
 * 
 * P(a -> 2 | b -> 4 | c -> 1) = {a + P(a -> 1 | b -> 4 | c -> 1)} +
 * 								 {b + P(a -> 2 | b -> 3 | c -> 1)} +
 * 								 {c + P(a -> 2 | b -> 4 | c -> 0)}
 * 
 * 		P(a -> 1 | b -> 4 | c -> 1) = {a + P(a -> 0 | b -> 4 | c -> 1)} +
 * 									  {b + P(a -> 1 | b -> 3 | c -> 1)} +
 * 									  {c + P(a -> 1 | b -> 4 | c -> 0)}
 * 	 	P(a -> 2 | b -> 3 | c -> 1) = {a + P(a -> 1 | b -> 3 | c -> 1)} +
 * 									  {b + P(a -> 2 | b -> 2 | c -> 1)} +
 * 									  {c + P(a -> 2 | b -> 3 | c -> 0)}
 * 		P(a -> 2 | b -> 4 | c -> 0) = {a + P(a -> 1 | b -> 4 | c -> 0)} +
 * 									  {b + P(a -> 2 | b -> 3 | c -> 0)}
 * 
 * Eventually we will get down to no more characters remaining. The code below implements the following algorithm
 */

public class PermutationWithDups {
	public static void main(String[] args) {
		PermutationWithDups pwd = new PermutationWithDups();

		List<String> allPerms = pwd.printPerms("arai");

		Iterator<String> permIter = allPerms.iterator();
		while (permIter.hasNext()) {
			System.out.println(permIter.next());
		}
	}

	ArrayList<String> printPerms(String s) {
		ArrayList<String> result = new ArrayList<String>();
		HashMap<Character, Integer> map = buildFreqTable(s);
		printPerms(map, "", s.length(), result);
		return result;
	}

	HashMap<Character, Integer> buildFreqTable(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
		}
		return map;
	}

	void printPerms(HashMap<Character, Integer> map, String prefix,
			int remaining, ArrayList<String> result) {
		// Base Case. Permutation has been completed.
		if (remaining == 0) {
			result.add(prefix);
			return;
		}

		// Try remaining letters for next char, and generate remaining
		// permutations
		for (Character c : map.keySet()) {
			int count = map.get(c);
			if (count > 0) {
				map.put(c, count - 1);
				printPerms(map, prefix + c, remaining - 1, result);
				map.put(c, count);
			}
		}
	}
}
