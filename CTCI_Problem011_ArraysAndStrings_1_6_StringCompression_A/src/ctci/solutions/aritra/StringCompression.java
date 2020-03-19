package ctci.solutions.aritra;

/*
 * Implement a method to perform basic string compression using the count of repeated characters. For example, the string aabcccccaaa would 
 * become a2b1c5a3. If the "compressed" string would not become similar than the original string, your method should return the original string.
 * You can assume the string has only uppercase and lowercase letters (a-z).
 * 
 * For the first method, we iterate through the string, copying characters to a new string and counting the repeats. At each iteration, 
 * check if the current character is the same as the next character. If not, add its compressed version to the result.
 * 
 * Why is this not a good approach?
 * The runtime is O(p + k^2), where p is the size of the original string and k is the number of character sequences. For example, if the string
 * is aabccdeeaa, then there are six character sequences. It's slow because string concatenation operates in O(n^2) time.
 */

public class StringCompression {
	public static void main(String[] args) {
		String str = "aabcccccaaa";
		System.out.println(compressBad(str));
	}
	
	static String compressBad(String str){
		String compressedString = "";
		int countConsecutive = 0;
		for(int i = 0; i < str.length(); i++){
			countConsecutive++;
			/*
			 * If next character is different than current, append this char to the result.
			 */
			if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)){
				compressedString += "" + str.charAt(i) + countConsecutive;
				countConsecutive = 0;
			}
		}
		return compressedString.length() < str.length()? compressedString : str;
	}
}
