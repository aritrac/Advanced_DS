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
 * We can implement a simple modification of the pre-order traversal algorithm, where we pass in level + 1 to the next recursive call. The code
 * below provides an implementation using DFS.
 */

public class ListOfDepths {
	int[] arr;
	TreeNode root;
	ArrayList<LinkedList<TreeNode>> lists;
	
	public void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level){
		if(root == null)
			return; //base case
		LinkedList<TreeNode> list = null;
		if(lists.size() == level){ //Level not contained in list
			list = new LinkedList<TreeNode>();
			//Levels are always traversed in order. So if this is the first time we've visited level i, we must have seen levels 0 through i - 1.
			//We can therefore safely add the level at the end
			lists.add(list);
		}else{
			list = lists.get(level);
		}
		list.add(root);
		createLevelLinkedList(root.left, lists, level + 1);
		createLevelLinkedList(root.right, lists, level + 1);
	}
	
	public ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
		createLevelLinkedList(root,lists,0);
		return lists;
	}
	
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
