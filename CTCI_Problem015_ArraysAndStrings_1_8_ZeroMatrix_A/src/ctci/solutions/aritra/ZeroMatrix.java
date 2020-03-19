package ctci.solutions.aritra;

/**
 * Question: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
 * 
 * Solution: At first glance, this problem seems easy: just iterate through the matrix and every time we see a cell with value zero,
 * set its row and column to 0. There's one problem with that solution though: when we come across other cells in that row or column, we'll see
 * the zeros and change their row and column to zero. Pretty soon, our entire matrix will be set to zeroes.
 * 
 * One way around this is to keep a second matrix which flags the zero locations. We would then do a second pass through the matrix to set the
 * zeroes. This would take O(MN) space.
 * 
 * Do we really need O(MN) space? No, since we're going to set the entire row and column to zero, we don't need to track that it was
 * exactly cell[2][4] (row2, column4). We only need to know that row 2 has a zero somewhere, and column 4 has a zero somewhere.
 * We'll set the entire row and column to zero anyway, so why should we keep track of the exact location of the zero?
 * 
 * The code below implements this algorithm. We use two arrays to keep track of all the rows with zeros and all the columns with zeroes. We then
 * nullify rows and columns based on the values in these arrays.
 *
 */
public class ZeroMatrix {
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3,0,5},
						  {0,6,4,3,8},
						  {6,6,0,4,3},
						  {5,4,3,2,1},
						  {9,9,1,2,3}};
		System.out.println("Before");
		displayMatrix(matrix);
		setZeroes(matrix);
		System.out.println("After");
		displayMatrix(matrix);
				
	}
	
	static void displayMatrix(int[][] matrix){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void setZeroes(int[][] matrix){
		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];
		
		//Store the row and column index with value 0
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if(matrix[i][j] == 0){
					row[i] = true;
					column[j] = true;
				}
			}
		}
		
		//Nullify rows
		for(int i = 0; i < row.length; i++){
			if(row[i])
				nullifyRow(matrix,i);
		}
		//Nullify columns
		for(int j = 0; j < column.length; j++){
			if(column[j])
				nullifyColumn(matrix,j);
		}
	}
	
	static void nullifyRow(int[][] matrix, int row){
		for(int j = 0; j < matrix[0].length; j++){
			matrix[row][j] = 0;
		}
	}
	
	static void nullifyColumn(int[][] matrix, int col){
		for(int i = 0; i < matrix.length; i++){
			matrix[i][col] = 0;
		}
	}
}
