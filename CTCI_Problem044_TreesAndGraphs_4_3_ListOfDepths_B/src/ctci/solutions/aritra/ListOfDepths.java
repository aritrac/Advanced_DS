package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * Question: Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth(i.e. if you have a tree with depth D, you'll have D linked lists)
 * 
 * Solution: Though we might think at first glance that this problem requires a level-by-level traversal, this isn't actually necessary. We can traverse the graph
 * anyway that we'd like, provided we know which level we're on as we do so.
 * 
 * Alternatively we can also implement a modification of breadth first search. With this implementation, we want to iterate through the root first, then level 2
 * and then level 3 and so on. With each level i, we will have already fully visited all nodes on level i - 1. This means that to get which nodes are on level i,
 * we can simply look at all children of the nodes of level i - 1
 * 
 * The code below implements this algorithm
 */

public class ListOfDepths {
	int[] arr;
	TreeNode root;
	ArrayList<LinkedList<TreeNode>> lists;
	
	
	ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		
		//Visit the root
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		if(root != null){
			current.add(root);
		}
		
		while(current.size() > 0){
			result.add(current); //Add previous level
			LinkedList<TreeNode> parents = current; //Go to next level
			current = new LinkedList<TreeNode>();
			
			for(TreeNode parent : parents){
				//Visit the children
				if(parent.left != null){
					current.add(parent.left);
				}
				if(parent.right != null){
					current.add(parent.right);
				}
			}
		}
		return result;
	}
	
	/**********************************************************/
	//TAKEN FROM PREVIOUS PROBLEM
	
	public void displayLists(){
		Iterator<LinkedList<TreeNode>> iter = lists.iterator();
		while(iter.hasNext()){
			LinkedList<TreeNode> list = iter.next();
			for(int i = 0; i < list.size(); i++){
				System.out.print(list.get(i).data + " ");
			}
			System.out.println();
		}
	}
	
	/**********************************************************/
	//TAKEN FROM PREVIOUS PROBLEM
	TreeNode createMinimalBST(int[] array){
		return createMinimalBST(array, 0, array.length - 1);
	}
	
	TreeNode createMinimalBST(int[] arr, int start, int end){
		if(end < start){
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid]);
		n.left = createMinimalBST(arr,start,mid - 1);
		n.right = createMinimalBST(arr,mid + 1,end);
		return n;
	}
	//TAKEN FROM PREVIOUS PROBLEM
	/*************************************************************/
	
	public static void main(String[] args) {
		ListOfDepths lod = new ListOfDepths();
		lod.arr = new int[]{1,2,3,4,5,6,7,8,9};
		
		lod.root = lod.createMinimalBST(lod.arr);
		lod.lists = lod.createLevelLinkedList(lod.root);
		lod.displayLists();
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
