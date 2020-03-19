package ctci.solutions.aritra;

/*
 * Question: You are given a binary tree in which each node contains an integer value (which might be positive or negative). Design an algorithm
 * to count the number of paths that sum to a given value. The path does not need to start or end at the root or leaf, but it must go downwards
 * (travelling only from parent nodes to child nodes)
 * 
 * Solution: One option is the brute force approach. We look at all possible paths. To do this, we traverse to each node. At each node, we recursively
 * try all paths downwards, tracking the sum as we go. As soon as we hit our target sum, we increment the total.
 */

public class PathsWithSum {
	TreeNode root;
	
	public PathsWithSum(){
		root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(-2);
		root.left.right = new TreeNode(2);
		root.left.right.right = new TreeNode(1);
		root.right = new TreeNode(-3);
		root.right.right = new TreeNode(11);
	}
	
	public static void main(String[] args) {
		PathsWithSum pws = new PathsWithSum();
		System.out.println("No of paths with sum 8 = " + pws.countPathsWithSum(pws.root, 8) );
		System.out.println("No of paths with sum 18 = " + pws.countPathsWithSum(pws.root, 18) );
		System.out.println("No of paths with sum 11 = " + pws.countPathsWithSum(pws.root, 11) );
	}
	
	int countPathsWithSum(TreeNode root, int targetSum){
		if(root == null)
			return 0;
		//Count paths with sum starting from the root
		int pathsFromRoot = countPathsWithSumFromNode(root,targetSum, 0);
		
		//Try nodes on the left and right
		int pathsOnLeft = countPathsWithSum(root.left, targetSum);
		int pathsOnRight = countPathsWithSum(root.right, targetSum);
		
		return pathsFromRoot + pathsOnLeft + pathsOnRight;
	}
	
	//Returns the number of paths with this sum starting from this node
	int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum){
		if(node == null)
			return 0;
		
		currentSum += node.data;
		
		int totalPaths = 0;
		if(currentSum == targetSum){ //Found a path from the root
			totalPaths++;
		}
		
		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
		
		return totalPaths;
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
