package ctci.solutions.aritra;

/**
 * Question: Remove Duplicates: Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 * 
 * Solution: This solution is the FOLLOW UP question that is how we can remove duplicates from an unsorted linked list if a temporary buffer
 * is not allowed. If we don't have buffer, we can iterate with two pointers: current which iterates through the linked list, and runner
 * which checks all subsequent nodes for duplicates
 * 
 * This code runs in O(1) space, but O(N^2) time
 */
public class RemoveDuplicates {
	public static void main(String[] args) {
		LinkedListNode head = new LinkedListNode(1);
		head.next = new LinkedListNode(3);
		head.next.next = new LinkedListNode(7);
		head.next.next.next = new LinkedListNode(3);
		head.next.next.next.next = new LinkedListNode(1);
		System.out.println("Before");
		displayLinkedList(head);
		System.out.println("After");
		deleteDups(head);
		displayLinkedList(head);
	}
	
	static void displayLinkedList(LinkedListNode head){
		LinkedListNode temp = head;
		while(temp != null){
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}
	
	static void deleteDups(LinkedListNode n){
		LinkedListNode current = n;
		while(current != null){
			//Remove all future nodes that have the same value
			LinkedListNode runner = current;
			while(runner.next != null){
				if(runner.next.data == current.data){
					runner.next = runner.next.next;
				}else{
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}
}

class LinkedListNode{
	LinkedListNode next = null;
	int data;
	
	public LinkedListNode(int d){
		data = d;
	}
}
