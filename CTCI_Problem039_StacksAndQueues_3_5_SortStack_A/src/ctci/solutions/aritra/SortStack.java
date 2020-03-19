package ctci.solutions.aritra;

import java.util.Stack;

/*
 * Question: Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, but you may not copy the elements into 
 * any other data structure (such as an array). The stack supports the following operations: push, pop, peek and isEmpty 
 * 
 * Solution: We can sort s1 by inserting each element from s1 in order into s2. Imagine we have the following stacks, where s2 is sorted and s1 is not. When we pop from s1,
 * we need to find the right place in s2 to insert this number. In this case, the correct place might be at one of the locations below the top of s2. So how we get this 
 * popped element at that place? We can do this by popping the element from s1 and holding it in a temporary variable. Then we move the elements greater than that to s1 
 * and then push this number onto s2.Now again we pop from s1 and since already sorted elements from s2 are not sitting on top of s1, we just pop from s1 and push it in 
 * s2 directly. The code below does exactly that
 */

public class SortStack {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.add(13);
		s.add(7);
		s.add(10);
		s.add(19);
		s.add(1);
		
		System.out.println("Before Sorting");
		for(int i = s.size() - 1; i >= 0; i--){
			System.out.print(s.get(i) + "->");
		}
		
		sort(s);
		System.out.println("\n\nAfter Sorting");
		
		for(int i = s.size() - 1; i >= 0; i--){
			System.out.print(s.get(i) + "->");
		}
	}
	
	public static void sort(Stack<Integer> s){
		Stack<Integer> r = new Stack<Integer>();
		while(!s.isEmpty()){
			//Insert each element in s in sorted order into r
			int tmp = s.pop();
			while(!r.isEmpty() && r.peek() > tmp){
				s.push(r.pop());
			}
			r.push(tmp);
		}
		while(!r.isEmpty()){
			s.push(r.pop()); //Copy elements from r back into s
		}
	}
}
