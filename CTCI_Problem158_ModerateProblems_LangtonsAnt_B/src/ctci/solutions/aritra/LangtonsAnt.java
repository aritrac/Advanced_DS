package ctci.solutions.aritra;

import java.util.HashSet;

/*
 * Question: An ant is sitting on an infinite grid of white and black squares. It initially faces right. At each step, it does the following:
 * 1)At a white square, flip the color of the square, turn 90 degrees right (clockwise), and move forward one unit.
 * 2)At a black square, flip the color of the square, turn 90 degrees left (counter-clockwise), and move forward one unit.
 * Write a program to simulate the first K moves that the ant makes and print the final board. Note that you are not provided with the data structure to represent
 * the grid. This is something you must design yourself. The only input to your method is K. You should print the final grid and return nothing. The
 * method signature might be something like void printKMoves(int K).
 * 
 * Solution: Although, it may seem "obvious" that we would use a matrix to represent a grid,it's actually easier not to do that. All we actually need to do is a list
 * of the white squares (as well as the ant's location and orientation).
 * 
 * We can do this by using a HashSet of the white squares. If a position is in the hash set, then the square is white. Otherwise, it is black.
 * The one tricky bit is how to print the board. Where do we start printing? Where do we end?
 * 
 * Since we will need to print a grid, we can track what should be top-left and bottom right corner of the grid. Each time the ant moves, we compare the ant's position
 * to the most top-left position and most bottom-right position, updating them if necessary.
 */

public class LangtonsAnt {
	private HashSet<Position> whites = new HashSet<Position>();
	private Ant ant = new Ant();
	private Position topLeftCorner = new Position(0,0);
	private Position bottomRightCorner = new Position(0,0);
	
	public LangtonsAnt(){}
	
	//Move ant
	public void move(){
		ant.turn(isWhite(ant.position)); //Turn
		flip(ant.position); //Flip
		ant.move(); //move
		ensureFit(ant.position);
	}
	
	//Flip color of cells
	private void flip(Position position){
		if(whites.contains(position)){
			whites.remove(position);
		}else{
			whites.add(position.clone());
		}
	}
	
	//Grow grid by tracking the most top-left and bottom-right positions
	private void ensureFit(Position position){
		int row = position.row;
		int column = position.column;
		
		topLeftCorner.row = Math.min(topLeftCorner.row, row);
		topLeftCorner.column = Math.min(topLeftCorner.column, column);
		
		bottomRightCorner.row = Math.max(bottomRightCorner.row, row);
		bottomRightCorner.column = Math.max(bottomRightCorner.column, column);
	}
	
	//Check if cell is white
	public boolean isWhite(Position p){
		return whites.contains(p);
	}
	
	//Check if cell is white
	public boolean isWhite(int row, int column){
		return whites.contains(new Position(row,column));
	}
	
	//Print board
	public String toString(){
		StringBuilder sb = new StringBuilder();
		int rowMin = topLeftCorner.row;
		int rowMax = bottomRightCorner.row;
		int colMin = topLeftCorner.column;
		int colMax = bottomRightCorner.column;
		for(int r = rowMin; r <= rowMax; r++){
			for(int c = colMin; c <= colMax; c++){
				if(r == ant.position.row && c == ant.position.column){
					sb.append(ant.orientation);
				}else if(isWhite(r,c)){
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

class Position{
	public int row;
	public int column;
	
	public Position(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Position){
			Position p = (Position) o;
			return p.row == row && p.column == column;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		//There are many options for hash functions. This is one.
		return (row * 31) ^ column;
	}
	
	public Position clone(){
		return new Position(row, column);
	}
}
