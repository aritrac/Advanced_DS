package ctci.solutions.aritra;

/*
 * Question: Consider a simple data structure called BiNode, which has pointers to two other nodes. The data structure BiNode could be used to represent both a binary tree
 * (where node1 is the left node and node2 is the right node) or a double linked list(where node1 is the previous node and node2 is the next node). Implement a method to convert
 * a binary search tree (implemented with BiNode) into a doubly linked list. The values should be kept in order and the operation should be performed in place
 * (that is, on the original data structure).
 * 
 * Solution: Instead of returning the head and tail of the linked list with NodePair, we can return just the head and then we can use the head to find the tail of the linked list.
 */

public class BiNode {
	public BiNode node1;
	public BiNode node2;
	public int data; 
	public BiNode(int d) {
		data = d;
	}
	
	BiNode convert(BiNode root){
		if(root == null)
			return null;
		BiNode part1 = convert(root.node1);
		BiNode part2 = convert(root.node2);
		
		if(part1 != null){
			concat(getTail(part1), root);
		}
		
		if(part2 != null){
			concat(root,part2);
		}
		
		return part1 == null ? root : part1;
	}
	
	public static BiNode getTail(BiNode node){
		if(node == null)
			return null;
		while(node.node2 != null){
			node = node.node2;
		}
		return node;
	}
	
	public static void concat(BiNode x, BiNode y){
		x.node2 = y;
		y.node1 = x;
	}
}
