package ctci.solutions.aritra;

/*
 * Implement a method to perform basic string compression using the count of repeated characters. For example, the string aabcccccaaa would become 
 * a2b1c5a3. If the "compressed" string would not become similar than the original string, your method should return the original string.You can 
 * assume the string has only uppercase and lowercase letters (a-z).
 * 
 * For the second method, we iterate through the string, copying characters to a new string and counting the repeats. At each iteration, check if the 
 * current character is the same as the next character. If not, add its compressed version to the result.
 * 
 * Why is this a good approach than the previous one?
 * The string concatenation is now done in O(n) as we have used StringBuilder instead of String to write this. Apart from this small change,
 * everything is similar to the previous approach.
 */

public class StringCompression {
	public static void main(String[] args) {
		String str = "aabcccccaaa";
		System.out.println(compress(str));
	}
	
	static String compress(String str){
		StringBuilder compressed = new StringBuilder();
		int countConsecutive = 0;
		for(int i = 0; i < str.length(); i++){
			countConsecutive++;
			/*
			 * If next character is different than current, append this char to result.
			 */
			if(i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)){
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressed.length() < str.length() ? compressed.toString() : str;
	}
}
