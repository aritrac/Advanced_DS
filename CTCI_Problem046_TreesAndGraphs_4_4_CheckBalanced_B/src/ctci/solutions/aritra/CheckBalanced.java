package ctci.solutions.aritra;

/*
 * Question: Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is defined to be a tree such that heights of the two
 * subtrees of any node never differ by more than one
 * 
 * Solution: This improved algorithm works by checking the height of each subtree as we recurse down from the root. On each node, we recursively get the heights of the left 
 * and right subtrees through the checkHeight method. If the subtree is balanced, then checkHeight will return the actual height of the subtree. If the subtree is not balanced,
 * then checkHeight will return an error code. We will immediately break and return an error code from the current call.
 */

public class CheckBalanced {
	public static void main(String[] args) {
		//Creating an unbalanced tree
		TreeNode A = new TreeNode("A");
		TreeNode B = new TreeNode("B");
		TreeNode C = new TreeNode("C");
		TreeNode D = new TreeNode("D");
		TreeNode E = new TreeNode("E");
		TreeNode F = new TreeNode("F");
		TreeNode G = new TreeNode("G");
		TreeNode H = new TreeNode("H");
		TreeNode I = new TreeNode("I");
		
		A.left = B;
		A.right = C;
		B.left = D;
		B.right = E;
		E.left = H;
		H.right = I;
		C.left = F;
		C.right = G;
		
		System.out.println("Is the tree above balanced? " + new CheckBalanced().isBalanced(A));
		
		//Creating a balanced tree

		A.left = B;
		A.right = C;
		B.left = D;
		B.right = E;
		E.left = null;
		C.left = E;
		C.right = G;
		
		System.out.println("Is the tree above balanced? " + new CheckBalanced().isBalanced(A));
	}
	
	int checkHeight(TreeNode root){
		if(root == null)
			return -1;
		
		int leftHeight = checkHeight(root.left);
		if(leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE; //Pass error up
		
		int rightHeight = checkHeight(root.right);
		if(rightHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE; //Pass error up
		int heightDiff = leftHeight - rightHeight;
		if(Math.abs(heightDiff) > 1){
			return Integer.MIN_VALUE; //Found error -> pass it back
		}else{
			return Math.max(leftHeight, rightHeight) + 1;
		}
	}
	
	boolean isBalanced(TreeNode root){
		return checkHeight(root) != Integer.MIN_VALUE;
	}
}

class TreeNode{
	String data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(String data){
		this.data = data;
	}
}
