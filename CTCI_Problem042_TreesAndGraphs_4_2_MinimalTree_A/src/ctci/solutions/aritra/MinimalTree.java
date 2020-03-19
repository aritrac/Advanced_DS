package ctci.solutions.aritra;

/*
 * Question: Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height
 * 
 * Solution: One way to implement this is to use a simple root.insertNode(int v) method which inserts the value v through a recursive process that starts with the root node.
 * This will indeed construct a tree with minimal height but it will not do so very efficiently. Each insertion will require traversing the tree, giving a total cost of O(nlogn)
 * to the tree.
 * 
 * Alternatively we can cut out the extra traversals by recursively using the createMinimalBST method. This method is passed just a subsection of the array and returns the root 
 * of a minimal tree for that array
 * 
 * The algorithm is as follows
 * 1)Insert into the tree the middle element of the array
 * 2)Insert (into the left subtree) the left subarray elements
 * 3)Insert (into the right subtree) the right subarray elements
 * 4)Recurse
 * 
 * The code below implements this algorithm
 */

public class MinimalTree {
	int[] arr;
	public static void main(String[] args) {
		MinimalTree mt = new MinimalTree();
		mt.arr = new int[]{1,2,3,4,5,6,7};
		TreeNode t = mt.createMinimalBST(mt.arr);
		mt.displayTree(0, t);
	}
	
	public void displayTree(int level,TreeNode t){
		if(t == null)
			return;
		System.out.println("Level " + level + " -> " + t.data);
		displayTree(level + 1,t.left);
		displayTree(level + 1,t.right);
	}
	
	TreeNode createMinimalBST(int[] array){
		return createMinimalBST(array, 0, array.length - 1);
	}
	
	TreeNode createMinimalBST(int[] arr, int start, int end){
		System.out.println("start = " + start + " end = " + end);
		if(end < start){
			System.out.println("Returning null");
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.left = createMinimalBST(arr,start,mid - 1);
		n.right = createMinimalBST(arr,mid + 1,end);
		return n;
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
