package ctci.solutions.aritra;

/*
 * Question: Implement a function to check if a linked list is a palindrome or not.
 * 
 * Solution: This problem uses a recursive approach to find whether a list is palindromic or not. Let us take for example an even number list such as the one below
 * 
 * 1->2->2->1 with length 4
 * 
 * Recursion goes like this
 * 1 -> length is 4
 * 2 -> length is 2
 * 2 -> length is 0 <- This is the base case which determines the Result node which in this case will be 3rd node from the start and head will be 2nd from the start and the 
 * recursion will start returning from here
 * 
 * So 2 will be compared with 2 which is equal, so the result node will store a boolean true and from the 3rd node it will now go to the 4th node while the head will be at 
 * the first node. Now 1 will be compared with 1 and the boolean true will remain unchanged and this will be sent to the caller to output the result
 * 
 * Now for an odd usecase
 * 1->2->3->2->1
 * 
 * Recursion goes like this
 * 1 -> length is 5
 * 2 -> length is 3
 * 3 -> length is 1 <- This is the base case which determines the Result node which in this case will be the 4th node from start and head will be 2nd node from the start
 * Now 2 will be compared with 2 which is equal, so the result node will store a boolean true and from the 4th node it will now go to the 5th node while the head will be at 
 * the first node. Now 1 will be compared with 1 and the boolean true will remain unchanged and this will be sent to the called function to output the result
 * 
 * If any comparison is false, for each return of the recursive call the same state of the Result node will be passed on till the recursion ends and hence the result of both
 * odd and even length will be false;
 */

public class Palindrome {
	public static void main(String[] args) {
		// Creating the linked list 0 -> 1 -> 2 -> 1 -> 0 which is a palindromic list
		LinkedListNode l1 = new LinkedListNode(0);
		LinkedListNode l2 = new LinkedListNode(1);
		LinkedListNode l3 = new LinkedListNode(2);
		LinkedListNode l4 = new LinkedListNode(3);
		LinkedListNode l5 = new LinkedListNode(0);

		l1.setNext(l2);
		l2.setNext(l3);
		l3.setNext(l4);
		l4.setNext(l5);

		System.out.println("Is the following list palindromic?");
		LinkedListNode head = l1;
		// Displaying the list
		while (head != null) {
			System.out.print(head.x + " -> ");
			head = head.next;
		}

		System.out.println(isPalindrome(l1));
	}

	public static boolean isPalindrome(LinkedListNode head) {
		int length = lengthOfList(head);
		Result p = isPalindromeRecurse(head, length);
		return p.result;
	}

	public static int lengthOfList(LinkedListNode n) {
		int size = 0;
		while (n != null) {
			size++;
			n = n.next;
		}
		return size;
	}

	public static Result isPalindromeRecurse(LinkedListNode head, int length) {
		if (head == null || length <= 0) { // Even number of nodes
			return new Result(head, true);
		} else if (length == 1) { // Odd number of nodes
			return new Result(head.next, true);
		}

		// Recurse on sublist
		Result res = isPalindromeRecurse(head.next, length - 2);

		// If child calls are not a palindrome, pass back up a failure
		if (!res.result || res.node == null) {
			return res;
		}

		// Check if matches corresponding node on other side
		res.result = (head.x == res.node.x);

		// Return corresponding node
		res.node = res.node.next;

		return res;
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

class Result {
	public LinkedListNode node;
	public boolean result;

	public Result(LinkedListNode node, boolean result) {
		this.node = node;
		this.result = result;
	}
}
