package ctci.solutions.aritra;

/*
 * Question: Imagine you have a square matrix, where each cell (pixel) is either black or white. Design an algorithm to find the maximum subsequence such that all four borders are filled
 * with black pixels.
 * 
 * Solution: We know that the biggest possible square has a length of size N, and there is only one possible square of size NxN. We can easily check for that square and return if we find it.
 * If we do not find a square of size NxN, we can try the next best thing: (N-1) x (N-1). We iterate through all squares of this size and return the first one we find. We then do the same for N - 2,
 * N - 3 and so on. Since we are searching progressively smaller squares, we know that the first square we find is the biggest.
 */

public class MaxSquareMatrix {
	
	//Starts here
	Subsquare findSquare(int[][] matrix){
		for(int i = matrix.length; i >= 1; i--){
			Subsquare square = findSquareWithSize(matrix, i);
			if(square != null)
				return square;
		}
		return null;
	}
	
	Subsquare findSquareWithSize(int[][] matrix, int squareSize){
		//On an edge of length N, there are (N - squareSize + 1) square of length squareSize.
		int count = matrix.length - squareSize + 1;
		
		//Iterate through all squares with side length squareSize.
		for(int row = 0; row < count; row++){
			for(int col = 0; col < count; col++){
				if(isSquare(matrix, row, col, squareSize)){
					return new Subsquare(row, col, squareSize);
				}
			}
		}
		return null;
	}
	
	//Checking if the border of the square is all black denoted by 0, 1 means it is white
	boolean isSquare(int[][] matrix, int row, int col, int size){
		//Check top and bottom border.
		for(int j = 0; j < size; j++){
			if(matrix[row][col + j] == 1){
				return false;
			}
			if(matrix[row+size-1][col+j] == 1){
				return false;
			}
		}
		
		//Check left and right border
		for(int i = 1; i < size - 1; i++){
			if(matrix[row + i][col] == 1){
				return false;
			}
			if(matrix[row + i][col + size - 1] == 1){
				return false;
			}
		}
		return true;
	}
}

class Subsquare {
	public int row, column, size;
	public Subsquare(int r, int c, int sz) {
		row = r;
		column = c;
		size = sz;
	}
	
	public void print() {
		System.out.println("(" + row + ", " + column + ", " + size + ")");
	}
}
