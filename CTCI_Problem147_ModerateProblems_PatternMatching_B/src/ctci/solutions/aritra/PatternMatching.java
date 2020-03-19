package ctci.solutions.aritra;

/*
 * Question: You are given two strings, pattern and value. The pattern string consists of just the letters a and b, describing a pattern within a string. For example, the catcatgocatgo 
 * matches the pattern aabab (where cat is a and go is b). It also matches patterns like a, ab and b. Write a method to determine if value matches pattern.
 * 
 * Solution: Let's think through our current algorithm. Searching through all values for the main string is fairly fast (it takes O(n) time). It's the alternate string
 * that is so slow: O(N^2) time. We should study how to optimize that.
 * 
 * Suppose we have a pattern like aabab and we're comparing it to the string catcatgocatgo. Once we've picked "cat" as the value for a to try, then the a strings are going to 
 * take up nine characters(three a strings with length three each). Therefore, the b strings must take up the remaining four characters, with each having length two. Moreover,
 * we know exactly, where they must occur too. If a is cat, and the pattern is aabab, then b must be go.
 * 
 * In other words, once we've picked a, we've picked b too. There's no need to iterate. Gathering some basic stats on pattern (number of as, number of bs, first occurrence of each)
 * and iterating through values for a (or whichever the main string is) will be sufficient.
 */

public class PatternMatching {
	
	public static void main(String[] args) {
		String pattern = "aabab";
		String value1 = "catcatgocatgo";
		
		PatternMatching pm = new PatternMatching();
		
		System.out.println(value1 + " matches " + pattern + "?" + pm.doesMatch(pattern, value1));
	}
	
	int countOf(String pattern, char c){
		int count = 0;
		for(int i = 0; i < pattern.length(); i++){
			if(pattern.charAt(i) == c){
				count++;
			}
		}
		return count;
	}
	
	boolean doesMatch(String pattern, String value){
		if(pattern.length() == 0)
			return value.length() == 0;
		char mainChar = pattern.charAt(0);
		char altChar = mainChar == 'a' ? 'b' : 'a';
		int size = value.length();
		
		int countOfMain = countOf(pattern, mainChar);
		int countOfAlt = countOf(pattern, altChar);
		int firstAlt = pattern.indexOf(altChar); //Finding the first index of the second word
		int maxMainSize = size / countOfMain; //Finding the length of the first word approx
		
		for(int mainSize = 0; mainSize <= maxMainSize; mainSize++){
			int remainingLength = size - mainSize * countOfMain;
			String first = value.substring(0, mainSize);
			if(countOfAlt == 0 || remainingLength % countOfAlt == 0){
				int altIndex = firstAlt * mainSize;
				int altSize = countOfAlt == 0 ? 0 : remainingLength / countOfAlt;
				String second = countOfAlt == 0 ? "" : value.substring(altIndex, altSize + altIndex);
				
				String cand = buildFromPattern(pattern, first, second);
				if(cand.equals(value)){
					return true;
				}
			}
		}
		return false;
	}
	
	String buildFromPattern(String pattern, String main, String alt){
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
