package ctci.solutions.aritra;

import java.util.HashSet;

/**
 * Question: Remove Duplicates: Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 * 
 * Solution: In order to remove duplicates from a linked list, we need to be able to track duplicates. A simple hash table will work well here.
 * In the below solution, we simply iterate through the linked list, adding each element to a hash table. When we discover a duplicate element,
 * we remove the element and continue iterating. We can do this all in one pass since we are using a linked list.
 *
 * This solution takes O(N) time, where N is the number of elements in the linked list.
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
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode previous = null;
		while(n != null){
			if(set.contains(n.data)){
				previous.next = n.next;
			}else{
				set.add(n.data);
				previous = n;
			}
			n = n.next;
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
