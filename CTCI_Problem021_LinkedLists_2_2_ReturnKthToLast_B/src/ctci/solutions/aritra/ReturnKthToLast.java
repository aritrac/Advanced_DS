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
 * Solution 2: This algorithm recurses through the linked list. When it hits the
 * end, the method passes back a counter set to 0. Each parent call adds 1 to
 * this counter. When the counter equals k, we know we have reached the kth to
 * last element of the linked list.
 * 
 * Implementing this is short and sweet - provided we have a way of
 * "passing back" an integer value through the stack. Unfortunately we can't
 * pass back a node and a counter using normal return statements. So how do we
 * handle this?
 * 
 * Approach A: Don't return the element. One way to do this is to change the
 * problem to simply printing the kth to last element. Then, we can pass back
 * the value of the counter simply through return values
 * 
 * Approach B: Create a Wrapper Class. We described earlier that the issue was
 * that we couldn't simultaneously return a counter and an index. If we wrap the
 * counter value with simple class (or even a single element array), we can
 * mimic passing by reference
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
		printKthToLast(head, 1);
		printKthToLast(head, 2);
		printKthToLast(head, 3);
		printKthToLast(head, 4);
		printKthToLast(head, 5);
	}

	static int printKthToLast(LinkedListNode head, int k) {
		if (head == null) {
			return 0;
		}
		int index = printKthToLast(head.next, k) + 1;
		if (index == k) {
			System.out.println(k + "th to last node is " + head.data);
		}
		return index;
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
