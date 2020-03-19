package ctci.solutions.aritra;

import java.util.Stack;

/*
 * Question: Implement a function to check if a linked list is a palindrome or not. We will use an iterative approach this time
 * 
 * Solution: We want to detect linked lists where the front half of the list is the reverse of the second half. How would we do that? By reversing the front half of the list. 
 * A stack can accomplish this. We need to push the first half of the elements onto a stack. We can do this in two different ways, depending on whether or not we
 * know the size of the linked list.
 * If we know the size of the linked list, we can iterate through the first half of the elements in a standard for loop, pushing each element onto a stack. We must be careful
 * of course, to handle the case where the length of the linked list is odd.
 * If we dont know the size of the linked list, we can iterate through the linked list, using the fast runner / slow runner technique described in the beginning of the chapter
 * .At each step in the loop, we push the data from the slow runner onto a stack. When the fast runner hits the end of the list, the slow runner will have reached the middle of 
 * the linked list. By this point, the stack will have all the elements from the front of the linked list, but in reverse order.
 * 
 * Now we can simple iterate through the rest of the linked list. At each iteration, we compare the node to the top of the stack. If we complete the iteration without finding
 * a difference, then the linked list is a palindrome.
 * 
 */

public class Palindrome {
	public static void main(String[] args) {
		//Creating the linked list 0 -> 1 -> 2 -> 1 -> 0 which is a palindromic list
		LinkedListNode l1 = new LinkedListNode(0);
		LinkedListNode l2 = new LinkedListNode(1);
		LinkedListNode l3 = new LinkedListNode(2);
		LinkedListNode l4 = new LinkedListNode(1);
		LinkedListNode l5 = new LinkedListNode(0);
		
		l1.setNext(l2);
		l2.setNext(l3);
		l3.setNext(l4);
		l4.setNext(l5);
		
		System.out.println("Is the following list palindromic?");
		LinkedListNode head = l1;
		//Displaying the list
		while(head != null){
			System.out.print(head.x + " -> ");
			head = head.next;
		}
		
		System.out.println(isPalindrome(l1));
	}
	
	public static boolean isPalindrome(LinkedListNode head){
		LinkedListNode fast = head;
		LinkedListNode slow = head;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		/*
		 * Push elements from first half of linked list onto stack. When fast runner (which is moving at 2x speed) reaches the end of the linked list, then we know we're at the middle
		 */
		while(fast != null && fast.next != null){
			stack.push(slow.x);
			slow = slow.next;
			fast = fast.next.next;
		}
		
		//Has odd number of elements, so skip the middle element
		//0 -> 1 -> 2 -> 3 -> 4   -> If the list has odd number of elements, fast will be pointing to the last element
		//0 -> 1 -> 2 -> 3        -> If the list has even number of elements, fast will be beyond the last element and set to null
		//So to begin comparison with the stack elements, progressing the slow pointer one place to skip the middle elements in case of odd number of elements.
		if(fast != null){
			slow = slow.next;
		}
		
		while(slow != null){
			int top = stack.pop();
			//If values are different, then its not a palindrome.
			if(top != slow.x){
				return false;
			}
			slow = slow.next;
		}
		return true;
	}
}

class LinkedListNode{
	public int x;
	public LinkedListNode next;
	
	public LinkedListNode(int x){
		this.x = x;
	}
	
	public LinkedListNode(){
		
	}
	
	public void setNext(LinkedListNode n){
		this.next = n;
	}
}
