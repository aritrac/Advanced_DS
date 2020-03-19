package ctci.solutions.aritra;

/**
 * 
 * Question: Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x. If x 
 * is contained within the list, the values of x only need to be after the elements less than x (see below). The partition element x can appear anywhere in the
 * "right partition"; it does not need to appear between the left and right partitions.
 * 
 * EXAMPLE: 
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition=5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 * 
 * Solution: We can make this code a bit shorter than the previous approach. If we don't care about making the elements of the list "stable" (which there's
 * no obligation to, since the interviewer hasn't specified that), then we can instead rearrange the elements by growing the list at the head and tail.
 * In this approach, we start a "new" list (using the existing nodes). Elements bigger than the pivot element are put at the tail and elements smaller
 * are put at the head. Each time we insert an element, we update either the head or tail.
 */

public class PartitionList {
	public static void main(String[] args) {
		LinkedListNode n1 = new LinkedListNode(3);
		LinkedListNode n2 = new LinkedListNode(5);
		LinkedListNode n3 = new LinkedListNode(8);
		LinkedListNode n4 = new LinkedListNode(5);
		LinkedListNode n5 = new LinkedListNode(10);
		LinkedListNode n6 = new LinkedListNode(2);
		LinkedListNode n7 = new LinkedListNode(1);
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		
		LinkedListNode tempList2 = n1;
		System.out.println("Before Partitioning");
		while(tempList2 != null){
			System.out.print(tempList2.x + " ");
			tempList2 = tempList2.next;
		}
		
		LinkedListNode newList = partition(n1, 5);
		
		LinkedListNode tempList = newList;
		System.out.println("\nAfter Partitioning");
		while(tempList != null){
			System.out.print(tempList.x + " ");
			tempList = tempList.next;
		}
	}
	
	static public LinkedListNode partition(LinkedListNode node, int x){
		LinkedListNode head = node;
		LinkedListNode tail = node;
		
		while(node != null){
			LinkedListNode next = node.next;
			if(node.x < x){
				/*Insert node at head*/
				node.next = head;
				head = node;
			}else{
				/*Insert node at tail*/
				tail.next = node;
				tail = node;
			}
			node = next;
		}
		tail.next = null;
		//The head has changed, so we need to return it to the user.
		return head;
	}
}

class LinkedListNode{
	public int x;
	public LinkedListNode next;
	
	public LinkedListNode(int x){
		this.x = x;
	}
}
