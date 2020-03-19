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
 * Approach : Create a Wrapper Class. We described earlier that the issue was
 * that we couldn't simultaneously return a counter and an index. If we wrap the
 * counter value with simple class (or even a single element array), we can
 * mimic passing by reference
 * 
 * Each of the recursive solutions takes O(n) space due to the recursive calls. There are a number of other solutions that we haven't
 * addressed. We could store the counter in a static variable. Or, we could create a class that stores both the node and the counter
 * and return an instance of that class. Regardless of which solution we pick, we need a way to update both the node and the counter
 * in a way that all levels of the recursive stack will see.
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
		Index idx = new Index();
		return kthToLast(head,k,idx);
	}
	
	static LinkedListNode kthToLast(LinkedListNode head, int k, Index idx){
		if(head == null)
			return null;
		LinkedListNode node = kthToLast(head.next, k, idx);
		idx.value = idx.value + 1;
		if(idx.value == k)
			return head;
		return node;
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

class Index{
	public int value = 0;
}
