package ctci.solutions.aritra;

import java.util.Stack;

/*
 * Question: How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element? Push, pop and min should all operate in 
 * O(1) time
 * 
 * Solution: If we kept track of the minimum at each state, we would be able to easily know the minimum. We can do this by having each node record what the minimum 
 * beneath itself is. Then, to find the min, you just need to look at the top element thinks is the min, but here is just one issue with this. If we have a large stack, 
 * we waste a lot of space
 * by keeping track of the min for every single element. Can we do better? We can maybe do a bit better than this by using an additional stack which keeps track of the mins
 * as shown in the following example.
 */

public class StackWithMin extends Stack<Integer>{
	private static final long serialVersionUID = 1L;
	
	Stack<Integer> s2;
	
	public StackWithMin(){
		s2 = new Stack<Integer>();
	}
	
	public void push(int value){
		if(value <= min()){
			s2.push(value);
		}
		super.push(value);
	}
	
	public Integer pop(){
		int value = super.pop();
		if(value == min()){
			s2.pop();
		}
		return value;
	}
	
	public int min(){
		if(s2.isEmpty()){
			return Integer.MAX_VALUE;
		}else{
			return s2.peek();
		}
	}
	
	public static void main(String[] args) {
		StackWithMin sm = new StackWithMin();
		sm.push(23);
		sm.push(11);
		sm.push(16);
		sm.push(9);
		sm.push(90);
		
		System.out.println(sm.min());
		
		sm.pop();
		sm.pop();
		
		System.out.println(sm.min());
	}
}
