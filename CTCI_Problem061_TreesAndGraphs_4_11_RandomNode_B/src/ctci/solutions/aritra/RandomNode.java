package ctci.solutions.aritra;

import java.util.Random;

/*
 * Question: You are implementing a binary search tree class from scratch, which , in addition to insert, find and delete, has a method getRandomNode()
 * which returns a random node from the tree. all nodes should be equally
 * likely to be chosen. Design and implement an algorithm for getRandomNode, and explain how you would implement the rest of the methods
 * 
 * Solution: Random number calls are expensive. If we'd like, we can reduce the number of random number calls substantially. Image we called getRandomNode on the tree below,
 * and then traversed left.We traversed left because we picked a number between 0 and 5 inclusive(on previous approach). When we traverse left, we again pick a random
 * number between 0 and 5. Why re-pick? The first number will work just fine.
 * But what if we went right instead? We have a number between 7 and 8 inclusive but we would need a number between 0 and 1 inclusive. That's easy to fix: just subtract out LEFT_SIZE + 1
 * Another way to think about what we're doing is that the intial random number call indicates which node (i) to return, and then we're locating the ith node in an in-order traversal.
 * Subtracting LEFT_SIZE + 1 from i reflects that, when we go right, we skip over LEFT_SIZE + 1 nodes in the in-order traversal.
 */

public class RandomNode {
	TreeNode root;
	
	public int size(){
		return root == null ? 0 : root.size();
	}
	
	public TreeNode getRandomNode(){
		if(root == null) 
			return null;
		Random random = new Random();
		int i = random.nextInt(size());
		return root.getIthNode(i);
	}
	
	public void insertInOrder(int value){
		if(root == null){
			root = new TreeNode(value);
		}else{
			root.insertInOrder(value);
		}
	}
	
	public RandomNode(){
		root = new TreeNode(50);
		root.insertInOrder(20);
		root.insertInOrder(60);
		root.insertInOrder(10);
		root.insertInOrder(25);
		root.insertInOrder(5);
		root.insertInOrder(15);
		root.insertInOrder(70);
		root.insertInOrder(65);
		root.insertInOrder(80);
	}
	public static void main(String[] args) {
		RandomNode rn = new RandomNode();
		System.out.println(rn.getRandomNode().data);
		System.out.println(rn.getRandomNode().data);
		System.out.println(rn.getRandomNode().data);
		System.out.println(rn.getRandomNode().data);
		System.out.println(rn.getRandomNode().data);
	}
}

class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;
	private int size = 0;
	
	public TreeNode(int data){
		this.data = data;
		size = 1;
	}
	
	public int size(){
		return size;
	}
	public int data(){
		return data;
	}
	public TreeNode find(int d){
		if(d == data){
			return this;
		}else if(d <= data){
			return left != null ? left.find(d) : null;
		}else if(d > data){
			return right != null ? right.find(d) : null;
		}
		return null;
	}
	
	public void insertInOrder(int d){
		if(d <= data){
			if(left == null){
				left = new TreeNode(d);
			}else{
				left.insertInOrder(d);
			}
		}else{
			if(right == null){
				right = new TreeNode(d);
			}else{
				right.insertInOrder(d);
			}
		}
		size++;
	}
	
	public TreeNode getIthNode(int i){
		int leftSize = left == null ? 0 : left.size();
		if(i < leftSize){
			return left.getIthNode(i);
		}else if(i == leftSize){
			return this;
		}else{
			//Skipping over leftSize + 1 nodes, so subtract them
			return right.getIthNode(i - (leftSize + 1));
		}
	}
}
