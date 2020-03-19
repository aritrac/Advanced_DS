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
 * Solution 1: If the size of the linked list is known, then the kth to last
 * element is the (length - k)th element. We can just iterate through the linked
 * list to find this element. Because this solution is trivial as follows
 * 
 *
 */
public class ReturnKthToLast {
	public static void main(String[] args) {
		LinkedListNode head = new LinkedListNode(1);
		head.next = new LinkedListNode(2);
		head.next.next = new LinkedListNode(3);
		head.next.next.next = new LinkedListNode(4);
		head.next.next.next.next = new LinkedListNode(5);
		System.out.println("The List");
		int size = 5;
		displayLinkedList(head);
		printKthToLast(head,1,size);
		printKthToLast(head,2,size);
		printKthToLast(head,3,size);
		printKthToLast(head,4,size);
		printKthToLast(head,5,size);
	}
	
	static void printKthToLast(LinkedListNode head,int k, int size){
		if(k > size)
			return;
		int count = size - k;
		while(head != null){
			if(count > 0){
				head = head.next;
			}else{
				break;
			}
			count--;
		}
		System.out.println(head.data);
	}
	
	static void displayLinkedList(LinkedListNode head){
		LinkedListNode temp = head;
		while(temp != null){
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}

class LinkedListNode{
	LinkedListNode next = null;
	int data;
	
	public LinkedListNode(int d){
		data = d;
	}
}