package ctci.solutions.aritra;

/*
 * Question: Design an algorithm to figure out if someone has won a game of tic-tac-toe
 * 
 * Solution: This scenario discusses the solution when we are designing for a 3x3 board specifically.If we really want to implement a solution for a 3x3 board, the code
 * is relatively short and simple. The only complex part is trying to be clean and organized, without writing too much duplicated code. The code below
 * checks each row, column and diagonal to see if there is a winner.
 */

public class TicTacWin {
	
	Piece hasWon(Piece[][] board){
		for(int i = 0; i < board.length; i++){
			//Checks rows
			if(hasWinner(board[i][0], board[i][1], board[i][2])){
				return board[i][0];
			}
			
			//Checks columns
			if(hasWinner(board[0][i], board[1][i], board[2][i])){
				return board[0][i];
			}
		}
		
		//Check diagonal
		if(hasWinner(board[0][0], board[1][1], board[2][2])){
			return board[0][0];
		}
		
		if(hasWinner(board[0][2], board[1][1], board[2][0])){
			return board[0][2];
		}
		
		return Piece.Empty;
	}
	
	boolean hasWinner(Piece p1, Piece p2, Piece p3){
		if(p1 == Piece.Empty){
			return false;
		}
		return p1 == p2 && p2 == p3;
	}
}

enum Piece{
	Empty,
	Cross,
	Zero
}