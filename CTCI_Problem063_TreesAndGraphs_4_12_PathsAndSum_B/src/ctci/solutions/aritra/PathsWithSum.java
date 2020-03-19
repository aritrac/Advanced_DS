package ctci.solutions.aritra;

import java.util.HashMap;

/*
 * Question: You are given a binary tree in which each node contains an integer value (which might be positive or negative). Design an algorithm
 * to count the number of paths that sum to a given value. The path does not need to start or end at the root or leaf, but it must go downwards
 * (travelling only from parent nodes to child nodes)
 * 
 * Solution: We traverse through the tree using DFS. As we visit each node:
 * 1)Track its running sum. We'll take this in as a parameter and immediately increment it by node.value
 * 2)Look up running sum - targetSum in the hashTable. The value there indicates the total number. Set totalPaths to this value
 * 3)If runningSum == targetSum then there's one additional path that starts at the root. Increment totalPaths
 * 4)Add runninSum to the hash table (incrementing the value if it's already there)
 * 5)Recurse left and right, counting the number of paths with sum targetSum
 * 6)After we're done recursing left and right, decrement the value of runningSum in the hashTable. This is essentially
 * backing out of our work, it reverses the changes to the hash table so that other nodes don't use it (since we are now done with the node)
 * 
 * Despite the complexity of deriving this algorithm, the code to implement this is relatively simple
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
		return countPathsWithSum(root, targetSum, 0, new HashMap<Integer,Integer>());
	}
	
	int countPathsWithSum(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount){
		if(node == null)
			return 0; //Base Case
		//Count paths with sum ending at the current node
		runningSum += node.data;
		int sum = runningSum - targetSum;
		int totalPaths = 0;
		if(pathCount.get(sum) != null){
			totalPaths = pathCount.get(sum);
		}
		
		//If runningSum equals targetSum, then one additional path starts at root. Add in this path
		if(runningSum == targetSum){
			totalPaths++;
		}
		
		//Increment path count, recurse and then decrement pathCount
		incrementHashTable(pathCount,runningSum,1); //Increment pathCount
		totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
		totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);
		
		incrementHashTable(pathCount, runningSum, -1); //Decrement pathCount
		return totalPaths;
	}
	
	void incrementHashTable(HashMap<Integer,Integer> hashTable, int key, int delta){
		int newCount = delta;
		if(hashTable.get(key) != null){
			newCount += hashTable.get(key);
		}
		
		if(newCount == 0){ //Remove when zero to reduce space usage
			hashTable.remove(key);
		}else{
			hashTable.put(key, newCount);
		}
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
