package ctci.solutions.aritra;

/*
 * Question: Implement a function to check if a binary tree is balanced. For the purposes of this question, a balanced tree is defined to be a tree such that heights of the two
 * subtrees of any node never differ by more than one
 * 
 * Solution: In this question, we've been fortunate enough to be told exactly what balanced means: that for each node, the two subtrees differ in height by no more than one.
 * We can implement a solution based on this definition. We can simply recurse through the entire tree, and for each node, compute the heights of each subtree
 */

public class CheckBalanced {
	public static void main(String[] args) {
		//Creating an unbalanced tree
		TreeNode A = new TreeNode("A");
		TreeNode B = new TreeNode("B");
		TreeNode C = new TreeNode("C");
		TreeNode D = new TreeNode("D");
		TreeNode E = new TreeNode("E");
		TreeNode G = new TreeNode("G");
		TreeNode H = new TreeNode("H");
		TreeNode I = new TreeNode("I");
		
		A.left = B;
		A.right = C;
		B.left = D;
		B.right = E;
		E.left = H;
		H.right = I;
		C.left = E;
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
	
	int getHeight(TreeNode root){
		if(root == null)
			return -1; //Base Case
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}
	
	boolean isBalanced(TreeNode root){
		if(root == null)
			return true; //Base Case
		int heightDiff = getHeight(root.left) - getHeight(root.right);
		if(Math.abs(heightDiff) > 1){
			return false;
		}else{
			return isBalanced(root.left) && isBalanced(root.right);
		}
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
