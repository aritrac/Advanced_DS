package ctci.solutions.aritra;

/*
 * Question: Imagine you are reading in a stream of integer. Periodically, you wish to be able to look up the rank of a number x (the number of values less than or equal to x).
 * Implement the data strcuture and algorithms to support these operations. That is, implement the method track(int x), which is called when each number is generated,
 * and the method getRankOfNumber(int x), which returns the number of values less than or equal to x(not including x itself)
 * 
 * Example:
 * Stream (in order of apprearance): 5,1,4,4,5,9,7,13,3
 * getRankOfNumber(1) = 0
 * getRankOfNumber(3) = 1
 * getRankOfNumber(4) = 3
 * 
 * Solution: 
 * A relatively easy way to implement this would be to have an array that holds all the elements in sorted order. When a new element comes in, we would need to shift the other
 * elements to make room. Implementing getRankOfNumber would be quite efficient, though. We would simply perform a binary search for n, and return the index.
 * However, this is very inefficient ofr inserting elements(that is, the track(int x) function). We need a data structure which is good at keeping relative ordering, as well as
 * updating when we insert new elements. A binary search tree can do just that.
 * Instead of inserting elements into an array, we insert elements into a binary search tree. The method track(int x) will run in O(log n) time, where n is the size
 * of the tree (provided, of course, that the tree is balanced).
 * To find the rank of a number, we could do an in-order traversal, keeping a counter as we traverse. The goal is that, by the time we find x, counter will equal the number
 * of elements less than x.
 * As long as we're moving left during searching for x, the counter won't change. Why? Because all the values we're skipping on the right side are greater than x. After all
 * the very smallest element (with rank of 1) is the leftmost node.
 * When we move to the right though, we skip over a bunch of elements on the left. All of these elements are less than x, so we'll need to increment counter by the number of elements
 * in the left subtree.
 * Rather than counting the size of the left subtree (which would be inefficient), we can track this information as we add new elements to the tree.
 * 
 * Let's walk through an example on the following tree. In the below wxample, the value in parentheses indicates the number of nodes in the left subtree (or, in other words,
 * the rank of the node relative to its subtree)
 * 													20(4)
 * 										15(3)					25(2)
 * 								10(1)					23(0)
 * 							5(0)	13(0)					24(0)
 * Suppose we want to find the rank of 24 in the tree above. We would compare 24 with the root, 20, and find that 24 must reside on the right. The root has 4 nodes in its left subtree,
 * and when we include the root itself, this gives us five total nodes smaller than 24. We set counter to 5.
 * Then, we compare 24 with node 25 and find that 24 must be on the left. The value of the counter does not update, since we're not 'passing over' any smaller nodes. the value
 * of the counter is still 5.
 * Next, we compare 24 with node 23, and find that 24 must be on the right. Counter gets incremented by just 1 (to 6), since 23 has no left nodes.
 * Finally, we find 24 and we return counter:6
 * 
 * Recursively the algorithm is as follows
 */

public class RankFromStream {
	RankNode root = null;
	
	public static void main(String[] args) {
		RankFromStream rfs = new RankFromStream();
		rfs.track(20);
		rfs.track(15);
		rfs.track(10);
		rfs.track(5);
		rfs.track(13);
		rfs.track(25);
		rfs.track(23);
		rfs.track(24);
		
		System.out.println("Rank of 20 = " + rfs.root.getRank(20));
		System.out.println("Rank of 24 = " + rfs.root.getRank(24));
		System.out.println("Rank of 10 = " + rfs.root.getRank(10));
		
	}
	
	void track(int number){
		if(root == null){
			root = new RankNode(number);
		}else{
			root.insert(number);
		}
	}
}

class RankNode{
	public int left_size = 0;
	public RankNode left, right;
	public int data = 0;
	
	public RankNode(int d){
		data = d;
	}
	
	public void insert(int d){
		if(d <= data){
			if(left != null)
				left.insert(d);
			else
				left = new RankNode(d);
			left_size++;
		}else{
			if(right != null)
				right.insert(d);
			else
				right = new RankNode(d);
		}
	}
	
	public int getRank(int d){
		if(d == data){
			return left_size;
		}else if(d < data){
			if(left == null)
				return -1;
			else
				return left.getRank(d);
		}else{
			int right_rank = right == null ? -1 : right.getRank(d);
			if(right_rank == -1)
				return -1;
			else
				return left_size + 1 + right_rank;
		}
	}
}
