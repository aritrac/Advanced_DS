package ctci.solutions.aritra;

/*
 * Question: Design an algorithm to figure out if someone has won a game of tic-tac-toe
 * 
 * Solution: This scenario discusses the design process for an NxN board. We will implement the NxN board using nested for loops
 */

public class TicTacWin {
	
	Piece hasWon(Piece[][] board){
		int size = board.length;
		if(board[0].length != size)
			return Piece.Empty;
		Piece first;
		
		//Check rows
		for(int i = 0; i < size; i++){
			first = board[i][0];
			if(first == Piece.Empty)
				continue;
			for(int j = 1; j < size; j++){
				if(board[i][j] != first){
					break;
				}else if(j == size - 1){//Last element
					return first;
				}
			}
		}
		
		//Check columns
		for(int i = 0; i < size; i++){
			first = board[0][i];
			if(first == Piece.Empty)
				continue;
			for(int j = 1; j < size; j++){
				if(board[j][i] != first){
					break;
				}else if(j == size - 1){//Last element
					return first;
				}
			}
		}
		
		//Check diagonals
		first = board[0][0];
		if(first != Piece.Empty){
			for(int i = 1; i < size; i++){
				if(board[i][i] != first){
					break;
				}else if(i == size - 1){ //Last element
					return first;
				}
			}
		}
		
		first = board[0][size - 1];
		if(first != Piece.Empty){
			for(int i = 1; i < size; i++){
				if(board[i][size - i - 1] != first){
					break;
				}else if(i == size - 1){ //Last element
					return first;
				}
			}
		}
		
		return Piece.Empty;
	}
}

enum Piece{
	Empty,
	Cross,
	Zero
}