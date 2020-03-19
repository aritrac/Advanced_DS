package ctci.solutions.aritra;

import java.util.Stack;

/*
 * Question: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses), compute the result
 * Example:
 * Input: 2 * 3 + 5 / 6 * 3 + 15
 * Output: 23.5
 * 
 * Solution: We can solve this problem using two stacks: one for numbers and one for operators
 * 
 * 2 - 6 - 7 * 8 / 2 + 5
 * 
 * the processing works as follows:
 * 1)Each time we see a number, it gets pushed onto numberStack
 * 2)Operators gets pushed onto operatorStack - as long as the operator has higher priority than the current top of stack. If priority(currentOperator) <= priority(operatorStack.top()),
 * then we collapse the top of the stacks:
 * >>Collapsing: pop two elements off numberStack, pop an operator off operatorStack, apply the operator, and push the result onto numberStack.
 * >>Priority: addition and subtraction have equal priority, which is lower than the priority of multiplication and division (also equal priority)
 * 
 * This collapsing continues until the above inequality is broken, at which point currentOperator is pushed onto operatorStack
 * At the very end, we collapse the stack.
 * 
 * Let's see this with an example: 2 - 6 - 7 * 8 / 2 + 5
 * 
 * 		action						numberStack						operatorStack
 * 2	numberStack.push(2)			2								[empty]
 * -	operatorStack.push(-)		2								-
 * 6	numberStack.push(6)			6,2								-
 * -	collapseStacks[2 - 6]		-4								[empty]
 * 		operatorStack.push(-)		-4								-
 * 7	numberStack.push(7)			7,-4							-
 * *	operatorStack.push(*)		7,-4							*,-
 * 8	numberStack.push(8)			8,7,-4							*,-
 * /	collapseStack [7 * 8]       56, -4							-
 * 		operatorStack.push(/)		56, -4							/,-
 * 2	numberStack.push(2)			2, 56, -4						/,-
 * +	collapseStack [56 / 2]		28, -4							-
 * 		collapseStack [-4 - 28]		-32								[empty]
 * 		operatorStack.push(+)		-32								+
 * 5	numberStack.push(5)			5,-32							+
 * 		collapseStack [-32 + 5]		-27								[empty]
 * 		return -27
 * 
 * The code below implements the following algorithm
 */

public class Calculator {
	
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		String sequence = "2-6-7*8/2+5";
		System.out.println("The value of the expression above = " + calc.compute(sequence));
	}
	
	
	double compute(String sequence){
		Stack<Double> numberStack = new Stack<Double>();
		Stack<Operator> operatorStack = new Stack<Operator>();
		
		for(int i = 0; i < sequence.length(); i++){
			try{
				//Get number and push
				int value = parseNextNumber(sequence, i);
				numberStack.push((double)value);
				
				//Move to the operator
				i += Integer.toString(value).length();
				if(i >= sequence.length()){
					break;
				}
				
				//Get operator, collapse top as needed, push operator
				Operator op = parseNextOperator(sequence, i);
				collapseTop(op, numberStack, operatorStack);
				operatorStack.push(op);
			}catch(NumberFormatException ex){
				return Integer.MIN_VALUE;
			}
		}
		
		//Do final collapse
		collapseTop(Operator.BLANK, numberStack, operatorStack);
		if(numberStack.size() == 1 && operatorStack.size() == 0){
			return numberStack.pop();
		}
		return 0;
	}
	
	//Collapse top until priority(futureTop) > priority(top). Collapsing means to pop the top 2 numbers and apply the operator popped from the top of the operatorStack, and
	//then push that onto the numbers stack
	void collapseTop(Operator futureTop, Stack<Double> numberStack, Stack<Operator> operatorStack){
		while(operatorStack.size() >= 1 && numberStack.size() >= 2){
			if(priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())){
				double second = numberStack.pop();
				double first = numberStack.pop();
				Operator op = operatorStack.pop();
				double collapsed = applyOp(first, op, second);
				numberStack.push(collapsed);
			}else{
				break;
			}
		}
	}
	
	//Return priority of operator. Mapped so addition == subtraction < multiplication == division
	int priorityOfOperator(Operator op){
		switch(op){
			case ADD: return 1;
			case SUBTRACT: return 1;
			case MULTIPLY: return 2;
			case DIVIDE: return 2;
			case BLANK: return 0;
		}
		return 0;
	}
	
	//Apply operatorL left [op] right
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
	
	//Return the number that starts at offSet
	int parseNextNumber(String seq, int offSet){
		StringBuilder sb = new StringBuilder();
		while(offSet < seq.length() && Character.isDigit(seq.charAt(offSet))){
			sb.append(seq.charAt(offSet));
			offSet++;
		}
		return Integer.parseInt(sb.toString());
	}
	
	//Return the operator that occurs as offset
	Operator parseNextOperator(String sequence, int offSet){
		if(offSet < sequence.length()){
			char op = sequence.charAt(offSet);
			switch(op){
				case '+': return Operator.ADD;
				case '-': return Operator.SUBTRACT;
				case '*': return Operator.MULTIPLY;
				case '/': return Operator.DIVIDE;
			}
		}
		return Operator.BLANK;
	}
}

enum Operator{
	ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK
}
