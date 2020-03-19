package ctci.solutions.aritra;

/*
 * Question: You are given two strings, pattern and value. The pattern string consists of just the letters a and b, describing a pattern within a string. For example, the catcatgocatgo 
 * matches the pattern aabab (where cat is a and go is b). It also matches patterns like a, ab and b. Write a method to determine if value matches pattern.
 * 
 * Solution: 
 * the pseudocode for this problem in brute force approach is:
 * for each possible substring a
 * 	for each possible substring b
 * 	 candidate = buildFromPattern(pattern,a,b);
 *   if candidate equals value
 *   	return true
 *   
 * Since the code is a bit involved, please run the dry run to understand the flow of this code.
 */

public class PatternMatching {
	public static void main(String[] args) {
		String pattern = "aabab";
		String value1 = "catcatgocatgo";
		
		PatternMatching pm = new PatternMatching();
		
		System.out.println(value1 + " matches " + pattern + "?" + pm.doesMatch(pattern, value1));
	}
	
	boolean doesMatch(String pattern, String value){ //Starts here
		if(pattern.length() == 0)
			return value.length() == 0;
		
		int size = value.length();
		
		for(int mainSize = 0; mainSize < size; mainSize++){
			String main = value.substring(0,mainSize); //This represents the first word
			System.out.println("R " + main);
			for(int altStart = mainSize; altStart <= size; altStart++){//This tries to find the second word by considering the remaining string except whatever is there in main currently.
				for(int altEnd = altStart; altEnd <= size; altEnd++){
					String alt = value.substring(altStart,altEnd);
					System.out.println(altStart + " " + altEnd);
					String cand = buildFromPattern(pattern, main, alt); //We build the entire string from the two words and the pattern provided
					//System.out.println(cand);
					if(cand.equals(value)){ //If the received entire string from the pattern and two words matches our actual string, then we return true.
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//Based on the first and second word received from the 3 loops above, we are generating the entire string based on the pattern and then returning the same here
	String buildFromPattern(String pattern, String main, String alt){ //Main is the first word, alt is the second word
		StringBuffer sb = new StringBuffer();
		char first = pattern.charAt(0);
		for(char c : pattern.toCharArray()){
			if(c == first){
				sb.append(main);
			}else{
				sb.append(alt);
			}
		}
		return sb.toString();
	}
}
