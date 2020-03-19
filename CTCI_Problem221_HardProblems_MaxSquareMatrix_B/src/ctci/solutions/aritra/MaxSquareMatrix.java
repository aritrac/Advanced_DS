package ctci.solutions.aritra;

/*
 * Question: Imagine you have a square matrix, where each cell (pixel) is either black or white. Design an algorithm to find the maximum subsequence such that all four borders are filled
 * with black pixels.
 * 
 * Solution: Starting from the bottom right corner, we will work our way all the way to top left corner. For every cell we traverse we are going to keep a tab of how many zeroes are to the
 * right of the cell and how many zeroes are to the bottom of the cell. So for all the cells we are going to compute this data and keep it in the processed 2D array.
 * 
 * The algorithm to fill the processed array is as follows:
 * 
 * We iterate from right to left , bottom to top. At each cell we do the following computation:
 * 
 * if A[r][c] is white, 
 * 		zeroes right and zeroes below are 0
 * else
 * 		A[r][c].zeroesRight = A[r][c + 1].zeroesRight + 1
 *      A[r][c].zeroesBelow = A[r + 1][c].zeroesBelow + 1
 *      
 * Then when we want to know whether a square with top left coordinates of (r,c) and size of z, we just need to check the processed array of the same locations.
 * We will check if at (r,c)       number of zeroes to the right is z or more
 *                  at (r,c + z)   number of zeroes down is z or more
 *                  at (r,c)       number of zeroes down is z or more
 *                  at (r + z, c)  number of zeroes to the right is z or more.
 * 
 * If all the 4 conditions evaluate to true, that means we got our square.
 */

public class MaxSquareMatrix {
	//Starts here, by progressively decreasing the size of the square to be found
	Subsquare findSquare(int[][] matrix){
		SquareCell[][] processed = processSquare(matrix);
		for(int i = matrix.length; i >= 1; i--){
			Subsquare square = findSquareWithSize(processed, i);
			if(square != null)
				return square;
		}
		return null;
	}

	Subsquare findSquareWithSize(SquareCell[][] matrix, int squareSize){
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
	
	boolean isSquare(SquareCell[][] matrix, int row, int col, int sz){
		SquareCell topLeft = matrix[row][col];
		SquareCell topRight = matrix[row][col + sz - 1];
		SquareCell bottomLeft = matrix[row + sz - 1][col];
		
		//Check top, left, right, and bottom edges, respectively
		if(topLeft.zerosRight < sz || topLeft.zerosBelow < sz || topRight.zerosBelow < sz || bottomLeft.zerosRight < sz){
			return false;
		}
		return true;
	}
	
	/*
	 * Starting from the bottom right corner, we will work our way all the way to top left corner. For every cell we traverse we are going to keep a tab of how many zeroes are to the
	 * right of the cell and how many zeroes are to the bottom of the cell. So for all the cells we are going to compute this data and keep it in the processed 2D array.
	 */
	SquareCell[][] processSquare(int[][] matrix){
		SquareCell[][] processed = new SquareCell[matrix.length][matrix.length];
		
		for(int r = matrix.length - 1; r >= 0; r--){
			for(int c = matrix.length - 1; c >= 0; c--){
				int rightZeros = 0;
				int belowZeros = 0;
				//Only need to process if it's a black cell
				if(matrix[r][c] == 0){
					rightZeros++; //This is adding the + 1 in the above formula A[r][c].zeroesRight = A[r][c + 1].zeroesRight + 1
					belowZeros++; //This is adding the + 1 in the above formula A[r][c].zeroesBelow = A[r + 1][c].zeroesBelow + 1
					//next column over is on same row
					if(c + 1 < matrix.length){
						SquareCell previous = processed[r][c + 1];
						rightZeros += previous.zerosRight;
					}
					if(r + 1 < matrix.length){
						SquareCell previous = processed[r+1][c];
						belowZeros += previous.zerosBelow;
					}
				}
				processed[r][c] = new SquareCell(rightZeros, belowZeros);
			}
		}
		return processed;
	}
}

class SquareCell {
	public int zerosRight = 0;
	public int zerosBelow = 0;
	public SquareCell(int right, int below) {
		zerosRight = right;
		zerosBelow = below;
	}
	
	public void setZerosRight(int right) {
		zerosRight = right;
	}
	
	public void setZerosBelow(int below) {
		zerosBelow = below;
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
