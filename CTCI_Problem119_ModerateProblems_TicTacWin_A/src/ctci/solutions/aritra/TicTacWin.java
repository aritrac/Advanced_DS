package ctci.solutions.aritra;

/*
 * Question: Design an algorithm to figure out if someone has won a game of tic-tac-toe
 * 
 * Solution: This scenario considers that hasWon() method will be called multiple times(for instance, as a part of tic-tac-toe website)? If the latter is the case,
 * we may want to add pre-processing time to optimize the runtime of hasWon().
 * 
 * There are only 3 ^ 9 ways to fill up a 3*3 board with 3 tokens i.e. (blank ,X and O) which is about 20000. Therefore we can represent our tic-tac-toe board as an int
 * with each digit representing a piece (0 means empty, 1 means X and 2 means O). We set up a hash table or array in advance with all possible boards as keys and the value indicating 
 * who has won. Or function is then simply hasWon() as shown.
 * 
 * To convert a board (represented by a char array) to an int, we can use what is essentially a "base 3" representation. Each board is represented as
 * 30v0 + 31v1 + 32v2 + ... 38v8 where vi is a 0 if the space is empty, a 1 if it is a Cross spot and a 2 if it is a Zero spot
 * 
 * Now looking up the winner of a board is just a matter of looking it up in a hash table.
 * Ofcourse, if we need to convert a board into this format every time we want to check for a winner , we haven't saved ourselves any time compared with other solutions.
 * But, if we can store the board this way from the very beginning, then the lookup process will be very efficient.
 */

public class TicTacWin {
	int convertBoardToInt(Piece[][] board){
		int sum = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				//Each value in enum has an integer associated with it. We can just use that.
				int value = board[i][j].ordinal();
				sum = sum * 3 + value; //creating the 3 digit integer 0 * 10 + 1 + 1 * 10 + 2 + 12 * 10 + 3 = 123 something in base 3 instead of base 10
			}
		}
		return sum;
	}
}

enum Piece{
	Empty,
	Cross,
	Zero
}