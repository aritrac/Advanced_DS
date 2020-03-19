package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Question: Design an algorithm to find the kth number such that only prime factors are 3,5 and 7.Note that 3,5 and 7 do not have to be factors, but it should not
 * have any other prime factors. For example, the first seven multiples would be (in order) 1,3,5,7,9,15,21
 * 
 * Solution: Let's first understand what this problem is askin for. It's asking for the kth smallest number that is in the form 3^a * 5^b * 7^c. Lets start with a brute force way of finding this.
 * We know that biggest kth number could be 3^k * 5^k * 7^k. So, the bruteforce way of doing this is to compute 3^a * 5^b *7^c for all values of a,b and c between 0 and k. We
 * can throw them all into a list, sort the list and then pick the kth smallest value as shown below
 */

public class KthMultiple {
	int getKthMagicNumber(int k){
		ArrayList<Integer> possibilities = allPossibleKFactors(k);
		Collections.sort(possibilities);
		return possibilities.get(k);
	}
	
	ArrayList<Integer> allPossibleKFactors(int k){
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int a = 0; a <= k; a++){ //loop 3
			int powA = (int)Math.pow(3, a);
			for(int b = 0; b <= k; b++){//loop 5
				int powB = (int) Math.pow(5, b);
				for(int c = 0; c <= k; c++){ //loop 7
					int powC = (int)Math.pow(7, c);
					int value = powA * powB * powC;
					
					//check for overflow
					if(value < 0 || powA == Integer.MAX_VALUE || powB == Integer.MAX_VALUE || powC == Integer.MAX_VALUE){
						value = Integer.MAX_VALUE;
					}
					values.add(value);
				}
			}
		}
		return values;
	}
}
