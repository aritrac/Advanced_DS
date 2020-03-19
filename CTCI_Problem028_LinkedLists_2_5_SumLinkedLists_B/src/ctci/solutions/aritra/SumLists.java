package ctci.solutions.aritra;

/*
 *	Question: You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is 
 *  at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
 *  
 *  Follow Up:
 *  Suppose the digits are stored in the forward order. Repeat the above problem.
 *  Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
 *  Output: 9 -> 1 -> 2. That is, 912.
 *  
 *  Solution: Part B or the Follow Up part is conceptually the same (recurse, carry the excess), but has some additional complications when it comes to implementation:
 *  1. One list may be shorter than the other, and we cannot handle this "on the fly". For example, suppose we are adding (1 -> 2 -> 3 -> 4) and (5 -> 6 -> 7).
 *  We need to know that the 5 should be "matched" with the 2, not the 1. We can accomplish this by comparing the lengths of the lists in the beginning and
 *  padding the shorter list with zeros.
 *  2. In the first part, successive results were added to the tail (i.e. passed forward). This meant that the recursive call would be passed the carry, and would
 *  return the result (which is then appended to the tail). In this case, however, results are added to the head (i.e. passed backward). The recursive call must
 *  return the result, as before, as well as the carry. This is not terribly challenging to implement, but it is more cumbersome. We can solve this issue by creating
 *  a wrapper class called Partial Sum. 
 *  
 *  The code below implements this algorithm.
 */

public class SumLists {
	public static void main(String[] args) {
		LinkedListNode l11 = new LinkedListNode(7);
		LinkedListNode l12 = new LinkedListNode(1);
		LinkedListNode l13 = new LinkedListNode(6);
		LinkedListNode l21 = new LinkedListNode(5);
		LinkedListNode l22 = new LinkedListNode(9);
		LinkedListNode l23 = new LinkedListNode(2);
		
		//Num 7 -> 1 -> 6
		l11.next = l12;
		l12.next = l13;
		
		//Num 5 -> 9 -> 2
		l21.next = l22;
		l22.next = l23;
		
		System.out.println("Num1 in reverse");
		LinkedListNode tempList = l11;
		while(tempList != null){
			System.out.print(tempList.x + " ");
			tempList = tempList.next;
		}
		
		System.out.println("Num2 in reverse");
		LinkedListNode tempList2 = l21;
		while(tempList2 != null){
			System.out.print(tempList2.x + " ");
			tempList2 = tempList2.next;
		}
		
		System.out.println("Sum in reverse");
		LinkedListNode sum = addLists(l11, l21,0);
		while(sum != null){
			System.out.print(sum.x + " ");
			sum = sum.next;
		}
	}
	
	public static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry){
		int len1 = length(l1);
		int len2 = length(l2);
		
		/*Pad the shorter list with zeros*/
		if(len1 < len2){
			l1 = padList(l1, len2 - len1);
		}else{
			l2 = padList(l2, len1 - len2);
		}
		
		//Add Lists
		PartialSum sum = addListsHelper(l1,l2);
		
		/*If there was a carry value left over, insert this at the front of the list. Otherwise, just return the linked list*/
		if(sum.carry == 0){
			return sum.sum;
		}else{
			LinkedListNode result = insertBefore(sum.sum, sum.carry);
			return result;
		}
	}
	
	public static PartialSum addListsHelper(LinkedListNode l1, LinkedListNode l2){
		if(l1 == null && l2 == null){
			PartialSum sum = new PartialSum();
			return sum;
		}
		/*Add smaller digits recursively*/
		PartialSum sum = addListsHelper(l1.next, l2.next);
		
		/*Add carry to the current data*/
		int val = sum.carry + l1.x + l2.x;
		
		/*Insert sum of current digits*/
		LinkedListNode full_result = insertBefore(sum.sum, val % 10);
		
		/*Return sum so far, and the carry value*/
		sum.sum = full_result;
		sum.carry = val/10;
		return sum;
	}
	
	//Determine length of list
	public static int length(LinkedListNode n1){
		int count = 0;
		while(n1 != null){
			count++;
			n1 = n1.next;
		}
		return count;
	}
	
	//Pad the list with zeros
	public static LinkedListNode padList(LinkedListNode l, int padding){
		LinkedListNode head = l;
		for(int i = 0; i < padding;  i++){
			head = insertBefore(head,0);
		}
		return head;
	}
	
	//Helper function to insert node in the front of a linked list
	public static LinkedListNode insertBefore(LinkedListNode list, int data){
		LinkedListNode node = new LinkedListNode(data);
		if(list != null){
			node.next = list;
		}
		return node;
	}
}

class PartialSum{
	public LinkedListNode sum = null;
	public int carry = 0;
}

class LinkedListNode{
	public int x;
	public LinkedListNode next;
	
	public LinkedListNode(int x){
		this.x = x;
	}
	
	public LinkedListNode(){
		
	}
	
	public void setNext(LinkedListNode n){
		this.next = n;
	}
}

