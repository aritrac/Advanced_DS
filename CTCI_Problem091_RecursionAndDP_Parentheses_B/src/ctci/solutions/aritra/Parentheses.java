package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Question: Implement an algorithm to print all valid (i.e. properly opened and closed) combinations of n pairs of parentheses.
 * 
 * Example:
 * Input: 3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 * 
 * Solution: 
 * The previous solution works, but is not very efficient. We waste a lot of time coming up with the duplicate strings
 * We can avoid this duplicate string issue by building the string from scratch. Under this approach, we add left and right parens as long as our
 * expression stays valid.
 * On each recursive call, we have the index for a particular character in the string. We need to select either a left or a right paren.
 * When we can use a left paren, and when can we use a right paren?
 * 
 * 1)Left Paren: As long as we haven't used up all the left parentheses, we can always insert a left paren
 * 2)We can insert a right paren as long as it won't lead to a syntax error. When we will get a syntax error? We will get a syntax error if there
 * are more right parentheses than left.
 * 
 * So we simply keep track of the number of left and right parentheses allowed. If there are left parentheses remaining, we'll insert a left
 * paren and recurse. If there are more right parens remaining than left (i.e. if there are more left parens in use than right parens, then we'll
 * insert a right paren and recurse)
 */

public class Parentheses {

	public static void main(String[] args) {
		Parentheses p = new Parentheses();
		ArrayList<String> parenList = p.generateParens(3);
		
		Iterator<String> iter = parenList.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
	ArrayList<String> generateParens(int count){
		char[] str = new char[count * 2];
		ArrayList<String> list = new ArrayList<String>();
		addParen(list,count,count,str,0);
		return list;
	}
	
	void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count){
		if(leftRem < 0 || rightRem < leftRem)
			return; //invalid state
		
		if(leftRem == 0 && rightRem == 0){ //no more parens left
			String s = String.copyValueOf(str);
			list.add(s);
		}else{
			//Add left paren, if there are any left parens remaining
			if(leftRem > 0){
				str[count] = '(';
				addParen(list, leftRem - 1, rightRem, str, count + 1);
			}
			
			//Add right paren, if expression is valid
			if(rightRem > leftRem){
				str[count] = ')';
				addParen(list, leftRem, rightRem - 1, str, count + 1);
			}
		}
	}
	
}
