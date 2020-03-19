package ctci.solutions.aritra;

/*
 * Question: Given an M x N matrix in which each row and each column is sorted in ascending order, write a method to find an element.
 * 
 * Solution: 
 * 
 * 15 20  40  85
 * 20 35  80  95
 * 30 55  95 105
 * 40 80 100 120
 * 
 * Suppose we are searching for the element 55. How can we identify where it is?
 * If we look at the start of the row or the start of a column, we can start to deduce the location. If the start of a column is greater than 55, we know that 55 can't be in that column, since
 * the start of the column is always the minimum element. Additionally we know that 55 can't be in any columns on the right, since the first element of each column must
 * increase in size from left to right. Therefore, if the start of the column is greater that the element X that we are searching for, we know that we need to move further to the left.
 * 
 * For rows, we use identical logic. If the start of a row is bigger than x, we know we need to move upwards.
 * 
 * Observe that we can also make a similar conclusion by looking at the ends of columns or rows. If the end of a column or row is less than x, then we know that we must
 * move down (for rows) or to the right (for columns) to find x. This is because the end is always the maximum element
 * 
 * We can bring these observations together into a solution. the observations are the following:
 * 1)If the start of a column is greater than x, then x is to the left of the column.
 * 2)If the end of a column is less than x, then x is to the right of the column
 * 3)If the start of a row is greater than x, then x is above that row.
 * 4)If the end of a row is less than x, then x is below that row.
 */

public class SortedMatrixSearch {
	public static void main(String[] args) {
		int[][] matrix = {
				{15,20,40,85},
				{20,35,80,95},
				{30,55,95,105},
				{40,80,100,120}
		};
		int elem = 55;
		System.out.println("Is 55 present in the matrix?" + new SortedMatrixSearch().findElement(matrix, elem));
	}
	
	boolean findElement(int[][] matrix, int elem){
		int row = 0;
		int col = matrix[0].length - 1;
		while(row < matrix.length && col >= 0){
			if(matrix[row][col] == elem){
				return true;
			}else if(matrix[row][col] > elem){
				col--;
			}else{
				row++;
			}
		}
		return false;
	}
}
