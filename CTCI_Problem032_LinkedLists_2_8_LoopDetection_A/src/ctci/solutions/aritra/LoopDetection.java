package ctci.solutions.aritra;

/*
 * Question: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
 * Definition:
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list
 * 
 * Example:
 * Input: A -> B -> C -> D -> E -> C [The same C as earlier]
 * Output: C
 * 
 * Solution:
 * 1. Create two pointers FastPointer and SlowPointer
 * 2. Move FastPointer at a rate of 2 steps and SlowPointer at a rate of 1 step
 * 3. When they collide, move SlowPointer to LinkedListHead. Keep FastPointer where it is.
 * 4. Move SlowPointer and FastPointer at a rate of one step. Return the new collision point
 */

public class LoopDetection {
	public static void main(String[] args) {
		LinkedListNode l1 = new LinkedListNode(1);
		LinkedListNode l2 = new LinkedListNode(2);
		LinkedListNode l3 = new LinkedListNode(3);
		LinkedListNode l4 = new LinkedListNode(4);
		LinkedListNode l5 = new LinkedListNode(5);
		LinkedListNode l6 = new LinkedListNode(6);
		LinkedListNode l7 = new LinkedListNode(7);
		LinkedListNode l8 = new LinkedListNode(8);
		LinkedListNode l9 = new LinkedListNode(9);
		LinkedListNode l10 = new LinkedListNode(10);
		LinkedListNode l11 = new LinkedListNode(11);
		
		l1.setNext(l2);
		l2.setNext(l3);
		l3.setNext(l4);
		l4.setNext(l5);
		l5.setNext(l6);
		l6.setNext(l7);
		l7.setNext(l8);
		l8.setNext(l9);
		l9.setNext(l10);
		l10.setNext(l11);
		l11.setNext(l4); //Cycle is at l4
		
		System.out.println("The cycle begins at " + findBeginning(l1).x);
	}
	
	public static LinkedListNode findBeginning(LinkedListNode head){
		LinkedListNode slow = head;
		LinkedListNode fast = head;
		
		//Find meeting point. This will be LOOP_SIZE - k steps into the linked list
		while(fast != null && fast.next != null){
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast){//Collision
				break;
			}
		}
		
		//Error check - no meeting point, and therefore no loop
		if(fast == null || fast.next == null){
			return null;
		}
		
		//Move slow to Head. Keep fast at Meeting Point. Each are k steps from the loop start. If they
		//move at the same pace, they must meet at loop start
		slow = head;
		while(slow != fast){
			slow = slow.next;
			fast = fast.next;
		}
		
		//Both now point to the start of the loop
		return fast;
	}
}

class LinkedListNode {
	public int x;
	public LinkedListNode next;

	public LinkedListNode(int x) {
		this.x = x;
	}

	public LinkedListNode() {

	}

	public void setNext(LinkedListNode n) {
		this.next = n;
	}
}
