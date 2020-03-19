package ctci.solutions.aritra;

/*
 * Question: Implement a function to check if a linked list is a palindrome or not.
 * 
 * Solution: Our first solution is to reverse the linked list and compare the reversed list to the original list. If they are the same, the lists are identical. 
 * Note that when we compare the linked list to the reversed list, we only actually need to compare the first half of the list. If the first half of the normal list 
 * matches the first half of the reversed list, then the second half of the normal list must match the second half of the reversed list.
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
	
	public static LinkedListNode reverseAndClone(LinkedListNode node){
		LinkedListNode head = null;
		while(node != null){ //Traversing the original list
			LinkedListNode n = new LinkedListNode(node.x); //clone
			n.next = head;
			head = n;
			node = node.next;
		}
		return head;
	}
	
	public static boolean isEqual(LinkedListNode one, LinkedListNode two){
		while(one != null && two != null){
			if(one.x != two.x){
				return false;
			}
			one = one.next;
			two = two.next;
		}
		return (one == null && two == null);
	}
	
	public static boolean isPalindrome(LinkedListNode head){
		LinkedListNode reversed = reverseAndClone(head);
		return isEqual(head, reversed);
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
