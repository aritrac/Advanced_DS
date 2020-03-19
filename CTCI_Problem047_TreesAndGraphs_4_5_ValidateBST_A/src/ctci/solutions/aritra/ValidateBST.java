package ctci.solutions.aritra;

/*
 * Question: Implement a function to check if a binary tree is a binary search tree
 * 
 * Solution: Our first thought  might be to do an in-order traversal, copy the elements to an array, and then check to see if the array is sorted.
 * This solution takes up a it of extra memory, but it works - mostly. The only problem  is that it can't handle duplicate values in the tree properly.
 * If we assume that the tree cannot have duplicate values, then this approach works.
 */

public class ValidateBST {
	int index = 0;
	
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
	
	public void copyBST(TreeNode root, int[] array){
		if(root == null) return;
		
		copyBST(root.left, array);
		array[index] = root.data;
		index++;
		copyBST(root.right,array);
	}
	
	public boolean checkBST(TreeNode root){
		int[] array = new int[7];
		copyBST(root,array);
		for(int i = 1; i < array.length; i++){
			if(array[i] <= array[i - 1])
				return false;
		}
		return true;
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