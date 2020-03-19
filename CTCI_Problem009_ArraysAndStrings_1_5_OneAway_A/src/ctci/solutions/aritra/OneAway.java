package ctci.solutions.aritra;
/**
 * 
 * @author ARITCHAT
 * Problem: There are 3 types of edits that can be performed on strings: insert a character, remove a character, replace a character. Given two strings, 
 * write a function to check if they are one edit(or zero edits) away
 * 
 * Solution:
 * This is one of those problems, where its helpful to think about the meaning of each of these operations. What does it mean for two strings 
 * to be one insertion,replacement or removal away from each other?
 * 
 * Replacement -> Consider two strings, such as bale and pale, that are one replacement away. Yes, that does mean that you could replace a 
 * character in bale to make pale. But more precisely, it means that they are different only in one place
 * 
 * Insertion -> The strings apple and aple are one insertion away. This means that if you compared the strings, they would be identical - 
 * except for a shift at some point in the strings
 * 
 * Removal -> The strings apple and aple are also one removal away, since removal is just the inverse of insertion. 
 * 
 * We can go ahead and implement this algorithm now. We'll merge the insertion and removal check into one step, and check the replacement step 
 * separately
 * 
 * Observe that you don't need to check the strings for insertion, removal and replacement edits. The lengths of the strings will indicate which of 
 * these you need to check,
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
		if(first.length() == second.length()){
			return oneEditReplace(first,second);
		}else if(first.length() + 1 == second.length()){
			return oneEditInsert(first,second);
		}else if(first.length() == second.length() + 1){
			return oneEditInsert(second,first);
		}
		return false;
	}
	
	static boolean oneEditReplace(String s1, String s2){
		boolean foundDifference = false;
		for(int i = 0; i < s1.length(); i++){
			if(s1.charAt(i) != s2.charAt(i)){
				if(foundDifference){
					return false;
				}
				foundDifference = true;
			}
		}
		return true;
	}
	
	/*
	 * Check if you can insert a character into s1 to make s2
	 */
	static boolean oneEditInsert(String s1, String s2){
		int index1 = 0;
		int index2 = 0;
		while(index2 < s2.length() && index1 < s1.length()){
			if(s1.charAt(index1) != s2.charAt(index2)){
				if(index1 != index2){
					return false;
				}
				index2++;
			}else{
				index1++;
				index2++;
			}
		}
		return true;
	}
}
