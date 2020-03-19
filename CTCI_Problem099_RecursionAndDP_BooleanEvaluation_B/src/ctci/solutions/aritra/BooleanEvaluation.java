package ctci.solutions.aritra;

import java.util.HashMap;

/*
 * Question: Given a boolean expression consisting of the symbols 0 (false), 1 (true) & (AND), | (OR) and ^ (XOR), and a desired boolean result value result,
 * implement a function to count the number of ways of parenthesizing the expression such that it evaluates to result. The expression should be fully
 * parenthesized (e.g. (0) ^ (1)) but not extraneously (e.g. (((0))^(1)))
 * 
 * Example
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&0&1^1|0") -> 10
 * 
 * Solution: Note in the previous approach, the tradeoff of computing the false results from the true ones and of computing the {leftTrue, rightTrue, leftFalse and rightFalse} values upfront
 * is a small amount of extra work in some cases. For example, if we're looking for the ways that an AND (&) can result in true, we never would have needed the leftFalse and rightFalse
 * results. Likewise if we're looking for the ways that an OR (|) can result in false, we never would have needed the leftTrue and rightTrue results.
 * 
 * Our previous code was blind to what we do and don't actually need to do and instead just computes all of the values.
 * 
 * Another optimization we can add to our previous code is as follows
 * Consider the expression 0^0&0^1|1 and these recursion paths
 * 1)Add parens around char 1. (0)^(0&0^1|1)
 * 		>> Add parens around char 3. (0)^((0)^(0^1|1))
 * 2)Add parens around char 3. (0^0)&(0^1|1)
 * 		>> Add parens around char 1. ((0)^(0))&(0^1|1)
 * 
 * Although these two expressions are different, they have a similar component: (0^1|1). We should reuse our efforts on this.
 * We can do this by using memoization, or a hash table. We just need to store the result of countEval(expression, result) for each expression and result.
 * If we see an expression that we've calculated before, we just return it from the cache
 */

public class BooleanEvaluation {
	public static void main(String[] args) {
		BooleanEvaluation be = new BooleanEvaluation();
		HashMap<String,Integer> hm = new HashMap<String, Integer>();
		System.out.println("0&0&0&0&1^1|0 can be evaluated to true in " + be.countEval("0&0&0&0&1^1|0", true, hm) + " ways");
		System.out.println("0&0&0&0&1^1|0 can be evaluated to false in " + be.countEval("0&0&0&0&1^1|0", false, hm) + " ways");
	}
	
	int countEval(String s, boolean result, HashMap<String, Integer> memo){
		if(s.length() == 0)
			return 0;
		if(s.length() == 1)
			return stringToBool(s) == result? 1 : 0;
		if(memo.containsKey(result + s))
			return memo.get(result + s);
		
		int ways = 0;
		
		for(int i = 1; i < s.length(); i += 2){
			char c = s.charAt(i);
			String left = s.substring(0,i);
			String right = s.substring(i+1, s.length());
			int leftTrue = countEval(left,true,memo);
			int leftFalse = countEval(left,false,memo);
			int rightTrue = countEval(right,true,memo);
			int rightFalse = countEval(right,false,memo);
			int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);
			
			int totalTrue = 0;
			if(c == '^'){
				totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
			}else if(c == '&'){
				totalTrue = leftTrue * rightTrue;
			}else if(c == '|'){
				totalTrue = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
			}
			
			int subWays = result? totalTrue : total - totalTrue;
			ways += subWays;
		}
		memo.put(result + s, ways);
		return ways;
	}
	
	boolean stringToBool(String c){
		return c.equals("1")? true : false;
	}
}
