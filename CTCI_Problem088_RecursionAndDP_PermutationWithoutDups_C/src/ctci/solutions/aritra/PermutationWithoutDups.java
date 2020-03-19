package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Question: Write a method to compute all permutations of a string of unique characters
 * 
 * Solution: Now we will build from permutations of all n-1 character substrings
 * 
 * Base Case: single character strings
 * The only permutation of a1 is the string a1. So
 * P(a1) = a1
 * 
 * Case: two character strings
 * P(a1a2) = a1a2 and a2a1
 * P(a2a3) = a2a3 and a3a2
 * P(a1a3) = a1a3 and a3a1
 * 
 * Case: three character strings
 * Here is where the cases get more interesting. How can we generate all permutations of three character strings such as a1a2a3
 * given the permutations of two character strings?
 * 
 * We just need to try each character as the first character and then append the permutations
 * P(a1a2a3) = {a1 + P(a2a3)} + {a2 + P(a1a3)} + {a3 + P(a1a2)}
 * {a1 + P(a2a3)} -> a1a2a3 a1a3a2
 * {a2 + P(a1a3)} -> a2a1a3 a2a3a1
 * {a3 + P(a1a2)} -> a3a1a2 a3a2a1
 * 
 * Now that we can generate all permutations of three character strings, we can use this to generate permutations of four character strings
 * 
 * P(a1a2a3a4) = {a1 + P(a2a3a4)} + {a2 + P(a1a3a4)} + {a2 + P(a1a2a4)} + {a2 + P(a1a2a3)}
 * 
 * We can now implement this as follows. It is recommended that you write down the dry run to understand this algo as it is a bit harder to do mentally.
 * 
 * Alternatively instead of passing the permutations back up the stack we can push the prefix down the stack. When we get to the bottom(base case),
 * prefix holds a full permutation. This is shown below
 */

public class PermutationWithoutDups {
	public static void main(String[] args) {
		PermutationWithoutDups pwd = new PermutationWithoutDups();
		
		List<String> allPerms = pwd.getPerms("arit");
		
		Iterator<String> permIter = allPerms.iterator();
		while(permIter.hasNext()){
			System.out.println(permIter.next());
		}
	}
	
	ArrayList<String> getPerms(String str){
		ArrayList<String> result = new ArrayList<String>();
		getPerms("", str, result);
		return result;
	}
	
	void getPerms(String prefix, String remainder, ArrayList<String> result){
		if(remainder.length() == 0)
			result.add(prefix);
		
		int len = remainder.length();
		for(int i = 0; i < len; i++){
			String before = remainder.substring(0,i);
			String after = remainder.substring(i + 1, len);
			char c = remainder.charAt(i);
			getPerms(prefix + c, before + after, result);
		}
	}
}
