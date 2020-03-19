package ctci.solutions.aritra;

/*
 * Question: Design an algorithm to figure out if someone has won a game of tic-tac-toe
 * 
 * Solution: This scenario discusses how to determine the winner if we know the last move. If we know the very last move that was made(and we've been checking for a winner up until now),
 * then we only need to check the row,column and diagonal that overlaps with this position.
 * 
 * there is actually a way to clean up this code to remove some of the duplicated code. We'll see this approach in a later function.
 */

public class TicTacWin {
	Piece hasWon(Piece[][] board, int row, int column){
		if(board.length != board[0].length)
			return Piece.Empty;
		
		Piece piece = board[row][column];
		
		if(piece == Piece.Empty)
			return Piece.Empty;
		
		if(hasWonRow(board,row) || hasWonColumn(board,column)){
			return piece;
		}
		
		if(row == column && hasWonDiagonal(board,1)){
			return piece;
		}
		
		if(row == (board.length - column - 1) && hasWonDiagonal(board,-1)){
			return piece;
		}
		return Piece.Empty;
	}
	
	boolean hasWonRow(Piece[][] board, int row){
		for(int c = 1; c < board[row].length; c++){
			if(board[row][c] != board[row][0]){
				return false;
			}
		}
		return true;
	}
	
	boolean hasWonColumn(Piece[][] board, int column){
		for(int r = 1; r < board.length; r++){
			if(board[r][column] != board[0][column]){
				return false;
			}
		}
		return true;
	}
	
	boolean hasWonDiagonal(Piece[][] board, int direction){
		int row = 0;
		int column = direction == 1 ? 0 : board.length - 1;
		Piece first = board[0][column];
		for(int i = 0; i < board.length; i++){
			if(board[row][column] != first){
				return false;
			}
			row += 1;
			column += direction;
		}
		return true;
	}
}

enum Piece{
	Empty,
	Cross,
	Zero
}