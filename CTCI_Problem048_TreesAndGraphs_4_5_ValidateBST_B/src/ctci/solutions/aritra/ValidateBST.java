package ctci.solutions.aritra;

/*
 * Question: Implement a function to check if a binary tree is a binary search tree
 * 
 * Solution: Our first thought  might be to do an in-order traversal, copy the elements to an array, and then check to see if the array is sorted.
 * This solution takes up a bit of extra memory, but it works - mostly. The only problem  is that it can't handle duplicate values in the tree properly.
 * If we assume that the tree cannot have duplicate values, then this approach works.
 * 
 * Note that it is necessary to keep track of the logical end of the array, since it would be allocated to hold all elements. When we examine this solution, we find
 * that the array is not actually necessary. We never use it other than to compare an element to the previous element. So why not just track the last element we saw
 * and compare it as we go.
 */

public class ValidateBST {
	Integer last_printed = null;
	
	public static void main(String[] args) {
		//A valid Binary Search Tree
		TreeNode A = new TreeNode(4);
		TreeNode B = new TreeNode(2);
		TreeNode C = new TreeNode(6);
		TreeNode D = new TreeNode(1);
		TreeNode E = new TreeNode(3);
		TreeNode F = new TreeNode(5);
		TreeNode G = new TreeNode(7);
		
		A.left = B;
		A.right = C;
		
		B.left = D;
		B.right = E;
		
		C.left = F;
		C.right = G;
		
		System.out.println("Is the above binary tree a binary search tree? " + new ValidateBST().checkBST(A));
		
		//An invalid Binary Search Tree
		B.data = 5;
		
		System.out.println("Is the above binary tree a binary search tree? " + new ValidateBST().checkBST(A));
	}
	
	public boolean checkBST(TreeNode n){
		if(n == null)
			return true;
		
		//Check/recurse left
		if(!checkBST(n.left))
			return false;
		
		//Check current
		if(last_printed != null && n.data <= last_printed){
			return false;
		}
		
		last_printed = n.data;
		
		//Check/recurse right
		if(!checkBST(n.right))
			return false;
		
		return true; //All good
	}
}

class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int data){
		this.data = data;
	}
}