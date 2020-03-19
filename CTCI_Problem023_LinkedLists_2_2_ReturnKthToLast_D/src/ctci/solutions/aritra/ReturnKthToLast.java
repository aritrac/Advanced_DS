package ctci.solutions.aritra;

/**
 * Question: Return Kth to Last: Implement an algorithm to find the kth to last
 * element of a singly linked list
 * 
 * Solution: We will approach this problem both recursively and non-recursively.
 * Remember that recursive solutions are often cleaner but less optimal .For
 * example, in this problem, the recursive implementation is about half the
 * length of the iterative solution but also takes O(n) space, where n is the
 * number of elements in the linked list.
 * 
 * Note that for this solution, we have defined k such that passing in k = 1
 * would return the last element, k = 2 would return to the second to the last
 * element, and so on. It is equally acceptable to define k such that k = 0
 * would return the last element
 * 
 * Solution : A more optimal but less straightforward solution is to implement this iteratively. We can use two pointers,p1 and p2. We place
 * them k nodes apart in the linked list by putting p2 at the beginning and moving p1 k nodes into the list. Then, when we move them at the same
 * pace, p1 will hit the end of the linked list after LENGTH - k steps. At that point, p2 will be LENGTH-k nodes into the list or k nodes from
 * the end.
 * 
 * This algorithm takes O(n) time and O(1) space.
 */

public class ReturnKthToLast {
	public static void main(String[] args) {
		LinkedListNode head = new LinkedListNode(1);
		head.next = new LinkedListNode(2);
		head.next.next = new LinkedListNode(3);
		head.next.next.next = new LinkedListNode(4);
		head.next.next.next.next = new LinkedListNode(5);
		System.out.println("The List");
		displayLinkedList(head);
		
		System.out.println(kthToLast(head, 1).data);
		System.out.println(kthToLast(head, 2).data);
		System.out.println(kthToLast(head, 3).data);
		System.out.println(kthToLast(head, 4).data);
		System.out.println(kthToLast(head, 5).data);
	}
	
	static LinkedListNode kthToLast(LinkedListNode head, int k){
		LinkedListNode p1 = head;
		LinkedListNode p2 = head;
		
		//Move p1 k nodes into the list
		for(int i = 0; i < k; i++){
			if(p1 == null)
				return null; //Out of bounds
			p1 = p1.next;
		}
		
		//Move both the pointers at the same pace. When p1 hits the end, p2 will be at the right element
		while(p1 != null){
			p1 = p1.next;
			p2 = p2.next;
		}
		
		return p2;
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
	int data;

	public LinkedListNode(int d) {
		data = d;
	}
}
