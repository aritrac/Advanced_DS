package ctci.solutions.aritra;

import java.util.ArrayList;

/*
 * Question: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses), compute the result
 * Example:
 * Input: 2 * 3 + 5 / 6 * 3 + 15
 * Output: 23.5
 * 
 * Solution: 
 * Lets take the expression as
 * 2 - 6 - 7 * 8 / 2 + 5
 * We can do this by reading left to right and maintaining two variables
 * 1)The first is processing, which maintains the result of the current cluster of terms(both the operator and the value). In the case of addition and subtraction, the cluster
 * will be just the current term. In the case of multiplication and division, it will be the full sequence (until you get to the next addition ord subtraction)
 * 2)The second is the result variable. If the next term is an addition or subtraction(or there is no next term), then processing is applied to result.
 * 
 * On the above example, we would do the following:
 * 1. Read +2. Apply it to processing. Apply processing to result. Clear processing
 * processing = {+, 2} --> null
 * result = 0		   --> 2
 * 
 * 2.Read -6. Apply it to processing. Apply processing to result. Clear processing
 * processing = {-, 6} --> null
 * result = 2          --> -4
 * 
 * 3.Read -7. Apply it to processing. Observe next sign is a *. Continue
 * processing = {-, 7}
 * result = -4
 * 
 * 4.Read *8. Apply it to processing. Observe next sign is a /. Continue
 * processing = {-, 56}
 * result = -4
 * 
 * 5.Read /2. Apply it to processing. Observe next sign is a +, which terminates this multiplication and division cluster. Apply processing to result. Clear processing.
 * processing = {-, 28} --> null
 * result = -4			--> -32
 * 
 * 6.Read +5. Apply it to processing. Apply processing to result. Clear processing.
 * processing = {+, 5} --> null
 * result = -32        --> -27
 * 
 * The code below implements this algorithm
 */

public class Calculator {
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		String sequence = "2-6-7*8/2+5";
		System.out.println("The value of the expression above = " + calc.compute(sequence));
	}
	
	/* Compute the result of the arithmetic sequence. This works by reading left to right and applying each term to a result. When we see a multiplication or division, we instead
	 * apply this sequence to a temporary variable.
	 */
	double compute(String sequence){
		ArrayList<Term> terms = Term.parseTermSequence(sequence);
		if(terms == null)
			return Integer.MIN_VALUE;
		
		double result = 0;
		Term processing = null;
		for(int i = 0; i < terms.size(); i++){
			Term current = terms.get(i);
			Term next = i + 1 < terms.size() ? terms.get(i + 1) : null;
			
			//Apply the current term to 'processing'
			processing = collapseTerm(processing,current);
			
			//If next term is + or -, then this cluster is done and we should apply 'processing' to 'result'
			if(next == null || next.getOperator() == Operator.ADD || next.getOperator() == Operator.SUBTRACT){
				result = applyOp(result,processing.getOperator(), processing.getNumber());
				processing = null;
			}
		}
		return result;
	}
	
	//Collapse two terms together using the operator in secondary and the numbers from each.
	Term collapseTerm(Term primary, Term secondary){
		if(primary == null)
			return secondary;
		if(secondary == null)
			return primary;
		double value = applyOp(primary.getNumber(),secondary.getOperator(), secondary.getNumber());
		primary.setNumber(value);
		return primary;
	}
	
	double applyOp(double left, Operator op, double right){
		if(op == Operator.ADD)
			return left + right;
		else if(op == Operator.SUBTRACT)
			return left - right;
		else if(op == Operator.MULTIPLY)
			return left * right;
		else if(op == Operator.DIVIDE)
			return left / right;
		else
			return right;
	}
}

enum Operator {
	ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK
}

class Term {	
	private double value;
	private Operator operator = Operator.BLANK;
	
	public Term(double v, Operator op) {
		value = v;
		operator = op;
	}
	
	public double getNumber() {
		return value;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public void setNumber(double v) {
		value = v;
	}
	
	public static ArrayList<Term> parseTermSequence(String sequence) {
		ArrayList<Term> terms = new ArrayList<Term>();
		int offset = 0;
		while (offset < sequence.length()) {
			Operator op = Operator.BLANK;
			if (offset > 0) {
				op = parseOperator(sequence.charAt(offset));
				if (op == Operator.BLANK) {
					return null;
				}
				offset++;
			}
			try {
				int value = parseNextNumber(sequence, offset);
				offset += Integer.toString(value).length();
				Term term = new Term(value, op);
				terms.add(term);
			} catch (NumberFormatException ex) {
				return null;
			}	
		}
		return terms;
	}
	
	public static Operator parseOperator(char op) {
		switch(op) {
		case '+': return Operator.ADD;
		case '-': return Operator.SUBTRACT;
		case '*': return Operator.MULTIPLY;
		case '/': return Operator.DIVIDE;
		}
		return Operator.BLANK;
	}
	
	public static int parseNextNumber(String sequence, int offset) {
		StringBuilder sb = new StringBuilder();
		while (offset < sequence.length() && Character.isDigit(sequence.charAt(offset))) {
			sb.append(sequence.charAt(offset));
			offset++;
		}
		return Integer.parseInt(sb.toString());	
	}
}
