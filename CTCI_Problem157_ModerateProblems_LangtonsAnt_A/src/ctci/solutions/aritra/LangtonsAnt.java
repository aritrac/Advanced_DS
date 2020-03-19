package ctci.solutions.aritra;

/*
 * Question: An ant is sitting on an infinite grid of white and black squares. It initially faces right. At each step, it does the following:
 * 1)At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward one unit.
 * 2)At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move forward one unit.
 * Write a program to simulate the first K moves that the ant makes and print the final board. Note that you are not provided with the data structure to represent
 * the grid. This is something you must design yourself. The only input to your method is K. You should print the final grid and return nothing. The
 * method signature might be something like void printKMoves(int K).
 * 
 * Solution: Technically, since we are only running the first k moves, we do have a max size for the grid. The ant cannot move more than K moves in either direction.
 * If we create a grid that has width 2K and height 2K and place the ant at the center, we know it will be big enough. The problem with this is that its not very extensible.
 * If you run K moves in a particular dimension, but the ant is probably going in circles a bit. You probably won't need all this space.
 * 
 * So now one thought is to use a resizable array, such as Java's ArrayList class. This allows us to grow an array as necessary. The problem is that the grid needs to grow
 * in two dimensions, but the ArrayList is only a single array. Additionally, we need to grow backward into negative values. The ArrayList class doesn't support this.
 * However, we take a similar approach by building our own resizable grid. Each time an ant hits an edge, we double the size of the grid in that dimension.
 * What about negative expansions? While conceptually we can talk about something being at negative positions, we cannot actually access array indices with negative values.
 * One way we can handle this is to create fake indices. Let us treat the ant as being at coordinates (-3, -10), but track some sort of offset or delta to translate
 * these coordinates into array indices.
 * 
 * This is actually unnecessary, though. The ant's location does not need to be publicly exposed or consistent. When the ant travels into negative coordinates, we can double
 * the size of the array and just move the ant and all cells into the positive coordinates. Essentially, we are relabelling all the indices.
 */

public class LangtonsAnt {
	private boolean[][] grid;
	private Ant ant = new Ant();
	
	public LangtonsAnt(){
		grid = new boolean[1][1];
	}
	
	//Copy old values into new array, with an offset/shift applied to the row and columns
	//matrix, copy the old values over, and adjust the ant's position so that it's in a positive
	//range.
	private void copyWithShift(boolean[][] oldGrid, boolean[][] newGrid, int shiftRow, int shiftColumn){
		for(int r = 0; r < oldGrid.length; r++){
			for(int c = 0; c < oldGrid[0].length; c++){
				newGrid[r + shiftRow][c + shiftColumn] = oldGrid[r][c];
			}
		}
	}
	
	//Ensure that the given position will fit on the array. If necesssary, double the size of the matrix, copy the old values over, and adjust the ant's position so that
	//it's in a positive range.
	private void ensureFit(Position position){
		int shiftRow = 0;
		int shiftColumn = 0;
		
		//Calculate new number of rows
		int numRows = grid.length;
		if(position.row < 0){
			shiftRow = numRows;
			numRows *= 2;
		}else if(position.row >= numRows){
			numRows *= 2;
		}
		
		//Calculate the number of columns
		int numColumns = grid[0].length;
		if(position.column < 0){
			shiftColumn = numColumns;
			numColumns *= 2;
		}else if(position.column >= numColumns){
			numColumns *= 2;
		}
		
		//Grow array, if necesary. Shift ant's position too.
		if(numRows != grid.length || numColumns != grid[0].length){
			boolean[][] newGrid = new boolean[numRows][numColumns];
			copyWithShift(grid, newGrid, shiftRow, shiftColumn);
			ant.adjustPosition(shiftRow, shiftColumn);
			grid = newGrid;
		}
	}
	
	//Flip color of cells
	private void flip(Position position){
		int row = position.row;
		int column = position.column;
		grid[row][column] = grid[row][column]? false : true;
	}
	
	//Move ant
	public void move(){
		ant.turn(grid[ant.position.row][ant.position.column]);
		flip(ant.position);
		ant.move();
		ensureFit(ant.position); //grow
	}
	
	//Print board.
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[0].length; c++){
				if(r == ant.position.row && c == ant.position.column){
					sb.append(ant.orientation);
				}else if(grid[r][c]){
					sb.append("X");
				}else{
					sb.append("_");
				}
			}
			sb.append("\n");
		}
		sb.append("Ant: " + ant.orientation + ". \n");
		return sb.toString();
	}
}

class Ant{
	public Position position = new Position(0,0);
	public Orientation orientation = Orientation.right;
	
	public void turn(boolean clockwise){
		orientation = orientation.getTurn(clockwise);
	}
	
	public void move(){
		if(orientation == Orientation.left){
			position.column--;
		}else if(orientation == Orientation.right){
			position.column++;
		}else if(orientation == Orientation.up){
			position.row--;
		}else if(orientation == Orientation.down){
			position.row++;
		}
	}
	
	public void adjustPosition(int shiftRow, int shiftColumn){
		position.row += shiftRow;
		position.column += shiftColumn;
	}
}

class Position{
	public int row;
	public int column;
	
	public Position(int row, int column){
		this.row = row;
		this.column = column;
	}
}

enum Orientation{
	left, up, right, down;
	
	public Orientation getTurn(boolean clockwise){
		if(this == left){
			return clockwise ? up : down;
		}else if(this == up){
			return clockwise? right : left;
		}else if(this == right){
			return clockwise? down : up;
		}else{ //down
			return clockwise? left : right;
		}
	}
	
	@Override
	public String toString(){
		if(this == left){
			return "\u2190";
		}else if(this == up){
			return "\u2191";
		}else if(this == right){
			return "\u2192";
		}else{ //down
			return "\u2193";
		}
	}
}
