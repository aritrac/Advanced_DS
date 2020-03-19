package ctci.solutions.aritra;

/*
 * Question: Consider a simple data structure called BiNode, which has pointers to two other nodes. The data structure BiNode could be used to represent both a binary tree
 * (where node1 is the left node and node2 is the right node) or a double linked list(where node1 is the previous node and node2 is the next node). Implement a method to convert
 * a binary search tree (implemented with BiNode) into a doubly linked list. The values should be kept in order and the operation should be performed in place
 * (that is, on the original data structure).
 * 
 * Solution: We know that the left and right halves of the binary search tree form their own sub-parts of the linked list(that is, they appear consecutively in the linked list).
 * So if we recursively converted the left and right subtrees to a doubly linked list, we could build the final linked list from those parts by merging the different parts.
 * To actually implement this, we'll need to get the head and tail of a linked list. We can do this in several different ways.
 * 
 * The first and easier approach is to create a new data structure called NodePair which holds just the head and tail of a linked list. The convert method can then return 
 * something of type NodePair. The code below implements this approach.
 */

class NodePair{
	BinNode head;
	BinNode tail;
	
	public NodePair(BinNode head, BinNode tail){
		this.head = head;
		this.tail = tail;
	}
	
	public NodePair convert(BinNode root){
		if(root == null)
			return null;
		NodePair part1 = convert(root.node1);
		NodePair part2 = convert(root.node2);
		
		if(part1 != null){
			concat(part1.tail,root);
		}
		if(part2 != null){
			concat(root, part2.head);
		}
		return new NodePair(part1 == null? root : part1.head, part2 == null ? root : part2.tail);
	}
	
	public static void concat(BinNode x, BinNode y){
		x.node2 = y;
		y.node1 = x;
	}
}

class BinNode {
	public BinNode node1;
	public BinNode node2;
	public int data; 
	public BinNode(int d) {
		data = d;
	}
}
