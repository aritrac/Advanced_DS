package ctci.solutions.aritra;

/*
 * Question: You are given two strings, pattern and value. The pattern string consists of just the letters a and b, describing a pattern within a string. For example, the catcatgocatgo 
 * matches the pattern aabab (where cat is a and go is b). It also matches patterns like a, ab and b. Write a method to determine if value matches pattern.
 * 
 * Solution: If you don't like the work of building a string only to compare it (and then destroy it), we can eliminate this.
 * Instead, we can iterate through the values for a and b as before. But this time, to check if the string matches the pattern(given those values for a and b),
 * we walk through value, comparing each substring to the first instance of the a and b strings.
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
		int firstAlt = pattern.indexOf(altChar);
		int maxMainSize = size / countOfMain;
		
		for(int mainSize = 0; mainSize <= maxMainSize; mainSize++){
			int remainingLength = size - mainSize * countOfMain;
			if(countOfAlt == 0 || remainingLength % countOfAlt == 0){
				int altIndex = firstAlt * mainSize;
				int altSize = countOfAlt == 0 ? 0 : remainingLength / countOfAlt;
				if(matches(pattern, value, mainSize, altSize, altIndex)){
					return true;
				}
			}
		}
		return false;
	}
	
	//Iterates through pattern and value. At each character within pattern, checks if this is the main string or the alternate string. Then checks if the next set of characters in value match the
	//original set of those characters (either the main or the alternate)
	
	boolean matches(String pattern, String value, int mainSize, int altSize, int firstAlt){
		int stringIndex = mainSize;
		for(int i = 1; i < pattern.length(); i++){
			int size = pattern.charAt(i) == pattern.charAt(0)? mainSize : altSize; //finding out the word length by comparing the first character always
			int offset = pattern.charAt(i) == pattern.charAt(0) ? 0 : firstAlt;    //gives the comparison string like will it be 'cat' or 'go'. If we need to compare with 'go', it is available at firstAlt
																			       //location and we can start comparing each character size times.
			if(!isEqual(value, offset, stringIndex, size)){
				return false;
			}
			stringIndex += size;
		}
		return true;
	}
	
	//Checks if two substrings are equal, starting at given offsets and continuing to size
	boolean isEqual(String s1, int offset1, int offset2, int size){
		for(int i = 0; i < size; i++){
			if(s1.charAt(offset1 + i) != s1.charAt(offset2 + i)){
				return false;
			}
		}
		return true;
	}
}
