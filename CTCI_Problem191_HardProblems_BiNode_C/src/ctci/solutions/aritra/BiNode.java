package ctci.solutions.aritra;

/*
 * Question: Consider a simple data structure called BiNode, which has pointers to two other nodes. The data structure BiNode could be used to represent both a binary tree
 * (where node1 is the left node and node2 is the right node) or a double linked list(where node1 is the previous node and node2 is the next node). Implement a method to convert
 * a binary search tree (implemented with BiNode) into a doubly linked list. The values should be kept in order and the operation should be performed in place
 * (that is, on the original data structure).
 * 
 * Solution: We can build our third and final approach off the second one.
 * This approach requires returning the head and tail of the linked list with BiNode. We can do this by returning each list head of a circular linked list.
 * To get the tail, then we simply call head.node1
 */

public class BiNode {
	public BiNode node1;
	public BiNode node2;
	public int data;

	public BiNode(int d) {
		data = d;
	}

	BiNode convertToCircular(BiNode root) {
		if (root == null)
			return null;

		BiNode part1 = convertToCircular(root.node1);
		BiNode part3 = convertToCircular(root.node2);

		if (part1 == null && part3 == null) {
			root.node1 = root;
			root.node2 = root;
			return root;
		}
		BiNode tail3 = (part3 == null) ? null : part3.node1; // tail of part3
																// got by
																// accessing
																// node1 in a
																// circular list

		// Join left to root
		if (part1 == null) {
			concat(part3.node1, root);
		} else {
			concat(part1.node1, root);
		}

		// Join right to root
		if (part3 == null) {
			concat(root, part1);
		} else {
			concat(root, part3);
		}

		// Join right to left
		if (part1 != null && part3 != null) {
			concat(tail3, part1);
		}

		return part1 == null ? root : part1;
	}

	// Convert list to a circular linked list, then break the circular
	// connection
	BiNode convert(BiNode root) {
		BiNode head = convertToCircular(root);
		head.node1.node2 = null;
		head.node1 = null;
		return head;
	}

	public static void concat(BiNode x, BiNode y) {
		x.node2 = y;
		y.node1 = x;
	}
}
