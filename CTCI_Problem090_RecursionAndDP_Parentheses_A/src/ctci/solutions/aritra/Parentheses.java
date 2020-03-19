package ctci.solutions.aritra;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * Question: Implement an algorithm to print all valid (i.e. properly opened and closed) combinations of n pairs of parentheses.
 * 
 * Example:
 * Input: 3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 * 
 * Solution: How might we build this from n = 2?
 * (()) ()()
 * We can do this by inserting a pair of parentheses inside every existing pair of parentheses, as well as one at the beginning of the string. Any other places
 * that we could insert parentheses, such as at the end of the string, would reduce to the earlier cases
 * 
 * So, we have the following:
 * 	(()) -> (()()) //inserted pair after 1st left paren
 *       -> ((())) //inserted pair after 2nd left paren
 *       -> ()(()) //inserted pair at the beginning of string
 *       
 *  ()() -> (())() //inserted pair after the 1st left paren
 *       -> ()(()) //inserted pair after the 2nd left paren
 *       -> ()()() //inserted pair after the beginning of string
 *       
 *  But wait - we have some duplicate pair listed. The string ()(()) is listed twice.
 *  If we are going to use this approach, we'll need to check for duplicate values before adding a string to our list
 */

public class Parentheses {
	public static void main(String[] args) {
		Parentheses p = new Parentheses();
		Set<String> parenList = p.generateParens(3);
		
		Iterator<String> iter = parenList.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	Set<String> generateParens(int remaining){
		Set<String> set = new HashSet<String>();
		if(remaining == 0){
			set.add("");
		}else{
			Set<String> prev = generateParens(remaining - 1);
			for(String str : prev){
				for(int i = 0; i < str.length(); i++){
					if(str.charAt(i) == '('){
						String s = insertInside(str,i);
						//Add s to set if it's not already there. Note: HashSet automatically checks for duplicates before adding,
						//so an explicit check is not necessary
						set.add(s);
					}
				}
				set.add("()" + str);
			}
		}
		return set;
	}
	
	String insertInside(String str, int leftIndex){
		String left = str.substring(0, leftIndex + 1);
		String right = str.substring(leftIndex + 1, str.length());
		return left + "()" + right;
	}
}
