package ctci.solutions.aritra;

import java.util.Stack;

/**
 * Question: Implement a MyQueue class which implements a queue using two stacks
 * 
 * Solution: Since the major difference between a queue and a stack is the order (first-in first-out vs last in first out), we know that we need to
 * modify peek() and pop() to go in reverse order. We can use our second stack to reverse the order of the elements (by popping s1 and pushing the elements onto s2)
 * In such an implementation, on each peek() and pop() operation, we would pop every thing from s1 onto s2, perform the peek/pop operation and then push everything back.
 * 
 * This will work, but if two pop/peeks are performed back-to-back, we're needlessly moving elements. We can implement a lazy approach where we let the elements
 * sit in s2 until we absolutely must reverse the elements
 * 
 * In this approach, stackNewest has the newest elements on top and stackOldest has the oldest elements on top. When we dequeue an element, we want to remove the oldest element
 * first, and so we dequeue from stackOldest. If stackOldest is empty, then we want to transfer all elements from stackNewest since it has the newest elements on top.
 * 
 */
public class QueueWithStacks<T> {
	Stack<T> stackNewest, stackOldest;
	
	public QueueWithStacks(){
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}
	
	public int size(){
		return stackNewest.size() + stackOldest.size();
	}
	
	public void add(T value){
		//Push onto stackNewest, which always has the newest elements on top
		stackNewest.push(value);
	}
	
	//Move elements from stackNewest into stackOldest. This is usually done so that we can do operations on stackOldest
	private void shiftStacks(){
		if(stackOldest.isEmpty()){
			while(!stackNewest.isEmpty()){
				stackOldest.push(stackNewest.pop());
			}
		}
	}
	
	public T peek(){
		shiftStacks(); //Ensure stackOldest has the current elements
		return stackOldest.peek(); //retrieve the oldest item
	}
	
	public T remove(){
		shiftStacks(); //Ensure stackOldest has the current elements
		return stackOldest.pop(); // pop the oldest item
	}
	
	public static void main(String[] args) {
		QueueWithStacks<Integer> qws = new QueueWithStacks<Integer>();
		
		qws.add(5);
		qws.add(10);
		qws.add(14);
		qws.add(11);
		qws.add(10);
		
		System.out.println(qws.peek());
		
		System.out.println(qws.remove());
		
		System.out.println(qws.remove());
		
		qws.add(19);
		
		System.out.println(qws.peek());
	}
}
