package ctci.solutions.aritra;
/**
 * 
 * @author ARITCHAT
 * Problem: There are 3 types of edits that can be performed on strings: insert a character, remove a character, replace a character. Given two 
 * strings, write a function to check if they are one edit(or zero edits) away
 * 
 * Solution:
 * This is one of those problems, where its helpful to think about the meaning of each of these operations. What does it mean for two strings to be 
 * one insertion,replacement or removal away from each other?
 * 
 * Replacement -> Consider two strings, such as bale and pale, that are one replacement away. Yes, that does mean that you could replace a character 
 * in bale to make pale. But more precisely, it means that they are different only in one place
 * 
 * Insertion -> The strings apple and aple are one insertion away. This means that if you compared the strings, they would be identical - except for 
 * a shift at some point in the strings
 * 
 * Removal -> The strings apple and aple are also one removal away, since removal is just the inverse of insertion. 
 * 
 * We might notice that the code for oneEditReplace is very similar to that for oneEditInsert. We can merge them into one method. To do this, observe 
 * that both methods follow similar logic:compare each character and ensure that the strings are only different by one. The methods vary in how we 
 * handle that difference. The method oneEditReplace does nothing other than flag the difference, whereas oneEditInsert increments the pointer 
 * to the longer string. We can handle both of these in the same method.
 *
 */
public class OneAway {
	public static void main(String[] args) {
		String s1 = "apple";
		String s2 = "aple";
		
		String s3 = "bale";
		String s4 = "pale";
		
		String s5 = "fail";
		String s6 = "tall";
		
		System.out.println("Is s1 and s2 oneEditAway()? " + oneEditAway(s1,s2));
		System.out.println("Is s3 and s4 oneEditAway()? " + oneEditAway(s3,s4));
		System.out.println("Is s5 and s6 oneEditAway()? " + oneEditAway(s5,s6));
	}
	
	static boolean oneEditAway(String first, String second){
		//Length checks
		if(Math.abs(first.length() - second.length()) > 1){
			return false;
		}
		
		//Get shorter and longer string
		String s1 = first.length() < second.length()?first:second;
		String s2 = first.length() < second.length()?second:first;
		
		int index1 = 0;
		int index2 = 0;
		boolean foundDifference = false;
		while(index2 < s2.length() && index1 < s1.length()){
			if(s1.charAt(index1) != s2.charAt(index2)){
				//Ensure that this is the first difference found
				if(foundDifference){
					return false;
				}
				foundDifference = true;
				
				if(s1.length() == s2.length()){
					//On replace move shorter pointer
					index1++;
				}
			}else{
				index1++;	//If matching, move shorter pointer
			}
			index2++;//Always move pointer for longer string
		}
		return true;
	}
}
