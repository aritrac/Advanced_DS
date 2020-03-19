package ctci.soutions.aritra;

/*
 * Question: Given an NxN matrix of positive and negative integers, write code to find the submatrix with the largest possible sum.
 * 
 * Solution: Notice that the earlier solution is made slower by a factor of O(N^2) simply because computing the sum of a matrix is so slow. We can actually reduce the compute time to O(1).
 * Consider the rectangle in MatrixSubMatrix.png.
 * 
 * Suppose we knew the following values:
 * 		ValD = area(point(0,0) -> point(x2,y2))
 * 		ValC = area(point(0,0) -> point(x2,y1))
 * 		ValB = area(point(0,0) -> point(x1,y2))
 * 		ValA = area(point(0,0) -> point(x1,y1))
 * 
 * Each Val* starts at the origin and ends at the bottom right corner of a subrectangle.
 * With these values, we know the following:
 * 		area(D) = ValD - area(A union C) - area(A union B) + area(A)
 * 
 * Or, written another way
 * 		area(D) = ValD - ValB - ValC + ValA
 * We can efficiently compute these values for all points in the matrix by using similar logic.
 * 
 * 		Val(x,y) = Val(x-1, y) + Val(y-1, x) - Val(x-1, y-1) + M[x][y]
 * 
 * We can precompute all such values and then efficiently find the maximum submatrix
 * 
 * The following code implements the algorithm
 * 
 */

public class MaxSubMatrix {
	Submatrix getMaxMatrix(int[][] matrix){
		Submatrix best = null;
		int rowCount = matrix.length;
		int columnCount = matrix[0].length;
		int[][] sumThrough = precomputeSums(matrix);
		
		for(int row1 = 0; row1 < rowCount; row1++){
			for(int row2 = row1; row2 < rowCount; row2++){
				for(int col1 = 0; col1 < columnCount; col1++){
					for(int col2 = col1; col2 < columnCount; col2++){
						int sum = sum(sumThrough, row1, col1, row2, col2);
						if(best == null || best.getSum() < sum){
							best = new Submatrix(row1, col1, row2, col2, sum);
						}
					}
				}
			}
		}
		return best;
	}
	
	int[][] precomputeSums(int[][] matrix){
		int[][] sumThrough = new int[matrix.length][matrix[0].length];
		for(int r = 0; r < matrix.length; r++){
			for(int c = 0; c < matrix[0].length; c++){
				int left = c > 0 ? sumThrough[r][c-1] : 0;
				int top = r > 0? sumThrough[r-1][c] : 0;
				int overlap = r > 0 && c > 0 ? sumThrough[r-1][c-1] : 0;
				sumThrough[r][c] = left + top - overlap + matrix[r][c];
			}
		}
		return sumThrough;
	}
	
	int sum(int[][] sumThrough, int r1, int c1, int r2, int c2){
		int topAndLeft = r1 > 0 && c1 > 0 ? sumThrough[r1 - 1][c1 - 1] : 0;
		int left = c1 > 0? sumThrough[r2][c1 - 1] : 0;
		int top = r1 > 0 ? sumThrough[r1 - 1][c2] : 0;
		int full = sumThrough[r2][c2];
		return full - left - top + topAndLeft;
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
