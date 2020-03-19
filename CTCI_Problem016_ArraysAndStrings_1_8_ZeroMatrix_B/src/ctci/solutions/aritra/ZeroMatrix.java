package ctci.solutions.aritra;

/**
 * Question: Write an algorithm such that if an element in an MxN matrix is 0,
 * its entire row and column are set to 0.
 * 
 * Solution: To make the previous approach of the same problem somewhat more
 * space efficient, we could use a bit vector instead of a boolean array. It
 * would still be O(N) space.
 * 
 * We can reduce the space to O(1) by using the first row as a replacement for
 * the row array and the first column as a replacement for the column array.
 * This works as follows:
 * 
 * 1)Check if the first row and the first column have any zeroes, and set
 * variables rowHasZero and columnHasZero.(We'll nullify the first row and the
 * first column later, if necessary) 
 * 2)Iterate through the rest of the matrix,
 * setting matrix[i][0] and matrix[0][j] to zero whenever there's a zero in
 * matrix[i][j] 
 * 3)Iterate through the rest of the matrix, nullifying row i if
 * there's a zero in matrix[i][0] 
 * 4)Iterate through the rest of the matrix,
 * nullifying column j if there's a zero in matrix[0][j] 
 * 5)Nullify the first row
 * and first column if necessary (based on values from Step 1)
 */

public class ZeroMatrix {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 0, 5 }, { 0, 6, 4, 3, 8 },
				{ 6, 6, 0, 4, 3 }, { 5, 4, 3, 2, 1 }, { 9, 9, 1, 2, 3 } };
		System.out.println("Before");
		displayMatrix(matrix);
		setZeroes(matrix);
		System.out.println("After");
		displayMatrix(matrix);

	}

	static void displayMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void setZeroes(int[][] matrix) {
		boolean rowHasZero = false;
		boolean colHasZero = false;

		// Check if first row has a zero
		for (int j = 0; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				rowHasZero = true;
				break;
			}
		}

		// Check if the first column has a zero
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				colHasZero = true;
				break;
			}
		}

		// Check for zeroes in the rest of the array
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		// Nullify rows based on values in the first column
		for (int i = 1; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				nullifyRow(matrix, i);
			}
		}

		// Nullify columns based on values in the first row
		for (int j = 1; j < matrix[0].length; j++) {
			if (matrix[0][j] == 0) {
				nullifyColumn(matrix, j);
			}
		}

		// Nullify first row and column as necessary
		if (rowHasZero)
			nullifyRow(matrix, 0);
		if (colHasZero)
			nullifyColumn(matrix, 0);
	}

	static void nullifyRow(int[][] matrix, int row) {
		for (int j = 0; j < matrix[0].length; j++) {
			matrix[row][j] = 0;
		}
	}

	static void nullifyColumn(int[][] matrix, int col) {
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][col] = 0;
		}
	}
}
