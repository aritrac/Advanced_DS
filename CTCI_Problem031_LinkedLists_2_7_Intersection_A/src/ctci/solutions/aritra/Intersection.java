package ctci.solutions.aritra;

/*
 * Question: Given two singly linked lists, determine if the two lists intersect. Return the intersecting node. Note that the intersection is defined based on reference not value.
 * That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.
 * 
 * Solution: 
 * We now have a multistep process
 * 1)Run through each linked list to get the lengths and the tails
 * 2)Compare the tails. If they are different (by reference, not by value, return immediately There is no intersection.
 * 3)Set two pointers to the start of each linked list
 * 4)On the longer list, advance its pointer by the difference in lengths
 * 5)Now, traverse on each linked list until the pointers are the same.
 */

public class Intersection {
	public static void main(String[] args) {
		LinkedListNode l1 = new LinkedListNode(3);
		LinkedListNode l2 = new LinkedListNode(1);
		LinkedListNode l3 = new LinkedListNode(5);
		LinkedListNode l4 = new LinkedListNode(9);
		LinkedListNode l5 = new LinkedListNode(7);
		LinkedListNode l6 = new LinkedListNode(2);
		LinkedListNode l7 = new LinkedListNode(1);
		
		l1.setNext(l2);
		l2.setNext(l3);
		l3.setNext(l4);
		l4.setNext(l5);
		l5.setNext(l6);
		l6.setNext(l7);
		
		LinkedListNode q1 = new LinkedListNode(4);
		LinkedListNode q2 = new LinkedListNode(6);
		q1.setNext(q2);
		q2.setNext(l5);
		
		System.out.println("The first list is as follows");
		LinkedListNode current = l1;
		while(current != null){
			System.out.print(current.x + "->");
			current = current.next;
		}
		
		System.out.println("\nThe second list is as follows");
		current = q1;
		while(current != null){
			System.out.print(current.x + "->");
			current = current.next;
		}
		
		System.out.println("\nThe intersection node has the data : " + findIntersection(l1, q1).x);
	}
	
	public static LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2){
		if(list1 == null || list2 == null)
			return null;
		
		//Get tail and sizes
		Result result1 = getTailAndSize(list1);
		Result result2 = getTailAndSize(list2);
		
		//If different tail nodes, then there's no intersection
		if(result1.tail != result2.tail){
			return null;
		}
		
		//Set pointers to the start of each linked list
		LinkedListNode shorter = result1.size < result2.size ? list1 : list2;
		LinkedListNode longer = result1.size < result2.size ? list2 : list1;
		
	    //Advance the pointer for the longer linked list by difference in lengths
		longer = getKthNode(longer, Math.abs(result1.size - result2.size));
		
		//Move both pointers until you have a collision
		while(shorter != longer){
			shorter = shorter.next;
			longer = longer.next;
		}
		
		//Return either one
		return longer;
	}
	
	public static Result getTailAndSize(LinkedListNode list){
		if(list == null)
			return null;
		int size = 1;
		LinkedListNode current = list;
		while(current.next != null){
			size++;
			current = current.next;
		}
		return new Result(current,size);
	}
	
	public static LinkedListNode getKthNode(LinkedListNode head, int k){
		LinkedListNode current = head;
		while(k > 0 && current != null){
			current = current.next;
			k--;
		}
		return current;
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

class Result{
	public LinkedListNode tail;
	public int size;
	public Result(LinkedListNode tail, int size){
		this.tail = tail;
		this.size = size;
	}
}
