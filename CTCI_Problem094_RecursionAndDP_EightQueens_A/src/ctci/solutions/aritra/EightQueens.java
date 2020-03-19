package ctci.solutions.aritra;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Question: We have eight queens which must be lined up on an 8x8 chess board such that none share the same row, column or diagonal. So we know
 * that each row and column and diagonal must be used exactly once.
 * 
 * Solution: Picture that queen that is placed last, which we assume is on row 8. On which cell on row 8 is this queen? There are eight possibilities, one for 
 * each column. So if we want to know all the valid ways of arranging 8 queens on an 8x8 chess board, it would be:
 * 
 * ways to arrange 8 queens on an 8x8 board = 
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,0) +
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,1) +
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,2) +
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,3) +
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,4) +
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,5) +
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,6) +
 * 		ways to arrange 8 queens on an 8x8 board with queen at (7,7) +
 * 
 * We can compute each one of these using a very similar approach
 * 
 * ways to arrange 8 queens on an 8x8 board with queen at (7,3) = 
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,0) +
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,1) +
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,2) +
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,3) +
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,4) +
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,5) +
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,6) +
 * 		ways to arrange 8 queens on an 8x8 board with queens at (7,3) and (6,7)
 * 
 * Note that we don't need to consider combinations with queens at (7,3) and (6,3), since there is a violation
 * of the requirement that every queen is in its row, column and diagonal
 * 
 * The code below implements this logic
 * 
 * Observe that since each row can only have one queen, we don't need to store our board as a full 8x8 matrix. We only need a single
 * array where column[r] = c indicates that row r has a queen at column c.
 */

public class EightQueens {
	int GRID_SIZE = 8;
	Integer[] columns = {0,0,0,0,0,0,0,0};
	ArrayList<Integer[]> results;
	
	public EightQueens(){
		results = new ArrayList<Integer[]>();
	}
	
	void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results){
		if(row == GRID_SIZE){ //Found valid placement
			results.add(columns.clone());
		}else{
			for(int col = 0; col < GRID_SIZE; col++){
				if(checkValid(columns, row, col)){
					columns[row] = col; //Place queen
					placeQueens(row + 1, columns, results);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		EightQueens eq = new EightQueens();
		eq.placeQueens(0, eq.columns, eq.results);
		Iterator<Integer[]> iter = eq.results.iterator();
		while(iter.hasNext()){
			Integer[] intArr = iter.next();
			System.out.print("\n{");
			for(int i = 0; i < intArr.length; i++){
				System.out.print(intArr[i] + ", ");
			}
			System.out.print("}\n");
		}
	}
	
	/*
	 * Check if(row1,column1) is a valid spot for a queen by checking if there is a queen in the same column or diagonal. We don't need to check
	 * it for queens in the same row because the calling placeQueen only attempts to place one queen at a time. We know this row is empty.
	 */
	boolean checkValid(Integer[] columns, int row1, int column1){
		for(int row2 = 0; row2 < row1; row2++){
			int column2 = columns[row2];
			//Check if (row2, column2) invalidates (row1, column1) as a queen spot
			
			//Check if rows have a queen in the same column
			if(column1 == column2){
				return false;
			}
			
			//Check diagonals: if the distance between the columns equals the distance between the rows, then they're
			//in the same diagonal.
			int columnDistance = Math.abs(column2 - column1);
			
			//row1 > row2, so no need for abs
			int rowDistance = row1 - row2;
			if(columnDistance == rowDistance){
				return false;
			}
		}
		return true;
	}
}
