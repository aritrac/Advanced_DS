package ctci.solutions.aritra;

/*
 *	Question: You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head
 *  of the list. Write a function that adds the two numbers and returns the sum as a linked list.
 *  
 *  Example:
 *  Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295
 *  Output: 2 -> 1 -> 9. That is, 912.
 *  
 *  Follow Up:
 *  Suppose the digits are stored in the forward order. Repeat the above problem.
 *  Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
 *  Output: 9 -> 1 -> 2. That is, 912.
 *  
 *  Solution:It's useful to remember in this problem how exactly addition works. Imagine the problem:
 *  	6 1 7
 *   +  2 9 5
 *  First, we add 7 and 5 to get 12. The digit 2 becomes the last digit of the number, and 1 gets carried over to the next step. Second, we add 1,1, and add 9 to get 11. The 1
 *  becomes the second digit, and the other 1 gets carried over the final step. Third and finally, we add 1,6 and 2 to get 9. So, our value becomes 912.
 *  
 *  We can mimic this process recursively by adding node by node, carrying over any "excess" data to the next node. Let's walk through this for the below linked list:
 *  	7 -> 1 -> 6
 *   +  5 -> 9 -> 2
 *  We do the following:
 *  1. We add 7 and 5 first, getting a result of 12. 2 becomes the first node in our linked list, and we carry the 1 to the next sum
 *  List: 2 -> ?
 *  2. We then add 1 and 9, as well as the "carry", getting a result of 11. 1 becomes the second element of our linked list, and we carry the 1 to the next sum.
 *  List: 2 -> 1 -> ?
 *  3. Finally, we add 6,2 and our carry to get 9. This becomes the final element of our linked list.
 *  List: 2 -> 1 -> 9
 *  
 *  The code below implements this algorithm
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
		if(l1 == null && l2 == null && carry == 0){
			return null;
		}
		
		LinkedListNode result = new LinkedListNode();
		int value = carry;
		if(l1 != null){
			value += l1.x;
		}
		if(l2 != null){
			value += l2.x;
		}
		
		result.x = value % 10;	/*Second digit of number*/
		
		/*Recurse*/
		if(l1 != null || l2 != null){
			LinkedListNode more = addLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
			result.setNext(more);
		}
		return result;
	}
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
