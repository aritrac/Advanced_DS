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
 * Solution: Rather than shifting and swapping elements, we can actually create two different linked lists: one for elements less than x, and one
 * for elements greater than or equal to x.
 * We iterate through the linked list, inserting elements into our before list or our after list. Once we reach the end of the linked list and
 * have completed this splitting, we merge the two lists
 * This approach is mostly "stable" in that elements stay in their original order, other than the necessary movement around the partition. The code below
 * implements this approach.
 *
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
	
	/*
	 * Pass in the head of the linked list and the value to partition around
	 */
	static LinkedListNode partition(LinkedListNode node, int x){
		LinkedListNode beforeStart = null;
		LinkedListNode beforeEnd = null;
		LinkedListNode afterStart = null;
		LinkedListNode afterEnd = null;
		
		/*
		 * Partition List
		 */
		
		while(node != null){
			LinkedListNode next = node.next;
			node.next = null;
			if(node.x < x){
				/*Insert node into end of before list*/
				if(beforeStart == null){
					beforeStart = node;
					beforeEnd = beforeStart;
				}else{
					beforeEnd.next = node;
					beforeEnd = node;
				}
			}else{
				/*Insert node into end of after list*/
				if(afterStart == null){
					afterStart = node;
					afterEnd = afterStart;
				}else{
					afterEnd.next = node;
					afterEnd = node;
				}
			}
			node = next;
		}
		
		if(beforeStart == null){
			return afterStart;
		}
		
		/*Merge before list and after list*/
		beforeEnd.next = afterStart;
		return beforeStart;
	}
}

class LinkedListNode{
	public int x;
	public LinkedListNode next;
	
	public LinkedListNode(int x){
		this.x = x;
	}
}
