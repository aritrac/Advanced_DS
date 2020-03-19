package ctci.aritra.solutions;

import java.util.Stack;

/*
 * Question: How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element? Push, pop and min should all operate in O(1) time
 * 
 * Solution: If we kept track of the minimum at each state, we would be able to easily know the minimum. We can do this by having each node record what the minimum 
 * beneath itself is. Then, to find the min, you just need to look at the top element thinks is the min
 */

public class StackMin extends Stack<NodeWithMin>{
	private static final long serialVersionUID = 1682506228156131443L;

	public void push(int value){
		int newMin = Math.min(value, min());
		super.push(new NodeWithMin(value,newMin));
	}
	
	public int min(){
		if(this.isEmpty()){
			return Integer.MAX_VALUE; //Error value
		}else{
			return peek().min;
		}
	}
	
	public static void main(String[] args) {
		StackMin sm = new StackMin();
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

class NodeWithMin{
	public int value;
	public int min;
	public NodeWithMin(int v, int min){
		value = v;
		this.min = min;
	}
}
