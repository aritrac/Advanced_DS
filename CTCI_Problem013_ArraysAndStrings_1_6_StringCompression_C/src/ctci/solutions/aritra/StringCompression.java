package ctci.solutions.aritra;

/*
 * Implement a method to perform basic string compression using the count of repeated characters. For example, the string aabcccccaaa would become 
 * a2b1c5a3. If the "compressed" string would not become similar than the original string, your method should return the original string.You can 
 * assume the string has only uppercase and lowercase letters (a-z).
 * 
 * Both of the previous solutions created the compressed string first and then return the shorter of the input string and the compressed
 * string. Instead we can check in advance. This will be more optimal in cases where we don't have a large number of repeating characters. It
 * will avoid us having to create a string that we never use. The downside of this is that it causes a second loop through the characters and
 * also adds nearly duplicated code.
 * 
 * One other benefit of this approach is that we can initialize StringBuilder to its necessary capacity up-front. Without this StringBuilder
 * will (behind the scenes) need to double its capacity everytime it hits capacity.The capacity could be double of what we ultimately need.
 *
 */

public class StringCompression {
	public static void main(String[] args) {
		String str = "aabcccccaaa";
		System.out.println(compress(str));
	}
	
	static String compress(String str){
		//Check final length and return input string if it would be longer
		int finalLength = countCompression(str);
		if(finalLength >= str.length())
			return str;
		
		StringBuilder compressed = new StringBuilder(finalLength); //initial capacity
		int countConsecutive = 0;
		for(int i = 0; i < str.length(); i++){
			countConsecutive++;
			//If next character is different than current, append this char to result
			if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)){
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressed.toString();
	}
	
	static int countCompression(String str){
		int compressedLength = 0;
		int countConsecutive = 0 ;
		for(int i = 0; i < str.length(); i ++){
			countConsecutive++;
			//If next character is different than the current, increase the length
			if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)){
				compressedLength += 1 + String.valueOf(countConsecutive).length();
				countConsecutive = 0;
			}
		}
		return compressedLength;
	}
}
