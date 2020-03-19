package ctci.solutions.aritra;

/**
 * Question:Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.
 * Can you do this in place?
 * 
 * Solution:Because we are rotating the matrix by 90 degrees, the easiest way to do this is to implement the rotation in layers. We perform a
 * circular rotation on each layer, moving the top edge to the right edge, the right edge to the bottom edge, the bottom edge to the left edge,
 * and the left edge to the top edge. How do we perform this four-way edge swap? One option is to copy the top edge to an array,and then
 * move the left to the top, the bottom to the left, and so on. This requires O(N) memory, which is actually unnecessary. A better way to do this
 * is to implement the swap index by index. In this case, we do the following.
 * 
 * for i = 0 to n
 * 	temp = top[i];
 * top[i] = left[i];
 * left[i] = bottom[i];
 * bottom[i] = right[i];
 * right[i] = temp
 * 
 * We perform such a swap on each layer, starting from the outermost layer and working our way inwards. Alternatively, we could start from the
 * inner layer and work outwards
 * This algorithm is O(N^2), which is the best we can do since any algorithm must touch all N^2 elements.
 * 
 *
 */
public class RotateMatrix {
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,4},
						  {5,6,7,8},
						  {8,7,6,5},
						  {4,3,2,1}};
		System.out.println("Before:");
		displayMatrix(matrix);
		rotate(matrix);
		System.out.println("After:");
		displayMatrix(matrix);
				
	}
	
	static void displayMatrix(int[][] matrix){
		int n = matrix.length;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static boolean rotate(int[][] matrix){
		if(matrix.length == 0 || matrix.length != matrix[0].length)
			return false; //Not a square
		int n = matrix.length;
		
		for(int layer = 0; layer < n/2; layer++){
			int first = layer;
			int last = n - 1 - layer;
			for(int i = first; i < last;i++){
				int offset = i - first;
				int top = matrix[first][i]; //save top row element
				
				//left -> top
				matrix[first][i] = matrix[last-offset][first];
				
				//bottom -> left
				matrix[last-offset][first] = matrix[last][last - offset];
				
				//right -> bottom
				matrix[last][last - offset] = matrix[i][last];
				
				//top -> right
				matrix[i][last] = top; //right < saved top
			}
		}
		return true;
	}
}
