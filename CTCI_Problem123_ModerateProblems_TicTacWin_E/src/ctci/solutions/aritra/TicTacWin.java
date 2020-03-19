package ctci.solutions.aritra;

import java.util.ArrayList;

/*
 * Question: Design an algorithm to figure out if someone has won a game of tic-tac-toe
 * 
 * Solution: This scenario discusses the design process for an NxN board. We will implement the NxN board using Increment and Decrement methods. We will just pass in the values
 * to another function that increments/decrements the rows and columns. The hasWon function now just needs the starting position and the amount to increment the row and column by.
 */

public class TicTacWin {
	public int row,column;
	private int rowIncrement, columnIncrement;
	
	public TicTacWin(int row, int column, int rowI, int colI){
		this.row = row;
		this.column = column;
		this.rowIncrement = rowI;
		this.columnIncrement = colI;
	}
	
	public void increment(){
		row += rowIncrement;
		column += columnIncrement;
	}
	
	public boolean inBounds(int size){
		return row >= 0 && column >= 0 && row < size && column < size;
	}
	
	Piece hasWon(Piece[][] board){
		if(board.length != board[0].length)
			return Piece.Empty;
		int size = board.length;
		
		//Create list of things to check
		ArrayList<TicTacWin> instructions = new ArrayList<TicTacWin>();
		for(int i = 0; i < board.length; i++){
			instructions.add(new TicTacWin(0,i,1,0));
			instructions.add(new TicTacWin(i, 0, 0, 1));
		}
		instructions.add(new TicTacWin(0,0,1,1));
		instructions.add(new TicTacWin(0, size - 1, 1, -1));
		
		//Check them
		for(TicTacWin instr : instructions){
			Piece winner = hasWon(board, instr);
			if(winner != Piece.Empty){
				return winner;
			}
		}
		return Piece.Empty;
	}
	
	Piece hasWon(Piece[][] board, TicTacWin instr){
		Piece first = board[instr.row][instr.column];
		while(instr.inBounds(board.length)){
			if(board[instr.row][instr.column] != first){
				return Piece.Empty;
			}
			instr.increment();
		}
		return first;
	}
}

enum Piece{
	Empty,
	Cross,
	Zero
}