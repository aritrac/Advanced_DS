package ctci.solutions.aritra;

/**
 * Question: Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e. any node but the first and last node, not necessarily
 * the exact middle) of a singly linked list given only access to that node
 * 
 * Example:
 * Input: the node c from the linked list a -> b -> c -> d -> e -> f
 * Result: nothing is returned, but the new linked list looks like a -> b -> d -> e -> f
 * 
 * Solution: In this problem, you are not given access to the head of the linked list. You only have access to that node. The solution is simply
 * to copy the data from the next node over to the current node, and then to delete the next node.
 *
 */
public class DeleteMiddleNode {
	public static void main(String[] args) {
		LinkedListNode head = new LinkedListNode('a');
		head.next = new LinkedListNode('b');
		head.next.next = new LinkedListNode('c');
		head.next.next.next = new LinkedListNode('d');
		head.next.next.next.next = new LinkedListNode('e');
		head.next.next.next.next.next = new LinkedListNode('f');
		System.out.println("The List");
		displayLinkedList(head);
		
		System.out.println("Deleting c ...");
		deleteNode(head.next.next);
		System.out.println("The List now");
		displayLinkedList(head);
	}
	
	static boolean deleteNode(LinkedListNode n){
		if(n == null || n.next == null)
			return false; //Failure
		LinkedListNode next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true;
	}
	
	static void displayLinkedList(LinkedListNode head) {
		LinkedListNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}

class LinkedListNode {
	LinkedListNode next = null;
	char data;

	public LinkedListNode(char d) {
		data = d;
	}
}
