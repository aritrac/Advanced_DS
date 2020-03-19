package ctci.solutions.aritra;

/*
 * Question: Given an NxN matrix of positive and negative integers, write code to find the submatrix with the largest possible sum.
 * 
 * Solution: Like many maximizing problems, this problem has a straightforward brute force solution. This solution simply iterates through all possible submatrices, computes the sum
 * and finds the largest.
 * 
 * To iterate through all possible submatrices (with no duplicates), we simply need to iterate through all ordered pairs of rows and then all ordered pairs of columns.
 */

public class MaxSubMatrix {

	Submatrix getMaxMatrix(int[][] matrix){
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		Submatrix best = null;
		for(int row1 = 0; row1 < rowCount; row1++){
			for(int row2 = row1; row2 < rowCount; row2++){
				for(int col1 = 0; col1 < columnCount; col1++){
					for(int col2 = col1; col2 < columnCount; col2++){
						int sum = sum(matrix, row1, col1, row2, col2);
						if(best == null || best.getSum() < sum){
							best = new Submatrix(row1, col1, row2, col2, sum);
						}
					}
				}
			}
		}
		return best;
	}
	
	int sum(int[][] matrix, int row1, int col1, int row2, int col2){
		int sum = 0;
		for(int r = row1; r <= row2; r++){
			for(int c = col1; c <= col2; c++){
				sum += matrix[r][c];
			}
		}
		return sum;
	}
}

class Submatrix{
	private int row1, row2, col1, col2, sum;
	public Submatrix(int r1, int c1, int r2, int c2, int sm){
		row1 = r1;
		col1 = c1;
		row2 = r2;
		col2 = c2;
		sum = sm;
	}
	
	public int getSum(){
		return sum;
	}
}
