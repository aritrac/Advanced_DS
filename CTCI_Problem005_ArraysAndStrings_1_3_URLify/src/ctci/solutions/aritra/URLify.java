package ctci.solutions.aritra;
/**
 * 
 * @author ARITCHAT
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string has 
 * sufficient space at the end to hold the additional characters, and that you are given the 'true' length of the string
 * 
 * The algorithm employs a two-scan approach. In the first scan, we count the number of spaces. By tripling this number,
 * we can compute how many extra characters we will have in the final string. In the second pass, which is done in reverse order
 * we actually edit the string. When we see a space, we replace it with '%20'. If there is no space then we copy the original character.
 *
 */
public class URLify {
	public static void main(String[] args) {
		String str = "Mr John Smith       ";
		int trueLength = 13;
		char[] chArray = str.toCharArray();
		System.out.println("Before replacement");
		System.out.println(str);
		System.out.println("After replacement");
		replaceSpaces(chArray,trueLength);
		System.out.println(chArray);
	}
	
	public static void replaceSpaces(char[] str, int trueLength){
		int spaceCount = 0;
		int index;
		int i = 0;
		for(i = 0; i < trueLength; i++){
			if(str[i] == ' '){
				spaceCount++;
			}
		}
		
		index = trueLength + spaceCount * 2;
		
		if(trueLength < str.length)
			str[trueLength] = '\0'; //Ending array in case of excess spaces
		
		for(i = trueLength - 1; i >= 0; i--){
			if(str[i] == ' '){
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index = index - 3;
			}else{
				str[index - 1] = str[i];
				index--;
			}
		}
	}
}
