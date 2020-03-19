package ctci.solutions.aritra;

/*
 * Question: Describe how you could use a single array to implement three stacks
 * 
 * Solutions: Like many problems, this one somewhat depends on how well we'd like to support these stacks. If we're okay with simply allocating
 * a fixed amount of space for each stack, we can do that. This may mean though that one stack runs out of space, while the others are nearly empty.
 * 
 * Alternatively, we can be flexible in our space allocation, but this significantly increases the complexity of the problem
 * 
 * In this solution, we can divide the array in three equal parts and allow the individual stack to grow in that limited space.
 * Note: We will use the notation "[" to mean inclusive of an end point and "(" to mean exclusive of an end point.
 * 
 * For stack 1, we will use [0,n/3)
 * For stack 2, we will use [n/3,2n/3)
 * For stack 3, we will use [2n/3,n)
 * The code is as follows 
 */

public class FixedMultiStack {
	private static int numberOfStacks = 3;
	private static int stackCapacity;
	private static int[] values;
	private static int[] sizes;
	
	public FixedMultiStack(int stackSize){
		stackCapacity = stackSize;
		values = new int[stackSize * numberOfStacks];
		sizes = new int[numberOfStacks];
	}
	
	//Push value onto stack
	public static void push(int stackNum, int value) throws FullStackException{
		//Check that we have space for the next element
		if(isFull(stackNum)){
			throw new FullStackException();
		}
		
		//Increment stack pointer and then update top value
		sizes[stackNum]++;
		values[indexOfTop(stackNum)] = value;
	}
	
	//Pop item from top stack
	public static int pop(int stackNum) throws EmptyStackException{
		if(isEmpty(stackNum)){
			throw new EmptyStackException();
		}
		
		int topIndex = indexOfTop(stackNum);
		int value = values[topIndex]; //Get top
		values[topIndex] = 0; //Clear
		sizes[stackNum]--; //Shrink
		return value;
	}
	
	//Return top element
	public static int peek(int stackNum) throws EmptyStackException{
		if(isEmpty(stackNum)){
			throw new EmptyStackException();
		}
		return values[indexOfTop(stackNum)];
	}
	
	//Return if stack is empty
	public static boolean isEmpty(int stackNum){
		return sizes[stackNum] == 0;
	}
	
	//Return if stack is full
	public static boolean isFull(int stackNum){
		return sizes[stackNum] == stackCapacity;
	}
	
	//Returns index of the top of the stack
	private static int indexOfTop(int stackNum){
		int offSet = stackNum * stackCapacity;
		int size = sizes[stackNum];
		return offSet + size - 1;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		FixedMultiStack fms = new FixedMultiStack(5);
		try{
			fms.push(0, 1);
			fms.push(0, 2);
			fms.push(0, 3);
			fms.push(0, 4);
			fms.push(0, 5);
			
			fms.push(1, 7);
			fms.push(1, 8);
			fms.push(1, 9);
			fms.push(1, 10);
			fms.push(1, 11);
			
			fms.push(2, 13);
			fms.push(2, 14);
			fms.push(2, 15);
			fms.push(2, 16);
			fms.push(2, 17);
			
			fms.display(0);
			fms.display(1);
			fms.display(2);
			
			
			fms.pop(0);
			fms.pop(0);
			fms.pop(0);
			fms.pop(0);
			fms.pop(0);
			
			fms.pop(1);
			fms.pop(1);
			fms.pop(1);
			
			fms.pop(2);
			
			fms.display(0);
			fms.display(1);
			fms.display(2);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void display(int stackNum){
		System.out.println("Stack " + stackNum + " contains");
		int offset = stackNum * stackCapacity;
		for(int i = offset; i <= indexOfTop(stackNum); i++){
			System.out.print(values[i] + "->");
		}
		System.out.println();
	}
}

class FullStackException extends Exception{
	private static final long serialVersionUID = 1L;

	FullStackException(){
		super();
	}
}

class EmptyStackException extends Exception{
	private static final long serialVersionUID = 1L;

	EmptyStackException(){
		super();
	}
}