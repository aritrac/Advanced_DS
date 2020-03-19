package ctci.solutions.aritra;

/*
 * Problem: Write an algorithm to find the next node of a given node in a binary search tree. You may assume that each node has a link to its parent.
 * 
 * Solution: The following is achieved with the pseudocode provided below
 * 
 * Node inorderSucc(Node n){
 * 	if(n has a right subtree){
 * 		return leftmost child of right subtree
 * 	}else{
 * 		while(n is a right child of n.parent){
 * 			n = n.parent; //Go up
 * 		}
 *      return n.parent; //Parent has not been traversed
 * 	}
 * }
 */

public class Successor {
	public static void main(String[] args) {
		
	}
	
	TreeNode inorderSucc(TreeNode n){
		if(n == null)
			return null;
		
		//Found right children -> return leftmost node of right subtree
		if(n.right != null){
			return leftMostChild(n.right);
		}else{
			TreeNode q = n;
			TreeNode x = q.parent;
			
			//Go up until we're on left instead of right
			while(x != null && x.left != q){
				q = x;
				x = x.parent;
			}
			return x;
		}
	}
	
	TreeNode leftMostChild(TreeNode n){
		if(n == null){
			return null;
		}
		while(n.left != null){
			n = n.left;
		}
		return n;
	}
}

class TreeNode{
	int data;
	TreeNode parent;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data){
		this.data = data;
	}
}
