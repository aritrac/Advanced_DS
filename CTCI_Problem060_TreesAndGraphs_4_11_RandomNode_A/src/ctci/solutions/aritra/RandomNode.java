package ctci.solutions.aritra;

import java.util.Random;

/*
 * Question: You are implementing a binary search tree class from scratch, which , in addition to insert, find and delete, has a method getRandomNode()
 * which returns a random node from the tree. all nodes should be equally
 * likely to be chosen. Design and implement an algorithm for getRandomNode, and explain how you would implement the rest of the methods
 * 
 * Solution: We can start with the root. With what probability should we return the root? Since we have N nodes, we must return the root node with
 * 1/N probability.(In fact, we must return each node with 1/N probability. After all, we have N nodes and each must have equal probability.
 * The total must be 1 (100%), therefore each must have 1/N probability).
 * Since each node must have probability 1/N, the odds of picking something from the left must have probability
 * LEFT_SIZE * 1/N. This should be the odds of going left. Likewise, the odds of going right should be RIGHT_SIZE * 1/N
 * 
 * This means that each node must know the size of the nodes on the left and the size of the nodes on the right. Fortunately, our
 * interviewer has told us that we're building this tree class from scratch. It's easy to keep track of this size information on inserts
 * and deletes. We can just store a size variable in each node. Increment size on inserts and decrement it on deletes
 */

public class RandomNode {
	TreeNode root;
	
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
		System.out.println(rn.root.getRandomNode().data);
		System.out.println(rn.root.getRandomNode().data);
		System.out.println(rn.root.getRandomNode().data);
		System.out.println(rn.root.getRandomNode().data);
		System.out.println(rn.root.getRandomNode().data);
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
	
	public TreeNode getRandomNode(){
		int leftSize = left == null ? 0 : left.size();
		Random random = new Random();
		int index = random.nextInt(size);
		if(index < leftSize){
			return left.getRandomNode();
		}else if(index == leftSize){
			return this;
		}else{
			return right.getRandomNode();
		}
	}
}
