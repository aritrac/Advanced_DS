package ctci.solutions.aritra;

/*
 * Question: Given a boolean expression consisting of the symbols 0 (false), 1 (true) & (AND), | (OR) and ^ (XOR), and a desired boolean result value result,
 * implement a function to count the number of ways of parenthesizing the expression such that it evaluates to result. The expression should be fully
 * parenthesized (e.g. (0) ^ (1)) but not extraneously (e.g. (((0))^(1)))
 * 
 * Example
 * countEval("1^0|0|1", false) -> 2
 * countEval("0&0&0&0&1^1|0") -> 10
 * 
 * Solution: Consider an expression like 0^0&0^1|1 and the target result true. How can we break down countEval(0^0&0^1|1,true) into smaller problems?
 * We could essentially iterate through each possible place to put a parenthesis.
 * 
 * countEval(0^0&0^1|1, true) =
 * 			countEval(0^0&0^1|1 where paren around char 1, true)
 * 		 +	countEval(0^0&0^1|1 where paren around char 3, true)
 * 		 +  countEval(0^0&0^1|1 where paren around char 5, true)
 * 		 +  countEval(0^0&0^1|1 where paren around char 7, true)
 * 
 * Now what? Let's look at just one of those expressions - the paren around char 3. This gives us (0^0)&(0^1|1)
 * 
 * In order to make that expression true, both the left and right side must be true. So:
 * 
 * left = "0^0"
 * right = "0^1|1"
 * countEval(left & right, true) = countEval(left, true) * countEval(right, true)
 * The reason we multiply the results of the left and right sides is that each result from the two sides can be paired up with each other to form a unique combination
 * 
 * Each of those terms can now be decomposed into smaller problems in a similar process.
 * 
 * What happens when we have an "|"(OR)? Or an "^" (XOR)?
 * 
 * If it's an OR, then either the left or the right side must be true - or both
 * 
 * countEval(left | right, true) = countEval(left,true) * countEval(right,false)
 * 								 + countEval(left,false) * countEval(right,true)
 * 								 + countEval(left,true) * countEval(right,true)
 * 
 * If it's an XOR, then either left or the right side can be true, but not both
 * 
 * countEval(left ^ right, true) = countEval(left, true) * countEval(right, false)
 * 								 + countEval(left, false) * countEval(right,true)
 * 
 * What if we were trying to make the result false instead? We can switch up the logic from above.
 * countEval(left & right, false) = countEval(left, true) * countEval(right, false)
 * 								  + countEval(left, false) * countEval(right, true)
 * 								  + countEval(left, false) * countEval(right, false)
 * 
 * countEval(left | right, false) = countEval(left, false) * countEval(right, false)
 * countEval(left ^ right, false) = countEval(left, false) * countEval(right,false)
 * 								  + countEval(left, true) * countEval(right, true)
 * 
 * Alternatively we can just use the same logic from above and subtract it out from the total number of ways of evaluating the expression.
 * 
 * totalEval(left) = countEval(left, true) + countEval(left, false)
 * totalEval(right) = countEval(right, true) + countEval(right, false)
 * totalEval(expression) = totalEval(left) * totalEval(right)
 * countEval(expression, false) = totalEval(expression) - countEval(expression, true)
 */

public class BooleanEvaluation {
	public static void main(String[] args) {
		BooleanEvaluation be = new BooleanEvaluation();
		System.out.println("0&0&0&0&1^1|0 can be evaluated to true in " + be.countEval("0&0&0&0&1^1|0", true) + " ways");
		System.out.println("0&0&0&0&1^1|0 can be evaluated to false in " + be.countEval("0&0&0&0&1^1|0", false) + " ways");
	}
	
	int countEval(String s, boolean result){
		if(s.length() == 0)
			return 0;
		if(s.length() == 1)
			return stringToBool(s) == result ? 1 : 0;
		int ways = 0;
		
		for(int i = 1; i < s.length(); i += 2){
			char c = s.charAt(i);
			String left = s.substring(0,i);
			String right = s.substring(i + 1, s.length());
			
			//Evaluate each side for each result
			int leftTrue = countEval(left, true);
			int leftFalse = countEval(left, false);
			int rightTrue = countEval(right, true);
			int rightFalse = countEval(right, false);
			int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);
			
			int totalTrue = 0;
			if(c == '^'){//required: one true and one false
				totalTrue = leftTrue * rightFalse | leftFalse * rightTrue;
			}else if(c == '&'){//required: both true
				totalTrue = leftTrue * rightTrue;
			}else if(c == '|'){//required: anything but both false
				totalTrue = leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
			}
			
			int subWays = result ? totalTrue : total - totalTrue;
			ways += subWays;
		}
		return ways;
	}
	
	boolean stringToBool(String c){
		return c.equals("1")? true : false;
	}
}
