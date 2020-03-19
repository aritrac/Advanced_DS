package ctci.solutions.aritra;

/*
 * Question: T1 and T2 are very large binary trees, with T1 much bigger than T2. Create an algorithm to determine if T2 is a subtree of T1
 * A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2. That is, if you cut off the tree at node n, 
 * the two trees would be identical
 * 
 * Solution: To find out we will do a preorder traversal of T2 and T1. If T2's string is in T1 then T2 is a subtree of T1. We will store 'X' wherever we find null in T2 traversal
 * to get past the issue of getting same string for different tree ordering. So,
 * If T2's pre-order traversal is a substring of T1's preorder traversal, then T2's root element must be found in T1. If we do a pre-order
 * traversal from this element in T1, we will follow an identical path to T2's traversal. Therefore, T2 is a subtree of T1
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
		StringBuilder string1 = new StringBuilder();
		StringBuilder string2 = new StringBuilder();
		
		getOrderString(t1,string1);
		getOrderString(t2,string2);
		
		return string1.indexOf(string2.toString()) != -1;
	}
	
	void getOrderString(TreeNode node, StringBuilder sb){
		if(node == null){
			sb.append("X");	//Add null indicator
			return;
		}
		sb.append(node.data + " ");	//Add root
		getOrderString(node.left, sb);	//Add left
		getOrderString(node.right, sb); //Add right
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