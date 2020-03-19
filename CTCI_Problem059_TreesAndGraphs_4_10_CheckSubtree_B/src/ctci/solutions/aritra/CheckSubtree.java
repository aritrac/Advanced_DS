package ctci.solutions.aritra;

/*
 * Question: T1 and T2 are very large binary trees, with T1 much bigger than T2. Create an algorithm to determine if T2 is a subtree of T1
 * A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2. That is, if you cut off the tree at node n, 
 * the two trees would be identical
 * 
 * Solution: An alternative approach is to search through the larger tree T1. Each time a node in T1 matches the root of T2, call matchTree.
 * The matchTree method will compare the two subtrees to see if they are identical. Even if the root is identical, we exit matchTree when we find a difference
 * between T1 and T2. We therefore probably do not actually look at m nodes on each call of matchTree. The code below implements the same
 */

public class CheckSubtree {
	TreeNode fifty;
	TreeNode twenty;
	TreeNode sixty;
	TreeNode ten;
	TreeNode twentyfive;
	TreeNode seventy;
	TreeNode five;
	TreeNode fifteen;
	TreeNode sixtyfive;
	TreeNode eighty;
	
	public CheckSubtree(){
		fifty = new TreeNode(50);
		twenty = new TreeNode(20);
		sixty = new TreeNode(60);
		ten = new TreeNode(10);
		twentyfive = new TreeNode(25);
		seventy = new TreeNode(70);
		five = new TreeNode(5);
		fifteen = new TreeNode(15);
		sixtyfive = new TreeNode(65);
		eighty = new TreeNode(80);
		
		fifty.left = twenty;
		fifty.right = sixty;
		
		twenty.left = ten;
		twenty.right = twentyfive;
		
		sixty.right = seventy;
		
		ten.left = five;
		ten.right = fifteen;
		
		seventy.left = sixtyfive;
		seventy.right = eighty;
	}
	
	public static void main(String[] args) {
		CheckSubtree cs = new CheckSubtree();
		
		System.out.println("Does 20 contains 70?" + cs.containsTree(cs.twenty, cs.seventy));
		
		System.out.println("Does 60 contains 70?" + cs.containsTree(cs.sixty, cs.seventy));
	}
	
	boolean containsTree(TreeNode t1, TreeNode t2){
		if(t2 == null)
			return true;	//The empty tree is always a subtree
		return subTree(t1,t2);
	}
	
	boolean subTree(TreeNode r1, TreeNode r2){
		if(r1 == null){
			return false; //big tree empty and subtree still not found
		}else if(r1.data == r2.data && matchTree(r1,r2)){
			return true;
		}
		return subTree(r1.left, r2) || subTree(r1.right, r2);
	}
	
	boolean matchTree(TreeNode r1, TreeNode r2){
		if(r1 == null && r2 == null){
			return true; //nothing left in the subtree
		}else if(r1 == null || r2 == null){
			return false; //exactly one tree is empty, therefore trees don't match
		}else if(r1.data != r2.data){
			return false; //data doesn't match
		}else{
			return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
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
